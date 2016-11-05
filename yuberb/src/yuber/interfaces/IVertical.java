package yuber.interfaces;

import java.util.List;

import yuber.shares.DataAdministrador;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataPagosProveedor;
import yuber.shares.DataProveedor;
import yuber.shares.DataServicio;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

public interface IVertical {
	
	//USUARIO
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant);

	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant);

	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant);
	 
	public DataUsuario getUsuario(String id, DataTenant tenant);
	
	public DataUsuario modificarUsuario(DataUsuario usuario, DataTenant tenant);
	
	//PROVEEDOR
	public DataProveedor altaProveedor (DataProveedor proveedor, DataTenant tenant);
	
	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant);
	
	public DataProveedor getProveedor (String id, DataTenant tenant);
	
	public void modificarProveedor (DataProveedor proveedor, DataTenant tenant);
	
	//ADMINISTRADOR
	public DataAdministrador altaAdmin(DataAdministrador admin, DataTenant tenant);
	
	public List<DataAdministrador> obtenerAdmins(Integer pagina, Integer elementosPagina, DataTenant tenant);
	
	public DataAdministrador loginAdmin(String usuario, String clave, DataTenant tenant);
	
	public DataAdministrador getAdmin(String id, DataTenant tenant);
	
	public void modificarAdmin(DataAdministrador admin, DataTenant tenant);
	
	//CONFIGURACION
	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant);
	
	public void modificarConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant);
	
	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant);
	
	public DataServicio pedirServicio(String idUsuario, String ubicacion, String destino, String descripcion, DataTenant tenant);
    
	public DataServicio ofrecerServicio(String idServicio, String idProveedor, DataTenant tenant);

	public List<DataProveedor> reporteRatingProveedores(Integer pagina, Integer elementosPagina, Integer rating, DataTenant tenant);

	public DataUsuario loginAltaUsuarioFacebook(String email, String nombre, String uid, DataTenant tenant);

	public void calificarServicio(String idServicio, Float calificacion, String comentario, DataTenant tenant);

	public DataServicio finalizarServicio(String idServicio, Float calificacionUsuario, DataTenant tenant);
	
	public DataServicio finalizarTransporte(String idServicio, Float distancia, Float calificacionUsuario, DataTenant tenant);

	public void guardarTokenUsuario(String idUsuario, String token, Integer ultimosDigitosTarjeta, DataTenant tenant);
	
	public void eliminarTokenUsuario(String idUsuario, DataTenant tenant);
	
	public void guardarTokenProveedor(String idProveedor, String token, Integer ultimosDigitosTarjeta, DataTenant tenant);
	
	public void eliminarTokenProveedor(String idProveedor, DataTenant tenant);
	
	public void cargarTarjeta(String idUsuario, Float cargo, DataTenant tenant);
	
	public void pagoAProveedor(String idProveedor, DataTenant tenant);

	public DataServicio cancelarServicio(String idServicio, DataTenant tenant);

	public DataProveedor iniciarJornadaLaboral(String idProveedor, DataTenant tenant);
	
	public DataProveedor finalizarJornadaLaboral(String idProveedor, DataTenant tenant);

	public DataServicio obtenerServicio(String idServicio, DataTenant tenant);
	
	public List<DataServicio> listarServicios(String idUsuProv, DataTenant tenant);

	public DataServicio iniciarServicio(String idServicio, DataTenant tenant);
	
	public List<DataPagosProveedor> listarPagosPendientes(String idProveedor, Integer pagina, Integer elementosPagina, DataTenant tenant);
	
	public void ingresarPuntoRecorrido(String idServicio, String punto, DataTenant tenant);
	
	public List<String> obtenerPuntosServicio(String idServicio, DataTenant tenant);
}
