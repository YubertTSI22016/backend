package yuber.api.rest;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import yuber.api.interceptor.TenantChecked;
import yuber.api.service.TenantRepo;
@TenantChecked
public class BaseApi {	
	@Context
	HttpServletRequest request;
	@EJB
	TenantRepo repo;
	 
	
}
