package yuber.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import yuber.shares.DataProveedor;
import yuber.shares.DataReporteProveedor;
import yuber.shares.DataTenant;

@Local
public interface ProveedorLocalApi {
	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataProveedor modificarProveedor(DataProveedor usu, DataTenant tenant);
	public DataProveedor getProveedor(String usu, DataTenant tenant);
	public DataProveedor crearProveedor(DataProveedor usu, DataTenant tenant);
	public void darBajaProveedor(String idProveedor, DataTenant tenant);
	public DataProveedor buscarProveedorPorMail(String mailProveedor, DataTenant tenant);
	public List<DataProveedor> reporteRatingProveedores(Integer pagina, Integer elementosPagina, Integer rating,DataTenant tenant);

	public List<DataReporteProveedor> rankingProveedoresPorGanancia(Date start, Date end, int pagina, int elementosPagina,DataTenant tenant) throws Exception;
	public DataProveedor modificarProveedor(DataProveedor proveedor, boolean b, DataTenant tenant);
}
