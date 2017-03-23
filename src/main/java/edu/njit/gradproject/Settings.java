package edu.njit.gradproject;

/**
 * Settings class manages settings needed by different modules in the application
 * @author Jim Benton
 *
 */
public class Settings
{

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	private String fileName = null;
	private String filePath = null;

}
