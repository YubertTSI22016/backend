 package yuber.schema;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import yuber.exceptions.SchemaException;
import yuber.interfaces.ISchemaHandler; 
@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class SchemaHandler implements ISchemaHandler{

	private static final Log log = LogFactory.getLog(SchemaHandler.class);
	private ArrayList<String> createSQL = new ArrayList<String>();
	private static final String delimiter = ";";
	@Inject
	EntityManager em;
	
	@Inject
	UserTransaction ut;
	@RequestScoped
	public void createSchema(String name) throws SchemaException {
		createSQL = new ArrayList<String>();
		try {
			if(ut.getStatus() != javax.transaction.Status.STATUS_NO_TRANSACTION){
				ut.commit();
			}
			log.info("Create schema if not exist"+name);
			ut.begin();
			em.createNativeQuery("CREATE SCHEMA " + name).executeUpdate();
			em.createNativeQuery("SET SCHEMA '"+ name+"'").executeUpdate();
			em.createNativeQuery("SET search_path TO "+ name).executeUpdate();
			ut.commit();
		} catch (Exception e) {
			log.info("failing to create schema" + name);
			 throw new SchemaException("Schema Already Exist"+e.getMessage());
		}
		log.info("Changing current Schema to: "+ name);
		try {  
			InputStream schemadll = SchemaHandler.class.getResourceAsStream("schema.ddl");
			Scanner scanner = new Scanner(schemadll).useDelimiter(delimiter);
		    while(scanner.hasNext()) {
		    	String sql = scanner.next();
		    	if(!sql.isEmpty())
		        createSQL.add(sql + delimiter);
		    }
			log.info("setSchema-start");
			create(em);
			ut.begin();
			em.createNativeQuery("SET SCHEMA 'public'").executeUpdate();
			em.createNativeQuery("SET search_path TO public").executeUpdate();
			log.info("setSchema-end");
			ut.commit();
		} catch (Exception e) {
			try {
				log.info("==RollingBack===");
				ut.rollback();
				
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				// TODO Auto-generated catch block
				// TODO Auto-generated catch block
				log.info(e1.getMessage());
				 
			}
			// TODO Auto-generated catch block
			log.info(e.getMessage()); 
		}
	}

	private void create(EntityManager em) throws IOException {
		String[] createSQsL = this.createSQL.toArray(new String[0]);
		for (int j = 0; j < createSQsL.length -1; j++) {
			try {
				 ut.begin();
				 log.info(createSQsL[j]);
				em.createNativeQuery(createSQsL[j]).executeUpdate();
				ut.commit();
			} catch (Exception e) {
				log.error("Unsuccessful: " + createSQsL[j]);
				log.error(e.getMessage());
			}
		}
	}
}
