package GradProject.Artifact001;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

/**
 * This class extends the Mapper class and is used to initially import the data used for processing.
 * @author Jim Benton
 *
 */
public class DataImporter extends Mapper<LongWritable, Text, GrowthNode, VertexWritable> 
{
	private MultipleOutputs<GrowthNode, VertexWritable> multiOut;
	
	protected void setup(Context context) throws IOException, InterruptedException
	{
		multiOut = new MultipleOutputs<GrowthNode, VertexWritable>(context);
	}
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		//Import with empty growth node, set the growth node in the reducer the first time through
		VertexWritable vertex = new VertexWritable();
		LongWritable theNode = null;
		//Parse the line of data
		String[] values = value.toString().split(" ");
		int currentPosition = 0;
		long totalweight = 0;
		for(String s : values)
		{
			if(currentPosition == 0) //Set the ID of the vertex
			{
				theNode = new LongWritable(Long.parseLong(s));
				vertex.checkAndSetMinVertex(theNode);
				vertex.addPoint(theNode, new LongWritable(0));
			}
			else //Read the other values from the line
			{
				LongWritable tempValue = new LongWritable(Long.parseLong(s));
				if(tempValue.get() > 0) //there is a connection in the graph
				{
					LongWritable tempKey = new LongWritable(currentPosition);
					//vertex.checkAndSetMinVertex(tempKey);
					vertex.addPoint(tempKey, tempValue);
					totalweight += tempValue.get();
				}
			}
			currentPosition++;
		}
		
		//kin initially 0
		vertex.setKin(new LongWritable(0));
		//kout is size of pointsTo
		vertex.setKtot(new LongWritable(totalweight));
		//set incl to 0
		vertex.setIncl(new DoubleWritable(0));
		//Create fake Growth Node
		VertexWritable fillerVertex = new VertexWritable(new LongWritable(-1));
		fillerVertex.setKin(new LongWritable(0));
		fillerVertex.setKtot(new LongWritable(totalweight));
		fillerVertex.setIncl(new DoubleWritable(0));
		GrowthNode fillerNode = new GrowthNode(fillerVertex);
		context.write(fillerNode, vertex); //output the Node and its VertexWritable
		
	}
	
	protected void cleanup(Context context) throws IOException, InterruptedException 
	{
		 multiOut.close();
	}
}
