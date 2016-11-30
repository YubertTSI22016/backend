package yuber.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

import yuber.shares.*;

@Entity
@XmlRootElement
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L; 
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
    private Boolean activo;
    @OneToMany(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
	@IndexColumn(name="LIST_INDEX")
    private List<JornadaLaboral> jornadas;
    @OneToOne(targetEntity=JornadaLaboral.class,fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    private JornadaLaboral jornadaActual;
    @OneToOne
    private Usuario usuario;
    private Float rating;
    private Integer cantidadServicios;
    @Embedded
    private Telefono telefono;
    private String descripcion;
    private String nombre;
    private String tokenTarjeta;
    private Integer ultimosNumerosTarjeta;
    private String stripeAccId;
    

    public Proveedor() {}
    
    public Proveedor(String id, Usuario usu, Boolean activo, List<JornadaLaboral> jls, JornadaLaboral jl, Float rat, Telefono tel, String desc, String nom, String tk, Integer cs, Integer unt, String sac) {
    	this.id = id;
    	this.usuario = usu;
        this.activo = activo;
        this.jornadas = jls;
        this.jornadaActual = jl;
        this.rating = rat;
        this.cantidadServicios = cs;
        this.telefono = tel;
        this.descripcion = desc;
        this.nombre = nom;
        this.tokenTarjeta = tk;
        this.ultimosNumerosTarjeta = unt;
        this.stripeAccId = sac;
    }
    
    public Proveedor(DataProveedor dt, Boolean conHijos){
    	this.setId(dt.getId());
    	if(dt.getUsuario() != null && conHijos)
    		this.setUsuario(new Usuario(dt.getUsuario(), false));
    	this.setActivo(dt.getActivo());
    	if(dt.getJornadas() != null && conHijos){
	    	List<JornadaLaboral> aux = new ArrayList<JornadaLaboral>();
	    	dt.getJornadas().stream().forEach((jor) -> {
	    		aux.add(new JornadaLaboral(jor, true));
	        });
	    	this.setJornadas(aux);
    	}
    	if(dt.getJornadaActual() != null && conHijos)
    		this.setJornadaActual(new JornadaLaboral(dt.getJornadaActual(), true));
    	this.setRating(dt.getRating());
    	this.setCantidadServicios(dt.getCantidadServicios());
    	if(dt.getTelefono() != null)
    		this.setTelefono(new Telefono(dt.getTelefono()));
    	this.setDescripcion(dt.getDescripcion());
    	this.setNombre(dt.getNombre());
    	this.setTokenTarjeta(dt.getTokenTarjeta());
    	this.setUltimosNumerosTarjeta(dt.getUltimosNumerosTarjeta());
    	this.setStripeAccId(dt.getStripeAccId());
    }
    
    public DataProveedor getDatatype(Boolean conHijos){
    	DataProveedor result = new DataProveedor();
    	result.setId(this.getId());
    	if(this.getUsuario() != null && conHijos)
    		result.setUsuario(this.getUsuario().getDatatype(false));
    	result.setActivo(this.getActivo());
    	if(this.getJornadas() != null && conHijos){
	    	List<DataJornadaLaboral> aux = new ArrayList<DataJornadaLaboral>();
	    	this.getJornadas().stream().forEach((jor) -> {
	    		aux.add(jor.getDatatype(true));
	        });
	    	result.setJornadas(aux);
    	}
    	if(this.getJornadaActual() != null && conHijos)
    		result.setJornadaActual(this.getJornadaActual().getDatatype(true));
    	result.setRating(this.getRating());
    	result.setCantidadServicios(this.getCantidadServicios());
    	if(this.getTelefono() != null)
    		result.setTelefono(this.getTelefono().getDatatype());
    	result.setDescripcion(this.getDescripcion());
    	result.setNombre(this.getNombre());
    	result.setTokenTarjeta(this.getTokenTarjeta());
    	result.setUltimosNumerosTarjeta(this.getUltimosNumerosTarjeta());
    	result.setStripeAccId(this.getStripeAccId());
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setUsuario(Usuario val){
        this.usuario = val;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }

    public void setActivo(Boolean val){
        this.activo = val;
    }
    
    public Boolean getActivo(){
        return this.activo;
    }
    
    public void setJornadas(List<JornadaLaboral> val){
        this.jornadas = val;
    }
    
    public List<JornadaLaboral> getJornadas(){
        return this.jornadas;
    }
    
    public void setJornadaActual(JornadaLaboral val){
        this.jornadaActual = val;
    }
    
    public JornadaLaboral getJornadaActual(){
        return this.jornadaActual;
    }
    
    public Float getRating(){
        return this.rating;
    }

    public void setRating(Float val){
        this.rating = val;
    }
    
    public Telefono getTelefono(){
        return this.telefono;
    }

    public void setTelefono(Telefono val){
        this.telefono = val;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String val){
        this.descripcion = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getTokenTarjeta(){
        return this.tokenTarjeta;
    }

    public void setTokenTarjeta(String val){
        this.tokenTarjeta = val;
    }
    
    public Integer getUltimosNumerosTarjeta(){
        return this.ultimosNumerosTarjeta;
    }

    public void setUltimosNumerosTarjeta(Integer val){
        this.ultimosNumerosTarjeta = val;
    }
    
    public void setCantidadServicios(Integer val){
    	this.cantidadServicios= val;
    }
    
    public Integer getCantidadServicios(){
    	return this.cantidadServicios;
    }
    
    public String getStripeAccId(){
        return this.stripeAccId;
    }

    public void setStripeAccId(String val){
        this.stripeAccId = val;
    }
}
