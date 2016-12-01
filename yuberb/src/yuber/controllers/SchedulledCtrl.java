package yuber.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import yuber.interfaces.ITenant;
import yuber.interfaces.IVertical;
import yuber.shares.DataTenant;
@Singleton
@TransactionManagement(value=TransactionManagementType.BEAN)
public class SchedulledCtrl
{
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IVertical")
	IVertical ctrVertical;	
	@EJB(lookup = "java:app/yuberb/TenantCtrl!yuber.interfaces.ITenant")
	ITenant ctrTenant;
	@Inject
	UserTransaction ut;
	private static final Log log = LogFactory.getLog(SchedulledCtrl.class);
 
	 @Schedule(second="*/50", minute="*", hour="*")
	    public void automaticTimeout() {
		 List<DataTenant> tenants = ctrTenant.list();
			for(Integer i = 0; i < tenants.size(); i++){
				try {
					ut.begin();
					ctrVertical.checkearTiemposPedidos(tenants.get(i));
					ut.commit();
				} catch (NotSupportedException | SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RollbackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HeuristicMixedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HeuristicRollbackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	    }
	
}