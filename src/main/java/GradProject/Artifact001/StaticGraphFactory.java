package GradProject.Artifact001;

import java.util.ArrayList;
import java.util.Iterator;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class StaticGraphFactory implements GraphFactory {

	public DrawGraph makeGraph(ArrayList<Node> nodes, String type) 
	{
		if(type.equalsIgnoreCase("network"))
		{
			Graph<DataNode, String> graph = new SparseMultigraph<DataNode, String>();
			int i = 1;
			
			for(Node n : nodes)
			{
				Iterator<Integer> connectionsIterator;
				connectionsIterator = ((DataNode) n).connectionsIterator();
				while(connectionsIterator.hasNext())
				{
					Integer connection = connectionsIterator.next();
					//Add an edge connecting n with all of the nodes it has connections with
					graph.addEdge("Edge"+i, (DataNode)n, (DataNode)nodes.get(connection));
					i++;
				}
			}
			return null;
		}
		else
			return null;
	}

}
