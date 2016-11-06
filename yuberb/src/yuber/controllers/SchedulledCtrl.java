package yuber.controllers;

import java.util.List;

import javax.ejb.EJB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import yuber.interfaces.ITenant;
import yuber.interfaces.IVertical;
import yuber.shares.DataTenant;

public class SchedulledCtrl implements Job
{
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IVertical")
	IVertical ctrVertical;	
	@EJB(lookup = "java:app/yuberb/TenantCtrl!yuber.interfaces.ITenant")
	ITenant ctrTenant;
	
	private static final Log log = LogFactory.getLog(SchedulledCtrl.class);
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		log.info("================================================= ENCARO Trigger =================================================");
		
		List<DataTenant> tenants = ctrTenant.list();
		for(Integer i = 0; i < tenants.size(); i++){
			ctrVertical.checkearTiemposPedidos(tenants.get(i));
		}
	}
	
}