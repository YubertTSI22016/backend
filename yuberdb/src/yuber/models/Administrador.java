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
public class Administrador implements Serializable{
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String nombre;
	private String apellido;
    @Embedded
	private Email email;
	private Boolean eliminado;
	private String clave;
	private Boolean activo;
		
	public Administrador(){}
	
	public Administrador(String id, String nom, String ape, Email mail, Boolean elim, String clave, Boolean activo){
    	this.id = id;
        this.nombre = nom;
        this.apellido = ape;
        this.email = mail;
        this.eliminado = elim;
        this.clave = clave;
		this.activo = activo;
	}
	
	public Administrador (DataAdministrador da){
		this.setId(da.getId());
		this.setNombre(da.getNombre());
		this.setApellido(da.getApellido());
		if (da.getEmail() != null){
			this.setEmail(new Email (da.getEmail()));
		}
		this.setEliminado(da.getEliminado());
		this.setClave(da.getClave());
		this.setActivo(da.getActivo());
	}
	
	public DataAdministrador getDatatype(){
		
		DataAdministrador result = new DataAdministrador();
		result.setId(this.getId());
		result.setNombre(this.getNombre());
		result.setApellido(this.getApellido());
		if (result.getEmail() != null){
			result.setEmail(this.getEmail().getDatatype());
		}
		result.setClave(this.getClave());
		result.setEliminado(this.getEliminado());
		result.setActivo(this.getActivo());
		
		return result;
	}

	public void setId(String id){
		this.id= id;
	}
	
	public void setNombre(String nombre){
		this.nombre= nombre;
	}
	
	public void setApellido(String apellido){
		this.apellido= apellido;
	}
	
	public void setEmail(Email mail){
		this.email= mail;
	}
	
	public void setClave(String clave){
		this.clave= clave;
	}
	
	public void setEliminado(Boolean elim){
		this.eliminado= elim;
	}
	
	public void setActivo(Boolean activo){
		this.activo= activo;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellido(){
		return this.apellido;
	}
	
	public Email getEmail(){
		return this.email;
	}
	
	public String getClave(){
		return this.clave;
	}
	
	public Boolean getEliminado(){
		return this.eliminado;
	}
	
	public Boolean getActivo(){
		return this.activo;
	}
}
