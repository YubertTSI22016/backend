package yuber.interceptors;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import yuber.shares.DataTenant;
@Interceptor
public class TenantIntercept {

	private static final Log log = LogFactory.getLog(TenantIntercept.class);
	@Inject
    EntityManager em;
	@AroundInvoke
	public Object mdbInterceptor(InvocationContext ctx) throws Exception
	{ 

    	log.info(":::::::::::INTERCEPTING EM:::::::::::::");
		int l = ctx.getParameters().length;
		DataTenant tenant = (DataTenant) ctx.getParameters()[l - 1];
		log.debug(tenant.getName());
		em.createNativeQuery("SET SCHEMA '"+ tenant.getName()+"'").executeUpdate();
		em.createNativeQuery("SET search_path TO "+ tenant.getName()).executeUpdate();
		
	   return ctx.proceed();
	}
		
}
