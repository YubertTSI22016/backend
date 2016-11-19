package yuber.shares;

import java.util.Date;
import java.util.List;

public class DataUsuario extends DataPersona{
    private String redSocialUsada;
    private String idRedSocial; 
    private DataProveedor proveedor;
    private List<DataServicio> servicios;
    private DataServicio servicioActivo;
    private String tokenTarjeta;
    private Integer ultimosNumerosTarjeta;
    private Float rating;
    private Integer cantidadServicios;
    private String stripeAccId;

 

    public DataUsuario() {}
    
    public DataUsuario(String id, String nom, String ape, DataEmail mail, DataTelefono tel, Date fecNac, Boolean elim, List<DataServicio> srv, DataServicio sa, String nomMos, String redSoc, String idRedsoc, String clv, DataProveedor prov, String token, Integer unt, Float rt, Integer cs, String sac) {
    	super.setId(id);
        super.setNombre(nom);
        super.setApellido(ape);
        super.setEmail(mail);
        super.setTelefonoContacto(tel);
        super.setFechaNacimiento(fecNac);
        super.setEliminado(elim);
        super.setClave(clv);
        this.redSocialUsada = redSoc;
        this.idRedSocial = idRedsoc; 
        this.proveedor = prov;
        this.servicios = srv;
        this.servicioActivo = sa;
        this.tokenTarjeta = token;
        this.ultimosNumerosTarjeta = unt;
        this.rating = rt;
        this.cantidadServicios = cs;
        this.stripeAccId = sac;
    }

    public void setRedSocialUsada(String val){
        this.redSocialUsada = val;
    }
    
    public String getRedSocialUsada(){
        return this.redSocialUsada;
    }

    public void setIdRedSocial(String val){
        this.idRedSocial = val;
    }
    
    public String getIdRedSocial(){
        return this.idRedSocial;
    }
    
    public void setProveedor(DataProveedor val){
        this.proveedor = val;
    }
    
    public DataProveedor getProveedor(){
        return this.proveedor;
    }
    
    public void setServicios(List<DataServicio> val){
        this.servicios = val;
    }
    
    public List<DataServicio> getServicios(){
        return this.servicios;
    }
    
    public void setServicioActivo(DataServicio val){
        this.servicioActivo = val;
    }
    
    public DataServicio getServicioActivo(){
        return this.servicioActivo;
    }
    
    public void setTokenTarjeta(String token){
    	this.tokenTarjeta = token;
    }

    public String getTokenTarjeta(){
    	return this.tokenTarjeta;
    }
    
    public void setUltimosNumerosTarjeta(Integer val){
    	this.ultimosNumerosTarjeta= val;
    }
    
    public Integer getUltimosNumerosTarjeta(){
    	return this.ultimosNumerosTarjeta;
    }
    
    public void setRating(Float val){
    	this.rating= val;
    }
    
    public Float getRating(){
    	return this.rating;
    }
    
    public void setCantidadServicios(Integer val){
    	this.cantidadServicios= val;
    }
    
    public Integer getCantidadServicios(){
    	return this.cantidadServicios;
    }
    
    public String getStripeAccId(){
        return this.stripeAccId;
    }

    public void setStripeAccId(String val){
        this.stripeAccId = val;
    }
}