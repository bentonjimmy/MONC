package GradProject.Artifact001;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;

public class HadoopController implements DBController 
{
	private KarateJob job;
	private LinkedHashMap<Long, Double> resolutionOrder;
	private TreeMap<Long, ResultsHolder> networkList;
	
	private static StaticNodeFactory factory;
	
	public HadoopController()
	{
		job = new KarateJob();
		resolutionOrder = new LinkedHashMap<Long, Double>();
		networkList = new TreeMap<Long, ResultsHolder>();
		factory = new StaticNodeFactory();
	}
	
	public ArrayList<Node> run(Node node)
	{
		ArrayList<Node> results = new ArrayList<Node>();
		int increment;
		long growthnode = ((DataNode) node).getId();
		job.runJob(growthnode, resolutionOrder, networkList);
		
		
		//need to determine the color to assign the node
		int networkSize = networkList.size();
		if(networkSize<256)
		{
			increment = Math.floorDiv(255, networkSize);
		}
		else
		{
			increment = 1;
		}
		//transform results into ArrayList<Node> using StaticNodeFactory
		Set<Long> ls = networkList.keySet();//Should be in ascending order

		for(Long l: ls)
		{
			//Create a data node
			DataNodeParameters parameters = new DataNodeParameters();
			parameters.setId(l);
			DataNode dn = (DataNode) factory.makeNode("datanode", parameters);
			
			//Add the node's connections
			ResultsHolder rh = networkList.get(l);
			LinkedList<Long> nodeConnections = rh.getPointsTo();
			for(Long connect: nodeConnections)
			{
				dn.addConnection(connect);
			}
			//Set the node's color
			if(dn.getId() == growthnode)
			{
				dn.setColor(Color.RED);
			}
			else
			{
				dn.setColor(new Color(255, (l*increment), (l*increment)));
			}
			
			results.add(dn);
		}
		
		
		return results;
	}
	

}
