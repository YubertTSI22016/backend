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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import yuber.interceptors.TenantIntercept;
import yuber.interfaces.ProveedorLocalApi;
import yuber.models.Proveedor;
import yuber.shares.DataTenant;
import yuber.shares.DataProveedor;


/**
 * Session Bean implementation class ProveedorSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
public class ProveedorSrv implements ProveedorLocalApi {
	@Inject
	EntityManager em;

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

	public void modificarProveedor(DataProveedor prv, DataTenant tenant) {
		Proveedor realObj = new Proveedor(prv);
		if (em.find(Proveedor.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El proveedor no existe");
		}
		em.merge(realObj);
	}

	public DataProveedor getProveedor(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Proveedor realObj = (Proveedor) session.get(Proveedor.class, id);
		return realObj.getDatatype(true);
	}

	public DataProveedor crearProveedor(DataProveedor prv, DataTenant tenant) {
		Proveedor realObj = new Proveedor(prv);
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
	public List<DataProveedor> reporteRatingProveedores(Integer pagina, Integer elementosPagina, Integer rating, DataTenant tenant) {
		List<DataProveedor> result = new ArrayList<DataProveedor>();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Proveedor.class);
		criteria.add(Restrictions.le("rating", Float.valueOf(rating)));
		criteria.addOrder(Order.asc("rating"));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Proveedor> listProv = new ArrayList<Proveedor>(new LinkedHashSet(criteria.list()));

		listProv.stream().forEach((prv) -> {
			result.add(prv.getDatatype(true));
		});
		return result;
	}

}