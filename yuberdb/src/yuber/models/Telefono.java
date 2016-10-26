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
    
    private String descripcionTel;
    private String telefono;
    
    public Telefono() {}
    
    public Telefono(DataTelefono dt){
    	this.setDescripcionTel(dt.getDescripcionTel());
    	this.setTelefono(dt.getTelefono());
    }
    
    public DataTelefono getDatatype(){
    	DataTelefono result = new DataTelefono();
    	result.setDescripcionTel(this.getDescripcionTel());
    	result.setTelefono(this.getTelefono());
    	return result;
    }
    
    public void setDescripcionTel(String val){
        this.descripcionTel = val;
    }
    
    public String getDescripcionTel(){
        return this.descripcionTel;
    }    
  
    public void setTelefono(String val){
        this.telefono = val;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
}