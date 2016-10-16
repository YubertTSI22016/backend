package yuber.api.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import yuber.interfaces.IConfiguracionVertical;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataTenant;


@Stateless
@Remote
public class ConfiguracionVerticalRepo {

	@EJB(lookup = "java:app/yuberb/ConfiguracionVerticalCtrl!yuber.interfaces.IConfiguracionVertical")
	IConfiguracionVertical ctrConfiguracionVertical;
	
	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant){
		return ctrConfiguracionVertical.getConfiguracionVertical(tenant);
	}
	
	public void modificarConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		ctrConfiguracionVertical.modificarConfiguracionVertical(conf, tenant);
	}
	
	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		return ctrConfiguracionVertical.crearConfiguracionVertical(conf, tenant);
	}
}
