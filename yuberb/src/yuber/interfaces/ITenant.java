package yuber.interfaces;

import java.util.List;

import javax.ejb.Local;

import yuber.exceptions.TenantException;
import yuber.shares.DataTenant;
@Local
public interface ITenant {
	
	public List<DataTenant> list();	
	public List<DataTenant> list(DataTenant filter);

	public DataTenant create(DataTenant tenant) throws TenantException, Exception; 
	public boolean delete(DataTenant tenant); 
	public boolean deactivate(DataTenant tenant);  
	Boolean activate(DataTenant tenant);
	public DataTenant get(DataTenant tenant);
}
