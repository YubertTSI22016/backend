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

import yuber.api.service.AdministradorRepo;
import yuber.shares.DataTenant;
import yuber.shares.DataAdministrador;

@RequestScoped
@Path("/admins/")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class AdministradorApi extends BaseApi {
	
	@EJB
	AdministradorRepo repo;
	
	@POST
	@Path("/alta/")
	public DataAdministrador alta(DataAdministrador admin){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.altaAdmin(admin, tenant);
	}

	@POST
	@Path("/login/")
	public DataAdministrador login(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.loginAdmin(obj.getString("admin"), obj.getString("clave"), tenant);
	}
	
	@GET
	@Path("/listar/")
	public List<DataAdministrador> listar(){
		Integer pagina =1;
		Integer elementos = 1000;
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerAdmins(pagina, elementos, tenant);
	}
	
	@GET
	@Path("/obtener/{id}")
	public DataAdministrador obtener(@PathParam("id") final String id){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getAdmin(id, tenant);
	}
	
	@POST
	@Path("/modificar/")
	public void modificar(DataAdministrador admin){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.modificarAdmin(admin, tenant);
	}
}
