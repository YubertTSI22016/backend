package yuber.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

import yuber.shares.*;

@Entity
@XmlRootElement
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L; 
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
    private Boolean activo;
    @OneToMany
	@IndexColumn(name="LIST_INDEX")
    private List<JornadaLaboral> jornadas;
    @OneToOne
    private Usuario usuario;
    

    public Proveedor() {}
    
    public Proveedor(Usuario usu, Boolean activo, List<JornadaLaboral> jl) {
    	this.id = id;
    	this.usuario = usu;
        this.activo = activo;
        this.jornadas = jl;
    }
    
    public Proveedor(DataProveedor dt){
    	this.setId(dt.getId());
    	this.setUsuario(new Usuario(dt.getUsuario()));
    	this.setActivo(dt.getActivo());
    	if(dt.getJornadas() != null){
	    	List<JornadaLaboral> aux = new ArrayList<JornadaLaboral>();
	    	dt.getJornadas().stream().forEach((jor) -> {
	    		aux.add(new JornadaLaboral(jor));
	        });
	    	this.setJornadas(aux);
    	}
    	
    	 
    }
    
    public DataProveedor getDatatype(Boolean conHijos){
    	DataProveedor result = new DataProveedor();
    	result.setId(this.getId());
    	if(this.getUsuario() != null)
    		result.setUsuario(this.getUsuario().getDatatype(true));
    	result.setActivo(this.getActivo());
    	if(this.getJornadas() != null){
	    	List<DataJornadaLaboral> aux = new ArrayList<DataJornadaLaboral>();
	    	this.getJornadas().stream().forEach((jor) -> {
	    		aux.add(jor.getDatatype());
	        });
	    	result.setJornadas(aux);
    	}
    	 
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setUsuario(Usuario val){
        this.usuario = val;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }

    public void setActivo(Boolean val){
        this.activo = val;
    }
    
    public Boolean getActivo(){
        return this.activo;
    }
    
    public void setJornadas(List<JornadaLaboral> val){
        this.jornadas = val;
    }
    
    public List<JornadaLaboral> getJornadas(){
        return this.jornadas;
    }
}
