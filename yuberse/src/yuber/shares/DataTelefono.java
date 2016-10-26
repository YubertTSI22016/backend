package yuber.shares;

public class DataTelefono{
    
	private String descripcionTel;
	private String telefono;
    
 

    public DataTelefono() {}
    
    public DataTelefono(String tel){
    	this.descripcionTel = "Contacto";
    	this.telefono = tel;
    }
    
    public DataTelefono(String desc, String tel) {
    	this.descripcionTel = desc;
    	this.telefono = tel;
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