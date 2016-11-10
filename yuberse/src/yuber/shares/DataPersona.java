package yuber.shares;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public abstract class DataPersona{
    private String id;
    private String nombre;
    private String apellido;
    private DataEmail email;
    private String clave;
    private DataTelefono telefonoContacto;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaNacimiento;
    private Boolean eliminado;
    
    public void setId(String val){
        this.id = val;
    }
    
    public void setClave(String c){
    	this.clave = c;
    }
    
    public String genClave(){
    	return this.clave;
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

    public void setEmail(DataEmail val){
        this.email = val;
    }
    
    public DataEmail getEmail(){
        return this.email;
    }

    public void setTelefonoContacto(DataTelefono val){
        this.telefonoContacto = val;
    }
    
    public DataTelefono getTelefonoContacto(){
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
}