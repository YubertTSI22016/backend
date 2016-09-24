package yuber.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import yuber.exceptions.SchemaException;
import yuber.exceptions.TenantException;
import yuber.interfaces.ISchemaHandler;
import yuber.interfaces.ITenant;
import yuber.interfaces.TenantLocalApi;
import yuber.shares.DataTenant;


/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class TenantCtrl implements ITenant{
	@EJB(lookup="java:app/yuberdb/TenantSrv!yuber.interfaces.TenantLocalApi")
	TenantLocalApi srvTenant;
	@EJB(lookup="java:app/yuberdb/SchemaHandler!yuber.interfaces.ISchemaHandler")
	ISchemaHandler srvSchemaHandler;
	public List<DataTenant> list() {  
		return srvTenant.list();
	} 
	public DataTenant get(DataTenant tenant) { 
		return srvTenant.get(tenant);
	}
	public DataTenant create(DataTenant tenant) throws TenantException, Exception { 
		DataTenant dt = srvTenant.get(tenant);
		try{
			if(dt == null){
				srvSchemaHandler.createSchema(tenant.getName());
				return srvTenant.create(tenant);
			}else{
				throw new TenantException("Tenant Already Exist");
			}
		}catch(SchemaException e){
			throw e;//new TenantException("Something went wrong");
		}
		
	}

	public boolean delete(DataTenant tenant) { 
		return srvTenant.delete(tenant);
	}

	public boolean deactivate(DataTenant tenant) { 
		return srvTenant.deactivate(tenant);
	}
	@Override
	public Boolean activate(DataTenant tenant) {
		return srvTenant.activate(tenant);
	}
	@Override
	public List<DataTenant> list(DataTenant filter) {
		return srvTenant.list(filter);
	}
}
