package yuber.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import yuber.api.service.VerticalRepo;
import yuber.shares.DataAdministrador;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataProveedor;
import yuber.shares.DataServicio;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

@RequestScoped
@Path("/vertical/")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class VerticalApi extends BaseApi{

	@EJB
	VerticalRepo verticalRepo;
	
	//USUARIO
	@POST
	@Path("/altausuario/")
	public DataUsuario altausuario(DataUsuario usuario){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.AltaUsuario(usuario, tenant);
	}
	
	@POST
	@Path("/loginusuario/")
	public DataUsuario loginusuario(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.loginUsuario(obj.getString("usuario"), obj.getString("clave"), tenant);
	}
	
	@GET	
	@Path("/listarusuarios/")
	public List<DataUsuario> listarusuarios(){
		Integer pagina = 1;
		Integer elementos = 1000;
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.obtenerUsuarios(pagina, elementos, tenant);
	}
	
	@GET	
	@Path("/obtenerusuario/{id}")
	public DataUsuario obtenerusuario(@PathParam("id") final String id){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.getUsuario(id, tenant);
	}
	
	@POST
	@Path("/modificarusuario/")
	public DataUsuario modificarusuario(DataUsuario usuario){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.modificarUsuario(usuario, tenant);
	}
	
	//PROVEEDOR
	@POST
	@Path("/altaproveedor/")
	public DataProveedor altaproveedor(DataProveedor proveedor){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.altaProveedor(proveedor, tenant);
	}
	
	@GET
	@Path("/listarproveedores/")
	public List<DataProveedor> listarproveedores(){
		Integer pagina =1;
		Integer elementosPagina= 1000;
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.obtenerProveedores(pagina, elementosPagina, tenant);
	}

	@GET
	@Path("/obtenerproveedor/{id}")
	public DataProveedor obtenerproveedor(@PathParam("id") final String id){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.getProveedor(id, tenant);
	}
	
	@POST
	@Path("/modificarproveedor/")
	public void modificarproveedor(DataProveedor proveedor){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		verticalRepo.modificarProveedor(proveedor, tenant);
	}
	
	//ADMINISTRADOR
	@POST
	@Path("/altaadmin/")
	public DataAdministrador altaadmin(DataAdministrador admin){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.altaAdmin(admin, tenant);
	}

	@POST
	@Path("/loginadmin/")
	public DataAdministrador loginadmin(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.loginAdmin(obj.getString("admin"), obj.getString("clave"), tenant);
	}
	
	@GET
	@Path("/listaradmins/")
	public List<DataAdministrador> listaradmins(){
		Integer pagina =1;
		Integer elementos = 1000;
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.obtenerAdmins(pagina, elementos, tenant);
	}
	
	@GET
	@Path("/obteneradmin/{id}")
	public DataAdministrador obteneradmin(@PathParam("id") final String id){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.getAdmin(id, tenant);
	}
	
	@POST
	@Path("/modificaradmin/")
	public void modificaradmin(DataAdministrador admin){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		verticalRepo.modificarAdmin(admin, tenant);
	}
	
	//CONFIGURACION VERTICAL
	@GET
	@Path("/obtenerconfig/")
	public DataConfiguracionVertical obtenerconfig(){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.getConfiguracionVertical(tenant);		
	}
	
	@POST
	@Path("/modificarconfig/")
	public void modificarconfig(DataConfiguracionVertical conf){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		verticalRepo.modificarConfiguracionVertical(conf, tenant);
	}
	
	@POST
	@Path("/crearconfig/")
	public DataConfiguracionVertical crearconfig(DataConfiguracionVertical conf){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.crearConfiguracionVertical(conf, tenant);
	}
	
	@POST
	@Path("/pedirservicio/")
	public DataServicio pedirServicio(String data){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		JSONObject obj = new JSONObject(data);
		return verticalRepo.pedirServicio(obj.getString("idUsuario"), obj.getString("ubicacion"), obj.getString("destino"), obj.getString("descripcion"), tenant);
	}
	
	@POST
	@Path("/ofrecerservicio/")
	public DataServicio ofrecerServicio(String data){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		JSONObject obj = new JSONObject(data);
		return verticalRepo.ofrecerServicio(obj.getString("idServicio"), obj.getString("idProveedor"), tenant);
	}
	
	@GET
	@Path("/reporteratingprov/{pagina:[0-9][0-9]*}/{elementosPagina:[0-9][0-9]*}/{rating:[0-5][0-5]*}")
	public List<DataProveedor> reporteRatingProveedores(@PathParam("pagina") final Integer pagina,@PathParam("elementosPagina") final Integer ElementosPagina, @PathParam("rating") final Integer rating){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.reporteRatingProveedores(pagina, ElementosPagina, rating, tenant);
	}
	
	@POST
	@Path("/loginaltafacebook/")
	public DataUsuario loginAltaUsuarioFacebook(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.loginAltaUsuarioFacebook(obj.getString("email"), obj.getString("nombre"), obj.getString("uid"), tenant);
	}
	
	@POST
	@Path("/calificarservicio/")
	public void calificarServicio(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		verticalRepo.calificarServicio(obj.getString("idServicio"), Float.valueOf(obj.getString("calificacion")), obj.getString("comentario"), tenant);
	}
	
	@POST
	@Path("/finalizarservicio/")
	public DataServicio finalizarServicio(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.finalizarServicio(obj.getString("idServicio"), Float.valueOf(obj.getString("precio")), Float.valueOf(obj.getString("calificacionUsuario")), tenant);
	}
	
	//PAGOS
	@POST
	@Path("/guardartoken/")
	public void guardartoken(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		verticalRepo.guardarToken(obj.getString("idUsuario"),obj.getString("token"),Integer.valueOf(obj.getString("ultimosDiegitosTarjeta")), tenant);
	}

	@POST
	@Path("/eliminartoken/")
	public void eliminartoken(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		verticalRepo.eliminarToken(obj.getString("idUsuario"), tenant);
	}
	
	@POST
	@Path("/cancelarservicio/")
	public DataServicio cancelarServicio(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.cancelarServicio(obj.getString("idServicio"), tenant);
	}
	
	@POST
	@Path("/iniciarjornadalaboral/")
	public DataProveedor iniciarJornadaLaboral(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.iniciarJornadaLaboral(obj.getString("idProveedor"), tenant);
	}
	
	@POST
	@Path("/finalizarjornadalaboral/")
	public DataProveedor finalizarJornadaLaboral(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.finalizarJornadaLaboral(obj.getString("idProveedor"), tenant);
	}
	
	@GET
	@Path("/obtenerservicio/{idServicio}")
	public DataServicio obtenerServicio(@PathParam("idServicio") final String idServicio){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.obtenerServicio(idServicio, tenant);
	}
	
	@GET
	@Path("/listarservicios/{idUsuProv}")
	public List<DataServicio> listarServicios(@PathParam("idUsuProv") final String idUsuProv){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return verticalRepo.listarServicios(idUsuProv, tenant);
	}

//	@POST
//	@Path("/cargartarjeta/")
//	public void cargartarjeta(DataUsuario usuario, Float carga){
//		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
//		verticalRepo.cargarTarjeta(usuario, carga, tenant);
//	}
}
