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
import yuber.interfaces.UsuarioLocalApi;
import yuber.models.Proveedor;
import yuber.shares.*;

/**
 * Session Bean implementation class UsuarioSrv
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
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Proveedor> listProv = new ArrayList<Proveedor>(new LinkedHashSet(criteria.list()));

		listProv.stream().forEach((prv) -> {
			proveedores.add(prv.getDatatype(true));
		});
		return proveedores;
	}

	public DataProveedor loginProveedor(String mailProveedor, String clave, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("email.email", mailProveedor));
		List<Proveedor> listPrv = criteria.list();
		if (listPrv.size() == 1) {
			DataProveedor proveedor = listPrv.get(0).getDatatype(true);
			if (proveedor.getClave().equals(clave))
				return proveedor;
		}
		return null;
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
		realObj.setEliminado(false);
		// guardo el proveedor en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	public void darBajaUsuario(String idProveedor, DataTenant tenant) {
		DataProveedor prv = getProveedor(idProveedor, tenant);
		prv.setEliminado(true);
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

}