package yuber.interfaces;

import java.util.List;

import yuber.shares.DataAdministrador;
import yuber.shares.DataTenant;

public interface IAdministrador {
	
	public DataAdministrador altaAdmin(DataAdministrador admin, DataTenant tenant);
	
	public List<DataAdministrador> obtenerAdmins(Integer pagina, Integer elementosPagina, DataTenant tenant);
	
	public DataAdministrador loginAdmin(String admin, String clave, DataTenant tenant);
	
	public DataAdministrador getAdmin(String id, DataTenant tenant);
	
	public void modificarAdmin(DataAdministrador admin, DataTenant tenant);

}
