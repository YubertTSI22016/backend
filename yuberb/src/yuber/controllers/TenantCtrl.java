package yuber.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import yuber.exceptions.SchemaException;
import yuber.exceptions.TenantException;
import yuber.interfaces.ISchemaHandler;
import yuber.interfaces.ITenant;
import yuber.interfaces.IVertical;
import yuber.interfaces.TenantLocalApi;
import yuber.shares.DataAdministrador;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataEmail;
import yuber.shares.DataTenant; 
/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class TenantCtrl implements ITenant {
	private final static String EMAIL_SUFIX = "@yuber.org";
	private static final String ROLE_SUFIX = "admin";
	@EJB(lookup = "java:app/yuberdb/TenantSrv!yuber.interfaces.TenantLocalApi")
	TenantLocalApi srvTenant;
	@EJB(lookup = "java:app/yuberdb/SchemaHandler!yuber.interfaces.ISchemaHandler")
	ISchemaHandler srvSchemaHandler;
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IVertical")
	IVertical srvVertical;
	
	@Inject
	UserTransaction ut;
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
				ut.begin();
				DataTenant res = srvTenant.create(tenant);
				ut.commit();
				ut.begin();
				insertConfigs(tenant);
				ut.commit();
				return res;
			} else {
				throw new TenantException("Tenant Already Exist");
			}
		} catch (SchemaException e) {
			throw e;// new TenantException("Something went wrong");
		}

	}
	private void insertConfigs(DataTenant tenant){
		DataConfiguracionVertical conf =  new DataConfiguracionVertical();
		conf.setNombre(tenant.getName());
		//TODO: this must be change to be handled by an Enumerated type
		conf.setTransporte(tenant.getTenantType().toLowerCase().equals("transporte"));
		conf.setFbId(tenant.getFbId());
		conf.setFbSecret(tenant.getFbSecret());
		DataAdministrador admin =  new DataAdministrador();
		admin.setActivo(true);
		admin.setClave(tenant.getName());
		DataEmail email =  new DataEmail();
		email.setEmail(tenant.getName() + TenantCtrl.EMAIL_SUFIX);
		admin.setEmail(email);
		admin.setNombre(tenant.getName() + TenantCtrl.ROLE_SUFIX);
		srvVertical.altaAdmin(admin, tenant);
		srvVertical.crearConfiguracionVertical(conf, tenant);
	}
	public boolean delete(DataTenant tenant) { 
		try {
			ut.begin();
			Boolean l = srvTenant.delete(tenant);
			ut.commit();
			return l;
		} catch (Exception e) {
			return false;
		} 
	}

	public boolean deactivate(DataTenant tenant) { 
		try {
			ut.begin();
			Boolean l = srvTenant.deactivate(tenant);
			ut.commit();
			return l;
		} catch (Exception e) {
			return false;
		} 
	}

	public Boolean activate(DataTenant tenant) {	 
		try {
			ut.begin();
			Boolean l = srvTenant.activate(tenant);
			ut.commit();
			return l;
		} catch (Exception e) {
			return false;
		} 
	}

	public List<DataTenant> list(DataTenant filter) {
		try {
			ut.begin();
			List<DataTenant> l = srvTenant.list(filter);
			ut.commit();
			return l;
		} catch (Exception e) {
			return null;
		} 
		
	}
}
