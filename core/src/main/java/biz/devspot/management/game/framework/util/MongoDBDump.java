package biz.devspot.management.game.framework.util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.json.JSONObject;

public class MongoDBDump implements DBDump {

    private final DB db;

    public MongoDBDump(DB db) {
        this.db = db;
    }

    @Override
    public void exportDump(OutputStream destination) throws IOException {
        for (String collectionName : db.getCollectionNames()) {
            DBCollection collection = db.getCollection(collectionName);
            DBCursor cursor = collection.find();
            while (cursor.hasNext()) {
                DBObject result = cursor.next();
                JSONObject json = new JSONObject(result.toString());
                if (collectionName.equals("system.indexes")) {
                    String ns = json.getString("ns");
                    if (ns.startsWith(db.getName() + ".")) {
                        ns = ns.substring(db.getName().length() + 2);
                        json.put("ns", ns);
                    }
                }
                json.put("$collection", collectionName);
                destination.write((json.toString() + "\n").getBytes());
            }
        }
    }

    @Override
    public void importDump(InputStream source) throws IOException {
        db.dropDatabase();
        BufferedReader br = new BufferedReader(new InputStreamReader(source));
        String line = br.readLine();
        while (line != null) {
            JSONObject json = new JSONObject(line);
            String collectionName = json.getString("$collection");
            json.remove("$collection");
            if (collectionName.equals("system.indexes")) {
                String ns = json.getString("ns");
                ns = db.getName() + "." + ns;
                json.put("ns", ns);
            }
            db.getCollection(collectionName).save((DBObject) JSON.parse(json.toString()));
            line = br.readLine();
        }
    }

}
