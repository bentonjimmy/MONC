package GradProject.Artifact001;

import java.util.ArrayList;

public class SQLAdapter 
{
	private HadoopController controller;
	private Settings settings;
	
	public SQLAdapter()
	{
		controller = new HadoopController();
	}
	
	public ArrayList<Node> drillDown(Node node)
	{
		ArrayList<Node> nodes;
		nodes = controller.run(node, "/Users/jmb66/Documents/NJIT/GradProject/DataSets/KarateClub", "zachary_unweighted.txt");
		
		return nodes;
	}
	
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

}
