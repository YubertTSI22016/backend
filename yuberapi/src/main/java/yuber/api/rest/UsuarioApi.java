package yuber.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import yuber.api.service.UsuarioRepo;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;


@RequestScoped
@Path("/usuarios/")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class UsuarioApi extends BaseApi{																														
	
	@EJB
	UsuarioRepo repo;
										
	@POST
	@Path("/alta/")
	public DataUsuario alta(DataUsuario usuario){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.AltaUsuario(usuario, tenant);
	}
	
	@POST
	@Path("/login/")
	public DataUsuario login(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.loginUsuario(obj.getString("usuario"), obj.getString("clave"), tenant);
	}
	
	@GET	
	@Path("/listar/")
	public List<DataUsuario> listar(){
		Integer pagina = 1;
		Integer elementos = 1000;
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerUsuarios(pagina, elementos, tenant);
	}
	
	@GET	
	@Path("/obtener/{id}")
	public DataUsuario obtener(@PathParam("id") final String id){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getUsuario(id, tenant);
	}
	
}
