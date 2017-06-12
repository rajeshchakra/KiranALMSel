package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jacob.com.LibraryLoader;

import selalmwrapper.ALMServiceWrapper;
import selalmwrapper.enums.StatusAs;
import selalmwrapper.exceptions.ALMServiceException;


public class SeleniumALMIntegrationExample {
	WebDriver driver;

	@BeforeClass
	public void init() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void login() throws InterruptedException, ALMServiceException {
		updateResults();
	}

	public void updateResults() throws ALMServiceException {
		//Load the DLL File
		System.setProperty("jacob.dll.path", "C:\\Testi\\jacob-1.18-x64.dll");
		LibraryLoader.loadJacobLibrary();
		//Call the Service Wrapper file
		ALMServiceWrapper wrapper = new ALMServiceWrapper("http://localhost:8081/qcbin");
		wrapper.connect("admin", "admin", "DEFAULT", "SampleProject");
		wrapper.updateResult("SampleTestSetFolder\\SubTestSetFolder1", "TestSet1", 2, "KiranTest", StatusAs.PASSED);
		wrapper.close();
	}

	@AfterClass
	public void close() {
		driver.quit();
	}
}
