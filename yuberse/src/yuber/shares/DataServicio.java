package yuber.shares;


import java.util.Date;

import yuber.shares.DataJornadaLaboral;

public class DataServicio {
	private String id;
	private Date fecha;
	private DataUsuario usuario;
	private DataProveedor proveedor;
	private Integer rating;
	private String comentario;
	private String estado;
	private String descripcion;
	private String coordenadasOrigen;
	private String coordenadasDestino;
	private Float precio;
	
	public DataServicio(){}
	
	public DataServicio(String id, Date fec, DataUsuario usu, DataProveedor prov, Integer rat, String coment, String est, String desc, String coordOri, String coordDest,Float prec){
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
    
    public void setRating(Integer val){
        this.rating = val;
    }
    
    public Integer getRating(){
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

