package mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Grady on 2017/9/20.
 */
public class MongoPratice {

    static MongoClient mongoClient;

    public static void main(String[] args) {
        String url = "10.1.1.186";
        int port = 27017;
        String databaseName = "mydb";
        String collectionName = "user";


        mongoClient = getConnection(url,port);

        MongoDatabase database = getDatabase(mongoClient,databaseName);
        MongoCollection<Document> collection = getCollection(database,collectionName);

//        System.out.println("collection = " + collection);
//        long start = new Date().getTime();
//        insertDocuments(collection);
//        System.out.println("时长： "+(new Date().getTime() - start));

//        long count = countOfCollection(collection);
//        System.out.println("count = " + count);

        queryAll(collection);

        mongoClient.close();

    }

    static MongoClient getConnection(String ip,int port){
//        MongoClientURI connectionUrl = new MongoClientURI("mongodb://"+ip+":"+port);
//        MongoClient mongoClient = new MongoClient(connectionUrl);
        MongoClient mongoClient = new MongoClient(ip,port);
        return mongoClient;
    }

    static MongoDatabase getDatabase(MongoClient mongoClient,String databaseName){
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        return database;
    }
    static MongoCollection<Document> getCollection(MongoDatabase database,String name){
        MongoCollection<Document> collection = database.getCollection(name);
        return collection;
    }

    //-------------------------------------- insert -------------------------------------------

    /**
     * 插入一行--一个文档
     * @param collection
     */
    static void insertDocument(MongoCollection collection){
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);
    }

    /**
     * 插入多行--多个文档
     * @param collection
     */
    static void insertDocuments(MongoCollection collection){
        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            documents.add(
                    new Document("name", "MongoDB_"+i)
                    .append("type", "database")
                    .append("count", i)
                    .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                    .append("info", new Document("x", 203).append("y", 102))
            );
        }
        collection.insertMany(documents);
    }


    //-------------------------------------- query -------------------------------------------
    /**
     * 集合中的文档数量
     * @param collection
     * @return
     */
    static long countOfCollection(MongoCollection collection){
        return collection.count();
    }

    /**
     * 集合的第一个文档
     * @param collection
     */
    static void queryFirst(MongoCollection collection){
        Document myDoc = (Document)collection.find().first();
        System.out.println(myDoc.toJson());
    }

    static void queryAll(MongoCollection collection){
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
        System.out.println("==========================================");

        for (Object obj : collection.find()) {
            Document cur = (Document)obj;
            System.out.println(cur.toJson());
        }
    }

    //-------------------------------------- update -------------------------------------------


    //-------------------------------------- delete -------------------------------------------

    static Document jsonToDoc(String json){
        Document document = Document.parse(json);
        return document;
    }
}
