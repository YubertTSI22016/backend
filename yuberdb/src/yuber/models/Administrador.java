package yuber.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import yuber.shares.DataTelefono;
import yuber.shares.DataAdministrador;

@Entity
@XmlRootElement
public class Administrador extends Persona implements Serializable{
	private static final long serialVersionUID = 1L;
		
	private Boolean activo;
		
	public Administrador(){}
	
	public Administrador(String id, String nom, String ape, Email mail, List<Telefono> tels, Date fecNac, Boolean elim, List<Servicio> srv, String clave, Boolean activo){
		super(id, clave, nom, ape, mail, tels, fecNac, elim, srv);
		this.activo = activo;
	}
	
	public Administrador (DataAdministrador da){
		this.setId(da.getId());
		this.setNombre(da.getNombre());
		this.setApellido(da.getApellido());
		if (da.getEmail() != null){
			this.setEmail(new Email (da.getEmail()));
		}
		if (da.getTelefonosContacto() != null){
			List<Telefono> aux = new ArrayList<Telefono>();
			da.getTelefonosContacto().stream().forEach ((tel) -> {
				aux.add(new Telefono(tel));
			});
			this.setTelefonosContacto(aux);
		}
		this.setFechaNacimiento(da.getFechaNacimiento());
		this.setEliminado(da.getEliminado());
		this.setClave(da.getClave());
		this.setActivo(da.getActivo());
	}
	
	public DataAdministrador getDatatype(Boolean conHijos){
		
		DataAdministrador result = new DataAdministrador();
		result.setId(this.getId());
		result.setNombre(this.getNombre());
		result.setApellido(this.getApellido());
		if (this.getEmail() != null){
			result.setEmail(this.getEmail().getDatatype());
		}
		if (this.getTelefonosContacto() != null && conHijos){
			List<DataTelefono> aux = new ArrayList<DataTelefono>();
			this.getTelefonosContacto().stream().forEach((tel) -> {
				aux.add(tel.getDatatype());
			});
		result.setTelefonosContacto(aux);	
		}
		result.setFechaNacimiento(this.getFechaNacimiento());
		result.setClave(this.getClave());
		result.setEliminado(this.getEliminado());
		this.setActivo(this.getActivo());
		
		return result;
	}
	
	public void setActivo(Boolean activo){
		this.activo= activo;
	}
	
	public Boolean getActivo(){
		return this.activo;
	}
}
