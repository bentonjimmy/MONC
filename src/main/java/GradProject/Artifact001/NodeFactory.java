package GradProject.Artifact001;

import java.util.ArrayList;

public interface NodeFactory {
	
	public Node makeNode(String type);
	public Node makeNode(String type, Parameters parameters);
	public ArrayList<Node> makeNodes(String type, int amount);

}
