package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	//public Logger logger;
	public Properties p;

	@BeforeClass(groups= {"Sanity"})
	@Parameters({"browser","os"})
	public void setup(String br, String os) throws IOException {
		FileReader file = new FileReader("C:\\Selenium_Prj_1\\WebDriverDemo\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
	//	logger = LogManager.getLogger(this.getClass());
		if(p.getProperty("execute_env").equalsIgnoreCase("remote")){
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("MAC")) {
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching os");
				//return;
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome":cap.setBrowserName("chrome");break;
			case "edge":cap.setBrowserName("MicrosoftEdge");break;
			case "firefox":cap.setBrowserName("firefox");break;
			default:System.out.println("invalid browser name");return;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"),cap);
		}
		
		if(p.getProperty("execute_env").equalsIgnoreCase("local-host")) {
			switch(br.toLowerCase()) {
			case "chrome":driver = new ChromeDriver();break;
			case "edge":driver = new EdgeDriver();break;
			case "firefox":driver = new FirefoxDriver();break;
			default:System.out.println("invalid browser name");return;
			}
		}
		
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity"})
	public void tearDown() {
		driver.close();
	}
	
	public String randomString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumeric() {
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}
	
	public String randomAlphaNumeric() {
		String generatedalphanum = RandomStringUtils.randomAlphanumeric(6);
		String alphabetic = RandomStringUtils.randomAlphabetic(3);
		String numeric = RandomStringUtils.randomNumeric(3);
		//return generatedalphanum;
		return alphabetic+numeric;
	}

	public String captureScreen(String name) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ name + "_" + timeStamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
