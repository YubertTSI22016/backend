package yuber.models;
import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import yuber.shares.DataTelefono;


@Embeddable
@Access(AccessType.FIELD)
@XmlRootElement
public class Telefono implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String descripcion;
    private String telefono;
    
    public Telefono() {}
    
    public Telefono(DataTelefono dt){
    	this.setDescripcion(dt.getDescripcion());
    	this.setTelefono(dt.getTelefono());
    }
    
    public DataTelefono getDatatype(){
    	DataTelefono result = new DataTelefono();
    	result.setDescripcion(this.getDescripcion());
    	result.setTelefono(this.getTelefono());
    	return result;
    }
    
    public void setDescripcion(String val){
        this.descripcion = val;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }    
  
    public void setTelefono(String val){
        this.telefono = val;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
}