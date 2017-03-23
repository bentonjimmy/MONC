package edu.njit.gradproject;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * The DataNode implements the Node interface and represents a node in a network.  
 * @author Jim Benton
 *
 */
public class DataNode implements Node {
	
	/**
	 * A no argument constructor for the DataNode class.  The initial color of the DataNode is set
	 * to red.
	 */
	public DataNode()
	{
		pointsTo = new LinkedHashMap<Integer, Long>();
		color = Color.RED;
		pointsToNum = 1;
	}
	
	/**
	 * This constructor takes a long number to construct the DataNode.  The long number is used to set
	 * the ID of the DataNode being constructed.
	 * @param id - the number used as the ID of the DataNode
	 */
	public DataNode(long id)
	{
		this();
		this.id = id;
		this.label = Long.toString(id);
	}
	
	/**
	 * This constructor takes a long number and a String to construct the DataNode.  The long number is used to set
	 * the ID of the DataNode being constructed.  The String argument is used to label the DataNode.
	 * @param id - the number used as the ID of the DataNode
	 * @param label - the label given to the DataNode
	 */
	public DataNode(long id, String label)
	{
		this();
		this.id = id;
		this.label = label;
	}
	
	/**
	 * This creates a connection between this DataNode and the DataNode identified by connection.
	 * @param connection
	 */
	public void addConnection(long connection)
	{
			pointsTo.put(pointsToNum, connection);
			pointsToNum++;
	}
	
	public long getConnection(Integer position)
	{
		return pointsTo.get(position);
	}
	
	/**
	 * Sets all the connections for the DataNode.
	 * @param connections - a LinkedHashMap of type <Integer, Long> used to create connections for the 
	 * DataNode
	 */
	public void setConnections(LinkedHashMap<Integer, Long> connections)
	{
		if(connections != null)
		{
			pointsTo = connections;
		}
	}
	
	public Iterator<Integer> connectionsIterator()
	{
		Set<Integer> iSet = pointsTo.keySet();
		return iSet.iterator();
	}
	
	public int getRepresents() {
		return represents;
	}
	public void setRepresents(int represents) {
		this.represents = represents;
	}
	public ArrayList<Long> getRepresentsIDs() {
		return representsIDs;
	}
	public void setRepresentsIDs(ArrayList<Long> representsIDs) {
		this.representsIDs = representsIDs;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color c) {
		color = c;
	}
	public String toString()
	{
		return label;
	}
	
	public double getResolution() {
		return resolution;
	}

	public void setResolution(double resolution) {
		this.resolution = resolution;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	protected int represents;
	protected ArrayList<Long> representsIDs;
	protected long id;
	protected String label;
	protected double resolution;
	protected Color color;
	protected LinkedHashMap<Integer, Long> pointsTo;
	private int pointsToNum;
	private int order;
	
}
