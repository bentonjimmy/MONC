package edu.njit.gradproject;

public interface SettingsDAO {
	
	public String retreiveSetting(String id);
	public boolean setSetting(String id, String value);
	public boolean addSetting(Settings setting);
	public boolean removeSetting(String id);
	public int count();
	/*
	public boolean isVisible(String id);
	public boolean setVisible(String id, boolean visible);
	public boolean isFunctional(String id);
	public boolean setFunctional(String id, boolean functional);
	*/

}
