package yuber.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import yuber.shares.DataReporteProveedor;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

@Local
public interface UsuarioLocalApi {
	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataUsuario loginUsuario(String mailUsuario, String clave, DataTenant tenant);
	public DataUsuario modificarUsuario(DataUsuario usu, DataTenant tenant);
	public DataUsuario getUsuario(String usu, DataTenant tenant);
	public DataUsuario crearUsuario(DataUsuario usu, DataTenant tenant);
	public void darBajaUsuario(String idUsuario, DataTenant tenant);
	public DataUsuario buscarUsuarioPorMail(String mailUsuario, DataTenant tenant);
	public DataUsuario loginFacebook(String mailUsuario, String uid, DataTenant tenant);
	public List<DataUsuario> rankingUsuariosActivos(Date from, Integer pagina, Integer elementosPagina, DataTenant tenant);
	public List<DataReporteProveedor> rankingUsuariosPorGanancia(Date start, Date end, int pagina, int elementosPagina,DataTenant tenant) throws Exception;
	public List<DataReporteProveedor> rankingUsuariosPorConsumo(Date start, Date end, int pagina, int elementosPagina,DataTenant tenant) throws Exception;
}
