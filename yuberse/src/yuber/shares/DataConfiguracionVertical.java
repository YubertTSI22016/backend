package yuber.shares;

import java.util.Date;


public class DataConfiguracionVertical {
	private String id;
	private String nombre;
	private Boolean transporte;
	private Boolean habilitado;
	private Float tarifaBase;
	private Float precioPorKm;
	private Float precioPorHora;
	private Float porcentajeRetencion;
	private String css;
	private String fbId;
	private String fbSecret;
	
public DataConfiguracionVertical(){}
	
	public DataConfiguracionVertical(String id, String nom, Boolean transp, Boolean habilitado, Float tarBase, Float precPorKm, Float precPorHor, Float pr, String cs){
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

	public void setFbId(String fbId) {
		this.fbId = fbId;
		
	}

 	public String getFbSecret() {
		return fbSecret;
	}

	public void setFbSecret(String fbSecret) {
		this.fbSecret = fbSecret;
	}

	public String getFbId() {
		return fbId;
	}
}
