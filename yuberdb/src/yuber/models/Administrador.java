package yuber.models;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import yuber.shares.DataAdministrador;
import yuber.shares.DataEmail;

@Entity
@XmlRootElement
public class Administrador extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	private Boolean activo;
	public Administrador(){}
	
	public Administrador(String id, String nom, String ape, Email mail, Boolean elim, String clave, Boolean activo){
    	super.setId(id);
        super.setNombre(nom);
        super.setApellido(ape);
        super.setEmail(mail);
        super.setEliminado(elim);
        super.setClave(clave);
		this.setActivo(activo);
	}
	
	public Administrador (DataAdministrador da){
		super.setId(da.getId());
		super.setNombre(da.getNombre());
		super.setApellido(da.getApellido());
		if (da.getEmail() != null){
			super.setEmail(new Email (da.getEmail()));
		}
		super.setEliminado(da.getEliminado());
		super.setClave(da.getClave());
		this.setActivo(da.getActivo());
	}
	
	public DataAdministrador getDatatype(){
		
		DataAdministrador result = new DataAdministrador();
		result.setId(super.getId());
		result.setNombre(super.getNombre());
		result.setApellido(super.getApellido());
		if (result.getEmail() != null){
			result.setEmail(super.getEmail().getDatatype());
		}
		result.setClave(super.getClave());
		result.setEliminado(super.getEliminado());
		result.setActivo(this.getActivo());
		
		return result;
	}
	
	public void setActivo(Boolean activo){
		this.activo= activo;
	}

	public Boolean getActivo(){
		return this.activo;
	}
}
