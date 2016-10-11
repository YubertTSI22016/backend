package yuber.interfaces;

import java.util.List;

import javax.ejb.Local;
import yuber.shares.DataAdministrador;
import yuber.shares.DataTenant;


@Local
public interface AdministradorLocalApi {
	public List<DataAdministrador> obtenerAdmins (Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataAdministrador loginAdmin(String mailAdmin, String clave, DataTenant tenant);
	public void modificarAdmin (DataAdministrador admin, DataTenant tenant);
	public DataAdministrador getAdmin (String admin, DataTenant tenant);
	public DataAdministrador crearAdmin (DataAdministrador admin, DataTenant tenant);
	public void darBajaAdmin (String idAdmin, DataTenant tenant);
	public DataAdministrador buscarAdminPorMail (String mailUsuario, DataTenant tenant );
	
}
