package yuber.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.IndexColumn;

import yuber.shares.*;

@Entity
@XmlRootElement
public class Proveedor extends Persona implements Serializable {
	private static final long serialVersionUID = 1L; 
	
    private Boolean activo;
    @OneToMany
	@IndexColumn(name="LIST_INDEX")
    private List<JornadaLaboral> jornadas;
    

    public Proveedor() {}
    
    public Proveedor(String id, String nom, String ape, Email mail, List<Telefono> tels, Date fecNac, Boolean elim, List<Servicio> srv, String clave, Boolean activo, List<JornadaLaboral> jl) {
        super(id,clave, nom, ape, mail, tels, fecNac, elim, srv);
        this.activo = activo;
        this.jornadas = jl;
    }
    
    public Proveedor(DataProveedor dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	this.setApellido(dt.getApellido());
    	if(dt.getEmail() != null){
    		this.setEmail(new Email(dt.getEmail()));
    	}
    	if(dt.getTelefonosContacto() != null){
	    	List<Telefono> aux = new ArrayList<Telefono>();
	    	dt.getTelefonosContacto().stream().forEach((tel) -> {
	    		aux.add(new Telefono(tel));
	        });
	    	this.setTelefonosContacto(aux);
    	}
    	this.setFechaNacimiento(dt.getFechaNacimiento());
    	this.setEliminado(dt.getEliminado());
    	if(dt.getServicios() != null){
	    	List<Servicio> aux = new ArrayList<Servicio>();
	    	dt.getServicios().stream().forEach((srv) -> {
	    		aux.add(new Servicio(srv));
	        });
	    	this.setServicios(aux);
    	}
    	this.setClave(dt.getClave());
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
    	result.setNombre(this.getNombre());
    	result.setApellido(this.getApellido());
    	if(this.getEmail()!=null)
    		result.setEmail(this.getEmail().getDatatype());
    	if(this.getTelefonosContacto()!=null && conHijos){
	    	List<DataTelefono> aux = new ArrayList<DataTelefono>();
	    	this.getTelefonosContacto().stream().forEach((tel) -> {
	    		aux.add(tel.getDatatype());
	        });
	    	result.setTelefonosContacto(aux);
    	}
    	result.setFechaNacimiento(this.getFechaNacimiento());
    	result.setEliminado(this.getEliminado());
    	if(this.getServicios()!=null && conHijos){
	    	List<DataServicio> aux = new ArrayList<DataServicio>();
	    	this.getServicios().stream().forEach((srv) -> {
	    		aux.add(srv.getDatatype());
	        });
	    	result.setServicios(aux);
    	}
    	result.setClave(this.getClave());
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
