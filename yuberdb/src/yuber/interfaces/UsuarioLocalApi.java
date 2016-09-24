package yuber.interfaces;

import java.util.List;

import javax.ejb.Local;

import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

@Local
public interface UsuarioLocalApi {
	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataUsuario loginUsuario(String mailUsuario, String clave, DataTenant tenant);
	public void modificarUsuario(DataUsuario usu, DataTenant tenant);
	public DataUsuario getUsuario(String usu, DataTenant tenant);
	public DataUsuario crearUsuario(DataUsuario usu, DataTenant tenant);
	public void darBajaUsuario(String idUsuario, DataTenant tenant);
	public DataUsuario buscarUsuarioPorMail(String mailUsuario, DataTenant tenant);
}
