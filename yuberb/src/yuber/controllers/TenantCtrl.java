package yuber.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import yuber.exceptions.SchemaException;
import yuber.exceptions.TenantException;
import yuber.interfaces.ISchemaHandler;
import yuber.interfaces.ITenant;
import yuber.interfaces.IVertical;
import yuber.interfaces.TenantLocalApi;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataTenant;

/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class TenantCtrl implements ITenant {
	@EJB(lookup = "java:app/yuberdb/TenantSrv!yuber.interfaces.TenantLocalApi")
	TenantLocalApi srvTenant;
	@EJB(lookup = "java:app/yuberdb/SchemaHandler!yuber.interfaces.ISchemaHandler")
	ISchemaHandler srvSchemaHandler;
	@EJB(lookup = "java:app/yuberdb/VerticalCtrl!yuber.interfaces.IVertical")
	IVertical srvVertical;
	public List<DataTenant> list() {
		return srvTenant.list();
	}

	public DataTenant get(DataTenant tenant) {
		return srvTenant.get(tenant);
	}

	public DataTenant create(DataTenant tenant) throws TenantException, Exception {
		DataTenant dt = srvTenant.get(tenant);
		try {
			if (dt == null) {
				srvSchemaHandler.createSchema(tenant.getName());
				DataTenant createdTenant = srvTenant.create(tenant);
				DataConfiguracionVertical conf =  new DataConfiguracionVertical();
				conf.setNombre(createdTenant.getName());
				//TODO: this must be change to be handled by an Enumerated type
				conf.setTransporte(tenant.getTenantType().toLowerCase().equals("transporte"));
				conf.setFbId(tenant.getFbId());
				conf.setFbSecret(tenant.getFbSecret());
				srvVertical.crearConfiguracionVertical(conf, createdTenant);
				
				return createdTenant;
			} else {
				throw new TenantException("Tenant Already Exist");
			}
		} catch (SchemaException e) {
			throw e;// new TenantException("Something went wrong");
		}

	}

	public boolean delete(DataTenant tenant) {
		return srvTenant.delete(tenant);
	}

	public boolean deactivate(DataTenant tenant) {
		return srvTenant.deactivate(tenant);
	}

	public Boolean activate(DataTenant tenant) {
		return srvTenant.activate(tenant);
	}

	public List<DataTenant> list(DataTenant filter) {
		return srvTenant.list(filter);
	}
}
