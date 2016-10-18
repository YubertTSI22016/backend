package yuber.api.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import yuber.interfaces.IAdministrador;
import yuber.interfaces.IConfiguracionVertical;
import yuber.interfaces.IProveedor;
import yuber.interfaces.IUsuario;
import yuber.shares.DataAdministrador;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataProveedor;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

@Stateless
@Remote
public class VerticalRepo {
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IUsuario")
	IUsuario ctrUsuario;
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IProveedor")
	IProveedor ctrProveedor;
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IAdministrador")
	IAdministrador ctrAdministrador;
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IConfiguracionVertical")
	IConfiguracionVertical ctrConfiguracionVertical;
	
	
	//USUARIO
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) {
		return ctrUsuario.AltaUsuario(usuario, tenant);
	}

	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrUsuario.obtenerUsuarios(pagina, elementosPagina, tenant);
	}

	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant) {
		return ctrUsuario.loginUsuario(usuario, clave, tenant);
	}
	
	public DataUsuario getUsuario(String id, DataTenant tenant) {
		return ctrUsuario.getUsuario(id, tenant);
	}
	
	public void modificarUsuario(DataUsuario usuario, DataTenant tenant){
		ctrUsuario.modificarUsuario(usuario, tenant);
	}
	
	//PROVEEDOR
	public DataProveedor altaProveedor(DataProveedor proveedor, DataTenant tenant) {
		return ctrProveedor.altaProveedor(proveedor, tenant);
	}

	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrProveedor.obtenerProveedores(pagina, elementosPagina, tenant);
	}

	public DataProveedor getProveedor(String id, DataTenant tenant) {
		return ctrProveedor.getProveedor(id, tenant);
	}
	
	public void modificarProveedor(DataProveedor proveedor, DataTenant tenant){
		ctrProveedor.modificarProveedor(proveedor, tenant);
	}
	
	//ADMINISTRADOR
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
	
	//CONFIGURACION VERTICAL
	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant){
		return ctrConfiguracionVertical.getConfiguracionVertical(tenant);
	}
	
	public void modificarConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		ctrConfiguracionVertical.modificarConfiguracionVertical(conf, tenant);
	}
	
	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		return ctrConfiguracionVertical.crearConfiguracionVertical(conf, tenant);
	}

}
