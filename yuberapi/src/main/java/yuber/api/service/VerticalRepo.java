package yuber.api.service;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import yuber.interfaces.IVertical;
import yuber.shares.DataAdministrador;
import yuber.shares.DataConfiguracionVertical;
import yuber.shares.DataPagosProveedor;
import yuber.shares.DataProveedor;
import yuber.shares.DataReporteProveedor;
import yuber.shares.DataServicio;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;
import yuber.shares.DataVerticalReport;

@Stateless
@Remote
public class VerticalRepo {
	@EJB(lookup = "java:app/yuberb/VerticalCtrl!yuber.interfaces.IVertical")
	IVertical ctrVertical;

	// USUARIO
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) {
		return ctrVertical.AltaUsuario(usuario, tenant);
	}

	public List<DataUsuario> rankingUsuariosActivos(Date from, Integer pagina, Integer elementosPagina,
			DataTenant tenant) {
		return ctrVertical.rankingUsuariosActivos(from, pagina, elementosPagina, tenant);
	}

	public List<DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrVertical.obtenerUsuarios(pagina, elementosPagina, tenant);
	}

	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant) {
		return ctrVertical.loginUsuario(usuario, clave, tenant);
	}

	public DataUsuario getUsuario(String id, DataTenant tenant) {
		return ctrVertical.getUsuario(id, tenant);
	}

	public DataUsuario modificarUsuario(DataUsuario usuario, DataTenant tenant) {
		return ctrVertical.modificarUsuario(usuario, tenant);
	}

	// PROVEEDOR
	public DataProveedor altaProveedor(DataProveedor proveedor, DataTenant tenant) {
		return ctrVertical.altaProveedor(proveedor, tenant);
	}

	public List<DataProveedor> obtenerProveedores(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrVertical.obtenerProveedores(pagina, elementosPagina, tenant);
	}

	public DataProveedor getProveedor(String id, DataTenant tenant) {
		return ctrVertical.getProveedor(id, tenant);
	}

	public void modificarProveedor(DataProveedor proveedor, DataTenant tenant) {
		ctrVertical.modificarProveedor(proveedor, tenant);
	}

	// ADMINISTRADOR
	public DataAdministrador altaAdmin(DataAdministrador admin, DataTenant tenant) {
		return ctrVertical.altaAdmin(admin, tenant);
	}

	public List<DataAdministrador> obtenerAdmins(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return ctrVertical.obtenerAdmins(pagina, elementosPagina, tenant);
	}

	public DataAdministrador loginAdmin(String admin, String clave, DataTenant tenant) {
		return ctrVertical.loginAdmin(admin, clave, tenant);
	}

	public DataAdministrador getAdmin(String id, DataTenant tenant) {
		return ctrVertical.getAdmin(id, tenant);
	}

	public void modificarAdmin(DataAdministrador admin, DataTenant tenant) {
		ctrVertical.modificarAdmin(admin, tenant);
	}

	// CONFIGURACION VERTICAL
	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant) {
		return ctrVertical.getConfiguracionVertical(tenant);
	}

	public void modificarConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant) {
		ctrVertical.modificarConfiguracionVertical(conf, tenant);
	}

	public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant) {
		return ctrVertical.crearConfiguracionVertical(conf, tenant);
	}

	public DataServicio pedirServicio(String idUsuario, String ubicacion, String destino, String descripcion,
			DataTenant tenant) {
		return ctrVertical.pedirServicio(idUsuario, ubicacion, destino, descripcion, tenant);
	}

	public DataServicio ofrecerServicio(String idServicio, String idProveedor, DataTenant tenant) {
		return ctrVertical.ofrecerServicio(idServicio, idProveedor, tenant);
	}

	public List<DataProveedor> reporteRatingProveedores(Integer pagina, Integer elementosPagina, Integer rating,
			DataTenant tenant) {
		return ctrVertical.reporteRatingProveedores(pagina, elementosPagina, rating, tenant);
	}

	public List<DataReporteProveedor> rankingProveedoresPorGanancia(Date start, Date end, int pagina,
			int elementosPagina, DataTenant tenant) {
		try {
			return ctrVertical.rankingProveedoresPorGanancia(start, end, pagina, elementosPagina, tenant);
		} catch (Exception e) {
			return null;
		}
	}

	public List<DataReporteProveedor> rankingUsuariosPorConsumo(Date start, Date end, Integer pagina,
			Integer elementosPagina, DataTenant tenant) {
		try {
			return ctrVertical.rankingUsuariosPorConsumo(start, end, pagina, elementosPagina, tenant);
		} catch (Exception e) {
			return null;
		}
	}

	public List<DataReporteProveedor> rankingUsuariosPorGanancia(Date start, Date end, int pagina, int elementosPagina,
			DataTenant tenant) {
		return ctrVertical.rankingUsuariosPorGanancia(start, end, pagina, elementosPagina, tenant);

	}

	public DataUsuario loginAltaUsuarioFacebook(String email, String nombre, String uid, DataTenant tenant) {
		return ctrVertical.loginAltaUsuarioFacebook(email, nombre, uid, tenant);
	}

	public void calificarServicio(String idServicio, Float calificacion, String comentario, DataTenant tenant) {
		ctrVertical.calificarServicio(idServicio, calificacion, comentario, tenant);
	}

	public DataServicio finalizarServicio(String idServicio, Float calificacionUsuario, DataTenant tenant) {
		return ctrVertical.finalizarServicio(idServicio, calificacionUsuario, tenant);
	}

	public DataServicio finalizarTransporte(String idServicio, Float distanciaTotal, Float calificacionUsuario,
			DataTenant tenant) {
		return ctrVertical.finalizarTransporte(idServicio, distanciaTotal, calificacionUsuario, tenant);
	}

	public void guardarTokenUsuario(String idUsuario, String token, Integer ultimosDigitosTarjeta, DataTenant tenant) {
		ctrVertical.guardarTokenUsuario(idUsuario, token, ultimosDigitosTarjeta, tenant);
	}

	public void eliminarTokenUsuario(String idUsuario, DataTenant tenant) {
		ctrVertical.eliminarTokenUsuario(idUsuario, tenant);
	}

	public void guardarTokenProveedor(String idProveedor, String token, Integer ultimosDigitosTarjeta,
			DataTenant tenant) {
		ctrVertical.guardarTokenProveedor(idProveedor, token, ultimosDigitosTarjeta, tenant);
	}

	public void eliminarTokenProveedor(String idProveedor, DataTenant tenant) {
		ctrVertical.eliminarTokenProveedor(idProveedor, tenant);
	}

	public void cargarTarjeta(String idUsuario, Float carga, DataTenant tenant) {
		ctrVertical.cargarTarjeta(idUsuario, carga, tenant);
	}

	public DataServicio cancelarServicio(String idServicio, DataTenant tenant) {
		return ctrVertical.cancelarServicio(idServicio, tenant);
	}

	public DataProveedor iniciarJornadaLaboral(String idProveedor, DataTenant tenant) {
		return ctrVertical.iniciarJornadaLaboral(idProveedor, tenant);
	}

	public DataProveedor finalizarJornadaLaboral(String idProveedor, DataTenant tenant) {
		return ctrVertical.finalizarJornadaLaboral(idProveedor, tenant);
	}

	public DataServicio obtenerServicio(String idServicio, DataTenant tenant) {
		return ctrVertical.obtenerServicio(idServicio, tenant);
	}

	public List<DataServicio> listarServicios(String idUsuProv, DataTenant tenant) {
		return ctrVertical.listarServicios(idUsuProv, tenant);
	}

	public DataServicio iniciarServicio(String idServicio, DataTenant tenant) {
		return ctrVertical.iniciarServicio(idServicio, tenant);
	}

	public List<DataPagosProveedor> listarPagosPendientes(String idProveedor, Integer pagina, Integer elementosPagina,
			DataTenant tenant) {
		return ctrVertical.listarPagosPendientes(idProveedor, pagina, elementosPagina, tenant);
	}

	public void ingresarPuntoRecorrido(String idServicio, String punto, DataTenant tenant) {
		ctrVertical.ingresarPuntoRecorrido(idServicio, punto, tenant);
	}

	public List<String> obtenerPuntosServicio(String idServicio, DataTenant tenant) {
		return ctrVertical.obtenerPuntosServicio(idServicio, tenant);
	}

	public void pagoAProveedor(String idProveedor, DataTenant tenant) {
		ctrVertical.pagoAProveedor(idProveedor, tenant);
	}

	public List<DataVerticalReport> getReport(Date start, DataTenant tenant) {
		return ctrVertical.getReport(start, tenant);
	}

}