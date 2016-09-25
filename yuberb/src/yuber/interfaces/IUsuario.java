package yuber.interfaces;

import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;
public interface IUsuario {
 
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant);

	public String get();
	 
	
}
