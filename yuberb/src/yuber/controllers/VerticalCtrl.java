package yuber.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import yuber.interfaces.AdministradorLocalApi;
import yuber.interfaces.ConfiguracionVerticalLocalApi;
import yuber.interfaces.IAdministrador;
import yuber.interfaces.IConfiguracionVertical;
import yuber.interfaces.IProveedor;
import yuber.interfaces.IUsuario;
import yuber.interfaces.ProveedorLocalApi;
import yuber.interfaces.UsuarioLocalApi;
import yuber.shares.DataAdministrador;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataProveedor;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

@Stateless
public class VerticalCtrl implements IUsuario, IProveedor, IAdministrador, IConfiguracionVertical{
	@EJB(lookup = "java:app/yuberdb/ConfiguracionVerticalSrv!yuber.interfaces.ConfiguracionVerticalLocalApi")
	ConfiguracionVerticalLocalApi srvConfiguracionVertical;
	@EJB(lookup = "java:app/yuberdb/ProveedorSrv!yuber.interfaces.ProveedorLocalApi")
	ProveedorLocalApi srvProveedor;
	@EJB(lookup = "java:app/yuberdb/AdministradorSrv!yuber.interfaces.AdministradorLocalApi")
	AdministradorLocalApi srvAdmin;
	@EJB(lookup = "java:app/yuberdb/UsuarioSrv!yuber.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;

	//USUARIO
	@Override
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) {
	  
		return srvUsuario.crearUsuario(usuario, tenant);
	}


	@Override
	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvUsuario.obtenerUsuarios(pagina, elementosPagina, tenant);
	}


	@Override
	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant) {
		return srvUsuario.loginUsuario(usuario, clave, tenant);
	}

	@Override
	public DataUsuario getUsuario(String id, DataTenant tenant) {
		return srvUsuario.getUsuario(id , tenant);
	}
	
	@Override
	public void modificarUsuario(DataUsuario usuario, DataTenant tenant){
		srvUsuario.modificarUsuario(usuario, tenant);
	}
	
	//PROVEEDOR
	@Override
	public DataProveedor altaProveedor(DataProveedor proveedor, DataTenant tenant){
		
		return srvProveedor.crearProveedor(proveedor, tenant);
	}
	
	@Override
	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant){
		
		return srvProveedor.obtenerProveedores(pagina, elementosPagina, tenant);
	}
	
	@Override
	public DataProveedor loginProveedor(String usuario, String clave, DataTenant tenant){
		
		return srvProveedor.loginProveedor(usuario, clave, tenant);
	}
	
	@Override
	public DataProveedor getProveedor(String id, DataTenant tenant){
		return srvProveedor.getProveedor(id, tenant);
	}
	
	@Override 
	public void modificarProveedor(DataProveedor proveedor, DataTenant tenant){
		
		srvProveedor.modificarProveedor(proveedor, tenant);
	}
	
	//ADMINISTRADOR
	@Override
	public DataAdministrador altaAdmin(DataAdministrador admin, DataTenant tenant) {
	  
		return srvAdmin.crearAdmin(admin, tenant);
	}


	@Override
	public List<DataAdministrador> obtenerAdmins(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvAdmin.obtenerAdmins(pagina, elementosPagina, tenant);
	}


	@Override
	public DataAdministrador loginAdmin(String usuario, String clave, DataTenant tenant) {
		return srvAdmin.loginAdmin(usuario, clave, tenant);
	}

	@Override
	public DataAdministrador getAdmin(String id, DataTenant tenant) {
		return srvAdmin.getAdmin(id , tenant);
	}
	
	@Override
	public void modificarAdmin(DataAdministrador admin, DataTenant tenant) {
		srvAdmin.modificarAdmin(admin, tenant);
	}
	
	//CONFIGURACION VERTICAL
	@Override
	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant){
		
		return srvConfiguracionVertical.getConfiguracionVertical(tenant);
	}
	
	@Override
	public void modificarConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		
		srvConfiguracionVertical.modificarConfiguracion(conf, tenant);
	}
	
	@Override
	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		
		
		return srvConfiguracionVertical.crearConfiguracionVertical(conf, tenant);
	}
}
