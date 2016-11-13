package yuber.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import yuber.shares.DataConfiguracionVertical;

@Entity
@XmlRootElement
public class ConfiguracionVertical{
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String nombre;
	private Boolean transporte;
	private Boolean habilitado;
	private Float tarifaBase;
	private Float precioPorKm;
	private Float precioPorHora;
	private Float porcentajeRetencion;
	@Column(length = 65535,columnDefinition="Text")
    private String css;
	private String fbId;
	private String fbSecret;
public ConfiguracionVertical(){}
	
	public ConfiguracionVertical(String id, String nom, Boolean transp,Boolean habilitado, Float tarBase, Float precPorKm, Float precPorHor, Float pr, String cs){
		this.id = id;
		this.nombre = nom;
		this.transporte = transp;
		this.habilitado = habilitado;
		this.tarifaBase = tarBase;
		this.precioPorKm = precPorKm;
		this.precioPorHora = precPorHor;
		this.porcentajeRetencion = pr;
		this.css = cs;
	}
	
	public ConfiguracionVertical(DataConfiguracionVertical dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	this.setTransporte(dt.getTransporte());
    	this.setHabilitado(dt.getHabilitado());
    	this.setTarifaBase(dt.getTarifaBase());
    	this.setPrecioPorKm(dt.getPrecioPorKm());
    	this.setPrecioPorHora(dt.getPrecioPorHora());
    	this.setPorcentajeRetencion(dt.getPorcentajeRetencion());
    	this.setCss(dt.getCss());
    	this.setCss(dt.getCss());
    	this.setFbId(dt.getFbId());
    	this.setFbSecret(dt.getFbSecret());
    }
    
    public DataConfiguracionVertical getDatatype(){
    	DataConfiguracionVertical result = new DataConfiguracionVertical();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setTransporte(this.getTransporte());
    	result.setHabilitado(this.getHabilitado());
    	result.setTarifaBase(this.getTarifaBase());
    	result.setPrecioPorKm(this.getPrecioPorKm());
    	result.setPrecioPorHora(this.getPrecioPorHora());
    	result.setPorcentajeRetencion(this.getPorcentajeRetencion());
    	result.setCss(this.getCss());
    	result.setFbId(this.getFbId());
    	result.setFbSecret(this.getFbSecret());
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setTransporte(Boolean val){
        this.transporte = val;
    }
    
    public Boolean getTransporte(){
        return this.transporte;
    }
    
    public void setHabilitado(Boolean val){
        this.habilitado = val;
    }
    
    public Boolean getHabilitado(){
        return this.habilitado;
    }
    
    public void setTarifaBase(Float val){
        this.tarifaBase = val;
    }
    
    public Float getTarifaBase(){
        return this.tarifaBase;
    }
    
    public void setPrecioPorKm(Float val){
        this.precioPorKm = val;
    }
    
    public Float getPrecioPorKm(){
        return this.precioPorKm;
    }
    
    public void setPrecioPorHora(Float val){
        this.precioPorHora = val;
    }
    
    public Float getPrecioPorHora(){
        return this.precioPorHora;
    }
    
    public void setPorcentajeRetencion(Float val){
        this.porcentajeRetencion = val;
    }
    
    public Float getPorcentajeRetencion(){
        return this.porcentajeRetencion;
    }
    
    public void setCss(String val){
        this.css = val;
    }
    
    public String getCss(){
        return this.css;
    }

	public String getFbId() {
		return fbId;
	}

	public void setFbId(String fbId) {
		this.fbId = fbId;
	}

	public String getFbSecret() {
		return fbSecret;
	}

	public void setFbSecret(String fbSecret) {
		this.fbSecret = fbSecret;
	}
}
