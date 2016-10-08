package yuber.shares;

import java.util.Date;
import java.util.List;

public class DataUsuario extends DataPersona{
    private String redSocialUsada;
    private String idRedSocial; 

 

    public DataUsuario() {}
    
    public DataUsuario(String id, String nom, String ape, DataEmail mail, List<DataTelefono> tels, Date fecNac, Boolean elim, List<DataServicio> srv, String nomMos, String redSoc, String idRedsoc, String clv) {
    	super.setId(id);
        super.setNombre(nom);
        super.setApellido(ape);
        super.setEmail(mail);
        super.setTelefonosContacto(tels);
        super.setFechaNacimiento(fecNac);
        super.setEliminado(elim);
        super.setClave(clv);
        super.setServicios(srv);
        this.redSocialUsada = redSoc;
        this.idRedSocial = idRedsoc; 
    }

    public void setRedSocialUsada(String val){
        this.redSocialUsada = val;
    }
    
    public String getRedSocialUsada(){
        return this.redSocialUsada;
    }

    public void setIdRedSocial(String val){
        this.idRedSocial = val;
    }
    
    public String getIdRedSocial(){
        return this.idRedSocial;
    }

}