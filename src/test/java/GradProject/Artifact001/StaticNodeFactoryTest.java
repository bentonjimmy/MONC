package GradProject.Artifact001;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.Test;

public class StaticNodeFactoryTest {

	/*
	 * Tests:
	 * Creating a StaticNodeFactory
	 * Creating a DataNodeParameters object
	 * Settings the parameters of the DataNodeParameters object
	 * Creating a DataNode using the parameters
	 */
	@Test
	public void makeNodeWithParameters() {
		StaticNodeFactory nodefactory = new StaticNodeFactory();
		String type = "DataNode";
		
		DataNodeParameters parameters = new DataNodeParameters();
		parameters.setId(1);
		parameters.setLabel("Test1");
		parameters.setRepresents(1);
		parameters.setRepresentsIDs(null);
		
		DataNode t1 = (DataNode) nodefactory.makeNode(type, parameters);
		
		assertTrue("t1 - test node ID", t1.getId() == 1);
		assertTrue("t1 - test node label", t1.getLabel().equalsIgnoreCase("Test1"));
		assertTrue("t1 - test represents one node", t1.getRepresents() == 1);
		assertTrue("t1 - test IDs of nodes represented size", t1.getRepresentsIDs().size() == 0);
		
		parameters.setId(2);
		parameters.setLabel("Test2");
		parameters.setRepresents(2);
		ArrayList<Long> integers = new ArrayList<Long>();
		integers.add(1L);
		integers.add(2L); //Should not be able to store itself in the list
		parameters.setRepresentsIDs(integers);
		
		DataNode t2 = (DataNode) nodefactory.makeNode(type, parameters);
		
		assertTrue("t2 - test node ID", t2.getId() == 2);
		assertTrue("t2 - test node label", t2.getLabel().equalsIgnoreCase("Test2"));
		assertTrue("t2 - test represents two nodes", t2.getRepresents() == 2);
		assertTrue("t2 - test IDs of nodes represented size", t2.getRepresentsIDs().size() == 1);
		
	}
	
	@Test
	public void makeNodeNoParameters()
	{
		StaticNodeFactory nodefactory = new StaticNodeFactory();
		String type = "DataNode";
		
		DataNode t3 = (DataNode) nodefactory.makeNode(type);
		assertTrue("t3 - test node ID", t3.getId() == 3); //IDs should increment by one, static variable
		assertTrue("t3 - test node label", t3.getLabel().equalsIgnoreCase("3")); //Label defaults to ID
		assertTrue("t3 - test represents one node", t3.getRepresents() == 1);
		assertTrue("t3 - test IDs of nodes represented size", t3.getRepresentsIDs().size() == 0);
	}
	
	@Test
	public void makeMultipleNodes()
	{
		StaticNodeFactory nodefactory = new StaticNodeFactory();
		String type = "DataNode";
		ArrayList<Node> nodes;
		
		int counter = 4;
		nodes = nodefactory.makeNodes(type, 3);
		for(Node node : nodes)
		{
			assertTrue("test node ID", ((DataNode)node).getId() == counter); //IDs should increment by one, static variable
			assertTrue("test node label", ((DataNode)node).getLabel().equalsIgnoreCase(Integer.toString(counter))); //Label defaults to ID
			assertTrue("test represents one node", ((DataNode)node).getRepresents() == 1);
			assertTrue("test IDs of nodes represented size", ((DataNode)node).getRepresentsIDs().size() == 0);
			counter++;
		}
		
	}
	
	@Test
	public void oneNodeWithMany()
	{
		StaticNodeFactory nodefactory = new StaticNodeFactory();
		String type = "DataNode";
		DataNodeParameters parameters = new DataNodeParameters();
		parameters.setId(1);
		parameters.setLabel("Test1");
		parameters.setRepresents(26); //sending1 and 25 nodes inside it
		parameters.setRepresentsIDs(null);
		DataNode sending1 = (DataNode) nodefactory.makeNode(type, parameters);
		
		assertTrue("sending1 - test node ID", sending1.getId() == 1);
		assertTrue("sending1 - test node label", sending1.getLabel().equalsIgnoreCase("Test1"));
		assertTrue("sending1 - test represents one node", sending1.getRepresents() == 26);
		assertTrue("sending1 - test IDs of nodes represented size", sending1.getRepresentsIDs().size() == 25);
		//Check the IDs of the nodes received
		int testID = 2;
		for(Long ints:sending1.getRepresentsIDs())
		{
			assertTrue("Test IDs Received", ints.intValue() == testID);
			testID++;
		}
	}

}
