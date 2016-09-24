package yuber.interfaces;

import java.util.List;

import yuber.shares.DataTenant;

public interface TenantLocalApi {

	List<DataTenant> list();
	List<DataTenant> list(DataTenant filter);
	DataTenant create(DataTenant tenant);

	Boolean delete(DataTenant tenant);

	Boolean deactivate(DataTenant tenant);
	Boolean activate(DataTenant tenant);
	DataTenant get(DataTenant tenant);
 

}
