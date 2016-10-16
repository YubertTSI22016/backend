package yuber.controllers;

import javax.ejb.Stateless;
import javax.ejb.EJB;

import yuber.interfaces.IConfiguracionVertical;
import yuber.interfaces.ConfiguracionVerticalLocalApi;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataTenant;

@Stateless
public class ConfiguracionVerticalCtrl implements IConfiguracionVertical{
	@EJB(lookup = "java:app/yuberdb/ConfiguracionVerticalSrv!yuber.interfaces.ConfiguracionVerticalLocalApi")
	ConfiguracionVerticalLocalApi srvConfiguracionVertical;
	
	@Override
	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant){
		
		return srvConfiguracionVertical.getConfiguracionVertical(tenant);
	}
	
	@Override
	public void modificarConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		
		srvConfiguracionVertical.modificarConfiguracion(conf, tenant);
	}
	
	@Override
	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
		
		
		return srvConfiguracionVertical.crearConfiguracionVertical(conf, tenant);
	}
	
}
