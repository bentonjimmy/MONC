package GradProject.Artifact001;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

public class DataNode implements Node {
	
	public DataNode()
	{
		pointsTo = new LinkedHashMap<Integer, Long>();
		color = Color.RED;
		pointsToNum = 1;
	}
	
	public DataNode(long id)
	{
		this();
		this.id = id;
		this.label = Long.toString(id);
	}
	
	public DataNode(long id, String label)
	{
		this();
		this.id = id;
		this.label = label;
	}
	
	public void addConnection(long connection)
	{
			pointsTo.put(pointsToNum, connection);
			pointsToNum++;
	}
	
	public long getConnection(Integer position)
	{
		return pointsTo.get(position);
	}
	
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
		return "V"+id;
	}
	
	public double getResolution() {
		return resolution;
	}

	public void setResolution(double resolution) {
		this.resolution = resolution;
	}

	protected int represents;
	protected ArrayList<Long> representsIDs;
	protected long id;
	protected String label;
	protected double resolution;
	protected Color color;
	protected LinkedHashMap<Integer, Long> pointsTo;
	private int pointsToNum;
	
}
