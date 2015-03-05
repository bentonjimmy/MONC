package GradProject.Artifact001;

import java.util.ArrayList;

/**
 * The Adapter class is used to retrieve data from the Hadoop components.
 * @author Jim Benton
 *
 */
public class Adapter 
{
	private HadoopController controller;
	private Settings settings;
	
	public Adapter()
	{
		controller = new HadoopController();
	}
	
	/**
	 * Given a Node this method will request data from the Hadoop components.  The data returned represents
	 * the results of the MONC algorithm performed on the given Node.
	 * @param node - the Node used to perform the MONC algorithm
	 * @return An ArrayList of type Node.  The Nodes returns represent the results of the MONC algorithm.
	 */
	public ArrayList<Node> drillDown(Node node)
	{
		ArrayList<Node> nodes;
		System.out.println("Adapter: Drill down on node: "+node.toString());
		nodes = controller.run(node, settings.getFilePath(), settings.getFileName());
		
		return nodes;
	}
	
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

}
