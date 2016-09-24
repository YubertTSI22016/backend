package yuber.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import yuber.interfaces.TenantLocalApi;
import yuber.models.Tenant;
import yuber.shares.DataTenant;

/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless

public class TenantSrv implements TenantLocalApi {
	@Inject
	EntityManager em;
	private static final Log log  =  LogFactory.getLog(TenantSrv.class);
	public List<DataTenant> filter(DataTenant filtro) {
		log.info(filtro.getDomain());
		List<DataTenant> tenants = new ArrayList<DataTenant>();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Tenant.class);
		if (filtro != null) {
			if (filtro.getIsActive() != null) {
				criteria.add(Restrictions.eq("isActive", filtro.getIsActive()));
			}
			if (filtro.getIsDelete() != null) {
				criteria.add(Restrictions.eq("isDelete", filtro.getIsDelete()));
			}
			if (filtro.getName() != null) {
				criteria.add(Restrictions.eq("name", filtro.getName()));
			}
			if (filtro.getDomain() != null) {
				criteria.add(Restrictions.eq("domain", filtro.getDomain()));
			}
			if (filtro.getId() != null && !filtro.getId().isEmpty() ) {
				criteria.add(Restrictions.eq("id", filtro.getId()));
			}
		} 
		List<Tenant> list = criteria.list();
		list.stream().forEach((tenant) -> {
			tenants.add(tenant.getDatatype());
		});
		return tenants;
	}

	@Override
	public List<DataTenant> list() {
		return filter(null);
	}
	@Override
	public List<DataTenant> list(DataTenant filtro) {
		return filter(filtro);
	}
	@Override
	public DataTenant get(DataTenant filtro) {
		List<DataTenant> li = filter(filtro);
		DataTenant dt = li.size()>0 ? li.get(0) : null;
		return dt;
	}

	@Override
	public DataTenant create(DataTenant tenant) {
		Tenant realObj = new Tenant(tenant);
        em.persist(realObj);
        return realObj.getDatatype();
	}

	@Override
	public Boolean delete(DataTenant tenant) {
		Tenant realObj = new Tenant(tenant);
		realObj.setIsDelete(true);
        em.merge(realObj);
        return realObj.getIsDelete();
	}

	@Override
	public Boolean deactivate(DataTenant tenant) {
		Tenant realObj = new Tenant(tenant);
		realObj.setIsActive(false);
        em.merge(realObj);
        return realObj.getIsActive();
	}

	@Override
	public Boolean activate(DataTenant tenant) {
		Tenant realObj = new Tenant(tenant);
		realObj.setIsActive(true);
        em.merge(realObj);
        return realObj.getIsActive();
	}

}
