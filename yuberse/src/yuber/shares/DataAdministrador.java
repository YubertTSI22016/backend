package yuber.shares;

public class DataAdministrador extends DataPersona{
	
	private Boolean activo;
	
	public DataAdministrador(){}
	
	public DataAdministrador(String id, String nom, String ape, DataEmail mail, Boolean elim, String clv, Boolean act) {
    	super.setId(id);
    	super.setNombre(nom);
    	super.setApellido(ape);
    	super.setEmail(mail);
    	super.setEliminado(elim);
    	super.setClave(clv);
        this.setActivo(act);
    }
	
	public void setActivo(Boolean act){
		this.activo = act;
	}
	
	public Boolean getActivo(){
		return this.activo;
	}
}
