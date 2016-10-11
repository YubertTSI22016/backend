package yuber.api.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import yuber.interfaces.IAdministrador;
import yuber.shares.DataTenant;
import yuber.shares.DataAdministrador;

@Stateless
@Remote
public class AdministradorRepo {

	@EJB(lookup = "java:app/yuberb/AdministradorCtrl!yuber.interfaces.IAdministrador")
	IAdministrador ctrAdministrador;

	public DataAdministrador altaAdmin(DataAdministrador admin, DataTenant tenant) {
		return ctrAdministrador.altaAdmin(admin, tenant);
	}

	public List<DataAdministrador> obtenerAdmins(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrAdministrador.obtenerAdmins(pagina, elementosPagina, tenant);
	}

	public DataAdministrador loginAdmin(String admin, String clave, DataTenant tenant) {
		return ctrAdministrador.loginAdmin(admin, clave, tenant);
	}
	
	public DataAdministrador getAdmin(String id, DataTenant tenant) {
		return ctrAdministrador.getAdmin(id, tenant);
	}
	
	public void modificarAdmin(DataAdministrador admin, DataTenant tenant){
		ctrAdministrador.modificarAdmin(admin, tenant);
	}
	
}
