package yuber.shares;

public class DataEmail{
	private String descripcionMail;
    private String email;
    

    public DataEmail(){}
    
    public DataEmail(String desc, String em) {
    	this.descripcionMail = desc;
    	this.email = em;
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
