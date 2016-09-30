package yuber.shares;

import java.util.Date;

import yuber.shares.DataProveedor;

public class DataJornadaLaboral {
	private String id;
	private DataProveedor proveedor;
	private Date inicio;
	private Date fin;
	
	public DataJornadaLaboral(){}
	
	public DataJornadaLaboral(String id, DataProveedor prov, Date ini, Date fin){
		this.id = id;
		this.proveedor = prov;
		this.inicio = ini;
		this.fin = fin;
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
}
