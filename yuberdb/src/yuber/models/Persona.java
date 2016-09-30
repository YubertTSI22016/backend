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
    private String nombrePila;
    private String apellido;
    private String clave;
    @Embedded
    private Email email;
    @Embedded
    @ElementCollection
    @IndexColumn(name="LIST_INDEX")
    private List<Telefono> telefonosContacto;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private Boolean eliminado;
    @OneToMany
	@IndexColumn(name="LIST_INDEX")
    private List<Servicio> servicios;
     

    public Persona() {}
    
    public Persona(String id, String clave, String nm, String ape, Email mail, List<Telefono> tels, Date fecNac, Boolean elim, List<Servicio> srv) {
        this.id = id;
        this.clave = clave;
        this.nombrePila = nm;
        this.apellido = ape;
        this.email = mail;
        this.telefonosContacto = tels;
        this.fechaNacimiento = fecNac;
        this.eliminado = elim;
        this.servicios = srv;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setNombrePila(String val){
        this.nombrePila = val;
    }
    
    public String getNombrePila(){
        return this.nombrePila;
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

    public void setTelefonosContacto(List<Telefono> val){
        this.telefonosContacto = val;
    }
    
    public List<Telefono> getTelefonosContacto(){
        return this.telefonosContacto;
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
    
    public List<Servicio> getServicios(){
    	return this.servicios;
    }
    
    public void setServicios(List<Servicio> val){
    	this.servicios = val;
    }
}