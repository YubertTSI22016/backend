package yuber.servicios;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.util.JSON;

import yuber.interceptors.TenantIntercept;
import yuber.interfaces.ProveedorLocalApi;
import yuber.models.Proveedor;
import yuber.models.ReporteProveedores;
import yuber.schema.MongoHandler;
import yuber.shares.DataJornadaLaboral;
import yuber.shares.DataProveedor;
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

	private String getJSON(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj).replace("{", " { ").replace("}", " } ").replace(":", " : ");
		log.info(json + "===========");
		return json;
	}

	private void guardarRegistroJornada(DataProveedor prv, DataTenant tenant)
			throws JsonParseException, JsonMappingException, IOException {
		Document reporteNuevo;
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String date = formater.format(new Date());
		if (prv.getJornadaActual() == null && prv.getJornadas().size() > 0) {

			DataJornadaLaboral jl = prv.getJornadas().get(prv.getJornadas().size() - 1);
			Document filter = new Document("fecha", date).append("idProveedor", prv.getId());
			MongoDatabase db = MongoHandler.getSchema(tenant.getName());

			ObjectMapper mapper = new ObjectMapper();
			Document reporteProveedor = db.getCollection("reporteJornadaProveedor").find(filter).first();

			if (reporteProveedor != null && !reporteProveedor.isEmpty()) {
				final String json = reporteProveedor.toJson();

				ReporteProveedores rep = mapper.readValue(json, ReporteProveedores.class);
				rep.setGanancia(rep.getGanancia() + jl.getSaldo());
				reporteNuevo = new Document("$set", Document.parse(getJSON(rep)));
				db.getCollection("reporteJornadaProveedor").updateOne(filter, reporteNuevo);

			} else {

				ReporteProveedores rep = new ReporteProveedores();
				rep.setIdProveedor(prv.getId());
				rep.setNombre(prv.getNombre());
				rep.setApellido(prv.getUsuario().getApellido());
				rep.setGanancia(jl.getSaldo());
				rep.setFecha(date + "T00:00:00.000Z");
				reporteNuevo = Document.parse(getJSON(rep));

				db.getCollection("reporteJornadaProveedor").insertOne(reporteNuevo);
			}

			log.info(db.getCollection("reporteJornadaProveedor").count());
		}

	}

	public DataProveedor modificarProveedor(DataProveedor prv, DataTenant tenant) {
		Proveedor realObj = new Proveedor(prv, true);
		if (em.find(Proveedor.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El proveedor no existe");
		}
		try {
			guardarRegistroJornada(prv, tenant);
		} catch (IOException e) {
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