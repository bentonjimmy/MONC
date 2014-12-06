package GradProject.Artifact001;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class HadoopControllerTest {

	@Test
	public void test() 
	{
		//Test that there are 34 nodes
		//Random sample certain nodes and their connections
		//Test colors, order, resolution
		HadoopController hc = new HadoopController();
		DataNode dn = new DataNode(3);
		
		ArrayList<Node> nodes = hc.run(dn);
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
		
		dn = (DataNode) nodes.get(17);
		assertTrue("Verify it is node 17", dn.getId() == 17);
		assertTrue("N17 - Verify proper node color", dn.getColor().equals(new Color(255, 33*7, 33*7)));
		assertTrue("N17 - Verify resolution value", dn.getResolution() == 2.0000832558592814);
		
		dn = (DataNode) nodes.get(34);
		assertTrue("Verify it is node 34", dn.getId() == 34);
		assertTrue("N34 - Verify proper node color", dn.getColor().equals(new Color(255, 15*7, 15*7)));
		assertTrue("N34 - Verify resolution value", dn.getResolution() == 0.8952970738897799);
	}

}
