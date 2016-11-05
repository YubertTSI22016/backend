package yuber.interfaces;

import java.util.List;
import javax.ejb.Local;
import yuber.shares.DataPagosProveedor;
import yuber.shares.DataTenant;

@Local
public interface PagosProveedorLocalApi {
	public List<DataPagosProveedor> obtenerPagosProveedor(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarPagosProveedor(DataPagosProveedor srv, DataTenant tenant);
	public void cambiarEstadoAPago(String idProveedor, DataTenant tenant);
	public DataPagosProveedor getPagosProveedor(String id, DataTenant tenant);
	public DataPagosProveedor crearPagosProveedor(DataPagosProveedor srv, DataTenant tenant);
	public List<DataPagosProveedor> listarPagosPendientes(String idProveedor, Integer pagina, Integer elementosPagina, DataTenant tenant);
}
