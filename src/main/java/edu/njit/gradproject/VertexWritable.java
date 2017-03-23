package edu.njit.gradproject;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;

public class VertexWritable implements Writable, Cloneable 
{
	private LongWritable minimalVertexId;
	TreeMap<LongWritable, LongWritable> pointsTo;
	private boolean activated;
	private DoubleWritable incl;
	private LongWritable kin, ktot;

	public VertexWritable()
	{
		super();
	}
	
	public VertexWritable(LongWritable minVertexId)
	{
		super();
		this.minimalVertexId = minVertexId;
	}
	
	public VertexWritable(VertexWritable vw)
	{
		super();
		this.minimalVertexId = vw.getMinimalVertexId();
		this.activated = vw.isActivated();
		this.incl = vw.getIncl();
		this.kin = vw.getKin();
		this.ktot = vw.getKtot();
		//copy over PointsTo
		if(vw.pointsTo != null)
		{
			this.pointsTo = new TreeMap<LongWritable, LongWritable>();
			Set<LongWritable> keys = vw.pointsTo.keySet(); //get all the keys
			for(LongWritable lw : keys)
			{
				LongWritable value = vw.pointsTo.get(lw); //Retrieve value
				this.pointsTo.put(new LongWritable(lw.get()), new LongWritable(value.get())); //add key/value pair to clone
			}
		}
	}
	
	@Override
	public void readFields(DataInput in) throws IOException 
	{
		minimalVertexId = new LongWritable();
		minimalVertexId.readFields(in);
		incl = new DoubleWritable();
		incl.readFields(in);
		kin = new LongWritable();
		kin.readFields(in);
		ktot = new LongWritable();
		ktot.readFields(in);
		int length = in.readInt();
		if(length > -1)
		{
			pointsTo = new TreeMap<LongWritable, LongWritable>();
			for(int i=0; i<length; i++)
			{
				LongWritable newKey = new LongWritable();
				newKey.readFields(in); //read the key
				LongWritable newValue = new LongWritable();
				newValue.readFields(in); //read the value
				pointsTo.put(newKey, newValue); //add the key/value pair to pointsTo
			}
		}
	}

	@Override
	public void write(DataOutput out) throws IOException 
	{
		minimalVertexId.write(out);
		incl.write(out);
		kin.write(out);
		ktot.write(out);
		if(pointsTo == null)
		{
			out.writeInt(-1);
		}
		else
		{
			out.writeInt(pointsTo.size());
			Set<LongWritable> keys = pointsTo.keySet(); //get all the keys
			for(LongWritable lw : keys)
			{
				lw.write(out); //write the key
				pointsTo.get(lw).write(out); //write the value
			}
		}
	}
	
	@Override
	public String toString()
	{
		return "[VertexId=" + minimalVertexId
                + ", pointsTo=" + pointsTo + "]";
	}
	
	@Override
	public VertexWritable clone()
	{
		VertexWritable newVertex = new VertexWritable(minimalVertexId);
		newVertex.setIncl(new DoubleWritable(incl.get()));
		newVertex.setKin(new LongWritable(kin.get()));
		newVertex.setKtot(new LongWritable(ktot.get()));
		if(pointsTo != null)
		{
			newVertex.pointsTo = new TreeMap<LongWritable, LongWritable>();
			Set<LongWritable> keys = pointsTo.keySet(); //get all the keys
			for(LongWritable lw : keys)
			{
				LongWritable value = pointsTo.get(lw); //Retrieve value
				newVertex.pointsTo.put(new LongWritable(lw.get()), new LongWritable(value.get())); //add key/value pair to clone
			}
		}
		
		return newVertex;
	}
	
	public boolean checkAndSetMinVertex(LongWritable id)
	{
		if(id != null)
		{
			if(minimalVertexId == null)
			{
				minimalVertexId = id;
				return true;
			}
			else
			{
				if(id.get() < minimalVertexId.get())
				{
					minimalVertexId = id;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean isMessage()
	{
		if(pointsTo == null)
			return true;
		else
			return false;
	}
	
	public VertexWritable makeMessage()
	{
		return new VertexWritable(minimalVertexId);
	}
	
	public void addPoint(LongWritable id, LongWritable weight)
	{
		if(pointsTo == null)
		{
			pointsTo = new TreeMap<LongWritable, LongWritable>();
		}
		pointsTo.put(id, weight);
	}
	
	public LongWritable getPoint(LongWritable key)
	{
		if(pointsTo != null)
		{
			return pointsTo.get(key);
		}
		return null;
	}
	
	public boolean isActivated()
	{
		return activated;
	}
	
	public void setActivated(boolean activated)
	{
		this.activated = activated;
	}

	public DoubleWritable getIncl() {
		return incl;
	}

	public void setIncl(DoubleWritable incl) {
		this.incl = incl;
	}

	public LongWritable getMinimalVertexId() {
		return minimalVertexId;
	}

	public LongWritable getKin() {
		return kin;
	}

	public void setKin(LongWritable kin) {
		this.kin = kin;
	}

	public LongWritable getKtot() {
		return ktot;
	}

	public void setKtot(LongWritable ktot) {
		this.ktot = ktot;
	}

	public TreeMap<LongWritable, LongWritable> getPointsTo() {
		return pointsTo;
	}

}
