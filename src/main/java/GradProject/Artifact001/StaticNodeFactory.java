package GradProject.Artifact001;

import java.util.ArrayList;

public class StaticNodeFactory implements NodeFactory {
	
	public StaticNodeFactory()
	{
		nextID = 1;
	}

	public Node makeNode(String type) {
		if(type != null)
		{
			if(type.equalsIgnoreCase("datanode"))
			{
				DataNode dn = new DataNode();
				dn.setId(nextID);
				dn.setLabel(Integer.toString(nextID));
				dn.setRepresents(1);
				ArrayList<Integer> represents = new ArrayList<Integer>();
				dn.setRepresentsIDs(represents);
				updateNextID(dn.getId());
				
				return dn;
			}
		}
		return null;
	}
	
	public Node makeNode(String type, Parameters parameters) {
		if(type != null)
		{
			if(type.equalsIgnoreCase("datanode"))
			{
				DataNode dn = new DataNode();
				DataNodeParameters params = (DataNodeParameters)parameters;
				dn.setId(params.getId());
				dn.setLabel(params.getLabel());
				dn.setRepresents(params.getRepresents());
				ArrayList<Integer> represents = new ArrayList<Integer>();
				if(params.getRepresents() > 1)
				{
					if(params.getRepresentsIDs() != null)
					{
						dn.setRepresentsIDs(params.getRepresentsIDs());
					}
					else
					{
						updateNextID(dn.getId());
						for(int i=0; i<params.getRepresents()-1; i++)
						{
							represents.add(nextID);
							updateNextID(nextID);
						}
						dn.setRepresentsIDs(represents);
					}
				}
				else
				{
					dn.setRepresentsIDs(represents);
				}
				
				updateNextID(dn.getId());
				
				return dn;
			}
		}
		
		return null;
	}

	public ArrayList<Node> makeNodes(String type, int amount) {
		if(type != null && amount > 0)
		{
			ArrayList<Node> nodes = new ArrayList<Node>();
			for(int i=0; i<amount; i++)
			{
				nodes.add(makeNode(type));
			}
		}
		return null;
	}
	
	private  void updateNextID(int id)
	{
		if(id >= nextID)
		{
			nextID = id + 1;
		}
	}

	private static int nextID; //looks at all created and received requests for nodes, changes next to one more than largest seen

}
