package edu.njit.gradproject;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class HadoopControllerTest {

	@Test
	public void test() 
	{
		//Test that there are 35 nodes
		//Random sample certain nodes and their connections
		//Test colors, order, resolution
		HadoopController hc = new HadoopController();
		DataNode dn = new DataNode(3);
		
		ArrayList<Node> nodes = hc.run(dn, "/Users/jmb66/Documents/NJIT/GradProject/DataSets/KarateClub", "zachary_unweighted.txt");
		assertTrue("Test nodes ArrayList size", nodes.size() == 35);//34 nodes from the data and one dummy
		
		dn = (DataNode) nodes.get(1);
		assertTrue("Verify it is node 1", dn.getId() == 1);
		assertTrue("N1 - Verify proper node color", dn.getColor().equals(new Color(255, 10*7, 10*7)));
		assertTrue("N1 - Verify resolution value", dn.getResolution() == 1.4442808413922494);
		Iterator<Integer> iter = dn.connectionsIterator();
		Integer connection = iter.next();
		assertTrue("N1 - Test connections: 2", dn.getConnection(connection) == 2);
		connection = iter.next();
		assertTrue("N1 - Test connections: 3", dn.getConnection(connection) == 3);
		connection = iter.next();
		assertTrue("N1 - Test connections: 4", dn.getConnection(connection) == 4);
		connection = iter.next();
		assertTrue("N1 - Test connections: 5", dn.getConnection(connection) == 5);
		connection = iter.next();
		assertTrue("N1 - Test connections: 6", dn.getConnection(connection) == 6);
		connection = iter.next();
		assertTrue("N1 - Test connections: 7", dn.getConnection(connection) == 7);
		connection = iter.next();
		assertTrue("N1 - Test connections: 8", dn.getConnection(connection) == 8);
		connection = iter.next();
		assertTrue("N1 - Test connections: 9", dn.getConnection(connection) == 9);
		connection = iter.next();
		assertTrue("N1 - Test connections: 11", dn.getConnection(connection) == 11);
		connection = iter.next();
		assertTrue("N1 - Test connections: 12", dn.getConnection(connection) == 12);
		connection = iter.next();
		assertTrue("N1 - Test connections: 13", dn.getConnection(connection) == 13);
		connection = iter.next();
		assertTrue("N1 - Test connections: 14", dn.getConnection(connection) == 14);
		connection = iter.next();
		assertTrue("N1 - Test connections: 18", dn.getConnection(connection) == 18);
		connection = iter.next();
		assertTrue("N1 - Test connections: 20", dn.getConnection(connection) == 20);
		connection = iter.next();
		assertTrue("N1 - Test connections: 22", dn.getConnection(connection) == 22);
		connection = iter.next();
		assertTrue("N1 - Test connections: 32", dn.getConnection(connection) == 32);
		assertTrue("N1 - No connections left", iter.hasNext() == false);
		
		dn = (DataNode) nodes.get(10);
		assertTrue("Verify it is node 10", dn.getId() == 10);
		assertTrue("N10 - Verify proper node color", dn.getColor().equals(new Color(255, 1*7, 1*7)));
		assertTrue("N10 - Verify resolution value", dn.getResolution() == 6.025685102665481);
		iter = dn.connectionsIterator();
		connection = iter.next();
		assertTrue("N10 - Test connections: 3", dn.getConnection(connection) == 3);
		connection = iter.next();
		assertTrue("N10 - Test connections: 34", dn.getConnection(connection) == 34);
		assertTrue("N10 - No connections left", iter.hasNext() == false);
		
		dn = (DataNode) nodes.get(17);
		assertTrue("Verify it is node 17", dn.getId() == 17);
		assertTrue("N17 - Verify proper node color", dn.getColor().equals(new Color(255, 33*7, 33*7)));
		assertTrue("N17 - Verify resolution value", dn.getResolution() >= 2.000083255859280 && dn.getResolution() <= 2.000083255859282);
		iter = dn.connectionsIterator();
		connection = iter.next();
		assertTrue("N17 - Test connections: 6", dn.getConnection(connection) == 6);
		connection = iter.next();
		assertTrue("N17 - Test connections: 7", dn.getConnection(connection) == 7);
		assertTrue("N17 - No connections left", iter.hasNext() == false);
		
		dn = (DataNode) nodes.get(34);
		assertTrue("Verify it is node 34", dn.getId() == 34);
		assertTrue("N34 - Verify proper node color", dn.getColor().equals(new Color(255, 15*7, 15*7)));
		assertTrue("N34 - Verify resolution value", dn.getResolution() >= 0.895297073889779 && dn.getResolution() <= 0.895297073889780); 
		iter = dn.connectionsIterator();
		connection = iter.next();
		assertTrue("N34 - Test connections: 9", dn.getConnection(connection) == 9);
		connection = iter.next();
		assertTrue("N34 - Test connections: 10", dn.getConnection(connection) == 10);
		connection = iter.next();
		assertTrue("N34 - Test connections: 14", dn.getConnection(connection) == 14);
		connection = iter.next();
		assertTrue("N34 - Test connections: 15", dn.getConnection(connection) == 15);
		connection = iter.next();
		assertTrue("N34 - Test connections: 16", dn.getConnection(connection) == 16);
		connection = iter.next();
		assertTrue("N34 - Test connections: 19", dn.getConnection(connection) == 19);
		connection = iter.next();
		assertTrue("N34 - Test connections: 20", dn.getConnection(connection) == 20);
		connection = iter.next();
		assertTrue("N34 - Test connections: 21", dn.getConnection(connection) == 21);
		connection = iter.next();
		assertTrue("N34 - Test connections: 23", dn.getConnection(connection) == 23);
		connection = iter.next();
		assertTrue("N34 - Test connections: 24", dn.getConnection(connection) == 24);
		connection = iter.next();
		assertTrue("N34 - Test connections: 27", dn.getConnection(connection) == 27);
		connection = iter.next();
		assertTrue("N34 - Test connections: 28", dn.getConnection(connection) == 28);
		connection = iter.next();
		assertTrue("N34 - Test connections: 29", dn.getConnection(connection) == 29);
		connection = iter.next();
		assertTrue("N34 - Test connections: 30", dn.getConnection(connection) == 30);
		connection = iter.next();
		assertTrue("N34 - Test connections: 31", dn.getConnection(connection) == 31);
		connection = iter.next();
		assertTrue("N34 - Test connections: 32", dn.getConnection(connection) == 32);
		connection = iter.next();
		assertTrue("N34 - Test connections: 33", dn.getConnection(connection) == 33);
		assertTrue("N34 - No connections left", iter.hasNext() == false);
	}

}
