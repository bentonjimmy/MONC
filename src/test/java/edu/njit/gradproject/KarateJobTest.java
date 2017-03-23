package edu.njit.gradproject;

import static org.junit.Assert.*;

import java.util.TreeMap;

import org.junit.Test;

public class KarateJobTest {

	@Test
	public void test() 
	{
		KarateJob kj = new KarateJob("/Users/jmb66/Documents/NJIT/GradProject/DataSets/KarateClub", "zachary_unweighted.txt");
		int node = 3;
		TreeMap<Long, ResultsHolder> nodeList = new TreeMap<Long, ResultsHolder>();
		
		kj.runJob(node, nodeList);
		assertTrue("Test nodeList size", nodeList.size() == 34);
	}

}
