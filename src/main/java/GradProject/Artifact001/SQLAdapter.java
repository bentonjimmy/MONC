package GradProject.Artifact001;

import java.util.ArrayList;

public class SQLAdapter 
{
	private HadoopController controller;
	
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
	
	public void upALevel(){}

}
