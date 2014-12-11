package GradProject.Artifact001;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ViewController 
{
	private Dimension dim;
	private StaticGraphFactory gf;
	private ArrayList<Node> nodes;
	private String style;
	private int nid = -1;
	private Controller controller;
	private JPanel thePanel;
	
	public static final String NETWORK_GRAPH = new String("Network");
	public static final String DENDROGRAM_GRAPH = new String("Dendrogram");
	
	public ViewController(Dimension dim)
	{
		this.dim = dim;
		gf = new StaticGraphFactory();
		style = "network";
	}
	
	
	//Retrieving the graph for a new node
	public JPanel updateView(int nodeID, String style)
	{
		if(nodeID != nid) //Retrieve new data
		{
			DataNode dn = new DataNode(nodeID);
			nodes = controller.updateModel(dn, "retrieve");
			changeGraph(style);
		}
		else //We are already using this node
		{
			System.out.println("ViewController: Already using node " + nodeID);
		}
		return thePanel;
	}
	
	//Changing from one graph to another
	public JPanel changeGraph(String style)
	{
		//only change the graph if it wasn't already set to that type
		if(this.style.equalsIgnoreCase(style) == false)
		{
			this.style = style;
			System.out.println("ViewController: Changing graph type to: " + style);
			DrawGraph graph = gf.makeGraph(nodes, this.style);
			thePanel = graph.plotData();
		}
		return thePanel;
	}


	public Dimension getDim() {
		return dim;
	}


	public void setDim(Dimension dim) {
		this.dim = dim;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}


	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}
	
}
