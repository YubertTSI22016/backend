package yuber.api.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import yuber.api.service.ConfiguracionVerticalRepo;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataTenant;

@RequestScoped
@Path("/configuraciones/")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class ConfiguracionVerticalApi extends BaseApi{
	
	@EJB
	ConfiguracionVerticalRepo repo;
	
	@GET
	@Path("/obtener/")
	public DataConfiguracionVertical obtener(){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getConfiguracionVertical(tenant);		
	}
	
	@POST
	@Path("/modificar/")
	public void modificar(DataConfiguracionVertical conf){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.modificarConfiguracionVertical(conf, tenant);
	}
	
	@POST
	@Path("/crear/")
	public DataConfiguracionVertical crear(DataConfiguracionVertical conf){
		
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.crearConfiguracionVertical(conf, tenant);
	}
}
