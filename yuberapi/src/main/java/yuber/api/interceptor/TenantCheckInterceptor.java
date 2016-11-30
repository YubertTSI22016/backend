package yuber.api.interceptor;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;

import yuber.api.service.TenantRepo;
import yuber.shares.DataTenant;

@Provider
@TenantChecked
public class TenantCheckInterceptor implements javax.ws.rs.container.ContainerRequestFilter {

	@EJB
	TenantRepo repo;
	String rs;

	@Override
	public void filter(ContainerRequestContext requestContext) {

		String id = requestContext.getHeaderString("lcbs-tenant");
		// String host = requestContext.getHeaderString("host");
		String referer = requestContext.getHeaderString("Referer");
		referer = referer == null ? requestContext.getHeaderString("X-Forwarded-Host") : referer;
		String host = null;
		if (referer != null) {
			String[] sp = referer.replaceAll("http://", "").split("/");
			host = sp.length > 0 ? sp[0] : null;
		}
		requestContext.getHeaders().keySet().forEach((key) -> {
			rs += key + ":" + requestContext.getHeaders().get(key) + "/";
		});
		DataTenant filter = new DataTenant();
		if (id != null && !id.isEmpty()) {
			filter.setId(id);
		} else {
//			if (host == null) {
//				throw new ForbiddenException("Servicio no disponible: " + host + " / " + rs);
//			}
			filter.setDomain(host);
		}
		filter.setIsActive(true);
		filter.setIsDelete(false);

		DataTenant tenant = repo.get(filter);

		requestContext.setProperty("tenant", tenant);

	}

}
