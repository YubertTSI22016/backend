package yuber.shares;

import java.util.Date;
import java.util.List;


public class DataProveedor{
	private String id;
	private DataUsuario usuario;
    private Boolean activo;
    private Boolean eliminado;
    private List<DataJornadaLaboral> jornadas;
    private DataJornadaLaboral jornadaActual;
    private Float rating;
    private Integer cantidadServicios;
    private DataTelefono telefono;
    private String descripcion;
    private String nombre;
    private String cuentaBancaria;

 

    public DataProveedor() {}
    
    public DataProveedor(String id, DataUsuario usu, Boolean activo, Boolean eliminado, String clv, List<DataJornadaLaboral> jor, DataJornadaLaboral jl, Float rat, DataTelefono tel, String desc, String nom, String cba, Integer cs) {
    	this.id = id;
    	this.usuario = usu;
        this.activo = activo;
        this.jornadas = jor;
        this.jornadaActual = jl;
        this.eliminado = false;
        this.rating = rat;
        this.telefono = tel;
        this.descripcion = desc;
        this.nombre = nom;
        this.cuentaBancaria = cba;
        this.cantidadServicios = cs;
    }

    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setUsuario(DataUsuario val){
        this.usuario = val;
    }
    
    public DataUsuario getUsuario(){
        return this.usuario;
    }
    
    public void setActivo(Boolean val){
        this.activo = val;
    }
    
    public Boolean getActivo(){
        return this.activo;
    }
    
    public void setEliminado(Boolean val){
        this.eliminado = val;
    }
    
    public Boolean getEliminado(){
        return this.eliminado;
    }
    
    public void setJornadas(List<DataJornadaLaboral> val){
        this.jornadas = val;
    }
    
    public List<DataJornadaLaboral> getJornadas(){
        return this.jornadas;
    }
    
    public void setJornadaActual(DataJornadaLaboral val){
        this.jornadaActual = val;
    }
    
    public DataJornadaLaboral getJornadaActual(){
        return this.jornadaActual;
    }
    
    public void setRating(Float val){
        this.rating = val;
    }
    
    public Float getRating(){
        return this.rating;
    }
    
    public DataTelefono getTelefono(){
        return this.telefono;
    }

    public void setTelefono(DataTelefono val){
        this.telefono = val;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String val){
        this.descripcion = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getCuentaBancaria(){
        return this.cuentaBancaria;
    }

    public void setCuentaBancaria(String val){
        this.cuentaBancaria = val;
    }
    
    public void setCantidadServicios(Integer val){
    	this.cantidadServicios= val;
    }
    
    public Integer getCantidadServicios(){
    	return this.cantidadServicios;
    }

}