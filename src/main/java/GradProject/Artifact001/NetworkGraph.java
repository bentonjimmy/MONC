package GradProject.Artifact001;

import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class NetworkGraph implements DrawGraph {

	private Graph<DataNode, String> graph;
	private Layout<DataNode, String> layout;
	private VisualizationViewer<DataNode,String> vv;
	
	public NetworkGraph(ArrayList<Node> nodes)
	{
		graph = new SparseMultigraph<DataNode, String>();
		int i = 1;
		
		for(Node n : nodes)//loop through all nodes
		{
			Iterator<Integer> connectionsSet;
			connectionsSet = ((DataNode) n).connectionsIterator();
			//Iterate through all the connections node n has
			while(connectionsSet.hasNext())
			{
				Integer position = connectionsSet.next();
				long dn = ((DataNode)n).getConnection(position);
				//Add an edge connecting n with all of the nodes it has connections with
				if(((DataNode)n).getId() < dn) //Only create a connection between nodes once
				{
					graph.addEdge("Edge"+i, (DataNode)n, (DataNode) nodes.get((int) dn));
					i++;
				}
			}
		}
		
		layout = new SpringLayout<DataNode, String>(graph);
	
		vv = new VisualizationViewer<DataNode,String>(layout);
		
		Transformer<DataNode,Paint> vertexPaint = new Transformer<DataNode,Paint>() 
				{
					public Paint transform(DataNode dn) 
					{
						return dn.getColor();
					}
				};
				
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<DataNode>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		
		// Create a graph mouse and add it to the visualization component
		DefaultModalGraphMouse<DataNode, String> gm = new DefaultModalGraphMouse<DataNode, String>();
		gm.setMode(Mode.TRANSFORMING);
		vv.setGraphMouse(gm); 
	}
	
	public JPanel plotData() 
	{
		return vv;
	}

}
