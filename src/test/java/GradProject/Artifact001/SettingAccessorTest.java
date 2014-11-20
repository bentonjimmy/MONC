package GradProject.Artifact001;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SettingAccessorTest {

	
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Tests:
	 * Creating and setting the Settings class
	 * Creating the SettingAccessor class
	 * Adding a setting using the accessor
	 * Retrieving settings using the accessor
	 * Updating a previously stored setting using the accessor 
	 */
	@Test
	public void singleSetting() {
		
		Settings setting = new Settings();
		setting.setName("DendogramEnabled");
		setting.setType("boolean");
		setting.setValue("enabled");
		
		SettingAccessor accessor = new SettingAccessor();
		boolean result = accessor.addSetting(setting);
		assertTrue("Test boolean result", result == true);
		assertTrue("Single setting test - enabled", accessor.retreiveSetting("DendogramEnabled").equalsIgnoreCase("enabled"));
		
		accessor.setSetting("DendogramEnabled", "disabled");
		assertTrue("Single setting test - disabled", accessor.retreiveSetting("DendogramEnabled").equalsIgnoreCase("disabled"));
	}
	
	/**
	 * Tests:
	 * Creating and setting the Settings class
	 * Creating the SettingAccessor class
	 * Adding multiple Settings using the accessor
	 * Retrieving multiple Settings using the accessor
	 * Updating previously stored Settings using the accessor 
	 */
	@Test
	public void multipleSettings()
	{
		Settings s1 = new Settings();
		Settings s2 = new Settings();
		Settings s3 = new Settings();
		
		s1.setName("DendogramEnabled");
		s1.setType("boolean");
		s1.setValue("enabled");
		s2.setName("NetworkGraphEnabled");
		s2.setType("boolean");
		s2.setValue("disabled");
		s3.setName("HadoopSite");
		s3.setType("string");
		s3.setValue("www.aws.amazon.com");
		
		SettingAccessor accessor = new SettingAccessor();
		accessor.addSetting(s1);
		accessor.addSetting(s2);
		accessor.addSetting(s3);
		
		//Test size of accessor
		assertTrue("Size of 3", accessor.count() == 3);
		
		//Test they are stored correctly
		assertTrue("Multi Setting Test - Dendogram", accessor.retreiveSetting("DendogramEnabled").equalsIgnoreCase("enabled"));
		assertTrue("Multi Setting Test - Network", accessor.retreiveSetting("NetworkGraphEnabled").equalsIgnoreCase("disabled"));
		assertTrue("Multi Setting Test - HadoopSite", accessor.retreiveSetting("HadoopSite").equalsIgnoreCase("www.aws.amazon.com"));
		
		//Test they can be changed and retrieved again
		accessor.setSetting("DendogramEnabled", "disabled");
		accessor.setSetting("NetworkGraphEnabled", "enabled");
		accessor.setSetting("HadoopSite", "signin.aws.amazon.com");
		assertTrue("Multi Setting Test - Dendogram", accessor.retreiveSetting("DendogramEnabled").equalsIgnoreCase("disabled"));
		assertTrue("Multi Setting Test - Network", accessor.retreiveSetting("NetworkGraphEnabled").equalsIgnoreCase("enabled"));
		assertTrue("Multi Setting Test - HadoopSite", accessor.retreiveSetting("HadoopSite").equalsIgnoreCase("signin.aws.amazon.com"));
	}
	
	/**
	 * Tests:
	 * Creating and setting the Settings class
	 * Creating the SettingAccessor class
	 * Adding multiple Settings using the accessor
	 * Removing Settings using the accessor
	 */
	@Test
	public void removeSettings()
	{
		Settings s1 = new Settings();
		Settings s2 = new Settings();
		Settings s3 = new Settings();
		
		s1.setName("DendogramEnabled");
		s1.setType("boolean");
		s1.setValue("enabled");
		s2.setName("NetworkGraphEnabled");
		s2.setType("boolean");
		s2.setValue("disabled");
		s3.setName("HadoopSite");
		s3.setType("string");
		s3.setValue("www.aws.amazon.com");
		
		SettingAccessor accessor = new SettingAccessor();
		accessor.addSetting(s1);
		accessor.addSetting(s2);
		accessor.addSetting(s3);
		
		//Test size of accessor
		assertTrue("Size of 3", accessor.count() == 3);
		
		//Test they can be removed
		boolean confirmation;
		confirmation = accessor.removeSetting("DendogramEnabled");
		assertTrue("First setting removed", confirmation == true);
		confirmation = accessor.removeSetting("NetworkGraphEnabled");
		assertTrue("Second setting removed", confirmation == true);
		
		//Test size of accessor after removing two
		assertTrue("Size of 1 - After removing 2", accessor.count() == 1);
	}

}
