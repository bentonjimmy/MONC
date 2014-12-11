package GradProject.Artifact001;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class StaticGraphFactory implements GraphFactory 
{

	public DrawGraph makeGraph(ArrayList<Node> nodes, String type) 
	{
		if(type.equalsIgnoreCase("network"))
		{
			return new NetworkGraph(nodes);
		}
		else if(type.equalsIgnoreCase("dendrogram"))
		{
			return new Dendogram(nodes);
		}
		else
			return null;
	}

}
