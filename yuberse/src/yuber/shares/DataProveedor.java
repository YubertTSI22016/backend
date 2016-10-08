package yuber.shares;

import java.util.Date;
import java.util.List;


public class DataProveedor extends DataPersona{
    private Boolean activo;
    private List<DataJornadaLaboral> jornadas;

 

    public DataProveedor() {}
    
    public DataProveedor(String id, String nom, String ape, DataEmail mail, List<DataTelefono> tels, Date fecNac, Boolean elim, List<DataServicio> srv, String nomMos, Boolean activo, String clv, List<DataJornadaLaboral> jor) {
    	super.setId(id);
        super.setNombre(nom);
        super.setApellido(ape);
        super.setEmail(mail);
        super.setTelefonosContacto(tels);
        super.setFechaNacimiento(fecNac);
        super.setEliminado(elim);
        super.setClave(clv);
        super.setServicios(srv);
        this.activo = activo;
        this.jornadas = jor;
    }

    public void setActivo(Boolean val){
        this.activo = val;
    }
    
    public Boolean getActivo(){
        return this.activo;
    }
    
    public void setJornadas(List<DataJornadaLaboral> val){
        this.jornadas = val;
    }
    
    public List<DataJornadaLaboral> getJornadas(){
        return this.jornadas;
    }

}