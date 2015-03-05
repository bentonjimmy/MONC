package GradProject.Artifact001;

import java.util.ArrayList;
import java.util.HashMap;

public class DataNodeParameters implements Parameters{
	
	public DataNodeParameters()
	{
		_parameters = new HashMap<String, String>();
	}
	
	
	public int getRepresents() {
		return _represents;
	}

	public void setRepresents(int represents) {
		this._represents = represents;
	}

	public ArrayList<Long> getRepresentsIDs() {
		return _representsIDs;
	}

	public void setRepresentsIDs(ArrayList<Long> representsIDs) {
		this._representsIDs = representsIDs;
	}
	
	public long getId() {
		return _id;
	}

	public void setId(long id) {
		this._id = id;
	}

	public String getLabel() {
		return _label;
	}

	public void setLabel(String label) {
		this._label = label;
	}

	private HashMap<String, String> _parameters;
	private int _represents;
	private ArrayList<Long> _representsIDs;
	private long _id;
	private String _label;

}
