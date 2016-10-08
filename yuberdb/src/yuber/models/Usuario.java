package yuber.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import yuber.shares.DataTelefono;
import yuber.shares.DataUsuario;


@Entity
@XmlRootElement
public class Usuario extends Persona implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    
    private String redSocialUsada;
    private String idRedSocial;
    

    public Usuario() {}
    
    public Usuario(String id, String nom, String ape, Email mail, List<Telefono> tels, Date fecNac, Boolean elim, List<Servicio> srv, String clave, String redSoc, String idRedsoc) {
        super(id,clave, nom, ape, mail, tels, fecNac, elim, srv);
        this.redSocialUsada = redSoc;
        this.idRedSocial = idRedsoc; 
    }
    
    public Usuario(DataUsuario dt){
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
    	this.setClave(dt.getClave());
    	this.setRedSocialUsada(dt.getRedSocialUsada());
    	this.setIdRedSocial(dt.getIdRedSocial());
    	 
    }
    
    public DataUsuario getDatatype(Boolean conHijos){
    	DataUsuario result = new DataUsuario();
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
    	result.setClave(this.getClave());
    	result.setRedSocialUsada(this.getRedSocialUsada());
    	this.setIdRedSocial(this.getIdRedSocial());
    	 
    	return result;
    }
    
   

    public void setRedSocialUsada(String val){
        this.redSocialUsada = val;
    }
    
    public String getRedSocialUsada(){
        return this.redSocialUsada;
    }

    public void setIdRedSocial(String val){
        this.idRedSocial = val;
    }
    
    public String getIdRedSocial(){
        return this.idRedSocial;
    }

   
}