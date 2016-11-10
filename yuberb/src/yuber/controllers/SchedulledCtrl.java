package yuber.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import yuber.interfaces.ITenant;
import yuber.interfaces.IVertical;
import yuber.shares.DataTenant;
@Singleton
public class SchedulledCtrl
{
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IVertical")
	IVertical ctrVertical;	
	@EJB(lookup = "java:app/yuberb/TenantCtrl!yuber.interfaces.ITenant")
	ITenant ctrTenant;
	
	private static final Log log = LogFactory.getLog(SchedulledCtrl.class);
 
	 @Schedule(second="*/3", minute="*", hour="*")
	    public void automaticTimeout() {
		 log.info("sape log papa");
		 List<DataTenant> tenants = ctrTenant.list();
			for(Integer i = 0; i < tenants.size(); i++){
				ctrVertical.checkearTiemposPedidos(tenants.get(i));
			}
	    }
	
}