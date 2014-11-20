package GradProject.Artifact001;

/**
 * Settings class that is used to create individual settings.
 * @author Jim Benton
 *
 */
public class Settings{
	//Interacts with Jackson
	public String getName() {
		return _name;
	}
	public void setName(String name) {
		this._name = name;
	}
	public String getType() {
		return _type;
	}
	public void setType(String type) {
		this._type = type;
	}
	public String getValue() {
		return _value;
	}
	public void setValue(String value) {
		this._value = value;
	}
	
	private String _name;
	private String _type;
	private String _value;

}
