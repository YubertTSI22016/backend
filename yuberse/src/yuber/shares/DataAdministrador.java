package yuber.shares;

import java.util.Date;
import java.util.List;

public class DataAdministrador extends DataPersona{
	private Boolean activo;
	
	public DataAdministrador(){}
	
	public DataAdministrador(String id, String nom, String ape, DataEmail mail, List<DataTelefono> tels, Date fecNac, Boolean elim, List<DataServicio> srv, String clv, Boolean act) {
    	super.setId(id);
        super.setNombre(nom);
        super.setApellido(ape);
        super.setEmail(mail);
        super.setTelefonosContacto(tels);
        super.setFechaNacimiento(fecNac);
        super.setEliminado(elim);
        super.setClave(clv);
        super.setServicios(srv);
        this.setActivo(act);
    }
	
	public void setActivo(Boolean act){
		this.activo = act;
	}
	
	public Boolean getActivo(){
		return this.activo;
	}
}
