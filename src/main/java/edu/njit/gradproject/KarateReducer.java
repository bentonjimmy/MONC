package edu.njit.gradproject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class KarateReducer extends Reducer<GrowthNode, VertexWritable, GrowthNode, VertexWritable> 
{
	static final Log LOG = LogFactory.getLog(KarateReducer.class);
	
	public static enum UpdateCounter
	{
		UPDATED
	}
	
	boolean depthOne;
	private MultipleOutputs<GrowthNode, VertexWritable> multiOut;
	
	protected void setup(Context context) throws IOException, InterruptedException
	{
		super.setup(context);
		multiOut = new MultipleOutputs<GrowthNode, VertexWritable>(context);
		if (Integer.parseInt(context.getConfiguration().get("recursion.depth")) == 1)
            depthOne = true;

	}

	protected void reduce(GrowthNode key, Iterable<VertexWritable> values, Context context) 
			throws IOException, InterruptedException
	{
		List<VertexWritable> vertices = new LinkedList<VertexWritable>();
		
		if(depthOne == true) //First time being reduced
		{
			GrowthNode growthNode = new GrowthNode();
			//Get the ID of the node that the community is growing around
			int growthNodeId = Integer.parseInt(context.getConfiguration().get("growthnode"));
			for(VertexWritable vertex : values)
			{
				if(vertex.getMinimalVertexId().get() == growthNodeId)
				{
					VertexWritable growthVertex = vertex.clone();
					growthNode = new GrowthNode(growthVertex);
					vertex.setActivated(true);
					vertices.add(new VertexWritable(vertex));
				}
				else
				{
					vertex.setActivated(true);
					vertices.add(new VertexWritable(vertex));
				}
			}
			
			for(VertexWritable vertex : vertices)
			{
				if(vertex.isActivated())
				{
					if(vertex.getMinimalVertexId().get() != growthNode.getVertexId().get())
					{
						multiOut.write("seq", growthNode, vertex);
					}
					multiOut.write("text", growthNode, vertex, context.getConfiguration().get("textoutput")+"/text");

					LOG.info(growthNode + " | " + vertex);
				}
			}
			context.getCounter(UpdateCounter.UPDATED).increment(1);
		}
		else //all other times being reduced
		{
			double maxIncl = 0;
			long inclId = 0;
			VertexWritable maxVertex = new VertexWritable();
			
			for(VertexWritable vertex : values) //Find the largest incl value
			{
				vertex.setActivated(true);
				vertices.add(new VertexWritable(vertex));
				if(vertex.getIncl().get() > maxIncl)
				{
					maxIncl = vertex.getIncl().get();
					inclId = vertex.getMinimalVertexId().get();
					maxVertex = vertex.clone();
				}
			}
			
			
			//Test for end of algorithm - no more nodes to add or no other nodes can join the group
			if(vertices.size() >= 1 && maxIncl > 0)
			{
				//Add the node with the largest incl value
				key.addIncl(new LongWritable(inclId), new DoubleWritable(maxIncl));
				//Add the node's kin and ktot values to the growth node
				key.addToKin(maxVertex.getKin());
				key.addToKtot(maxVertex.getKtot());
				LOG.info("Adding vertice " + maxVertex.getMinimalVertexId() + ", resolution: " + maxIncl);
				TreeMap<LongWritable, LongWritable> vertexPoints = maxVertex.pointsTo;
				Set<LongWritable> keyset = vertexPoints.keySet();
				//add the points that the growth node will now point to
				for(LongWritable lw : keyset)
				{
					//iterate through points, check if they are already in the growth Node's list
					//add them if they are not
					if(key.getPoint(lw) == null)
					{
						key.addPoint(new LongWritable(lw.get()), new LongWritable(maxVertex.getPoint(lw).get())); //make this accessible to the growth node
						
					}
				}
				
				//Only increment if there is more than one vertex in vertices
				if(vertices.size() > 1)
				{
					context.getCounter(UpdateCounter.UPDATED).increment(1);
					
					for(VertexWritable vertex : vertices)
					{
						if(vertex.getMinimalVertexId().get() == inclId)
						{
							vertex.setActivated(false);
						}
						
						if(vertex.isActivated())
						{
							multiOut.write("seq", key, vertex);
							LOG.info(key + " | " + vertex);
						}
					}
				}
				else
				{
					multiOut.write("text", key, vertices.get(0));
				}
			}
			
			else
			{
				multiOut.write("text", key, vertices.get(0));
			}
			
		}	
	}
	
	protected void cleanup(Context context) throws IOException, InterruptedException 
	{
		 multiOut.close();
	}
}
