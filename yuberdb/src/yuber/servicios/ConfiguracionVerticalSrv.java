package yuber.servicios;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import yuber.interceptors.TenantIntercept;
import yuber.interfaces.ConfiguracionVerticalLocalApi;
import yuber.models.ConfiguracionVertical;
import yuber.shares.*;

/**
 * Session Bean implementation class ConfiguracionVerticalSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
public class ConfiguracionVerticalSrv implements ConfiguracionVerticalLocalApi {
	@Inject
	EntityManager em;

	public ConfiguracionVerticalSrv() {

	}

	public DataConfiguracionVertical getConfiguracionVertical(DataTenant tenant){
        //obtengo la configuracion de la vertical de la bd
        Session session = (Session) em.getDelegate();
        List<ConfiguracionVertical> listConf = session.createCriteria(ConfiguracionVertical.class).list();
        ConfiguracionVertical conf;
        if(listConf.size() > 0){
        	conf = listConf.get(0);
        }else{
        	DataConfiguracionVertical newConf = new DataConfiguracionVertical();
        	newConf.setNombre("Nuevo Servicio");
        	//newConf.setDiasCreacionViaje(30);
        	//newConf = crearConfiguracionEmpresa(newConf, tenant);
        	conf = new ConfiguracionVertical(newConf);
        }
        
        return conf.getDatatype();
    }
    
    public void modificarConfiguracion(DataConfiguracionVertical conf, DataTenant tenant){
    	ConfiguracionVertical realObj = new ConfiguracionVertical(conf);
    	if(em.find(ConfiguracionVertical.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La configuracion no existe");
    	}
    	em.merge(realObj);
    }
    
    public DataConfiguracionVertical crearConfiguracionVertical(DataConfiguracionVertical conf, DataTenant tenant){
    	ConfiguracionVertical realObj = new ConfiguracionVertical(conf);
        //guardo la configuracion de la vertical en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }

}