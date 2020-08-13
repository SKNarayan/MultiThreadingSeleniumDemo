package generic;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverProvider {

    private DriverProvider(){

    }

    private static DriverProvider instance = new DriverProvider();

    public static DriverProvider getInstance(){
        return instance;
    }

    //we need to create ThreadLocal class for creating testLogger for individual thread or testCase
    public static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<ExtentTest>();
    //creating node for group of test cases per page
    public static ThreadLocal<ExtentTest> nodeLog = new ThreadLocal<ExtentTest>();

    //Anounmus implementation of initialValue() method of ThreadLocal class
    static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(){
        @Override
        protected WebDriver initialValue(){
            String chromePath = System.getProperty("user.dir")+"/drivers/chromedriver";
            System.setProperty("webdriver.chrome.driver", chromePath);
            return new ChromeDriver();
        }
    };

    public WebDriver getDriver(){
        return driver.get();
    }

    public void removeDriver(){
        driver.get().quit();
        driver.remove();
        nodeLog.remove();
        testLogger.remove();
    }
    

}
