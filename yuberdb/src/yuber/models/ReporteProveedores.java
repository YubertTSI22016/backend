package yuber.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReporteProveedores implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String nombre;
	public String apellido;
	public String id;
	public float ganancia;
	public String idProveedor;
	public String fecha;
	public ReporteProveedores(){}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getGanancia() {
		return ganancia;
	}
	public void setGanancia(float ganancia) {
		this.ganancia = ganancia;
	}

	public String getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
