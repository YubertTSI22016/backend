package yuber.schema;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import yuber.models.ReporteProveedores;

public class MongoHandler {
	private MongoClient mongoClient = null;

	private static MongoHandler instance = null;

	private MongoHandler() {
		mongoClient = new MongoClient();
	}

	public static MongoHandler getInstance() {
		if (MongoHandler.instance == null) {
			MongoHandler.instance = new MongoHandler();
		}
		return MongoHandler.instance;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public static MongoDatabase getSchema(String schema) {
		return MongoHandler.getInstance().getMongoClient().getDatabase(schema);

	}

	public static String getJSON(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj).replace("{", " { ").replace("}", " } ").replace(":", " : ");
		return json;
	}

	public static <T> T buildObject(Class<T> clazz, String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		T rep = mapper.readValue(json, clazz);
		return rep;
	}
}
