package yuber.servicios;

import javax.ejb.LocalBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Session Bean implementation class EntityManagerProducer
 */
@ApplicationScoped
@LocalBean
public class EntityManagerProducer {

	private static final Log log = LogFactory.getLog(EntityManagerProducer.class);
	@PersistenceContext(unitName = "yuberdb")
	private EntityManager em;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {

		log.info(":::::::::::getting em:::::::::::::");
		em.createNativeQuery("SET SCHEMA 'public'").executeUpdate();
		em.createNativeQuery("SET search_path TO public").executeUpdate();

		return em;
	}
}