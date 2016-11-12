package yuber.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import yuber.interceptors.TenantIntercept;
import yuber.interfaces.AdministradorLocalApi;
import yuber.models.Administrador;
import yuber.models.VerticalReport;
import yuber.shares.DataAdministrador;
import yuber.shares.DataEmail;
import yuber.shares.DataTenant;
import yuber.shares.DataVerticalReport;

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
		if(listAdmin.size() == 0 && mailAdmin.equals("admin") && obtenerAdmins(1,1,tenant).size() == 0){
			DataAdministrador defaultAdmin = new DataAdministrador();
			DataEmail defaultAdminEmail = new DataEmail();
			defaultAdminEmail.setEmail("admin");
			defaultAdmin.setActivo(true);
			defaultAdmin.setClave("admin");
			defaultAdmin.setEliminado(false);
			defaultAdmin.setNombre("Admin");
			defaultAdmin.setApellido("Admin");
			defaultAdmin.setEmail(defaultAdminEmail);
			return crearAdmin(defaultAdmin,tenant);
		}else if (listAdmin.size() == 1) {
			DataAdministrador admin = listAdmin.get(0).getDatatype();
			if (admin.genClave().equals(clave))
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
	public List<DataVerticalReport> getReport(Date start, DataTenant tenant){
		Session session = (Session) em.getDelegate();
		Query query = session.getNamedQuery("ReporteVertical")
				.setDate("start", start);
		List<VerticalReport> l = query.list();
		List<DataVerticalReport> result =new ArrayList<DataVerticalReport>();
		l.forEach(vrport ->{
			result.add(vrport.toData());
		});
		return result;
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
