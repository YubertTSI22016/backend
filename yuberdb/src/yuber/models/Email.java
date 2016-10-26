package yuber.models;
import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import yuber.shares.DataEmail;


@Embeddable
@Access(AccessType.FIELD)
@XmlRootElement
public class Email implements Serializable{
    private static final long serialVersionUID = 1L;
    private String descripcionMail;
    private String email;
    public Email() {
        descripcionMail = "";
    	email = "";
    }
    
    public Email(DataEmail dt){
    	this.descripcionMail = dt.getDescripcionMail();
    	this.email = dt.getEmail();
    }
    
    public DataEmail getDatatype(){
       	DataEmail result = new DataEmail();
       	result.setDescripcionMail(this.descripcionMail);
    	result.setEmail(this.email);
    	return result;
    }
    
    public void setDescripcionMail(String val){
        this.descripcionMail = val;
    }
    
    public String getDescripcionMail(){
        return this.descripcionMail;
    }
    
    public void setEmail(String val){
        this.email = val;
    }
    
    public String getEmail(){
        return this.email;
    }
}