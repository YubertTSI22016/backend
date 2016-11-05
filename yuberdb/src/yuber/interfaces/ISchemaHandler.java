package yuber.interfaces;

import javax.ejb.Local;

import yuber.exceptions.SchemaException;

@Local
public interface ISchemaHandler {
	
	public void createSchema(String name) throws Exception;
}
