package step_definitions;

import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Hooks{
    public static WebDriver driver;

    
    @Before
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void openBrowser() throws MalformedURLException { 	
    	System.out.println("Called openBrowser");
    	
    	String projectLocation = System.getProperty("user.dir");
//    	System.setProperty("webdriver.gecko.driver", projectLocation+ "\\lib\\driver\\geckodriver.exe");
//    	driver = new FirefoxDriver();

    	System.setProperty("webdriver.chrome.driver", projectLocation+ "\\lib\\driver\\chromedriver.exe");
    	ChromeOptions options = new ChromeOptions();    	
//    	options.setExperimentalOption("useAutomationExtension", false);
    	
//    	driver.get(url);

//    	options = webdriver.ChromeOptions()
    	options.addArguments("start-maximized");
//    	options.add_argument('headless')
    	options.addArguments("disable-infobars");
    	options.addArguments("--disable-extensions");
    	options.addArguments("--disable-gpu");
    	options.addArguments("--disable-dev-shm-usage");
    	options.addArguments("--no-sandbox");
    	WebDriver driver = new ChromeDriver(options);
//    	options.add_argument('--remote-debugging-port=9222')
//    	driver = webdriver.Chrome(options=options)

//    	driver = new ChromeDriver();    	
    	driver.manage().deleteAllCookies();
    }

 
    
    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
       
        if(scenario.isFailed()) {
        try {
        	 scenario.write("Current Page URL is " + driver.getCurrentUrl());
//            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        
        }
        driver.quit();
        
    }
    
}