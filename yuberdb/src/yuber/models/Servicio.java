package yuber.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import yuber.shares.DataServicio;

@Entity
@XmlRootElement
public class Servicio {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Proveedor proveedor;
	private Float rating;
	private String comentario;
	private String estado;
	private String descripcion;
	private String coordenadasOrigen;
	private String coordenadasDestino;
	private Float precio;
	
	public Servicio(){}
	
	public Servicio(String id, Date fec, Usuario usu, Proveedor prov, Float rat, String coment, String est, String desc, String coordOri, String coordDest,Float prec){
		this.id = id;
		this.fecha = fec;
		this.usuario = usu;
		this.proveedor = prov;
		this.rating = rat;
		this.comentario = coment;
		this.estado = est;
		this.descripcion = desc;
		this.coordenadasOrigen = coordOri;
		this.coordenadasDestino = coordDest;
		this.precio = prec;
	}
	
	public Servicio(DataServicio dt){
    	this.setId(dt.getId());
    	this.setFecha(dt.getFecha());
    	if(dt.getProveedor() != null)
    		this.setProveedor(new Proveedor(dt.getProveedor()));
    	if(dt.getUsuario() != null)
    		this.setUsuario(new Usuario(dt.getUsuario(), false));
    	this.setRating(dt.getRating());
    	this.setComentario(dt.getComentario());
    	this.setEstado(dt.getEstado());
    	this.setDescripcion(dt.getDescripcion());
    	this.setCoordenadasOrigen(dt.getCoordenadasOrigen());
    	this.setCoordenadasDestino(dt.getCoordenadasDestino());
    	this.setPrecio(dt.getPrecio());
    	 
    }
    
    public DataServicio getDatatype(){
    	DataServicio result = new DataServicio();
    	result.setId(this.getId());
    	result.setFecha(this.getFecha());
    	if(this.getProveedor() != null)
    		result.setProveedor(this.getProveedor().getDatatype(true));
    	if(this.getUsuario() != null)
    		result.setUsuario(this.getUsuario().getDatatype(true));
    	result.setRating(this.getRating());
    	result.setComentario(this.getComentario());
    	result.setEstado(this.getEstado());
    	result.setDescripcion(this.getDescripcion());
    	result.setCoordenadasOrigen(this.getCoordenadasOrigen());
    	result.setCoordenadasDestino(this.getCoordenadasDestino());
    	result.setPrecio(this.getPrecio());
    	 
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setFecha(Date val){
        this.fecha = val;
    }
    
    public Date getFecha(){
        return this.fecha;
    }
    
    public void setProveedor(Proveedor val){
        this.proveedor = val;
    }
    
    public Proveedor getProveedor(){
        return this.proveedor;
    }
    
    public void setUsuario(Usuario val){
        this.usuario = val;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public void setRating(Float val){
        this.rating = val;
    }
    
    public Float getRating(){
        return this.rating;
    }
    
    public void setComentario(String val){
        this.comentario = val;
    }
    
    public String getComentario(){
        return this.comentario;
    }
    
    public void setEstado(String val){
        this.estado = val;
    }
    
    public String getEstado(){
        return this.estado;
    }
    
    public void setDescripcion(String val){
        this.descripcion = val;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public void setCoordenadasOrigen(String val){
        this.coordenadasOrigen = val;
    }
    
    public String getCoordenadasOrigen(){
        return this.coordenadasOrigen;
    }
    
    public void setCoordenadasDestino(String val){
        this.id = coordenadasDestino;
    }
    
    public String getCoordenadasDestino(){
        return this.coordenadasDestino;
    }
    
    public void setPrecio(Float val){
        this.precio = val;
    }
    
    public Float getPrecio(){
        return this.precio;
    }
    
}
