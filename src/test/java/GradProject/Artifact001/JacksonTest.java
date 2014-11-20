package GradProject.Artifact001;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JacksonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * Basic test of functionality of Jackson.  This is the example from website.
	 */
	@Test
	public void userTest() throws JsonParseException, JsonMappingException, IOException 
	{
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(new File("user.json"), User.class);
		
		assertTrue("First Name Test", user.getName().getFirst().equalsIgnoreCase("Joe"));
		assertTrue("Last Name Test", user.getName().getLast().equalsIgnoreCase("Sixpack"));
		assertTrue("Verified Test", user.isVerified() == false);
		
	}
	
	/*
	 * Tests:
	 * Reading multiple settings from a file
	 * The Settings were properly created and stored
	 */
	@Test
	public void readArrayTest() throws JsonParseException, JsonMappingException, IOException 
	{
		Jackson jackson = new Jackson("settings.json");
		SettingAccessor mySettings = jackson.getSettingAccessor();
		
		assertTrue("Size of mySettings - 3", mySettings.count() == 3);
		assertTrue("Multi Setting Test - Dendogram", mySettings.retreiveSetting("DendogramEnabled").equalsIgnoreCase("enabled"));
		assertTrue("Multi Setting Test - Network", mySettings.retreiveSetting("NetworkGraphEnabled").equalsIgnoreCase("disabled"));
		assertTrue("Multi Setting Test - HadoopSite", mySettings.retreiveSetting("HadoopSite").equalsIgnoreCase("www.aws.amazon.com"));
		
		
	}
	
	/*
	 * Tests:
	 * Reading multiple settings from a file
	 * The Settings were properly created and stored
	 * Settings can be written back to the file
	 */
	@Test
	public void writeArrayTest()
	{
		String fileloc = "settings.json";
		
		//Read 3 objects from file
		Jackson jackson = new Jackson(fileloc);
		SettingAccessor mySettings = jackson.getSettingAccessor();
		assertTrue("Size of mySettings - 3", mySettings.count() == 3);
		
		//Add a 4th object to the Settings Accessor
		Settings setting = new Settings();
		setting.setName("FaveWebsite");
		setting.setType("String");
		setting.setValue("www.deadspin.com");
		mySettings.addSetting(setting);
		mySettings.setSetting("DendogramEnabled", "disabled");
		mySettings.setSetting("NetworkGraphEnabled", "enabled");
		mySettings.setSetting("HadoopSite", "signin.aws.amazon.com");
		
		//Write it back to file
		assertTrue("Size of mySettings - 4", mySettings.count() == 4);
		jackson.writeSettingsToFile(mySettings);
		
		
		//Use a new Jackson object to read the file
		Jackson j2 = new Jackson(fileloc);
		SettingAccessor s2 = j2.getSettingAccessor();
		assertTrue("Size of s2 - 4", s2.count() == 4);
		
		//Remove the 4th object and write back to file
		assertTrue("Write Settings Test - Dendogram", s2.retreiveSetting("DendogramEnabled").equalsIgnoreCase("disabled"));
		assertTrue("Write Settings Test - Network", s2.retreiveSetting("NetworkGraphEnabled").equalsIgnoreCase("enabled"));
		assertTrue("Write Settings Test - HadoopSite", s2.retreiveSetting("HadoopSite").equalsIgnoreCase("signin.aws.amazon.com"));
		s2.removeSetting("FaveWebsite");
		s2.setSetting("DendogramEnabled", "enabled");
		s2.setSetting("NetworkGraphEnabled", "disabled");
		s2.setSetting("HadoopSite", "www.aws.amazon.com");
		assertTrue("Size of s2 - 3 again", s2.count() == 3);
		
	}

}
