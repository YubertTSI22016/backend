package yuber.interfaces;


import javax.ejb.Local;

import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataTenant;


@Local
public interface ConfiguracionVerticalLocalApi {
	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant);
	public void modificarConfiguracion(DataConfiguracionVertical conf, DataTenant tenant);
	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant);
}
