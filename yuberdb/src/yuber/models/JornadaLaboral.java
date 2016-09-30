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

import yuber.shares.DataJornadaLaboral;

@Entity
@XmlRootElement
public class JornadaLaboral {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@ManyToOne
	private Proveedor proveedor;
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fin;
	
	public JornadaLaboral(){}
	
	public JornadaLaboral(String id, Proveedor prov, Date ini, Date fin){
		this.id = id;
		this.proveedor = prov;
		this.inicio = ini;
		this.fin = fin;
	}
	
	public JornadaLaboral(DataJornadaLaboral dt){
    	this.setId(dt.getId());
    	this.setProveedor(new Proveedor(dt.getProveedor()));
    	this.setInicio(dt.getInicio());
    	this.setFin(dt.getFin());
    	 
    }
    
    public DataJornadaLaboral getDatatype(){
    	DataJornadaLaboral result = new DataJornadaLaboral();
    	result.setId(this.getId());
    	result.setProveedor(this.getProveedor().getDatatype(true));
    	result.setInicio(this.getInicio());
    	result.setFin(this.getFin());
    	 
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
