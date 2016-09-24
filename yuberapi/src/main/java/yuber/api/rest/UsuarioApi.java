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
	@Path("/altausuario/")
	public DataUsuario AltaUsuario(DataUsuario usuario){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.AltaUsuario(usuario, tenant);
	}
	
}
