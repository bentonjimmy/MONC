package GradProject.Artifact001;

import java.util.HashMap;

public class SettingAccessor implements SettingsDAO {
	/**
	 * Constructor for SettingAccessor
	 */
	public SettingAccessor()
	{
		settings = new HashMap<String, Settings>();
	}

	/**
	 * Retrieves the setting for the given id.  If no setting is found then null
	 * is returned
	 */
	public String retreiveSetting(String id) 
	{
		if(id != null)
		{
			Settings setting = settings.get(id);
			if(setting != null)
			{
				return setting.getValue();
			}
		}
		
		return null;
	}

	/**
	 * Sets the setting with a given id to the value passed.  True is returned if the setting
	 * was successfully updated.  Otherwise, false is returned.
	 */
	public boolean setSetting(String id, String value) 
	{
		if(id != null)
		{
			Settings setting = settings.get(id);
			if(setting != null)
			{
				setting.setValue(value);
				settings.put(id, setting);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds the given setting to the list of settings.  If the setting already exists it will overwrite the value.
	 * If the setting is successfully added then true is returned.  Otherwise, false is returned.
	 */
	public boolean addSetting(Settings setting)
	{
		if(setting != null)
		{
			settings.put(setting.getName(), setting);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the setting with the given id from the settings.  True is returned if the setting
	 * is successfully removed.  Otherwise, false is returned.
	 */
	public boolean removeSetting(String id)
	{
		if(id != null)
		{
			String remove = this.retreiveSetting(id);
			if(remove != null)
			{
				settings.remove(id);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the number of settings
	 */
	public int count()
	{
		return settings.size();
	}
	
	private HashMap <String, Settings> settings;

}
