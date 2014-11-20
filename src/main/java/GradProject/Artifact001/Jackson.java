package GradProject.Artifact001;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Given the file location of a json file containing the applications settings, Jackson will read the json
 * data from the file and create a Settings object based on the settings read.
 * @author Jim Benton
 *
 */
public class Jackson {

	/**
	 * The Jackson constructor takes a file location as its only parameter.  The json data is read from the file
	 * and used to create the object holding the Setting objects.
	 * @param fileloc - location of the json file to read
	 */
	public Jackson(String fileloc)
	{
		this._fileloc = fileloc;
		_settings = new SettingAccessor();
		//Read the settings from the file
		fillSettings();
	}
	
	public SettingAccessor getSettingAccessor()
	{
		return _settings;
	}
	
	public boolean writeSettingsToFile(SettingAccessor settings)
	{
		return false;
	}
	
	public String getFileloc() {
		return _fileloc;
	}

	public void setFileloc(String fileloc) {
		this._fileloc = fileloc;
	}
	
	private void fillSettings()
	{
		ObjectMapper mapper = new ObjectMapper();
		Settings[] setArray;
		try {
			setArray = mapper.readValue(new File(_fileloc), Settings[].class);
			for(int i=0; i<setArray.length; i++)
			{
				_settings.addSetting(setArray[i]);
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File could not be read");
			e.printStackTrace();
		}
		
	}


	private String _fileloc;
	private SettingAccessor _settings;
	
}
