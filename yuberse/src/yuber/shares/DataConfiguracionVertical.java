package yuber.shares;

import java.util.Date;


public class DataConfiguracionVertical {
	private String id;
	private String nombre;
	private Boolean transporte;
	private Float tarifaBase;
	private Float precioPorKm;
	private Float precioPorHora;
	
public DataConfiguracionVertical(){}
	
	public DataConfiguracionVertical(String id, String nom, Boolean transp, Float tarBase, Float precPorKm, Float precPorHor){
		this.id = id;
		this.nombre = nom;
		this.transporte = transp;
		this.tarifaBase = tarBase;
		this.precioPorKm = precPorKm;
		this.precioPorHora = precPorHor;
	}
	
	public DataConfiguracionVertical(DataConfiguracionVertical dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	this.setTransporte(dt.getTransporte());
    	this.setTarifaBase(dt.getTarifaBase());
    	this.setPrecioPorKm(dt.getPrecioPorKm());
    	this.setPrecioPorHora(dt.getPrecioPorHora());
    	 
    }
    
    public DataConfiguracionVertical getDatatype(){
    	DataConfiguracionVertical result = new DataConfiguracionVertical();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setTransporte(this.getTransporte());
    	result.setTarifaBase(this.getTarifaBase());
    	result.setPrecioPorKm(this.getPrecioPorKm());
    	result.setPrecioPorHora(this.getPrecioPorHora());
    	 
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
}
