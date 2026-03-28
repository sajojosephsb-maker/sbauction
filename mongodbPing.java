import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoException;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;

public class mongodbPing {
    public static void main(String[] args) {
        // Read the connection string from your system environment variables
        String uri = System.getenv("MONGODB_URI");
        
        if (uri == null || uri.isBlank()) {
            System.err.println("ERROR: MONGODB_URI environment variable is not set.");
            return;
        }

        System.out.println("Attempting to connect to MongoDB Atlas...");

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Send a ping to confirm a successful connection
            BsonDocument ping = new BsonDocument("ping", new BsonInt64(1));
            Document response = mongoClient.getDatabase("admin").runCommand(ping);
            
            System.out.println("---------------------------------------");
            System.out.println("SUCCESS: Connected to Atlas!");
            System.out.println("Response: " + response.toJson());
            System.out.println("---------------------------------------");

        } catch (MongoException e) {
            System.err.println("---------------------------------------");
            System.err.println("FAILURE: Connection failed.");
            System.err.println("Error: " + e.getMessage());
            System.err.println("---------------------------------------");
        }
    }
}
