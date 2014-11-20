package GradProject.Artifact001;

import java.util.ArrayList;
import java.util.HashMap;

public class DataNodeParameters implements Parameters{
	
	public DataNodeParameters()
	{
		_parameters = new HashMap<String, String>();
	}
	
	
	/**
	 * Adds a parameter with the name <code>name</code> and the value <code>value</code>.
	 * @param name - String used to reference the parameter
	 * @param value - The value of the parameter
	 */
	/*
	public void addParameter(String name, String value)
	{
		if(name != null && value != null)
		{
			_parameters.put(name, value);
		}
	}
	*/
	
	/**
	 * Retrieves the value of the parameter with the given <code>name</code>.  If not parameter
	 * matches the name then <code>null</code> is returned.
	 * @param name - the name of the parameter
	 * @return - the String value associates with the parameter name
	 */
	/*
	public String getParameter(String name)
	{
		if(name != null)
		{
			return _parameters.get(name);
		}
		else
		{
			return null;
		}
	}
	*/
	
	public int getRepresents() {
		return _represents;
	}

	public void setRepresents(int represents) {
		this._represents = represents;
	}

	public ArrayList<Integer> getRepresentsIDs() {
		return _representsIDs;
	}

	public void setRepresentsIDs(ArrayList<Integer> representsIDs) {
		this._representsIDs = representsIDs;
	}
	
	public int getId() {
		return _id;
	}

	public void setId(int id) {
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
	private ArrayList<Integer> _representsIDs;
	private int _id;
	private String _label;

}
