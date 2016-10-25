package yuber.api.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import yuber.interfaces.IVertical;
import yuber.shares.DataAdministrador;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataProveedor;
import yuber.shares.DataServicio;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

@Stateless
@Remote
public class VerticalRepo {
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IVertical")
	IVertical ctrVertical;	
	
	//USUARIO
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) {
		return ctrVertical.AltaUsuario(usuario, tenant);
	}

	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrVertical.obtenerUsuarios(pagina, elementosPagina, tenant);
	}

	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant) {
		return ctrVertical.loginUsuario(usuario, clave, tenant);
	}
	
	public DataUsuario getUsuario(String id, DataTenant tenant) {
		return ctrVertical.getUsuario(id, tenant);
	}
	
	public void modificarUsuario(DataUsuario usuario, DataTenant tenant){
		ctrVertical.modificarUsuario(usuario, tenant);
	}
	
	//PROVEEDOR
	public DataProveedor altaProveedor(DataProveedor proveedor, DataTenant tenant) {
		return ctrVertical.altaProveedor(proveedor, tenant);
	}

	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrVertical.obtenerProveedores(pagina, elementosPagina, tenant);
	}

	public DataProveedor getProveedor(String id, DataTenant tenant) {
		return ctrVertical.getProveedor(id, tenant);
	}
	
	public void modificarProveedor(DataProveedor proveedor, DataTenant tenant){
		ctrVertical.modificarProveedor(proveedor, tenant);
	}
	
	//ADMINISTRADOR
	public DataAdministrador altaAdmin(DataAdministrador admin, DataTenant tenant) {
		return ctrVertical.altaAdmin(admin, tenant);
	}

	public List<DataAdministrador> obtenerAdmins(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrVertical.obtenerAdmins(pagina, elementosPagina, tenant);
	}

	public DataAdministrador loginAdmin(String admin, String clave, DataTenant tenant) {
		return ctrVertical.loginAdmin(admin, clave, tenant);
	}
	
	public DataAdministrador getAdmin(String id, DataTenant tenant) {
		return ctrVertical.getAdmin(id, tenant);
	}
	
	public void modificarAdmin(DataAdministrador admin, DataTenant tenant){
		ctrVertical.modificarAdmin(admin, tenant);
	}
	
	//CONFIGURACION VERTICAL
	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant){
		return ctrVertical.getConfiguracionVertical(tenant);
	}
	
	public void modificarConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		ctrVertical.modificarConfiguracionVertical(conf, tenant);
	}
	
	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		return ctrVertical.crearConfiguracionVertical(conf, tenant);
	}
	
	public DataServicio pedirServicio(String idUsuario, String ubicacion, String destinoOMensaje, DataTenant tenant){
        return ctrVertical.pedirServicio(idUsuario, ubicacion, destinoOMensaje, tenant);
	}
	public DataServicio ofrecerServicio(String idServicio, String idProveedor, DataTenant tenant){
	        return ctrVertical.ofrecerServicio(idServicio, idProveedor, tenant);
	}

	public List<DataProveedor> reporteRatingProveedores(Integer pagina, Integer elementosPagina, Integer rating, DataTenant tenant) {
		return ctrVertical.reporteRatingProveedores(pagina, elementosPagina, rating, tenant);
	}
	
	public DataUsuario loginAltaUsuarioFacebook(String email, String nombre, String uid, DataTenant tenant){
		return ctrVertical.loginAltaUsuarioFacebook(email, nombre, uid, tenant);
	}

}