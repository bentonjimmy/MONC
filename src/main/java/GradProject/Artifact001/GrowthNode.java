package GradProject.Artifact001;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

public class GrowthNode implements WritableComparable<GrowthNode> {

	private VertexWritable vertex;
	//private LongWritable kin, ktot;
	private LinkedHashMap<LongWritable, DoubleWritable> inclMap;
	
	public GrowthNode()
	{
		super();
		inclMap = new LinkedHashMap<LongWritable, DoubleWritable>();
	}
	
	public GrowthNode(VertexWritable vertex)
	{
		this();
		this.vertex = vertex;
		this.addIncl(vertex.getMinimalVertexId(), new DoubleWritable(0));
	}
	
	public boolean inCommunity(LongWritable id)
	{
		return inclMap.containsKey(id);
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		
		int length = in.readInt();
		if(length > -1)
		{
			inclMap = new LinkedHashMap<LongWritable, DoubleWritable>();
			for(int i=0; i<length; i++)
			{
				LongWritable newKey = new LongWritable();
				newKey.readFields(in); //read the key
				DoubleWritable newValue = new DoubleWritable();
				newValue.readFields(in); //read the value
				inclMap.put(newKey, newValue); //add the key/value pair to pointsTo
			}
		}
		
		vertex =  new VertexWritable();
		vertex.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		
		if(inclMap == null)
		{
			out.writeInt(-1);
		}
		else
		{
			int mapsize = inclMap.size();
			out.writeInt(mapsize);
			Set<LongWritable> keys = inclMap.keySet(); //get all the keys
			for(LongWritable lw : keys)
			{
				lw.write(out); //write the key
				inclMap.get(lw).write(out); //write the value
			}
		}
		vertex.write(out);
	}

	@Override
	public int compareTo(GrowthNode o) {
		if(o.getVertexId().get() == this.getVertexId().get())
			return 0;
		else
			return 1;
	}
	
	@Override
	public String toString()
	{
		//change to incl
		return "GN [" + inclMap + "]";
	}
	
	/**
	 * Adds a node and it's inclusion resolution parameter to the community the growth node
	 * is creating
	 * @param id - the ID of the node being added
	 * @param incl - the value of the inclusion resolution parameter
	 */
	public void addIncl(LongWritable id, DoubleWritable incl)
	{	
		inclMap.put(id, incl);
	}
	
	public LinkedHashMap<LongWritable, DoubleWritable> getIncl()
	{
		return inclMap;
	}
	
	public LongWritable getPoint(LongWritable key)
	{
		return vertex.getPoint(key);
	}
	
	public void addPoint(LongWritable id, LongWritable weight)
	{
		vertex.addPoint(id, weight);
	}
	
	public LongWritable addToKin(LongWritable value)
	{
		Long vertexKin = vertex.getKin().get() + value.get();
		vertex.setKin(new LongWritable(vertexKin));
		return new LongWritable(vertexKin);
	}

	public LongWritable addToKtot(LongWritable value)
	{
		Long vertexTot = vertex.getKtot().get() + value.get();
		vertex.setKtot(new LongWritable(vertexTot));
		return new LongWritable(vertexTot);
	}
	
	public LongWritable getKin() {
		return vertex.getKin();
	}

	public void setKin(LongWritable kin) {
		vertex.setKin(kin);
	}

	public LongWritable getKtot() {
		return vertex.getKtot();
	}

	public void setKtot(LongWritable ktot) {
		vertex.setKtot(ktot);
	}
	
	public LongWritable getVertexId()
	{
		return vertex.getMinimalVertexId();
	}

}
