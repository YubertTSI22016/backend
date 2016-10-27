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
    @Embedded
    private Telefono telefono;
    private String descripcion;
    private String nombre;
    private String cuentaBancaria;
    

    public Proveedor() {}
    
    public Proveedor(String id, Usuario usu, Boolean activo, List<JornadaLaboral> jls, JornadaLaboral jl, Float rat, Telefono tel, String desc, String nom, String cba) {
    	this.id = id;
    	this.usuario = usu;
        this.activo = activo;
        this.jornadas = jls;
        this.jornadaActual = jl;
        this.rating = rat;
        this.telefono = tel;
        this.descripcion = desc;
        this.nombre = nom;
        this.cuentaBancaria = cba;
    }
    
    public Proveedor(DataProveedor dt){
    	this.setId(dt.getId());
    	if(dt.getUsuario() != null)
    		this.setUsuario(new Usuario(dt.getUsuario(), true));
    	this.setActivo(dt.getActivo());
    	if(dt.getJornadas() != null){
	    	List<JornadaLaboral> aux = new ArrayList<JornadaLaboral>();
	    	dt.getJornadas().stream().forEach((jor) -> {
	    		aux.add(new JornadaLaboral(jor, true));
	        });
	    	this.setJornadas(aux);
    	}
    	if(dt.getJornadaActual() != null)
    		this.setJornadaActual(new JornadaLaboral(dt.getJornadaActual(), false));
    	this.setRating(dt.getRating());
    	if(dt.getTelefono() != null)
    		this.setTelefono(new Telefono(dt.getTelefono()));
    	this.setDescripcion(dt.getDescripcion());
    	this.setNombre(dt.getNombre());
    	this.setCuentaBancaria(dt.getCuentaBancaria());
    }
    
    public DataProveedor getDatatype(Boolean conHijos){
    	DataProveedor result = new DataProveedor();
    	result.setId(this.getId());
    	if(this.getUsuario() != null)
    		result.setUsuario(this.getUsuario().getDatatype(true));
    	result.setActivo(this.getActivo());
    	if(this.getJornadas() != null){
	    	List<DataJornadaLaboral> aux = new ArrayList<DataJornadaLaboral>();
	    	this.getJornadas().stream().forEach((jor) -> {
	    		aux.add(jor.getDatatype());
	        });
	    	result.setJornadas(aux);
    	}
    	if(this.getJornadaActual() != null)
    		result.setJornadaActual(this.getJornadaActual().getDatatype());
    	result.setRating(this.getRating());
    	if(this.getTelefono() != null)
    		result.setTelefono(this.getTelefono().getDatatype());
    	result.setDescripcion(this.getDescripcion());
    	result.setNombre(this.getNombre());
    	result.setCuentaBancaria(this.getCuentaBancaria());
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
    
    public String getCuentaBancaria(){
        return this.cuentaBancaria;
    }

    public void setCuentaBancaria(String val){
        this.cuentaBancaria = val;
    }
}
