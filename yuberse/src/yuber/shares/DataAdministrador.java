package yuber.shares;

public class DataAdministrador {
	
	private String id;
	private String nombre;
	private String apellido;
	private DataEmail email;
	private Boolean eliminado;
	private String clave;
	private Boolean activo;
	
	public DataAdministrador(){}
	
	public DataAdministrador(String id, String nom, String ape, DataEmail mail, Boolean elim, String clv, Boolean act) {
    	this.setId(id);
        this.setNombre(nom);
        this.setApellido(ape);
        this.setEmail(mail);
        this.setEliminado(elim);
        this.setClave(clv);
        this.setActivo(act);
    }

	public void setId(String val){
        this.id = val;
	}
	
	public void setNombre(String nom){
		this.nombre = nom;
	}
	
	public void setApellido(String ape){
		this.apellido = ape;
	}

	public void setEmail(DataEmail mail){
		this.email = mail;
	}

	public void setEliminado (Boolean eli){
		this.eliminado = eli;
	}
	    
    public void setClave(String c){
    	this.clave = c;
    }
    
	public void setActivo(Boolean act){
		this.activo = act;
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
	
	public DataEmail getEmail(){
		return this.email;
	}	
	
	public Boolean getEliminado(){
		return this.eliminado;
	}
	
	public String getClave(){
		return this.clave;
	}
	
	public Boolean getActivo(){
		return this.activo;
	}
}
