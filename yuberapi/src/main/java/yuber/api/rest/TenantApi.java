package yuber.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import yuber.api.service.TenantRepo;
import yuber.exceptions.TenantException;
import yuber.shares.DataTenant;

@RequestScoped
@Path("/tenant")
@Produces("application/json")
@Consumes("application/json")
public class TenantApi {


	@EJB
	TenantRepo repo;
										
	@GET
	@Path("/list/")
	public List<DataTenant> list(){
		return repo.list();
	}
	@GET
	@Path("/find/")
	public DataTenant get(DataTenant tenant){
		return repo.get(tenant);
	}
	@POST
	@Path("/create/")
	public DataTenant create(DataTenant tenant) throws TenantException, Exception{
		return repo.create(tenant);
	}
	@POST
	@Path("/delete/")
	public boolean delete(DataTenant tenant){
		return repo.delete(tenant);
	}
	@POST
	@Path("/deactivate/")
	public boolean deactivate(DataTenant tenant){
		return repo.deactivate(tenant);
	}
	@POST
	@Path("/activate/")
	public boolean activate(DataTenant tenant){
		return repo.activate(tenant);
	}
}
