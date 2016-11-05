package yuber.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import lcbs.models.HibernateUtils;
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fin;
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
	@ElementCollection
	@CollectionTable(name="puntosRecorrido", joinColumns=@JoinColumn(name="servicio_id"))
	@Column(name="punto")
	private List<String> puntosRecorrido;
	
	public Servicio(){}
	
	public Servicio(String id, Date fec, Date ini, Date fin, Usuario usu, Proveedor prov, Float rat, String coment, String est, String desc, String coordOri, String coordDest,Float prec, List<String> pts){
		this.id = id;
		this.fecha = fec;
		this.inicio = ini;
		this.fin = fin;
		this.usuario = usu;
		this.proveedor = prov;
		this.rating = rat;
		this.comentario = coment;
		this.estado = est;
		this.descripcion = desc;
		this.coordenadasOrigen = coordOri;
		this.coordenadasDestino = coordDest;
		this.precio = prec;
		this.puntosRecorrido = pts;
	}
	
	public Servicio(DataServicio dt, Boolean conHijos){
    	this.setId(dt.getId());
    	this.setFecha(dt.getFecha());
    	this.setInicio(dt.getInicio());
    	this.setFin(dt.getFin());
    	if(dt.getProveedor() != null && conHijos)
    		this.setProveedor(new Proveedor(dt.getProveedor(), false));
    	if(dt.getUsuario() != null && conHijos)
    		this.setUsuario(new Usuario(dt.getUsuario(), false));
    	this.setRating(dt.getRating());
    	this.setComentario(dt.getComentario());
    	this.setEstado(dt.getEstado());
    	this.setDescripcion(dt.getDescripcion());
    	this.setCoordenadasOrigen(dt.getCoordenadasOrigen());
    	this.setCoordenadasDestino(dt.getCoordenadasDestino());
    	this.setPrecio(dt.getPrecio());
    	this.setPuntosRecorrido(dt.getPuntosRecorrido());
    }
    
    public DataServicio getDatatype(Boolean conHijos){
    	DataServicio result = new DataServicio();
    	result.setId(this.getId());
    	result.setFecha(this.getFecha());
    	result.setInicio(this.getInicio());
    	result.setFin(this.getFin());
    	if(this.getProveedor() != null && conHijos)
    		result.setProveedor(this.getProveedor().getDatatype(true));
    	if(this.getUsuario() != null && conHijos)
    		result.setUsuario(this.getUsuario().getDatatype(false));
    	result.setRating(this.getRating());
    	result.setComentario(this.getComentario());
    	result.setEstado(this.getEstado());
    	result.setDescripcion(this.getDescripcion());
    	result.setCoordenadasOrigen(this.getCoordenadasOrigen());
    	result.setCoordenadasDestino(this.getCoordenadasDestino());
    	result.setPrecio(this.getPrecio());
    	result.setPuntosRecorrido(HibernateUtils.initializeAndUnproxy(this.getPuntosRecorrido()));
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
    
    public void setInicio(Date val){
        this.inicio = val;
    }
    
    public Date getInicio(){
        return this.inicio;
    }
    
    public void setFin(Date val){
        this.fin = val;
    }
    
    public Date getFin(){
        return this.fin;
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
        this.coordenadasDestino = val;
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
    
    public void setPuntosRecorrido(List<String> val){
        this.puntosRecorrido = val;
    }
    
    public List<String> getPuntosRecorrido(){
        return this.puntosRecorrido;
    }
    
}
