package edu.njit.gradproject;

import java.util.ArrayList;

/**
 * The Controller class is used to create a bridge for the data coming from the Adapter to the components
 * used to visualize the data.  Its main job is receiving data from the adapter and telling the view components
 * to update with the new data.
 * @author Jim Benton
 *
 */
public class Controller 
{
	private Adapter sa;
	private ViewController vc;
	
	public Controller(ViewController vc, Adapter sa)
	{
		this.vc = vc;
		this.sa = sa;
	}
	
	/**
	 * This method is used to retrieve new data based off a request from the view
	 * @param node - the node selected by the user
	 * @param action - the action that needs to be performed by the adapter
	 * @return an ArrayList of type Node that is used to display updated results
	 */
	public ArrayList<Node> updateModel(Node node, String action)
	{
		if(action.equalsIgnoreCase("retrieve"))
		{
			return sa.drillDown(node);
		}
		return null;
	}

	public Adapter getSa() {
		return sa;
	}

	public void setSa(Adapter sa) {
		this.sa = sa;
	}

	public ViewController getVc() {
		return vc;
	}

	public void setVc(ViewController vc) {
		this.vc = vc;
	}

}
