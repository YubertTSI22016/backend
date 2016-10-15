package yuber.interfaces;

import java.util.List;

import yuber.shares.DataProveedor;
import yuber.shares.DataTenant;

public interface IProveedor {
	
	public DataProveedor altaProveedor (DataProveedor proveedor, DataTenant tenant);
	
	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant);
	
	public DataProveedor loginProveedor (String usuario, String clave, DataTenant tenant);
	
	public DataProveedor getProveedor (String id, DataTenant tenant);
	
	public void modificarProveedor (DataProveedor proveedor, DataTenant tenant);

}
