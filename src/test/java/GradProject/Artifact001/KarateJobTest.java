package GradProject.Artifact001;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import org.junit.Test;

public class KarateJobTest {

	@Test
	public void test() 
	{
		KarateJob kj = new KarateJob("/Users/jmb66/Documents/NJIT/GradProject/DataSets/KarateClub", "zachary_unweighted.txt");
		int node = 3;
		LinkedHashMap<Long, Double> lhm = new LinkedHashMap<Long, Double>();
		TreeMap<Long, ResultsHolder> nodeList = new TreeMap<Long, ResultsHolder>();
		
		kj.runJob(node, lhm, nodeList);
		assertTrue("Test nodeList size", nodeList.size() == 34);
	}

}
