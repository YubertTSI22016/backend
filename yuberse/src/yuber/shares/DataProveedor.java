package yuber.shares;

import java.util.Date;
import java.util.List;


public class DataProveedor{
	private String id;
	private DataUsuario usuario;
    private Boolean activo;
    private List<DataJornadaLaboral> jornadas;

 

    public DataProveedor() {}
    
    public DataProveedor(String id, DataUsuario usu, Boolean activo, String clv, List<DataJornadaLaboral> jor) {
    	this.id = id;
    	this.usuario = usu;
        this.activo = activo;
        this.jornadas = jor;
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
    
    public void setJornadas(List<DataJornadaLaboral> val){
        this.jornadas = val;
    }
    
    public List<DataJornadaLaboral> getJornadas(){
        return this.jornadas;
    }

}