package yuber.shares;

public class DataReporteProveedor {
	public String nombre;
	public String apellido;
	public float ganancia;
	public String proveedor;

	public DataReporteProveedor(String nombre, String apellido, String proveedor, float ganancia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.proveedor = proveedor;
		this.ganancia = ganancia;
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

}
