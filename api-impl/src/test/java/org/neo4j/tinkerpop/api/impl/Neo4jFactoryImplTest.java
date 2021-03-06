package org.neo4j.tinkerpop.api.impl;

import org.junit.Test;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.HighlyAvailableGraphDatabaseFactory;
import org.neo4j.kernel.ha.HighlyAvailableGraphDatabase;
import org.neo4j.tinkerpop.api.Neo4jGraphAPI;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author mh
 * @since 21.11.15
 */
public class Neo4jFactoryImplTest {

    @Test
    public void testCreateGraphDatabaseFactory() throws Exception {
        GraphDatabaseFactory factory = new Neo4jFactoryImpl().createGraphDatabaseFactory(Collections.singletonMap("ha.server_id", "1"));
        assertEquals(true, factory instanceof HighlyAvailableGraphDatabaseFactory);
    }

    @Test
    public void testCreateHighlyAvailableGraphDatabase() throws Exception {
        Path file = Files.createTempDirectory("tp-test-db");
        String path = file.toAbsolutePath().toString();
        Map<String, String> config = new HashMap<>();
        config.put("ha.server_id","1");
        config.put("ha.initial_hosts","localhost:5001");
        Neo4jGraphAPIImpl db = (Neo4jGraphAPIImpl) new Neo4jFactoryImpl().newGraphDatabase(path, config);
        assertEquals(true, db.getGraphDatabase() instanceof HighlyAvailableGraphDatabase);
        db.shutdown();
    }
}
