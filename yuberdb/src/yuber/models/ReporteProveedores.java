package yuber.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import yuber.shares.DataReporteProveedor;
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
	public String proveedor;
	public Date fecha;
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

	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String idProveedor) {
		this.proveedor = idProveedor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString(){
		return this.ganancia + ":" + this.proveedor + ":" + this.nombre;
	}
	
	public DataReporteProveedor toData(){
		DataReporteProveedor data = new DataReporteProveedor(nombre, apellido, proveedor, ganancia);
		return data;
	}
}
