package yuber.interfaces;

import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataTenant;

public interface IConfiguracionVertical {
	
	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant);	
	public void modificarConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant);
	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant);

}
