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

import yuber.api.service.ProveedorRepo;
import yuber.shares.DataProveedor;
import yuber.shares.DataTenant;

@RequestScoped
@Path("/proveedores/")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class ProveedorApi extends BaseApi{
	
	@EJB
	ProveedorRepo repo;
	
	@POST
	@Path("/alta/")
	public DataProveedor alta(DataProveedor proveedor){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.altaProveedor(proveedor, tenant);
	}
	
	@POST 
	@Path("/login/")
	public DataProveedor login(String data){
		
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.loginProveedor(obj.getString("usuario"), obj.getString("clave"), tenant);
	}
	
	@GET
	@Path("/listar/")
	public List<DataProveedor> listar(){
		Integer pagina =1;
		Integer elementosPagina= 1000;
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerProveedores(pagina, elementosPagina, tenant);
	}

	@GET
	@Path("/obtener/{id}")
	public DataProveedor obtener(@PathParam("id") final String id){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getProveedor(id, tenant);
	}
	
	@POST
	@Path("/modificar/")
	public void modificar(DataProveedor proveedor){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.modificarProveedor(proveedor, tenant);
	}
	
}
