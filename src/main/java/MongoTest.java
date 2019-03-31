
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;


public class MongoTest {

   public static void main(String[] args) {
       System.out.print("Hello World");

       //For atlas connection instead of using local host
       // MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
       // MongoClient mongoClient = new MongoClient(connectionString);


       MongoClient mongoClient = new MongoClient();
       MongoDatabase database = mongoClient.getDatabase("Music");
       MongoCollection<Document> collection = database.getCollection("Songs");


       //print the docs
       printAll();

       Document doc = new Document("Artist", "Eagles")
               .append("Song", "Hotel California")
               .append("Year", "1976");


       // insert document
       collection.insertOne(doc);

       //print the docs
       printAll();


       // update value
       collection.updateOne(eq("Artist", "Eagles"), new Document("$set", new Document("Artist", "The Eagles")));

       //print the docs
       printAll();


       collection.deleteOne(eq("Artist", "The Eagles"));

       //print the docs
       printAll();

   }

    public static void printAll(){
       MongoClient mongoClient = new MongoClient();
       MongoDatabase database = mongoClient.getDatabase("Music");
       MongoCollection<Document> collection = database.getCollection("Songs");
       MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }


}



