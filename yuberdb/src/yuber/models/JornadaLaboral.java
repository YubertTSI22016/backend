package yuber.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

import yuber.shares.DataJornadaLaboral;
import yuber.shares.DataServicio;
import yuber.shares.DataTelefono;

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
	@OneToMany
	@IndexColumn(name="LIST_INDEX")
    private List<Servicio> servicios;
	
	public JornadaLaboral(){}
	
	public JornadaLaboral(String id, Proveedor prov, Date ini, Date fin, List<Servicio> serv){
		this.id = id;
		this.proveedor = prov;
		this.inicio = ini;
		this.fin = fin;
		this.servicios = serv;
	}
	
	public JornadaLaboral(DataJornadaLaboral dt){
    	this.setId(dt.getId());
    	this.setProveedor(new Proveedor(dt.getProveedor()));
    	this.setInicio(dt.getInicio());
    	this.setFin(dt.getFin());
    	if(dt.getServicios() != null){
	    	List<Servicio> aux = new ArrayList<Servicio>();
	    	dt.getServicios().stream().forEach((srv) -> {
	    		aux.add(new Servicio(srv));
	        });
	    	this.setServicios(aux);
    	}
    }
    
    public DataJornadaLaboral getDatatype(){
    	DataJornadaLaboral result = new DataJornadaLaboral();
    	result.setId(this.getId());
    	result.setProveedor(this.getProveedor().getDatatype(true));
    	result.setInicio(this.getInicio());
    	result.setFin(this.getFin());
    	if(this.getServicios()!=null){
	    	List<DataServicio> aux = new ArrayList<DataServicio>();
	    	this.getServicios().stream().forEach((srv) -> {
	    		aux.add(srv.getDatatype());
	        });
	    	result.setServicios(aux);
    	}
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
    
    public void setServicios(List<Servicio> val){
        this.servicios = val;
    }
    
    public List<Servicio> getServicios(){
        return this.servicios;
    }
}
