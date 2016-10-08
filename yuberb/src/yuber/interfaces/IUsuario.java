package yuber.interfaces;

import java.util.List;

import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;
public interface IUsuario {
 
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant);

	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant);

	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant);
	 
	public DataUsuario getUsuario(String id, DataTenant tenant);
}
