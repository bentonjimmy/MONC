package edu.njit.gradproject;

import java.util.LinkedList;

public class ResultsHolder 
{
	ResultsHolder()
	{
		pointsTo = new LinkedList<Long>();
	}
	
	public void addPoint(Long point)
	{
		if(point != null)
		{
			pointsTo.add(point);
		}
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public double getResolution() {
		return resolution;
	}

	public void setResolution(double resolution) {
		this.resolution = resolution;
	}

	public LinkedList<Long> getPointsTo() {
		return pointsTo;
	}

	public void setPointsTo(LinkedList<Long> pointsTo) {
		this.pointsTo = pointsTo;
	}

	private int order;
	private double resolution;
	private LinkedList<Long> pointsTo;
	
}
