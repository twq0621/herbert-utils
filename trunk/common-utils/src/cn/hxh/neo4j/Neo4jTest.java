package cn.hxh.neo4j;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class Neo4jTest {

	public enum MyRelationshipTypes implements RelationshipType {

		KNOWS

	}

	public static void main(String[] args) {

		GraphDatabaseService graphDb = new EmbeddedGraphDatabase("var/base");

		Transaction tx = graphDb.beginTx();

		try {

			Node firstNode = graphDb.createNode();

			Node secondNode = graphDb.createNode();

			Relationship relationship = firstNode.createRelationshipTo(secondNode, MyRelationshipTypes.KNOWS);

			firstNode.setProperty("message", "Hello, ");

			secondNode.setProperty("message", "world!");

			relationship.setProperty("message", "brave Neo4j ");

			tx.success();

			System.out.print(firstNode.getProperty("message"));

			System.out.print(relationship.getProperty("message"));

			System.out.print(secondNode.getProperty("message"));

		}

		finally {

			tx.finish();

			graphDb.shutdown();

		}

	}

}
