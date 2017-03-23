package edu.njit.gradproject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;

public class ResolutionParameter 
{
	public static final double inclusion(GrowthNode gn, VertexWritable vw)
	{
		//calculate vw's kin value
		long vwkin = 0;
		//Get the list of nodes in the community
		LinkedHashMap<LongWritable, DoubleWritable> community = gn.getIncl();
		//Go through each node in the community and check if vw has a connection to them
		TreeMap<LongWritable, LongWritable> vwPoints = vw.getPointsTo();
		int vwsize = vwPoints.size();
		//Do this while there are still nodes in the list and you haven't checked all of vw's nodes
		Set<LongWritable> communityKeys = community.keySet();
		Iterator<LongWritable> iter = communityKeys.iterator();
		while(iter.hasNext())
		{
			LongWritable key = iter.next();
			LongWritable value = vwPoints.get(key);
			if(value != null) //connection to a node in the community
			{
				vwkin += value.get();
			}
		}
		//Set kin equal to 2 times the number of internal connections
		vw.setKin(new LongWritable(vwkin*2));
		
		double numerator = Math.log((double)gn.getKin().get()+(double)vw.getKin().get()+1.0) - Math.log((double)gn.getKin().get()+1.0);
		double denominator = Math.log((double)gn.getKtot().get()+(double)vw.getKtot().get()) - Math.log((double)gn.getKtot().get());
		double resolution = numerator/denominator;
		return resolution;
	}
	
	public static final double exclusion(GrowthNode gn, VertexWritable vw)
	{
		return 0;
	}
}
