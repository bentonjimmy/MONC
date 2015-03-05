package GradProject.Artifact001;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.util.ArrayList;

import org.junit.Test;

public class ControllerTest {

	@Test
	public void singleNodeToModel()
	{
		Adapter sqladapter = new Adapter();
		ViewController viewController = new ViewController(new Dimension(300, 300));
		Controller controller = new Controller(viewController, sqladapter);
		StaticNodeFactory nodefactory = new StaticNodeFactory();
		String type = "DataNode";
		DataNodeParameters parameters = new DataNodeParameters();
		parameters.setId(1);
		parameters.setLabel("Test1");
		parameters.setRepresents(1);
		parameters.setRepresentsIDs(null);
		DataNode t1 = (DataNode) nodefactory.makeNode(type, parameters);
		
		ArrayList<Node> list = controller.updateModel(t1, "retrieve");
		assertTrue("Returned size - 1", list.size() == 1);
		DataNode returned = (DataNode) list.get(0);
		assertTrue("Check returned ID", returned.getId() == t1.getId());
		assertTrue("Check returned label", returned.getLabel().equalsIgnoreCase(t1.getLabel()));
	}
	
	@Test
	public void multipleNodesToModel()
	{
		//Send multiple nodes
		//Receive back nodes at lowest possible level
		Adapter sqladapter = new Adapter();
		ViewController viewController = new ViewController(new Dimension(300, 300));
		Controller controller = new Controller(viewController, sqladapter);
		
		StaticNodeFactory nodefactory = new StaticNodeFactory();
		String type = "DataNode";
		DataNodeParameters parameters = new DataNodeParameters();
		parameters.setId(1);
		parameters.setLabel("Test1");
		parameters.setRepresents(26); //sending1 and 25 nodes inside it
		parameters.setRepresentsIDs(null);
		DataNode sending1 = (DataNode) nodefactory.makeNode(type, parameters);
		
		ArrayList<Node> list = controller.updateModel(sending1, "retrieve");
		assertTrue("Received list", list.size() == 25);
		//Check IDs of received nodes
		int testID = 2;
		for(Node node: list)
		{
			assertTrue("Test received Nodes IDs", ((DataNode)node).getId() == testID);
			testID++;
		}
	}

}
