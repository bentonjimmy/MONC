package GradProject.Artifact001;

import static org.junit.Assert.*;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

public class ViewControllerTest {

	@Test
	public void visualNode3Test() 
	{
		JFrame frame = new JFrame("Visual Test");
		Dimension dim = new Dimension(350, 350);
		ViewController vc = new ViewController(dim);
		Adapter adapter = new Adapter();
		Controller controller = new Controller(vc, adapter);
		vc.setController(controller);
		
		int Node = 3;
		String style = "network";
		JPanel panel = vc.updateView(Node, style);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);  
		
		assertTrue("Returned panel is not null", panel != null);
	}

}
