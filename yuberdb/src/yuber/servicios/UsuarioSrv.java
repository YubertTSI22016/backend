package yuber.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.bson.Document;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

import yuber.interceptors.TenantIntercept;
import yuber.interfaces.UsuarioLocalApi;
import yuber.models.ReporteProveedores;
import yuber.models.Usuario;
import yuber.schema.MongoHandler;
import yuber.shares.DataReporteProveedor;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

/**
 * Session Bean implementation class UsuarioSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
public class UsuarioSrv implements UsuarioLocalApi {
	@Inject
	EntityManager em;

	public UsuarioSrv() {

	}

	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataUsuario> usuarios = new ArrayList();
		// obtengo todos los usuarios de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Usuario> listUsu = new ArrayList<Usuario>(new LinkedHashSet(criteria.list()));

		listUsu.stream().forEach((usu) -> {
			usuarios.add(usu.getDatatype(true));
		});
		return usuarios;
	}
	
	public List<DataReporteProveedor> rankingUsuariosPorConsumo(Date start, Date end, int pagina, int elementosPagina,DataTenant tenant) throws Exception{
		ArrayList<DataReporteProveedor> result = new ArrayList<DataReporteProveedor>();
		MongoDatabase db = MongoHandler.getSchema(tenant.getName());
		List<String> s = Arrays.asList("nombre", "apellido", "proveedor", "ganancia", "cantidad");
		MongoCursor<Document> it = db.getCollection("reporteUsuarios")
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
						Aggregates.sort(Sorts.orderBy(Sorts.descending("cantidad"))),Aggregates.skip(pagina * elementosPagina + 1), Aggregates.limit(elementosPagina),
						Aggregates.project(Projections.fields(Projections.include(s),Projections.computed("proveedor", "$_id")))
						)).iterator();
		while (it.hasNext()) {
			Document d = it.next();
			result.add(MongoHandler.buildObject(ReporteProveedores.class, d.toJson()).toData());
		}
		return result;
		
	}
	public List<DataReporteProveedor> rankingUsuariosPorGanancia(Date start, Date end, int pagina, int elementosPagina,DataTenant tenant) throws Exception
	{
		ArrayList<DataReporteProveedor> result = new ArrayList<DataReporteProveedor>();
		MongoDatabase db = MongoHandler.getSchema(tenant.getName());
		List<String> s = Arrays.asList("nombre", "apellido", "proveedor", "ganancia");
		MongoCursor<Document> it = db.getCollection("reporteUsuarios")
				.aggregate(Arrays.asList(
						Aggregates.match(
								Filters.and(Filters.gte("fecha", start.getTime()),
								Filters.lte("fecha", end.getTime()))
						),
						Aggregates.project(Projections.fields(Projections.include(s))),
						Aggregates.group("$proveedor", 
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
	public List<DataUsuario> rankingUsuariosActivos(Date from, Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataUsuario> usuarios = new ArrayList();
		// obtengo todos los usuarios de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class, "usuario");
		criteria.createCriteria("servicios").add(Restrictions.ge("fecha",from));
 		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Usuario> listUsu = new ArrayList<Usuario>(new LinkedHashSet(criteria.list()));

		listUsu.stream().forEach((usu) -> {
			usuarios.add(usu.getDatatype(true));
		});
		return usuarios;
	}
	public DataUsuario loginUsuario(String mailUsuario, String clave, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email.email", mailUsuario));
		List<Usuario> listUsu = criteria.list();
		if (listUsu.size() == 1) {
			DataUsuario usuario = listUsu.get(0).getDatatype(true);
			if(usuario.getEliminado())
				return null;
			if (usuario.genClave().equals(clave))
				return usuario;
		}
		return null;
	}
	
	public DataUsuario loginFacebook(String mailUsuario, String uid, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email.email", mailUsuario));
		List<Usuario> listUsu = criteria.list();
		if (listUsu.size() == 1) {
			DataUsuario usuario = listUsu.get(0).getDatatype(true);
			if(usuario.getEliminado())
				return null;
			if (usuario.getRedSocialUsada().equals("Facebook") && usuario.getIdRedSocial().equals(uid))
				return usuario;
		}
		return new DataUsuario();
	}

	public DataUsuario modificarUsuario(DataUsuario usu, DataTenant tenant) {
		Usuario realObj = new Usuario(usu, true);
		if (em.find(Usuario.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El usuario no existe");
		}
		em.merge(realObj);
		return realObj.getDatatype(true);
	}

	public DataUsuario getUsuario(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Usuario realObj = (Usuario) session.get(Usuario.class, id);
		if(realObj == null)
			return null;
		return realObj.getDatatype(true);
	}

	public DataUsuario crearUsuario(DataUsuario usu, DataTenant tenant) {
		Usuario realObj = new Usuario(usu, true);
		realObj.setEliminado(false);
		// guardo el usuario en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	public void darBajaUsuario(String idUsuario, DataTenant tenant) {
		DataUsuario usu = getUsuario(idUsuario, tenant);
		usu.setEliminado(true);
		this.modificarUsuario(usu, tenant);
	}

	public DataUsuario buscarUsuarioPorMail(String mailUsuario, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email.email", mailUsuario));
		List<Usuario> listUsu = criteria.list();
		if (listUsu.size() > 0) {
			DataUsuario usuario = listUsu.get(0).getDatatype(false);
			return usuario;
		}
		return null;
	}
	
}