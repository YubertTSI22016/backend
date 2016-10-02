package yuber.servicios;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import yuber.interceptors.TenantIntercept;
import yuber.interfaces.ServicioLocalApi;
import yuber.models.Servicio;
import yuber.shares.DataTenant;
import yuber.shares.DataServicio;

/**
 * Session Bean implementation class ServicioSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
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
			servicios.add(srv.getDatatype());
		});
		return servicios;
	}

	public void modificarServicio(DataServicio srv, DataTenant tenant) {
		Servicio realObj = new Servicio(srv);
		if (em.find(Servicio.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El servicio no existe");
		}
		em.merge(realObj);
	}

	public DataServicio getServicio(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Servicio realObj = (Servicio) session.get(Servicio.class, id);
		return realObj.getDatatype();
	}

	public DataServicio crearServicio(DataServicio srv, DataTenant tenant) {
		Servicio realObj = new Servicio(srv);
		// guardo el servicio en bd
		em.persist(realObj);
		return realObj.getDatatype();
	}
}