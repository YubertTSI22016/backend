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

import yuber.shares.*;

@Entity
@XmlRootElement
public class PagosProveedor {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@ManyToOne
	private Proveedor proveedor;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;
	@ManyToOne
	private Servicio servicio;
	private Boolean pago;
	private Float porcentageRetencion;
	
	public PagosProveedor(){}
	
	public PagosProveedor(String id, Proveedor prov, Date fecp, Servicio serv, Boolean pago, Float porcR){
		this.id = id;
		this.proveedor = prov;
		this.fechaPago = fecp;
		this.servicio = serv;
		this.pago = pago;
		this.porcentageRetencion = porcR;
	}
	
	public PagosProveedor(DataPagosProveedor dt, Boolean conHijos){
    	this.setId(dt.getId());
    	if(dt.getProveedor() != null && conHijos)
    		this.setProveedor(new Proveedor(dt.getProveedor(), false));
    	this.setFechaPago(dt.getFechaPago());
    	if(dt.getServicio() != null && conHijos)
    		this.setServicio(new Servicio(dt.getServicio(), false));
    	this.setPago(dt.getPago());
    	this.setPorcentageRetencion(dt.getPorcentageRetencion());
    }
    
    public DataPagosProveedor getDatatype(Boolean conHijos){
    	DataPagosProveedor result = new DataPagosProveedor();
    	result.setId(this.getId());
    	if(this.getProveedor() != null && conHijos)
    		result.setProveedor(this.getProveedor().getDatatype(false));
    	result.setFechaPago(this.getFechaPago());
    	if(this.getServicio() != null && conHijos)
    		result.setServicio(this.getServicio().getDatatype(false));
    	result.setPago(this.getPago());
    	result.setPorcentageRetencion(this.getPorcentageRetencion());
    	return result;
    }
	
	public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setProveedor(Proveedor val){
        this.proveedor = val;
    }
    
    public Proveedor getProveedor(){
        return this.proveedor;
    }
    
    public void setFechaPago(Date val){
        this.fechaPago = val;
    }
    
    public Date getFechaPago(){
        return this.fechaPago;
    }
    
    public void setServicio(Servicio val){
        this.servicio = val;
    }
    
    public Servicio getServicio(){
        return this.servicio;
    }
    
    public void setPago(Boolean val){
        this.pago = val;
    }
    
    public Boolean getPago(){
        return this.pago;
    }
    
    public void setPorcentageRetencion(Float val){
        this.porcentageRetencion = val;
    }
    
    public Float getPorcentageRetencion(){
        return this.porcentageRetencion;
    }
}
