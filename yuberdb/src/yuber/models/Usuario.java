package yuber.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.IndexColumn;

import yuber.shares.DataServicio;
import yuber.shares.DataTelefono;
import yuber.shares.DataUsuario;


@Entity
@XmlRootElement
public class Usuario extends Persona implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    
    private String redSocialUsada;
    private String idRedSocial;
    @OneToMany
	@IndexColumn(name="LIST_INDEX")
    private List<Servicio> servicios;
    @OneToOne(targetEntity=Servicio.class, cascade = {CascadeType.ALL})
    private Servicio servicioActivo;
    @OneToOne
    private Proveedor proveedor;
    private String tokenTarjeta;
    private Integer ultimosNumerosTarjeta;
    

    public Usuario() {}
    
    public Usuario(String id, String nom, String ape, Email mail, Telefono tel, Date fecNac, Boolean elim, List<Servicio> srv, Servicio sa, String clave, String redSoc, String idRedsoc, Proveedor prov, String token, Integer unt) {
        super(id,clave, nom, ape, mail, tel, fecNac, elim);
        this.redSocialUsada = redSoc;
        this.idRedSocial = idRedsoc;
        this.proveedor = prov;
        this.servicios = srv;
        this.servicioActivo = sa;
        this.tokenTarjeta = token;
        this.ultimosNumerosTarjeta = unt;
    }
    
    public Usuario(DataUsuario dt, Boolean conHijos){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	this.setApellido(dt.getApellido());
    	if(dt.getEmail() != null){
    		this.setEmail(new Email(dt.getEmail()));
    	}
    	if(dt.getTelefonoContacto() != null)
    		this.setTelefonoContacto(new Telefono(dt.getTelefonoContacto()));
    	this.setFechaNacimiento(dt.getFechaNacimiento());
    	this.setEliminado(dt.getEliminado());
    	this.setClave(dt.getClave());
    	this.setRedSocialUsada(dt.getRedSocialUsada());
    	this.setIdRedSocial(dt.getIdRedSocial());
    	if(dt.getProveedor() != null)
    		this.setProveedor(new Proveedor(dt.getProveedor()));
    	if(dt.getServicios() != null){
	    	List<Servicio> aux = new ArrayList<Servicio>();
	    	dt.getServicios().stream().forEach((srv) -> {
	    		aux.add(new Servicio(srv));
	        });
	    	this.setServicios(aux);
    	}
    	if(dt.getServicioActivo() != null && conHijos)
    		this.setServicioActivo(new Servicio(dt.getServicioActivo()));
    	this.setTokenTarjeta(dt.getTokenTarjeta());
    	this.setUltimosNumerosTarjeta(dt.getUltimosNumerosTarjeta());
    }
    
    public DataUsuario getDatatype(Boolean conHijos){
    	DataUsuario result = new DataUsuario();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setApellido(this.getApellido());
    	if(this.getEmail()!=null)
    		result.setEmail(this.getEmail().getDatatype());
    	if(this.getTelefonoContacto()!=null)
    		result.setTelefonoContacto(this.getTelefonoContacto().getDatatype());
    	result.setFechaNacimiento(this.getFechaNacimiento());
    	result.setEliminado(this.getEliminado());
    	result.setClave(this.getClave());
    	result.setRedSocialUsada(this.getRedSocialUsada());
    	result.setIdRedSocial(this.getIdRedSocial());
    	if(this.getProveedor()!=null)
    		result.setProveedor(this.getProveedor().getDatatype(true));
    	if(this.getServicios()!=null && conHijos){
	    	List<DataServicio> aux = new ArrayList<DataServicio>();
	    	this.getServicios().stream().forEach((srv) -> {
	    		aux.add(srv.getDatatype());
	        });
	    	result.setServicios(aux);
    	}
    	if(this.getServicioActivo()!=null)
    		result.setServicioActivo(this.getServicioActivo().getDatatype()); 
    	result.setTokenTarjeta(this.getTokenTarjeta());
    	result.setUltimosNumerosTarjeta(this.getUltimosNumerosTarjeta());
    	return result;
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
    
    public void setProveedor(Proveedor val){
        this.proveedor = val;
    }
    
    public Proveedor getProveedor(){
        return this.proveedor;
    }
    
    public void setServicios(List<Servicio> val){
        this.servicios = val;
    }
    
    public List<Servicio> getServicios(){
        return this.servicios;
    }
    
    public void setServicioActivo(Servicio val){
        this.servicioActivo = val;
    }
    
    public Servicio getServicioActivo(){
        return this.servicioActivo;
    }

    public void setTokenTarjeta(String val){
    	this.tokenTarjeta= val;
    }
    
    public String getTokenTarjeta(){
    	return this.tokenTarjeta;
    }
    
    public void setUltimosNumerosTarjeta(Integer val){
    	this.ultimosNumerosTarjeta= val;
    }
    
    public Integer getUltimosNumerosTarjeta(){
    	return this.ultimosNumerosTarjeta;
    }
   
}