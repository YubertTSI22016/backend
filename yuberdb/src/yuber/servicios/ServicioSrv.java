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

import org.bson.Document;
import org.bson.conversions.Bson;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import yuber.interceptors.TenantIntercept;
import yuber.interfaces.ServicioLocalApi;
import yuber.models.ReporteProveedores;
import yuber.models.Servicio;
import yuber.schema.MongoHandler;
import yuber.shares.DataTenant;
import yuber.shares.DataJornadaLaboral;
import yuber.shares.DataProveedor;
import yuber.shares.DataServicio;

/**
 * Session Bean implementation class ServicioSrv
 */
@Stateless
@Interceptors({ TenantIntercept.class })
public class ServicioSrv implements ServicioLocalApi {
	@Inject
	EntityManager em;

	public ServicioSrv() {

	}

	public List<DataServicio> obtenerServicios(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataServicio> servicios = new ArrayList();
		// obtengo todos los servicios de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Servicio.class);
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Servicio> listSrv = new ArrayList<Servicio>(new LinkedHashSet(criteria.list()));

		listSrv.stream().forEach((srv) -> {
			servicios.add(srv.getDatatype(true));
		});
		return servicios;
	}

	public void modificarServicio(DataServicio srv, DataTenant tenant) {
		Servicio realObj = new Servicio(srv, true);
		if (em.find(Servicio.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El servicio no existe");
		}
		// realObj.getUsuario().getId();
		if (realObj.getEstado().equals("Finalizado")) {
			try {
				guardarRegistroUsuarios(realObj, tenant);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
		em.merge(realObj);
	}

	private void guardarRegistroUsuarios(Servicio srv, DataTenant tenant)
			throws JsonParseException, JsonMappingException, IOException, ParseException {
		Document reporteNuevo;
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = formater.parse(formater.format(srv.getFin()));
		// realObj.getPrecio()

		MongoDatabase db = MongoHandler.getSchema(tenant.getName());
		Bson filter = Aggregates
				.match(Filters.and(Filters.eq("fecha", date.getTime()), Filters.eq("proveedor", srv.getUsuario().getId())));
		List<String> s = Arrays.asList("nombre", "apellido", "proveedor", "ganancia", "cantidad");

		Document reporteProveedor = db.getCollection("reporteUsuarios")
				.aggregate(Arrays.asList(filter, Aggregates.project(Projections.fields(Projections.include(s)))))
				.first();
		if (reporteProveedor != null && !reporteProveedor.isEmpty()) {
			final String json = reporteProveedor.toJson();
			ReporteProveedores rep = MongoHandler.buildObject(ReporteProveedores.class, json);
			reporteNuevo = new Document("$set", new Document("ganancia", rep.getGanancia() + srv.getPrecio())).append("$set", new Document("cantidad", rep.getCantidad() + 1));
			db.getCollection("reporteUsuarios").updateOne(Filters.eq("proveedor", srv.getUsuario().getId()), reporteNuevo);
			
		} else {
			ReporteProveedores rep = new ReporteProveedores();
			rep.setProveedor(srv.getUsuario().getId());
			rep.setNombre(srv.getUsuario().getNombre());
			rep.setApellido(srv.getUsuario().getApellido());
			rep.setGanancia(srv.getPrecio());
			rep.setCantidad(1);
			rep.setFecha(date);
			reporteNuevo = Document.parse(MongoHandler.getJSON(rep));
			db.getCollection("reporteUsuarios").insertOne(reporteNuevo);
		}

	}

	public DataServicio getServicio(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Servicio realObj = (Servicio) session.get(Servicio.class, id);
		if (realObj == null)
			return null;
		return realObj.getDatatype(true);
	}

	public DataServicio crearServicio(DataServicio srv, DataTenant tenant) {
		Servicio realObj = new Servicio(srv, true);
		// guardo el servicio en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	@Override
	public List<DataServicio> listarServiciosPorProveedor(String idUsuProv, DataTenant tenant) {
		List<DataServicio> servicios = new ArrayList();
		// obtengo todos los servicios de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Servicio.class);
		criteria.add(Restrictions.eq("proveedor.id", idUsuProv));
		List<Servicio> listSrv = new ArrayList<Servicio>(new LinkedHashSet(criteria.list()));

		listSrv.stream().forEach((srv) -> {
			servicios.add(srv.getDatatype(true));
		});
		return servicios;
	}

	@Override
	public List<DataServicio> obtenerServiciosPendientes(DataTenant tenant) {
		List<DataServicio> servicios = new ArrayList();
		// obtengo todos los servicios de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Servicio.class);
		criteria.add(Restrictions.eq("estado", "Solicitado"));
		List<Servicio> listSrv = new ArrayList<Servicio>(new LinkedHashSet(criteria.list()));

		listSrv.stream().forEach((srv) -> {
			servicios.add(srv.getDatatype(false));
		});
		return servicios;
	}
}