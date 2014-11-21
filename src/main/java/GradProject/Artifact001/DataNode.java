package GradProject.Artifact001;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DataNode implements Node {
	
	public DataNode()
	{
		pointsTo = new LinkedList<Integer>();
		color = Color.RED;
	}
	
	public DataNode(int id)
	{
		this();
		this.id = id;
		this.label = Integer.toString(id);
	}
	
	public DataNode(int id, String label)
	{
		this();
		this.id = id;
		this.label = label;
	}
	
	public void addConnection(Integer connection)
	{
		if(connection != null)
		{
			pointsTo.add(connection);
		}
	}
	
	public void setConnections(LinkedList<Integer> connections)
	{
		if(connections != null)
		{
			pointsTo = connections;
		}
	}
	
	public Iterator<Integer> connectionsIterator()
	{
		return pointsTo.iterator();
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRepresents() {
		return represents;
	}
	public void setRepresents(int represents) {
		this.represents = represents;
	}
	public ArrayList<Integer> getRepresentsIDs() {
		return representsIDs;
	}
	public void setRepresentsIDs(ArrayList<Integer> representsIDs) {
		this.representsIDs = representsIDs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getBorder() {
		return border;
	}
	public void setBorder(int border) {
		this.border = border;
	}
	public int getDiameter() {
		return diameter;
	}
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
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
	protected int x;
	protected int y;
	protected int represents;
	protected ArrayList<Integer> representsIDs;
	protected int id;
	protected String label;
	protected Color color;
	protected int border;
	protected int diameter;
	protected int length;
	protected LinkedList<Integer> pointsTo;
	
}
