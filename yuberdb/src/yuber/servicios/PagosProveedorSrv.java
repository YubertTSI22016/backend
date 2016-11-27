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
import yuber.interfaces.PagosProveedorLocalApi;
import yuber.models.PagosProveedor;
import yuber.shares.DataTenant;
import yuber.shares.DataJornadaLaboral;
import yuber.shares.DataPagosProveedor;

/**
 * Session Bean implementation class PagosProveedorSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
public class PagosProveedorSrv implements PagosProveedorLocalApi {
	@Inject
	EntityManager em;

	public PagosProveedorSrv() {

	}

	public List<DataPagosProveedor> obtenerPagosProveedor(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataPagosProveedor> pagosProv = new ArrayList();
		// obtengo todos los pagos a proveedor de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(PagosProveedor.class);
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<PagosProveedor> listSrv = new ArrayList<PagosProveedor>(new LinkedHashSet(criteria.list()));

		listSrv.stream().forEach((srv) -> {
			pagosProv.add(srv.getDatatype(true));
		});
		return pagosProv;
	}

	public void modificarPagosProveedor(DataPagosProveedor srv, DataTenant tenant) {
		PagosProveedor realObj = new PagosProveedor(srv, true);
		if (em.find(PagosProveedor.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El pago a proveedor no existe");
		}
		em.merge(realObj);
	}
	
	
	public void cambiarEstadoAPago(String idProveedor, DataTenant tenant) {
		List<DataPagosProveedor> pagosProv = new ArrayList();
		// obtengo todos los pagos a proveedor de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(PagosProveedor.class);
		criteria.add(Restrictions.eq("proveedor.id", idProveedor));
		criteria.add(Restrictions.eq("pago", false));
		List<PagosProveedor> listSrv = new ArrayList<PagosProveedor>(new LinkedHashSet(criteria.list()));
		for ( int i=0; i<listSrv.size(); i++ ) {
			PagosProveedor current = listSrv.get(i);
			current.setPago(true);
		    em.merge(current);
		    if ( i % 20 == 0 ) { //20, same as the JDBC batch size
		        //flush a batch of inserts and release memory:
		        em.flush();
		        em.clear();
		    }
		}
	}

	public DataPagosProveedor getPagosProveedor(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		PagosProveedor realObj = (PagosProveedor) session.get(PagosProveedor.class, id);
		if(realObj == null)
			return null;
		return realObj.getDatatype(true);
	}

	public DataPagosProveedor crearPagosProveedor(DataPagosProveedor srv, DataTenant tenant) {
		PagosProveedor realObj = new PagosProveedor(srv, true);
		// guardo el pagos a proveedor en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	@Override
	public List<DataPagosProveedor> listarPagosPendientes(String idProveedor, Integer pagina, Integer elementosPagina,
			DataTenant tenant) {
		List<DataPagosProveedor> pagosProv = new ArrayList();
		// obtengo todos los pagos a proveedor de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(PagosProveedor.class);
		criteria.add(Restrictions.eq("proveedor.id", idProveedor));
		criteria.add(Restrictions.eq("pago", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<PagosProveedor> listSrv = new ArrayList<PagosProveedor>(new LinkedHashSet(criteria.list()));

		listSrv.stream().forEach((srv) -> {
			pagosProv.add(srv.getDatatype(true));
		});
		return pagosProv;
	}

}