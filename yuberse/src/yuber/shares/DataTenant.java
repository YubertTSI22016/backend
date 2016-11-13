package yuber.shares;

public class DataTenant {
	private String name;
	private String domain;
	private String id;
	private Boolean isActive = true;
	private Boolean isDelete = false;
	
	private String tenantType = "";
	private String fbId = "";
	private String fbSecret= "";
	
	public DataTenant(){}
	public DataTenant(String id, String name, String domain, Boolean isActive, String tenantType, String fbId, String fbSecret) {
		super();
		this.id = id;
		this.name = name;
		this.domain = domain;
		this.isActive = isActive;
		this.fbId = fbId;
		this.fbSecret = fbSecret;
		this.tenantType = tenantType;
	}
	public DataTenant(String id, String name, String domain, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.domain = domain;
		this.isActive = isActive;
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
	public String getTenantType() {
		return tenantType;
	}
	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
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