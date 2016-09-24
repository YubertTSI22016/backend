package yuber.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import yuber.shares.DataTenant;

/**
 * Entity implementation class for Entity: Tenant
 *
 */
@Entity
public class Tenant implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id; 
	private String name;
	private String domain; 
	private Boolean isActive;
	private Boolean isDelete; 
	public Tenant(){
		super();
	}
	public Tenant(String id, String name, String domain, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.domain = domain;
		this.isActive = isActive;
	}
	 public Tenant(DataTenant dt){
		 this.setId(dt.getId());
		 this.setDomain(dt.getDomain());
		 this.setIsActive(dt.getIsActive());
		 this.setIsDelete(dt.getIsDelete());
		 this.setName(dt.getName());
	 }
	    
    public DataTenant getDatatype(){
    	DataTenant dt = new DataTenant(this.id, this.name, this.domain, this.isActive);
    	dt.setIsDelete(this.isDelete);
    	return dt;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
}
