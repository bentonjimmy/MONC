package GradProject.Artifact001;

import java.util.ArrayList;

public class Controller 
{
	private SQLAdapter sa;
	private ViewController vc;
	
	public Controller(ViewController vc, SQLAdapter sa)
	{
		this.vc = vc;
		this.sa = sa;
	}
	
	public void updateView(ArrayList<Node> nodelist){}
	
	public ArrayList<Node> updateModel(Node node, String action)
	{
		if(action.equalsIgnoreCase("retrieve"))
		{
			return sa.drillDown(node);
		}
		return null;
	}

}
