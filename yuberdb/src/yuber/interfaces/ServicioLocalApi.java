package yuber.interfaces;

import java.util.List;

import javax.ejb.Local;

import yuber.shares.DataServicio;
import yuber.shares.DataTenant;

@Local
public interface ServicioLocalApi {
	public List<DataServicio> obtenerServicios(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarServicio(DataServicio srv, DataTenant tenant);
	public DataServicio getServicio(String id, DataTenant tenant);
	public DataServicio crearServicio(DataServicio srv, DataTenant tenant);
	public List<DataServicio> listarServiciosPorProveedor(String idUsuProv, DataTenant tenant);
	public List<DataServicio> obtenerServiciosPendientes(DataTenant tenant);
}
