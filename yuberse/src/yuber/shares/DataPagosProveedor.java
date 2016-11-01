package yuber.shares;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import yuber.shares.DataProveedor;

public class DataPagosProveedor {
	private String id;
	private DataProveedor proveedor;
	@XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
	private Date fechaPago;
	private DataServicio servicio;
	private Boolean pago;
	private Float porcentageRetencion;
	
	public DataPagosProveedor(){}
	
	public DataPagosProveedor(String id, DataProveedor prov, Date fecp, DataServicio serv, Boolean pago, Float porcR){
		this.id = id;
		this.proveedor = prov;
		this.fechaPago = fecp;
		this.servicio = serv;
		this.pago = pago;
		this.porcentageRetencion = porcR;
	}
	
	public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setProveedor(DataProveedor val){
        this.proveedor = val;
    }
    
    public DataProveedor getProveedor(){
        return this.proveedor;
    }
    
    public void setFechaPago(Date val){
        this.fechaPago = val;
    }
    
    public Date getFechaPago(){
        return this.fechaPago;
    }
    
    public void setServicio(DataServicio val){
        this.servicio = val;
    }
    
    public DataServicio getServicio(){
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
