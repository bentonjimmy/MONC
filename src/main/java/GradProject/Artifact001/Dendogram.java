package GradProject.Artifact001;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.BalloonLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Dendogram extends JPanel implements DrawGraph 
{
	private ArrayList<Node> nodes;
	private TreeMap<Integer, DataNode> nodeTree;
	private Dimension dim;
	
	public Dendogram(ArrayList<Node> nodes) 
	{
		this.nodes = nodes;
		nodeTree = new TreeMap<Integer, DataNode>();
		for(Node n: nodes)
		{
			nodeTree.put(((DataNode)n).getOrder(), (DataNode)n);
		}
	}

	public JPanel plotData() 
	{
		this.repaint();
		
		return this;
	}
	
	 @Override
	    public void paint(Graphics g)
	    {
	    	super.paint(g);
	    	doDrawing(g);
	    }
	
	private void doDrawing(Graphics g)
	{
		dim = this.getSize();
		int workableWidth = (int)dim.getWidth() - 30;
		int workableHeight = (int)dim.getHeight() - 40;
		int baseline = workableHeight;
		int printline = baseline + 15;
		//This is the space that the graph can be printed in
		Dimension workableSpace =  new Dimension(workableWidth, workableHeight);
		//The number of horizontal lines in the dendrogram
		int numOfHorizLines = nodeTree.size() - 1;
		//Vertical space between each line in the dendrogram
		int verticalSpace = (int) (workableSpace.getHeight() / (nodeTree.size()));
		//The width of the lines in the dendrogram
		int lineWidth = (int) (((int) (workableSpace.getWidth() / numOfHorizLines)) * (1.0 + (1.0/(double)(numOfHorizLines))));
		ArrayList<Point> points = new ArrayList<Point>();
		
		Graphics2D g2d = (Graphics2D)g;
		this.setBackground(Color.white);
		g2d.setColor(Color.DARK_GRAY);
		BasicStroke stroke = new BasicStroke(3);
		g2d.setStroke(stroke);
		
		g2d.drawLine(0, baseline, (int) dim.getWidth(), baseline);
		Point pointLeft = new Point(15, baseline-(verticalSpace));
		Point pointRight = new Point(15+lineWidth, baseline-(verticalSpace));
		//draws horizontal line
		g2d.drawLine((int)pointLeft.getX(), (int)pointLeft.getY(), (int)pointRight.getX(), (int)pointRight.getY());
		//draws line from upper left to baseline
		g2d.drawLine((int)pointLeft.getX(), (int)pointLeft.getY(), (int)pointLeft.getX(), baseline);
		//draws line from upper right to baseline
		g2d.drawLine((int)pointRight.getX(), (int)pointRight.getY(), (int)pointRight.getX(), baseline);
		//print node ID on left
		g2d.drawString(nodeTree.get(0).getLabel(), (int)pointLeft.getX(), printline);
		//print node ID on right
		g2d.drawString(nodeTree.get(1).getLabel(), (int)pointRight.getX(), printline);
		//print node resolution on top of the horizontal line
		g2d.drawString(Double.toString(nodeTree.get(1).getResolution()), (int)pointLeft.getX(), (int)pointLeft.getY()-g2d.getFontMetrics().getAscent());
		points.add(pointLeft);
		points.add(pointRight);
		
		int x = (int) (pointLeft.getX() + (lineWidth/2));
		
		for(int i=1; i<numOfHorizLines; i++)
		{
			pointLeft = new Point(x, baseline-((i+1)*verticalSpace));
			pointRight = new Point(x+lineWidth, baseline-((i+1)*verticalSpace));
			//draws horizontal line
			g2d.drawLine((int)pointLeft.getX(), (int)pointLeft.getY(), (int)pointRight.getX(), (int)pointRight.getY());
			//draws line from upper left to line below it
			g2d.drawLine((int)pointLeft.getX(), (int)pointLeft.getY(), (int)pointLeft.getX(), (int)points.get((i*2)-1).getY());
			//draws line from upper right to baseline
			g2d.drawLine((int)pointRight.getX(), (int)pointRight.getY(), (int)pointRight.getX(), baseline);
			//print node ID on right
			g2d.drawString(nodeTree.get(i+1).getLabel(), (int)pointRight.getX(), printline);
			//print node resolution on top of the horizontal line
			g2d.drawString(Double.toString(nodeTree.get(i+1).getResolution()), (int)pointLeft.getX(), (int)pointLeft.getY()-g2d.getFontMetrics().getAscent());
			points.add(pointLeft);
			points.add(pointRight);
			//increment x to next location
			x = (int) (pointRight.getX() - (lineWidth/2));
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
		
	}

}
