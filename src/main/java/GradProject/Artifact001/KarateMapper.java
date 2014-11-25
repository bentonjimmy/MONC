package GradProject.Artifact001;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class KarateMapper extends Mapper<GrowthNode, VertexWritable, GrowthNode, VertexWritable> 
{
	private MultipleOutputs<GrowthNode, VertexWritable> multiOut;
	protected void setup(Context context) throws IOException, InterruptedException
	{
		multiOut = new MultipleOutputs<GrowthNode, VertexWritable>(context);
	}
	
	@Override
	public void map(GrowthNode key, VertexWritable value, Context context)
			throws IOException, InterruptedException
	{
		value.setIncl(new DoubleWritable(ResolutionParameter.inclusion(key, value)));
		//multiOut.write("seq", key, value);
		context.write(key, value);
	}
	
	protected void cleanup(Context context) throws IOException, InterruptedException 
	{
		 multiOut.close();
	}
	
}