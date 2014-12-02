package GradProject.Artifact001;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.junit.Test;

public class KarateJobTest {

	@Test
	public void test() 
	{
		KarateJob kj = new KarateJob();
		int node = 3;
		LinkedHashMap<Long, Double> lhm = new LinkedHashMap<Long, Double>();
		LinkedHashMap<Long, LinkedList<Long>> nodeList = new LinkedHashMap<Long, LinkedList<Long>>();
		
		kj.runJob(node, lhm, nodeList);
		assertTrue("Test nodeList size", nodeList.size() == 33);
	}

}
