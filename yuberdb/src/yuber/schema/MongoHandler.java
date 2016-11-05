package yuber.schema;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

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
}
