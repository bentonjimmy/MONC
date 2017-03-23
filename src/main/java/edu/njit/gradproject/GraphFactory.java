package edu.njit.gradproject;

import java.util.ArrayList;

public interface GraphFactory {
	
	public DrawGraph makeGraph(ArrayList<Node> nodes, String type);

}
