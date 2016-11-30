package yuber.servicios;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

import yuber.interceptors.TenantIntercept;
import yuber.interfaces.ProveedorLocalApi;
import yuber.models.Proveedor;
import yuber.models.ReporteProveedores;
import yuber.schema.MongoHandler;
import yuber.shares.DataJornadaLaboral;
import yuber.shares.DataProveedor;
import yuber.shares.DataReporteProveedor;
import yuber.shares.DataTenant;

/**
 * Session Bean implementation class ProveedorSrv
 */
@Stateless
@Interceptors({ TenantIntercept.class })
public class ProveedorSrv implements ProveedorLocalApi {
	@Inject
	EntityManager em;
	private static final Log log = LogFactory.getLog(ProveedorSrv.class);

	public ProveedorSrv() {

	}

	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataProveedor> proveedores = new ArrayList();
		// obtengo todos los usuarios de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Proveedor.class);
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Proveedor> listProv = new ArrayList<Proveedor>(new LinkedHashSet(criteria.list()));

		listProv.stream().forEach((prv) -> {
			proveedores.add(prv.getDatatype(true));
		});
		return proveedores;
	}

	public List<DataReporteProveedor> rankingProveedoresPorGanancia(Date start, Date end, int pagina, int elementosPagina,DataTenant tenant) throws Exception
	{
		ArrayList<DataReporteProveedor> result = new ArrayList<DataReporteProveedor>();
		MongoDatabase db = MongoHandler.getSchema(tenant.getName());
		List<String> s = Arrays.asList("nombre", "apellido", "proveedor", "ganancia", "cantidad");
		MongoCursor<Document> it = db.getCollection("reporteJornadaProveedor")
				.aggregate(Arrays.asList(
						Aggregates.match(
								Filters.and(Filters.gte("fecha", start.getTime()),
								Filters.lte("fecha", end.getTime()))
						),
						Aggregates.project(Projections.fields(Projections.include(s))),
						Aggregates.group("$proveedor",  
									Accumulators.sum("cantidad", "$cantidad"),
									Accumulators.sum("ganancia", "$ganancia"),
									Accumulators.first("nombre", "$nombre"),
									Accumulators.first("apellido", "$apellido")
						),
						Aggregates.sort(Sorts.orderBy(Sorts.descending("ganancia"))),Aggregates.skip(pagina * elementosPagina + 1), Aggregates.limit(elementosPagina),
						Aggregates.project(Projections.fields(Projections.include(s),Projections.computed("proveedor", "$_id")))
						)).iterator();
		while (it.hasNext()) {
			Document d = it.next();
			result.add(MongoHandler.buildObject(ReporteProveedores.class, d.toJson()).toData());
		}
		return result;
	}

	private void guardarRegistroJornada(DataProveedor prv, DataTenant tenant)
			throws JsonParseException, JsonMappingException, IOException, ParseException {
		Document reporteNuevo;
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = formater.parse(formater.format(new Date()));
		if (prv.getJornadaActual() == null && prv.getJornadas().size() > 0) {

			DataJornadaLaboral jl = prv.getJornadas().get(prv.getJornadas().size() - 1);
			MongoDatabase db = MongoHandler.getSchema(tenant.getName());
			Bson filter = Aggregates
					.match(Filters.and(Filters.eq("fecha", date.getTime()), Filters.eq("proveedor", prv.getId())));
			List<String> s = Arrays.asList("nombre", "apellido", "proveedor", "ganancia", "cantidad");

			Document reporteProveedor = db.getCollection("reporteJornadaProveedor")
					.aggregate(Arrays.asList(filter, Aggregates.project(Projections.fields(Projections.include(s)))))
					.first();
			if (reporteProveedor != null && !reporteProveedor.isEmpty()) {
				final String json = reporteProveedor.toJson();
				ReporteProveedores rep = MongoHandler.buildObject(ReporteProveedores.class, json);
				int cant = jl.getServicios() != null ? jl.getServicios().size() : 0; 
				reporteNuevo = new Document("$set", new Document("ganancia", rep.getGanancia() + jl.getSaldo())).append("$set", new Document("cantidad", rep.getCantidad() + cant));
				db.getCollection("reporteJornadaProveedor").updateOne(Filters.eq("proveedor", prv.getId()),
						reporteNuevo);

			} else {
				ReporteProveedores rep = new ReporteProveedores();
				rep.setProveedor(prv.getId());
				rep.setNombre(prv.getNombre());
				rep.setApellido(prv.getUsuario().getApellido());
				rep.setGanancia(jl.getSaldo());
				int cant = jl.getServicios() != null ? jl.getServicios().size() : 0; 
				rep.setCantidad( cant);
				rep.setFecha(date);
				reporteNuevo = Document.parse(MongoHandler.getJSON(rep));
				db.getCollection("reporteJornadaProveedor").insertOne(reporteNuevo);
			}

		}
	}

	public DataProveedor modificarProveedor(DataProveedor prv, DataTenant tenant) {
		Proveedor realObj = new Proveedor(prv, true);
		if (em.find(Proveedor.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El proveedor no existe");
		}
		try {
			guardarRegistroJornada(realObj.getDatatype(true), tenant);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.merge(realObj);
		return realObj.getDatatype(true);
	}

	public DataProveedor getProveedor(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Proveedor realObj = (Proveedor) session.get(Proveedor.class, id);
		if (realObj == null)
			return null;
		return realObj.getDatatype(true);
	}

	public DataProveedor crearProveedor(DataProveedor prv, DataTenant tenant) {
		Proveedor realObj = new Proveedor(prv, true);
		realObj.setActivo(true);
		// guardo el proveedor en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	public void darBajaProveedor(String idProveedor, DataTenant tenant) {
		DataProveedor prv = getProveedor(idProveedor, tenant);
		prv.setActivo(false);
		this.modificarProveedor(prv, tenant);
	}

	public DataProveedor buscarProveedorPorMail(String mailProveedor, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("email.email", mailProveedor));
		List<Proveedor> listPrv = criteria.list();
		if (listPrv.size() > 0) {
			DataProveedor proveedor = listPrv.get(0).getDatatype(false);
			return proveedor;
		}
		return null;
	}

	@Override
	public List<DataProveedor> reporteRatingProveedores(Integer pagina, Integer elementosPagina, Integer rating,
			DataTenant tenant) {
		List<DataProveedor> result = new ArrayList<DataProveedor>();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Proveedor.class);
		criteria.add(Restrictions.le("rating", Float.valueOf(rating)));
		criteria.addOrder(Order.desc("rating"));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Proveedor> listProv = new ArrayList<Proveedor>(new LinkedHashSet(criteria.list()));

		listProv.stream().forEach((prv) -> {
			result.add(prv.getDatatype(true));
		});
		return result;
	}

}