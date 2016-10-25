package yuber.controllers;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pusher.rest.Pusher;

import yuber.interfaces.AdministradorLocalApi;
import yuber.interfaces.ConfiguracionVerticalLocalApi;
import yuber.interfaces.IVertical;
import yuber.interfaces.ProveedorLocalApi;
import yuber.interfaces.ServicioLocalApi;
import yuber.interfaces.UsuarioLocalApi;
import yuber.shares.*;

@Stateless
public class VerticalCtrl implements IVertical{
	@EJB(lookup = "java:app/yuberdb/ConfiguracionVerticalSrv!yuber.interfaces.ConfiguracionVerticalLocalApi")
	ConfiguracionVerticalLocalApi srvConfiguracionVertical;
	@EJB(lookup = "java:app/yuberdb/ProveedorSrv!yuber.interfaces.ProveedorLocalApi")
	ProveedorLocalApi srvProveedor;
	@EJB(lookup = "java:app/yuberdb/AdministradorSrv!yuber.interfaces.AdministradorLocalApi")
	AdministradorLocalApi srvAdmin;
	@EJB(lookup = "java:app/yuberdb/UsuarioSrv!yuber.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;
	@EJB(lookup = "java:app/yuberdb/ServicioSrv!yuber.interfaces.ServicioLocalApi")
	ServicioLocalApi srvServicio;

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
	
	@Override
	public DataServicio pedirServicio(String idUsuario, String ubicacion, String destinoOMensaje, DataTenant tenant){
		DataServicio servicio = new DataServicio();
		servicio.setUsuario(srvUsuario.getUsuario(idUsuario, tenant));
		servicio.setCoordenadasOrigen(ubicacion);
		DataConfiguracionVertical conf = srvConfiguracionVertical.getConfiguracionVertical(tenant);
		if(conf.getTransporte()){
			servicio.setCoordenadasDestino(destinoOMensaje);
		}else{
			servicio.setDescripcion(destinoOMensaje);
		}
		DataServicio srv = srvServicio.crearServicio(servicio, tenant);
        
        Pusher pusher = new Pusher("259107", "c2f52caa39102181e99f", "805644b0daae68d5a848");
        pusher.setEncrypted(true);

        pusher.trigger(tenant+"-proveedores", "solicitud-recibida", Collections.singletonMap("message", srv.getId()));
        return srv;
	}
	
	@Override
	public DataServicio ofrecerServicio(String idServicio, String idProveedor, DataTenant tenant){
		DataServicio servicio = srvServicio.getServicio(idServicio, tenant);
		servicio.setProveedor(srvProveedor.getProveedor(idProveedor, tenant));
		srvServicio.modificarServicio(servicio, tenant);
        
        Pusher pusher = new Pusher("259107", "c2f52caa39102181e99f", "805644b0daae68d5a848");
        pusher.setEncrypted(true);

        pusher.trigger(tenant+"-proveedores", "solicitud-recibida", Collections.singletonMap("message", servicio.getId()));
        return servicio;
	}


	@Override
	public List<DataProveedor> reporteRatingProveedores(Integer pagina, Integer elementosPagina, Integer rating, DataTenant tenant) {
		return srvProveedor.reporteRatingProveedores(pagina, elementosPagina, rating, tenant);
	}
	
	@Override
	public DataUsuario loginAltaUsuarioFacebook(String email, String nombre, String uid, DataTenant tenant){
		DataUsuario result = srvUsuario.loginFacebook(email, uid, tenant);
		if(result != null){
			if(result.getId() != null){
				return result;
			}else{
				result.setNombre(nombre);
				result.setRedSocialUsada("Facebook");
				DataEmail mail = new DataEmail();
				mail.setEmail(email);
				result.setEmail(mail);
				result.setIdRedSocial(uid);
				return srvUsuario.crearUsuario(result, tenant);
			}
		}
		return null;
	}
}
