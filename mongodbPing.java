import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoException;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;

/**
 * Run: mvn compile exec:java -Dexec.mainClass="mongodbPing"
 */
public class mongodbPing {

    public static void main(String[] args) {
        // Reads the connection string from your terminal's environment variables
        String uri = System.getenv("MONGODB_URI");
        
        if (uri == null || uri.isBlank()) {
            System.err.println("ERROR: MONGODB_URI not found. Run: export MONGODB_URI='your_string'");
            return;
        }

        System.out.println("Connecting to Atlas...");

        // use try-with-resources to automatically close the connection
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            
            // The 'ping' command is the standard way to check if a server is reachable
            BsonDocument ping = new BsonDocument("ping", new BsonInt64(1));
            Document response = mongoClient.getDatabase("admin").runCommand(ping);
            
            System.out.println("---------------------------------------");
            System.out.println("SUCCESS: Pinged your MongoDB Atlas Cluster!");
            System.out.println("Details: " + response.toJson());
            System.out.println("---------------------------------------");

        } catch (MongoException e) {
            System.err.println("---------------------------------------");
            System.err.println("FAILURE: Check your IP Whitelist or Credentials.");
            System.err.println("Error: " + e.getMessage());
            System.err.println("---------------------------------------");
        }
    }
}
