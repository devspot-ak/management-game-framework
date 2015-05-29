package biz.devspot.management.game.framework.util;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MongoDBDumpTest {

    public MongoDBDumpTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDump() throws Exception {
        ByteArrayOutputStream destination = new ByteArrayOutputStream();
        DB db1 = new Fongo("Test Server").getDB("dumprestoretest1");
        //DB db1 = new MongoClient().getDB("dumprestoretest1");
        DBCollection c1 = db1.getCollection("c1");
        c1.save((DBObject) JSON.parse("{\"_id\": 123}"));
        c1.save((DBObject) JSON.parse("{\"_id\": 456}"));
        MongoDBDump dbDump1 = new MongoDBDump(db1);
        dbDump1.exportDump(destination);
        String dump = new String(destination.toByteArray());
        System.out.println(dump);
        DB db2 = new Fongo("Test Server").getDB("dumprestoretest2");
        //DB db2 = new MongoClient().getDB("dumprestoretest2");
        MongoDBDump dbDump2 = new MongoDBDump(db2);
        dbDump2.importDump(new ByteArrayInputStream(dump.getBytes()));
        DBCollection cd1 = db2.getCollection("c1");
        DBObject result = cd1.findOne(new BasicDBObject("_id", 123));
        System.out.println(result.toString());
        assertEquals(123, result.get("_id"));
        result = cd1.findOne(new BasicDBObject("_id", 456));
        System.out.println(result.toString());
        assertEquals(456, result.get("_id"));
    }

}
