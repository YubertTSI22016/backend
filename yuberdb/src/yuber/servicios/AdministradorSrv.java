package yuber.servicios;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import yuber.interceptors.TenantIntercept;
import yuber.interfaces.AdministradorLocalApi;
import yuber.models.Administrador;
import yuber.shares.DataTenant;
import yuber.shares.DataAdministrador;

@Stateless
@Interceptors ({TenantIntercept.class})
public class AdministradorSrv implements AdministradorLocalApi {

	@Inject
	EntityManager em;
	
	public AdministradorSrv(){}
	
	public List<DataAdministrador> obtenerAdmins(Integer pagina, Integer elementosPagina, DataTenant tenant){
		List<DataAdministrador> admins = new ArrayList();
		//obtengo todos los admins de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Administrador.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.setFirstResult((pagina - 1)* elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Administrador> listAdmin = new ArrayList<Administrador>(new LinkedHashSet(criteria.list()));
		
		listAdmin.stream().forEach((admin)->{
			admins.add(admin.getDatatype());
		});
		return admins;
	}
	
	public DataAdministrador loginAdmin (String mailAdmin, String clave, DataTenant tenant){
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Administrador.class);
		criteria.add(Restrictions.eq("email.email", mailAdmin));
		List<Administrador> listAdmin = criteria.list();
		if (listAdmin.size() == 1) {
			DataAdministrador admin = listAdmin.get(0).getDatatype();
			if (admin.getClave().equals(clave))
				return admin;
		}
		return null;
	}
	
	public void modificarAdmin(DataAdministrador admin, DataTenant tenant) {
		Administrador realObj = new Administrador(admin);
		if (em.find(Administrador.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El administrador no existe");
		}
		em.merge(realObj);
	}
	
	public DataAdministrador getAdmin(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Administrador realObj = (Administrador) session.get(Administrador.class, id);
		return realObj.getDatatype();
	}

	public DataAdministrador crearAdmin(DataAdministrador admin, DataTenant tenant) {
		Administrador realObj = new Administrador(admin);
		realObj.setEliminado(false);
		// guardo el administrador en bd
		em.persist(realObj);
		return realObj.getDatatype();
	}

	public void darBajaAdmin(String idAdmin, DataTenant tenant) {
		DataAdministrador admin = getAdmin(idAdmin, tenant);
		admin.setEliminado(true);
		this.modificarAdmin(admin, tenant);
	}

	public DataAdministrador buscarAdminPorMail(String mailAdmin, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Administrador.class);
		criteria.add(Restrictions.eq("email.email", mailAdmin));
		List<Administrador> listAdmin = criteria.list();
		if (listAdmin.size() > 0) {
			DataAdministrador admin = listAdmin.get(0).getDatatype();
			return admin;
		}
		return null;
	}
	
}
