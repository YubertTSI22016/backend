package yuber.shares;


import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import yuber.shares.DateAdapter;
import yuber.shares.DataJornadaLaboral;

public class DataServicio {
	private String id;
	@XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
	private Date fecha;
	@XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
	private Date inicio;
	@XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
	private Date fin;
	private DataUsuario usuario;
	private DataProveedor proveedor;
	private Float rating;
	private String comentario;
	private String estado;
	private String descripcion;
	private String coordenadasOrigen;
	private String coordenadasDestino;
	private Float precio;
	private List<String> puntosRecorrido;
	
	public DataServicio(){}
	
	public DataServicio(String id, Date fec, Date ini, Date fin, DataUsuario usu, DataProveedor prov, Float rat, String coment, String est, String desc, String coordOri, String coordDest,Float prec, List<String> pts){
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
    
    public void setProveedor(DataProveedor val){
        this.proveedor = val;
    }
    
    public DataProveedor getProveedor(){
        return this.proveedor;
    }
    
    public void setUsuario(DataUsuario val){
        this.usuario = val;
    }
    
    public DataUsuario getUsuario(){
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

