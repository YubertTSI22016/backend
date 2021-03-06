package yuber.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pusher.rest.Pusher;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Account;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Transfer;

import yuber.interfaces.AdministradorLocalApi;
import yuber.interfaces.ConfiguracionVerticalLocalApi;
import yuber.interfaces.IVertical;
import yuber.interfaces.PagosProveedorLocalApi;
import yuber.interfaces.ProveedorLocalApi;
import yuber.interfaces.ServicioLocalApi;
import yuber.interfaces.UsuarioLocalApi;
import yuber.shares.DataAdministrador;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataEmail;
import yuber.shares.DataJornadaLaboral;
import yuber.shares.DataPagosProveedor;
import yuber.shares.DataProveedor;
import yuber.shares.DataReporteProveedor;
import yuber.shares.DataServicio;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;
import yuber.shares.DataVerticalReport;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
public class VerticalCtrl implements IVertical {
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
	@EJB(lookup = "java:app/yuberdb/PagosProveedorSrv!yuber.interfaces.PagosProveedorLocalApi")
	PagosProveedorLocalApi srvPagosProveedor;
	private static final Log log = LogFactory.getLog(VerticalCtrl.class);

	// USUARIO
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(usuario.genClave().getBytes());

			byte byteData[] = md.digest();
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		usuario.setClave(sb.toString());
		return srvUsuario.crearUsuario(usuario, tenant);
	}

	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvUsuario.obtenerUsuarios(pagina, elementosPagina, tenant);
	}

	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant) {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(clave.getBytes());

			byte byteData[] = md.digest();
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			log.info("Error login ================================================= " + e.getMessage());
		}

		clave = sb.toString();
		log.info("Clave Login ================================================= " + clave);
		return srvUsuario.loginUsuario(usuario, clave, tenant);
	}

	public DataUsuario getUsuario(String id, DataTenant tenant) {
		return srvUsuario.getUsuario(id, tenant);
	}

	public DataUsuario modificarUsuario(DataUsuario usuario, DataTenant tenant) {
		return srvUsuario.modificarUsuario(usuario, tenant);
	}

	// PROVEEDOR

	public DataProveedor altaProveedor(DataProveedor proveedor, DataTenant tenant) {
		DataProveedor result = srvProveedor.crearProveedor(proveedor, tenant);
		DataUsuario usuario = srvUsuario.getUsuario(result.getUsuario().getId(), tenant);
		usuario.setProveedor(result);
		srvUsuario.modificarUsuario(usuario, tenant);
		return srvProveedor.getProveedor(result.getId(), tenant);
	}

	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant) {

		return srvProveedor.obtenerProveedores(pagina, elementosPagina, tenant);
	}

	public DataProveedor getProveedor(String id, DataTenant tenant) {
		return srvProveedor.getProveedor(id, tenant);
	}

	public void modificarProveedor(DataProveedor proveedor, DataTenant tenant) {

		srvProveedor.modificarProveedor(proveedor, tenant);
	}

	// ADMINISTRADOR

	public DataAdministrador altaAdmin(DataAdministrador admin, DataTenant tenant) {

		return srvAdmin.crearAdmin(admin, tenant);
	}

	public List<DataAdministrador> obtenerAdmins(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvAdmin.obtenerAdmins(pagina, elementosPagina, tenant);
	}

	public DataAdministrador loginAdmin(String usuario, String clave, DataTenant tenant) {
		return srvAdmin.loginAdmin(usuario, clave, tenant);
	}

	public DataAdministrador getAdmin(String id, DataTenant tenant) {
		return srvAdmin.getAdmin(id, tenant);
	}

	public void modificarAdmin(DataAdministrador admin, DataTenant tenant) {
		srvAdmin.modificarAdmin(admin, tenant);
	}

	// CONFIGURACION VERTICAL

	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant) {

		return srvConfiguracionVertical.getConfiguracionVertical(tenant);
	}

	public void modificarConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant) {

		srvConfiguracionVertical.modificarConfiguracion(conf, tenant);
	}

	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant) {

		return srvConfiguracionVertical.crearConfiguracionVertical(conf, tenant);
	}

	public DataServicio pedirServicio(String idUsuario, String ubicacion, String destino, String descripcion,
			DataTenant tenant) {
		DataServicio servicio = new DataServicio();
		DataUsuario usuario = srvUsuario.getUsuario(idUsuario, tenant);
		servicio.setUsuario(usuario);
		servicio.setCoordenadasOrigen(ubicacion);
		servicio.setFecha(new Date());
		DataConfiguracionVertical conf = srvConfiguracionVertical.getConfiguracionVertical(tenant);
		if (conf.getTransporte()) {
			servicio.setCoordenadasDestino(destino);
		}
		servicio.setDescripcion(descripcion);
		servicio.setEstado("Solicitado");
		servicio = srvServicio.crearServicio(servicio, tenant);
		if (conf.getTransporte()) {
			usuario.setServicioActivo(servicio);
		}
		List<DataServicio> serviciosUsuario = usuario.getServicios();
		serviciosUsuario.add(servicio);
		usuario.setServicios(serviciosUsuario);
		usuario = srvUsuario.modificarUsuario(usuario, tenant);
		Pusher pusher = new Pusher("259107", "c2f52caa39102181e99f", "805644b0daae68d5a848");
		pusher.setEncrypted(true);
		pusher.trigger(tenant.getId() + "-proveedores", "solicitud-recibida",
				Collections.singletonMap("message", servicio));
		return servicio;
	}

	public DataServicio ofrecerServicio(String idServicio, String idProveedor, DataTenant tenant) {
		DataServicio servicio = srvServicio.getServicio(idServicio, tenant);
		DataProveedor proveedor = srvProveedor.getProveedor(idProveedor, tenant);
		servicio.setProveedor(proveedor);
		servicio.setEstado("Aceptado");
		servicio.setInicio(new Date());
		srvServicio.modificarServicio(servicio, tenant);
		log.info("OFRECER SERVICIO ID : "+ servicio.getId());
		DataJornadaLaboral jornadaActual = proveedor.getJornadaActual();
		DataConfiguracionVertical conf = srvConfiguracionVertical.getConfiguracionVertical(tenant);
		if (conf.getTransporte()) {
			jornadaActual.setServicioActivo(servicio);
		}
		List<DataServicio> serviciosProv = jornadaActual.getServicios();
		if (serviciosProv == null)
			serviciosProv = new ArrayList<DataServicio>();
		serviciosProv.add(servicio);
		jornadaActual.setServicios(serviciosProv);
		proveedor.setJornadaActual(jornadaActual);
		log.info("===================================Jornada actual "+jornadaActual);
		srvProveedor.modificarProveedor(proveedor, tenant);
		servicio = srvServicio.getServicio(idServicio, tenant);
		Pusher pusher = new Pusher("259107", "c2f52caa39102181e99f", "805644b0daae68d5a848");
		pusher.setEncrypted(true);
		pusher.trigger(tenant.getId() + "-usuario-" + servicio.getUsuario().getId(), "solicitud-aceptada",
				Collections.singletonMap("message", servicio));
		pusher.trigger(tenant.getId() + "-proveedores", "solicitud-cancelada",
				Collections.singletonMap("message", servicio));
		return servicio;
	}

	public DataServicio cancelarServicio(String idServicio, DataTenant tenant) {
		DataServicio servicio = srvServicio.getServicio(idServicio, tenant);
		servicio.setEstado("Cancelado");
		servicio.setFin(new Date());
		srvServicio.modificarServicio(servicio, tenant);
		Pusher pusher = new Pusher("259107", "c2f52caa39102181e99f", "805644b0daae68d5a848");
		pusher.setEncrypted(true);
		pusher.trigger(tenant.getId() + "-proveedores", "solicitud-cancelada",
				Collections.singletonMap("message", servicio));
		pusher.trigger(tenant.getId() + "-usuario-" + servicio.getUsuario().getId(), "solicitud-cancelada",
				Collections.singletonMap("message", servicio));
		return servicio;
	}

	public DataServicio obtenerServicio(String idServicio, DataTenant tenant) {
		return srvServicio.getServicio(idServicio, tenant);
	}

	public DataProveedor iniciarJornadaLaboral(String idProveedor, DataTenant tenant) {
		DataProveedor proveedor = srvProveedor.getProveedor(idProveedor, tenant);
		DataJornadaLaboral jornada = proveedor.getJornadaActual();
		if (jornada != null) {
			return null;
		}
		jornada = new DataJornadaLaboral();
		jornada.setInicio(new Date());
		jornada.setProveedor(proveedor);
		proveedor.setJornadaActual(jornada);
		return srvProveedor.modificarProveedor(proveedor, tenant);
	}

	public DataProveedor finalizarJornadaLaboral(String idProveedor, DataTenant tenant) {
		DataProveedor proveedor = srvProveedor.getProveedor(idProveedor, tenant);
		DataJornadaLaboral jornada = proveedor.getJornadaActual();
		if (jornada == null) {
			return null;
		}
		jornada.setFin(new Date());
		proveedor.setJornadaActual(null);
		List<DataJornadaLaboral> historialJornadas = proveedor.getJornadas();
		historialJornadas.add(jornada);
		proveedor.setJornadas(historialJornadas);
		return srvProveedor.modificarProveedor(proveedor, true, tenant);
	}

	public DataServicio iniciarServicio(String idServicio, DataTenant tenant) {
		DataServicio servicio = srvServicio.getServicio(idServicio, tenant);
		servicio.setEstado("Iniciado");
		servicio.setInicio(new Date());
		srvServicio.modificarServicio(servicio, tenant);
		return servicio;
	}

	public DataServicio finalizarServicio(String idServicio, Float calificacionUsuario, DataTenant tenant) {
		DataConfiguracionVertical conf = srvConfiguracionVertical.getConfiguracionVertical(tenant);
		DataServicio servicio = srvServicio.getServicio(idServicio, tenant);
		servicio.setEstado("Finalizado");
		servicio.setFin(new Date());
		long diferenciaFechas = servicio.getFin().getTime() - servicio.getInicio().getTime();
		Float horasServicio = Float.valueOf(diferenciaFechas / (1000 * 60 * 60));
		Float precio = horasServicio * conf.getPrecioPorHora() + conf.getTarifaBase();
		servicio.setPrecio(precio);
		srvServicio.modificarServicio(servicio, tenant);
		DataUsuario usuario = srvUsuario.getUsuario(servicio.getUsuario().getId(), tenant);
		usuario.setServicioActivo(null);
		if (usuario.getCantidadServicios() == null) {
			usuario.setCantidadServicios(1);
			usuario.setRating(calificacionUsuario);
		} else {
			Integer cs = usuario.getCantidadServicios();
			cs = cs + 1;
			usuario.setCantidadServicios(cs);
			Float nuevoRating = (usuario.getRating() * usuario.getCantidadServicios() + calificacionUsuario) / cs;
			usuario.setRating(nuevoRating);
		}
		srvUsuario.modificarUsuario(usuario, tenant);
		DataProveedor proveedor = srvProveedor.getProveedor(servicio.getProveedor().getId(), tenant);
		DataJornadaLaboral jornada = proveedor.getJornadaActual();
		jornada.setServicioActivo(null);
		proveedor.setJornadaActual(jornada);
		srvProveedor.modificarProveedor(proveedor, tenant);
		servicio = srvServicio.getServicio(idServicio, tenant);

		log.info("CARGAR TARJETA INICIO ================================================= ");

		cargarTarjeta(usuario.getId(), precio, tenant);

		log.info("CARGAR TARJETA FIN ================================================= ");

		DataPagosProveedor pagoAProveedor = new DataPagosProveedor();
		pagoAProveedor.setProveedor(proveedor);
		pagoAProveedor.setPago(false);
		pagoAProveedor.setServicio(servicio);
		pagoAProveedor.setPorcentageRetencion(conf.getPorcentajeRetencion());
		srvPagosProveedor.crearPagosProveedor(pagoAProveedor, tenant);

		return servicio;
	}

	public DataServicio finalizarTransporte(String idServicio, Float distancia, Float calificacionUsuario,
			DataTenant tenant) {
		DataConfiguracionVertical conf = srvConfiguracionVertical.getConfiguracionVertical(tenant);
		DataServicio servicio = srvServicio.getServicio(idServicio, tenant);
		servicio.setEstado("Finalizado");
		servicio.setFin(new Date());
		Float precio = distancia * conf.getPrecioPorKm() + conf.getTarifaBase();
		servicio.setPrecio(precio);
		srvServicio.modificarServicio(servicio, tenant);
		DataUsuario usuario = srvUsuario.getUsuario(servicio.getUsuario().getId(), tenant);
		usuario.setServicioActivo(null);
		if (usuario.getCantidadServicios() == null) {
			usuario.setCantidadServicios(1);
			usuario.setRating(calificacionUsuario);
		} else {
			Integer cs = usuario.getCantidadServicios();
			cs = cs + 1;
			usuario.setCantidadServicios(cs);
			Float nuevoRating = (usuario.getRating() * usuario.getCantidadServicios() + calificacionUsuario) / cs;
			usuario.setRating(nuevoRating);
		}
		srvUsuario.modificarUsuario(usuario, tenant);
		DataProveedor proveedor = srvProveedor.getProveedor(servicio.getProveedor().getId(), tenant);
		DataJornadaLaboral jornada = proveedor.getJornadaActual();
		jornada.setServicioActivo(null);
		proveedor.setJornadaActual(jornada);
		srvProveedor.modificarProveedor(proveedor, tenant);
		servicio = srvServicio.getServicio(idServicio, tenant);
		cargarTarjeta(usuario.getId(), precio, tenant);
		DataPagosProveedor pagoAProveedor = new DataPagosProveedor();
		pagoAProveedor.setProveedor(proveedor);
		pagoAProveedor.setPago(false);
		pagoAProveedor.setServicio(servicio);
		pagoAProveedor.setPorcentageRetencion(conf.getPorcentajeRetencion());
		srvPagosProveedor.crearPagosProveedor(pagoAProveedor, tenant);
		return servicio;
	}

	public List<DataPagosProveedor> listarPagosPendientes(String idProveedor, Integer pagina, Integer elementosPagina,
			DataTenant tenant) {
		return srvPagosProveedor.listarPagosPendientes(idProveedor, pagina, elementosPagina, tenant);
	}

	public List<DataProveedor> reporteRatingProveedores(Integer pagina, Integer elementosPagina, Integer rating,
			DataTenant tenant) {
		return srvProveedor.reporteRatingProveedores(pagina, elementosPagina, rating, tenant);
	}

	public List<DataReporteProveedor> rankingProveedoresPorGanancia(Date start, Date end, int pagina,
			int elementosPagina, DataTenant tenant) throws Exception {
		return srvProveedor.rankingProveedoresPorGanancia(start, end, pagina, elementosPagina, tenant);
	}
	 
	public List<DataReporteProveedor> rankingUsuariosPorGanancia(Date start, Date end, int pagina, int elementosPagina,DataTenant tenant){
		try{
			return srvUsuario.rankingUsuariosPorGanancia(start, end, pagina, elementosPagina, tenant);	
		}catch(Exception e){
			return null;
		}
	}	
	public DataUsuario loginAltaUsuarioFacebook(String email, String nombre, String uid, DataTenant tenant) {
		DataUsuario result = srvUsuario.loginFacebook(email, uid, tenant);
		if (result != null) {
			if (result.getId() != null) {
				return result;
			} else {
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

	public void calificarServicio(String idServicio, Float calificacion, String comentario, DataTenant tenant) {
		DataServicio serv = srvServicio.getServicio(idServicio, tenant);
		serv.setRating(calificacion);
		serv.setComentario(comentario);
		DataProveedor proveedor = serv.getProveedor();
		if (proveedor.getCantidadServicios() == null) {
			proveedor.setCantidadServicios(1);
			proveedor.setRating(calificacion);
		} else {
			Integer cs = proveedor.getCantidadServicios();
			cs = cs + 1;
			proveedor.setCantidadServicios(cs);
			Float nuevoRating = (proveedor.getRating() * proveedor.getCantidadServicios() + calificacion) / cs;
			proveedor.setRating(nuevoRating);
		}
		srvProveedor.modificarProveedor(proveedor, tenant);
		srvServicio.modificarServicio(serv, tenant);
	}

	public void guardarTokenUsuario(String idUsuario, String token, Integer ultimosDigitosTarjeta, DataTenant tenant) {

		DataUsuario usu = getUsuario(idUsuario, tenant);
		usu.setTokenTarjeta(token);
		usu.setUltimosNumerosTarjeta(ultimosDigitosTarjeta);
		Stripe.apiKey = "sk_test_7EZ8SFryAQ9k8jrdQplMBlYk";

		String accId = "";
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("description", "Customer for "+usu.getEmail().getEmail());
		customerParams.put("source", token);

		Customer cus = new Customer();
		try {
			cus = Customer.create(customerParams);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException
				| APIException e) {
			e.printStackTrace();
		}
		accId = cus.getId();
		usu.setStripeAccId(accId);
		srvUsuario.modificarUsuario(usu, tenant);
	}

	public void eliminarTokenUsuario(String idUsuario, DataTenant tenant) {

		DataUsuario usu = getUsuario(idUsuario, tenant);
		usu.setTokenTarjeta(null);
		usu.setUltimosNumerosTarjeta(null);
		srvUsuario.modificarUsuario(usu, tenant);
	}

	public void guardarTokenProveedor(String idProveedor, String token, Integer ultimosDigitosTarjeta,
			DataTenant tenant) {

		DataProveedor prov = getProveedor(idProveedor, tenant);
		prov.setTokenTarjeta(token);
		prov.setUltimosNumerosTarjeta(ultimosDigitosTarjeta);
		Stripe.apiKey = "sk_test_7EZ8SFryAQ9k8jrdQplMBlYk";

		Map<String, Object> accountParams = new HashMap<String, Object>();
		accountParams.put("managed", true);
		accountParams.put("country", "US");
		accountParams.put("email", prov.getUsuario().getEmail().getEmail());
		String accId = "";

		try {
			Account acc = Account.create(accountParams);
			accId = acc.getId();

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("external_account", token);
			params.put("default_for_currency", true);
			acc.getExternalAccounts().create(params);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException
				| APIException e) {
			e.printStackTrace();
		}
		prov.setStripeAccId(accId);
		srvProveedor.modificarProveedor(prov, tenant);
	}

	public void eliminarTokenProveedor(String idProveedor, DataTenant tenant) {

		DataProveedor prov = getProveedor(idProveedor, tenant);
		prov.setTokenTarjeta(null);
		prov.setUltimosNumerosTarjeta(null);
		srvProveedor.modificarProveedor(prov, tenant);
	}

	// CARGO LA TARJETA DEL USUARIO

	public void cargarTarjeta(String idUsuario, Float cargo, DataTenant tenant) {
		// Set your secret key: remember to change this to your live secret key
		// in production
		// See your keys here: https://dashboard.stripe.com/account/apikeys
		Stripe.apiKey = "sk_test_7EZ8SFryAQ9k8jrdQplMBlYk";

		// Get the credit card details submitted by the form
		// String token = request.getParameter("stripeToken");
		DataUsuario usu = getUsuario(idUsuario, tenant);
		String cusId = usu.getStripeAccId();
		String emailUsuario = usu.getEmail().getEmail();

		// Create a charge: this will charge the user's card
		try {
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("amount", Math.round(cargo * 100)); // Amount in
																	// cents
			chargeParams.put("currency", "usd");
			chargeParams.put("customer", cusId);
			chargeParams.put("description", "Cargo para: " + emailUsuario);

			// Charge charge = Charge.create(chargeParams);
			Charge.create(chargeParams);

		} catch (Exception e) {
			log.info("=================================================" + e.getMessage()
					+ "=================================================");
		}

	}

	public List<DataServicio> listarServicios(String idUsuProv, DataTenant tenant) {
		DataUsuario usuario = srvUsuario.getUsuario(idUsuProv, tenant);
		if (usuario != null) {
			return usuario.getServicios();
		}
		DataProveedor proveedor = srvProveedor.getProveedor(idUsuProv, tenant);
		if (proveedor != null) {
			return srvServicio.listarServiciosPorProveedor(idUsuProv, tenant);
		}
		return null;
	}

	public void ingresarPuntoRecorrido(String idServicio, String punto, DataTenant tenant) {

		DataServicio srv = srvServicio.getServicio(idServicio, tenant);
		List<String> puntos = srv.getPuntosRecorrido();
		if (puntos == null)
			puntos = new ArrayList<String>();
		puntos.add(punto);
		srv.setPuntosRecorrido(puntos);
		srvServicio.modificarServicio(srv, tenant);
	}

	public List<String> obtenerPuntosServicio(String idServicio, DataTenant tenant) {
		DataServicio srv = srvServicio.getServicio(idServicio, tenant);
		return srv.getPuntosRecorrido();
	}

	public void pagoAProveedor(String idProveedor, DataTenant tenant) {
		Stripe.apiKey = "sk_test_7EZ8SFryAQ9k8jrdQplMBlYk";
		DataProveedor prov = srvProveedor.getProveedor(idProveedor, tenant);
		List<DataPagosProveedor> listaPagosPendientes = srvPagosProveedor.listarPagosPendientes(idProveedor, 1, 1000000,
				tenant);
		Float pago = 0.0f;
		for (Integer i = 0; i < listaPagosPendientes.size(); i++) {
			DataPagosProveedor pp = listaPagosPendientes.get(i);
			pago += pp.getServicio().getPrecio() * (1 - (pp.getPorcentageRetencion() / 100));
			pp.setPago(true);
		}
		srvPagosProveedor.cambiarEstadoAPago(idProveedor, tenant);
		try {
			Map<String, Object> transferParams = new HashMap<String, Object>();
			transferParams.put("amount", Math.round(pago * 100));
			transferParams.put("currency", "usd");
			transferParams.put("destination", "default_for_currency");
			// aqui en vez de token de la tarjeta va el ID de la cuenta de
			// usuario Stripe conectada
			transferParams.put("destination", prov.getStripeAccId());

			Transfer.create(transferParams);
		} catch (Exception e) {
			log.info("================================================= " + e.getMessage()
					+ " =================================================");
		}
	}

	@Override
	public void checkearTiemposPedidos(DataTenant tenant) {
		List<DataServicio> servicios = srvServicio.obtenerServiciosPendientes(tenant);
		for (Integer i = 0; i < servicios.size(); i++) {
			DataServicio servicio = servicios.get(i);
			long diferenciaFechas = (new Date()).getTime() - servicio.getFecha().getTime();
			Float segundosDeSolicitado = Float.valueOf(diferenciaFechas / (1000));
			if (segundosDeSolicitado > 15f) {
				this.cancelarServicio(servicio.getId(), tenant);
			}
		}
	}

	@Override
	public List<DataUsuario> rankingUsuariosActivos(Date from, Integer pagina, Integer elementosPagina,
			DataTenant tenant) {
		return srvUsuario.rankingUsuariosActivos(from, pagina, elementosPagina, tenant);
	}
	public List<DataVerticalReport> getReport(Date start, DataTenant tenant){
		return srvAdmin.getReport(start, tenant);
	}

	@Override
	public List<DataReporteProveedor> rankingUsuariosPorConsumo(Date start, Date end, Integer pagina,
			Integer elementosPagina, DataTenant tenant) {
		try{
			return srvUsuario.rankingUsuariosPorConsumo(start, end, pagina, elementosPagina, tenant);	
		}catch(Exception e){
			return null;
		}
	}
}
