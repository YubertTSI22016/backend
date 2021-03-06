package yuber.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

@Entity
@XmlRootElement
public abstract class Persona implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String apellido;
    private String clave;
    @Embedded
    private Email email;
    @Embedded
    private Telefono telefonoContacto;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private Boolean eliminado;
     

    public Persona() {}
    
    public Persona(String id, String clave, String nom, String ape, Email mail, Telefono tel, Date fecNac, Boolean elim) {
        this.id = id;
        this.clave = clave;
        this.nombre = nom;
        this.apellido = ape;
        this.email = mail;
        this.telefonoContacto = tel;
        this.fechaNacimiento = fecNac;
        this.eliminado = elim;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setApellido(String val){
        this.apellido = val;
    }
    
    public String getApellido(){
        return this.apellido;
    }

    public void setEmail(Email val){
        this.email = val;
    }
    
    public Email getEmail(){
        return this.email;
    }

    public void setTelefonoContacto(Telefono val){
        this.telefonoContacto = val;
    }
    
    public Telefono getTelefonoContacto(){
        return this.telefonoContacto;
    }

    public void setFechaNacimiento(Date val){
        this.fechaNacimiento = val;
    }
    
    public Date getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    
    public void setEliminado(Boolean val){
    	this.eliminado = val;
    }
    
    public Boolean getEliminado(){
    	return this.eliminado;
    }
    
    public String getClave(){
    	return this.clave;
    }
    
    public void setClave(String val){
    	this.clave = val;
    }
}