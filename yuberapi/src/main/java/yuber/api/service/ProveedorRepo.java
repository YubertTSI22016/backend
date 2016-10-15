package yuber.api.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import yuber.interfaces.IProveedor;
import yuber.shares.DataProveedor;
import yuber.shares.DataTenant;

@Stateless
@Remote
public class ProveedorRepo{

	@EJB(lookup = "java:app/yuberb/ProveedorCtrl!yuber.interfaces.IProveedor")
	IProveedor ctrProveedor;

	public DataProveedor altaProveedor(DataProveedor proveedor, DataTenant tenant) {
		return ctrProveedor.altaProveedor(proveedor, tenant);
	}

	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrProveedor.obtenerProveedores(pagina, elementosPagina, tenant);
	}

	public DataProveedor loginProveedor(String usuario, String clave, DataTenant tenant) {
		return ctrProveedor.loginProveedor(usuario, clave, tenant);
	}
	
	public DataProveedor getProveedor(String id, DataTenant tenant) {
		return ctrProveedor.getProveedor(id, tenant);
	}
	
	public void modificarProveedor(DataProveedor proveedor, DataTenant tenant){
		ctrProveedor.modificarProveedor(proveedor, tenant);
	}

}
