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

 

    public DataProveedor() {}
    
    public DataProveedor(String id, DataUsuario usu, Boolean activo, Boolean eliminado, String clv, List<DataJornadaLaboral> jor, DataJornadaLaboral jl, Float rat) {
    	this.id = id;
    	this.usuario = usu;
        this.activo = activo;
        this.jornadas = jor;
        this.jornadaActual = jl;
        this.eliminado = false;
        this.rating = rat;
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

}