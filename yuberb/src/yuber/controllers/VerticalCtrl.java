package yuber.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pusher.rest.Pusher;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

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
	public DataUsuario modificarUsuario(DataUsuario usuario, DataTenant tenant){
		return srvUsuario.modificarUsuario(usuario, tenant);
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
		DataUsuario usuario = srvUsuario.getUsuario(idUsuario, tenant);
		servicio.setUsuario(usuario);
		servicio.setCoordenadasOrigen(ubicacion);
		DataConfiguracionVertical conf = srvConfiguracionVertical.getConfiguracionVertical(tenant);
		if(conf.getTransporte()){
			servicio.setCoordenadasDestino(destinoOMensaje);
		}else{
			servicio.setDescripcion(destinoOMensaje);
		}
		servicio.setEstado("Solicitado");
		servicio = srvServicio.crearServicio(servicio, tenant);
		usuario.setServicioActivo(servicio);
		usuario = srvUsuario.modificarUsuario(usuario, tenant);
        
        //Pusher pusher = new Pusher("259107", "c2f52caa39102181e99f", "805644b0daae68d5a848");
        ///pusher.setEncrypted(true);

        //pusher.trigger(tenant+"-proveedores", "solicitud-recibida", Collections.singletonMap("message", servicio.getId()));
        return servicio;
	}
	
	@Override
	public DataServicio ofrecerServicio(String idServicio, String idProveedor, DataTenant tenant){
		DataServicio servicio = srvServicio.getServicio(idServicio, tenant);
		DataProveedor proveedor = srvProveedor.getProveedor(idProveedor, tenant);
		servicio.setProveedor(proveedor);
		servicio.setEstado("Aceptado");
		srvServicio.modificarServicio(servicio, tenant);
		DataJornadaLaboral jornadaActual = proveedor.getJornadaActual();
		jornadaActual.setServicioActivo(servicio);
		proveedor.setJornadaActual(jornadaActual);
		srvProveedor.modificarProveedor(proveedor, tenant);
        
        //Pusher pusher = new Pusher("259107", "c2f52caa39102181e99f", "805644b0daae68d5a848");
        //pusher.setEncrypted(true);

        //pusher.trigger(tenant+"-proveedores", "solicitud-recibida", Collections.singletonMap("message", servicio.getId()));
        return servicio;
	}
	
	@Override
	public DataServicio cancelarServicio(String idServicio, DataTenant tenant){
		DataServicio servicio = srvServicio.getServicio(idServicio, tenant);
		servicio.setEstado("Cancelado");
		srvServicio.modificarServicio(servicio, tenant);
        return servicio;
	}
	
	@Override
	public DataServicio obtenerServicio(String idServicio, DataTenant tenant){
		return srvServicio.getServicio(idServicio, tenant);
	}
	
	@Override
	public DataProveedor iniciarJornadaLaboral(String idProveedor, DataTenant tenant){
		DataProveedor proveedor = srvProveedor.getProveedor(idProveedor, tenant);
		DataJornadaLaboral jornada = proveedor.getJornadaActual();
		if(jornada != null){
			return null;
		}
		jornada = new DataJornadaLaboral();
		jornada.setInicio(new Date());
		jornada.setProveedor(proveedor);
		proveedor.setJornadaActual(jornada);
		return srvProveedor.modificarProveedor(proveedor, tenant);
	}
	
	@Override
	public DataProveedor finalizarJornadaLaboral(String idProveedor, DataTenant tenant){
		DataProveedor proveedor = srvProveedor.getProveedor(idProveedor, tenant);
		DataJornadaLaboral jornada = proveedor.getJornadaActual();
		if(jornada == null){
			return null;
		}
		jornada.setFin(new Date());
		proveedor.setJornadaActual(null);
		List<DataJornadaLaboral> historialJornadas = proveedor.getJornadas();
		historialJornadas.add(jornada);
		proveedor.setJornadas(historialJornadas);
        return srvProveedor.modificarProveedor(proveedor, tenant);
	}
	
	@Override
	public DataServicio finalizarServicio(String idServicio, Float precio, DataTenant tenant){
		DataServicio servicio = srvServicio.getServicio(idServicio, tenant);
		servicio.setEstado("Finalizado");
		servicio.setPrecio(precio);
		srvServicio.modificarServicio(servicio, tenant);
		DataUsuario usuario = servicio.getUsuario();
		List<DataServicio> servicios = usuario.getServicios();
		if(servicios == null)
			servicios = new ArrayList<DataServicio>();
		servicios.add(servicio);
		usuario.setServicios(servicios);
		usuario.setServicioActivo(null);
		srvUsuario.modificarUsuario(usuario, tenant);
		DataProveedor proveedor = servicio.getProveedor();
		DataJornadaLaboral jornada = proveedor.getJornadaActual();
		List<DataServicio> serviciosp = jornada.getServicios();
		servicios.add(servicio);
		jornada.setServicios(serviciosp);
		jornada.setServicioActivo(null);
		proveedor.setJornadaActual(jornada);
		srvProveedor.modificarProveedor(proveedor, tenant);
		cargarTarjeta (usuario.getId(), precio,tenant);
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


	@Override
	public void calificarServicio(String idServicio, Float calificacion, String comentario, DataTenant tenant) {
		DataServicio serv = srvServicio.getServicio(idServicio, tenant);
		serv.setRating(calificacion);
		serv.setComentario(comentario);
		srvServicio.modificarServicio(serv, tenant);
	}
	
	//PAGOS
	//GUARDO EL TOKEN EN EL USUARIO
	@Override
	public void guardarToken(String idUsuario, String token, Integer ultimosDigitosTarjeta, DataTenant tenant){

		DataUsuario usu = getUsuario(idUsuario, tenant);
		usu.setTokenTarjeta(token);
		usu.setUltimosNumerosTarjeta(ultimosDigitosTarjeta);
		srvUsuario.modificarUsuario(usu, tenant);
	}

	//ELIMINO EL TOKEN DEL USUARIO
	@Override
	public void eliminarToken(String idUsuario, DataTenant tenant){

		DataUsuario usu = getUsuario(idUsuario, tenant);
		usu.setTokenTarjeta(null);
		usu.setUltimosNumerosTarjeta(null);
		srvUsuario.modificarUsuario(usu, tenant);
	}	

	//CARGO LA TARJETA DEL USUARIO
	@Override
	public void cargarTarjeta (String idUsuario, Float cargo, DataTenant tenant) {
		// Set your secret key: remember to change this to your live secret key in production
		// See your keys here: https://dashboard.stripe.com/account/apikeys
		Stripe.apiKey = "sk_test_7EZ8SFryAQ9k8jrdQplMBlYk";
	
		// Get the credit card details submitted by the form
		//String token = request.getParameter("stripeToken");
		DataUsuario usu = getUsuario(idUsuario, tenant);
		String token = usu.getTokenTarjeta();
		String emailUsuario = usu.getEmail().getEmail();
	
		// Create a charge: this will charge the user's card
		try {
		  Map<String, Object> chargeParams = new HashMap<String, Object>();
		  chargeParams.put("amount", cargo); // Amount in cents
	 	  chargeParams.put("currency", "usd");
		  chargeParams.put("source", token);
		  chargeParams.put("description", "Cargo para: " + emailUsuario);
	
	 	  //Charge charge = Charge.create(chargeParams);
	 	  Charge.create(chargeParams);
	 	  
		} catch (Exception e) {
			  // Something else happened, completely unrelated to Stripe
		}

	}
	
	@Override
	public List<DataServicio> listarServicios(String idUsuProv, DataTenant tenant){
		DataUsuario usuario = srvUsuario.getUsuario(idUsuProv, tenant);
		if(usuario!=null){
			return usuario.getServicios();
		}
		DataProveedor proveedor = srvProveedor.getProveedor(idUsuProv, tenant);
		if(proveedor != null){
			return srvServicio.listarServiciosPorProveedor(idUsuProv, tenant);
		}
		return null;
	}

}
