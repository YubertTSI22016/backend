package yuber.shares;

public class DataReporteProveedor {
	public String nombre;
	public String apellido;
	public float ganancia;
	public String proveedor;
	public int cantidad;
	public DataReporteProveedor(String nombre, String apellido, String proveedor, float ganancia, int cantidad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.proveedor = proveedor;
		this.ganancia = ganancia;
		this.cantidad = cantidad;
	}

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

	public float getGanancia() {
		return ganancia;
	}

	public void setGanancia(float ganancia) {
		this.ganancia = ganancia;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
