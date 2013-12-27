package com.hengyi.japp.crm;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.index.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@RunWith(SpringJUnit4ClassRunner.class)
public class Neo4jCoreTest {
	private static final Logger log = LoggerFactory
			.getLogger(Neo4jCoreTest.class);
	private static final GraphDatabaseService gds = new GraphDatabaseFactory()
			.newEmbeddedDatabase("/home/jzb/data/japp.crm.db");

	public static void main(String[] args) {
		Index<Node> index = gds.index().forNodes("CustomerIndicator");
		log.debug("{}", index.get("name", "国别").getSingle());
		log.debug("{}", index.query("name", "国*").getSingle());
		log.debug("{}", gds.index().existsForNodes("CustomerReport"));
		// index = gds.index().forNodes("Report");
	}
}
