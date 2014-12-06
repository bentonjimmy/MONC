package GradProject.Artifact001;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class KarateJob 
{
	
	private String filepath, filename;
	private static final Log LOG = LogFactory.getLog(KarateJob.class);
	 //private LinkedHashMap<Long, Double> lhm;
	 //private LinkedHashMap<Long, LinkedList<Long>> nodeList;
 
	 public KarateJob(String path, String filename)
	 {
		 this.filepath = path;
		 this.filename = filename;
	 }
	 
	 public void runJob(long growthnode, LinkedHashMap<Long, Double> lhm, TreeMap<Long, ResultsHolder> nodeList)
     {
		 int depth = 0;
		 String outpath = new String(filepath+"/depth_");
		 
		try 
		{
			depth = hadoopJob(growthnode, outpath);
			parseFirstResults(filepath, nodeList);
			parseResults(depth, outpath, lhm, nodeList);
		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 System.out.println("depth is: " + depth);
     }
	 
	 protected void parseFirstResults(String path, TreeMap<Long, ResultsHolder> nodeList)
	 {
		 String line;
		 char[] charResults;
		 
		 //nodeList = new LinkedHashMap<Long, LinkedList<Long>>();
		 
		 try 
		 {
			//open file
			BufferedReader in = new BufferedReader(new FileReader(path+"/text-r-00000"));
			//nodeList = new LinkedHashMap<Long, LinkedList<Long>>();
			while((line = in.readLine()) != null)
			{
				ResultsHolder rh = new ResultsHolder();
				//LinkedList<Long> pointsTo = new LinkedList<Long>();
				//read one line
				
				//get the node ID
				int vertexEquals = line.indexOf("=", line.indexOf("]"));
				int vertexComma = line.indexOf(",", line.indexOf("]"));
				Long nodeID = Long.valueOf(line.substring(vertexEquals+1, vertexComma));
				//remove header area
				int size = line.codePointCount(line.indexOf("{", vertexComma)+1, line.indexOf("}", vertexComma));
				
				charResults = new char[size];
				line.getChars(line.indexOf("{",vertexComma)+1, line.indexOf("}",vertexComma), charResults, 0);
				String stringResults = new String(charResults);
				//tokenize and read into a data structure
				String delim1 = ", ";
				String equals = "=";
				StringTokenizer commaDelim = new StringTokenizer(stringResults, delim1);
				while(commaDelim.hasMoreTokens())
				{
					String[] nodeAndResult = commaDelim.nextToken().split(equals);
					if(nodeAndResult.length != 2)
					{
						System.err.println("Malformed results!");
					}
					//[1] is 0 when it is pointing to itself
					if(Integer.valueOf(nodeAndResult[1]) != 0)
					{
						//pointsTo.add(Long.valueOf(nodeAndResult[0]));
						rh.addPoint(Long.valueOf(nodeAndResult[0]));
					}
				}
				nodeList.put(nodeID, rh);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 protected void parseResults(int depth, String outpath, LinkedHashMap<Long, Double> lhm, TreeMap<Long, ResultsHolder> nodeList)
	 {
		 String line;
		 char[] charResults;
		 //lhm = new LinkedHashMap<Long, Double>();
		 
		 try {
			//open file
			BufferedReader in = new BufferedReader(new FileReader(outpath+(depth-1)+"/text-r-00000"));
			//read one line
			line = in.readLine();
			//remove header area
			int size = line.codePointCount(line.indexOf("{")+1, line.indexOf("}")-1);
			charResults = new char[size];
			line.getChars(line.indexOf("{")+1, line.indexOf("}")-1, charResults, 0);
			String stringResults = new String(charResults);
			//tokenize and read into a data structure
			String delim1 = ", ";
			String equals = "=";
			StringTokenizer commaDelim = new StringTokenizer(stringResults, delim1);
			int order = 0;
			while(commaDelim.hasMoreTokens())
			{
				String[] nodeAndResult = commaDelim.nextToken().split(equals);
				if(nodeAndResult.length != 2)
				{
					System.err.println("Malformed results!");
				}
				ResultsHolder rh = nodeList.get(Long.valueOf(nodeAndResult[0]));
				rh.setResolution(Double.valueOf(nodeAndResult[1]));
				rh.setOrder(order);
				nodeList.put(Long.valueOf(nodeAndResult[0]), rh);
				//lhm.put(Long.valueOf(nodeAndResult[0]), Double.valueOf(nodeAndResult[1]));
				order++;
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		 
	 }
	 
	 protected int hadoopJob(long growthnode, String outpath) throws IOException,
     InterruptedException, ClassNotFoundException 
	 {
		 int depth = 1;

		 Configuration conf = new Configuration();
         conf.set("recursion.depth", depth + "");
         conf.set("growthnode", Long.toString(growthnode));
         Job job = new Job(conf);
         job.setJobName("Karate Club");

         job.setMapperClass(DataImporter.class);
         job.setReducerClass(KarateReducer.class);
         job.setJarByClass(DataImporter.class);

         Path in = new Path(filepath+"/"+filename);
         //Path out = new Path("/Users/jmb66/Documents/NJIT/GradProject/DataSets/KarateClub/depth_1");
         Path out = new Path(outpath + depth);
         
         FileInputFormat.addInputPath(job, in);
         FileSystem fs = FileSystem.get(conf);
         if (fs.exists(out))
                 fs.delete(out, true);

         //SequenceFileOutputFormat.setOutputPath(job, out);
         FileOutputFormat.setOutputPath(job, out);
         //FileInputFormat.setInputPaths(job, in);
         
         job.setInputFormatClass(TextInputFormat.class);
         
         job.setOutputFormatClass(SequenceFileOutputFormat.class);
         //job.setOutputFormatClass(TextOutputFormat.class);
         job.setOutputKeyClass(GrowthNode.class);
         job.setOutputValueClass(VertexWritable.class);
         
         // Defines additional single text based output 'text' for the job
         MultipleOutputs.addNamedOutput(job, "text", TextOutputFormat.class, GrowthNode.class, VertexWritable.class);
         
      // Defines additional sequence-file based output 'sequence' for the job
         MultipleOutputs.addNamedOutput(job, "seq", SequenceFileOutputFormat.class, GrowthNode.class, VertexWritable.class);

         job.waitForCompletion(true);

         long counter = job.getCounters().findCounter(KarateReducer.UpdateCounter.UPDATED).getValue();
         depth++;
         while (counter > 0) {
                 conf = new Configuration();
                 conf.set("recursion.depth", depth + "");
                 job = new Job(conf);
                 job.setJobName("Karate Club " + depth);

                 job.setMapperClass(KarateMapper.class);
                 job.setReducerClass(KarateReducer.class);
                 job.setJarByClass(KarateMapper.class);

                 in = new Path(filepath+"/depth_" + (depth - 1) + "/");
                 //out = new Path("/Users/jmb66/Documents/NJIT/GradProject/DataSets/KarateClub/depth_" + depth);
                 out = new Path(outpath + depth);
                 
                 //SequenceFileInputFormat.addInputPath(job, in);
                 if (fs.exists(out))
                         fs.delete(out, true);

                 //SequenceFileOutputFormat.setOutputPath(job, out);
                 FileOutputFormat.setOutputPath(job, out);
                 FileInputFormat.addInputPath(job, in);
                 
                 job.setInputFormatClass(SequenceFileInputFormat.class);
                 //job.setInputFormatClass(TextInputFormat.class);
                 
                 job.setOutputFormatClass(SequenceFileOutputFormat.class);
                 //job.setOutputFormatClass(TextOutputFormat.class);
                 job.setOutputKeyClass(GrowthNode.class);
                 job.setOutputValueClass(VertexWritable.class);
                 
                 //Defines additional single text based output 'text' for the job
                 MultipleOutputs.addNamedOutput(job, "text", TextOutputFormat.class, GrowthNode.class, VertexWritable.class);
                 
                 //Defines additional sequence-file based output 'sequence' for the job
                 MultipleOutputs.addNamedOutput(job, "seq", SequenceFileOutputFormat.class, GrowthNode.class, VertexWritable.class);

                 job.waitForCompletion(true);
                 depth++;
                 counter = job.getCounters().findCounter(KarateReducer.UpdateCounter.UPDATED).getValue();
         }
         
         return depth;
	 }

}
