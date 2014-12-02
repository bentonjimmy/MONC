package GradProject.Artifact001;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

public class NetworkGraphTest {

	@Test
	public void threeNodeTest() 
	{
		DataNode dn0 = new DataNode(0);
		DataNode dn1 = new DataNode(1);
		dn1.setColor(new Color(255, 0, 0));
		dn1.addConnection(2L);
		dn1.addConnection(3L);
		DataNode dn2 = new DataNode(2);
		dn2.setColor(new Color(255, 85, 85));
		dn2.addConnection(1L);
		dn2.addConnection(3L);
		DataNode dn3 = new DataNode(3);
		dn3.setColor(new Color(255, 170, 170));
		dn3.addConnection(1L);
		dn3.addConnection(2L);
		ArrayList<Node> nodelist = new ArrayList<Node>();
		nodelist.add(dn0);
		nodelist.add(dn1);
		nodelist.add(dn2);
		nodelist.add(dn3);
		
		NetworkGraph ng = new NetworkGraph(nodelist);
		JPanel jp = ng.plotData();
		
		JFrame frame = new JFrame("Simple Graph View 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(jp);
		frame.pack();
		frame.setVisible(true);  
		System.out.println("Test of 3 nodes");
	}

}
