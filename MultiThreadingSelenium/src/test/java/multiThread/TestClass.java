package multiThread;

import com.aventstack.extentreports.ExtentTest;
import generic.DriverProvider;
import generic.Reporting;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


public class TestClass extends Reporting {

    @BeforeSuite
    public void startReporting(){
        initializeReport();
    }

    @AfterSuite
    public void endReporting(){
        generateReport();
    }
    //public static WebDriver driver;
   // public static String chromePath = System.getProperty("user.dir")+"/drivers/chromedriver";

    @BeforeMethod
    public void beforeTest(Method reportResult) throws InterruptedException{

        DriverProvider.getInstance().getDriver().get("https://www.bhphotovideo.com/");
        DriverProvider.getInstance().getDriver().manage().window().maximize();
        DriverProvider.getInstance().getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

        //we will provide the test case name by using reflection concept
        ExtentTest testLogger = report.createTest(reportResult.getName());

        //assign logger object to particular thread
        DriverProvider.testLogger.set(testLogger);


//        System.setProperty("webdriver.chrome.driver", chromePath);
//        driver = new ChromeDriver();
//        driver.get("https://www.bhphotovideo.com/");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(4000);
//        driver.switchTo().defaultContent();
//        //finding search element
//        driver.findElement(By.xpath("")).click();
//        Thread.sleep(5000);


    }

    @Test
    public void oneTest() throws InterruptedException{

        System.out.println(Thread.currentThread().getId());
        DriverProvider.getInstance().getDriver().findElement(By.id("top-search-input")).sendKeys("pen drive");
        DriverProvider.testLogger.get().info("Searching for pen drive");
        DriverProvider.getInstance().getDriver().findElement(By.xpath("//button[@name='Top Nav-Search']")).click();
        DriverProvider.testLogger.get().info("Clicked on search button");
        DriverProvider.getInstance().getDriver().findElement(By.linkText("SanDisk 128GB Ultra Flair USB 3.0 Flash Drive")).click();
        DriverProvider.testLogger.get().info("Clicked on sandisk 128GB link");
        Thread.sleep(5000);

//        WebDriver driver;
//
//        System.setProperty("webdriver.chrome.driver", chromePath);
//        driver = new ChromeDriver();
//        driver.get("https://www.bhphotovideo.com/");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(4000);
//        driver.switchTo().defaultContent();
//        //finding search element
//        driver.findElement(By.id("top-search-input")).click();
//        Thread.sleep(5000);
//
//        driver.findElement(By.id("top-search-input")).sendKeys("pen drive");
//        Thread.sleep(10000);
//        driver.findElement(By.xpath("//button[@name='Top Nav-Search']")).click();
//        driver.findElement(By.linkText("SanDisk 128GB Ultra Flair USB 3.0 Flash Drive")).click();
//        Thread.sleep(2000);

    }

    @Test
    public void twoTest() throws InterruptedException{

        System.out.println(Thread.currentThread().getId());
        DriverProvider.getInstance().getDriver().findElement(By.id("top-search-input")).sendKeys("dell monitors");
        DriverProvider.testLogger.get().info("Searched for dell monitor");
        DriverProvider.getInstance().getDriver().findElement(By.xpath("//button[@name='Top Nav-Search']")).click();
        DriverProvider.testLogger.get().info("Clicked on search button");
        DriverProvider.getInstance().getDriver().findElement(By.linkText("Dell U2415 24\" Widescreen LED Backlit IPS Monitor")).click();
        DriverProvider.testLogger.get().info("Clicked on Dell LED monitor");
        Thread.sleep(5000);

//        WebDriver driver;
//
//        System.setProperty("webdriver.chrome.driver", chromePath);
//        driver = new ChromeDriver();
//        driver.get("https://www.bhphotovideo.com/");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(4000);
//        driver.switchTo().defaultContent();
//        //finding search element
//        driver.findElement(By.id("top-search-input")).click();
//        Thread.sleep(5000);
//
//        driver.findElement(By.id("top-search-input")).sendKeys("dell laptopd");
//        driver.findElement(By.linkText("Dell 15.6\" Latitude 3500 Laptop")).click();
//        Thread.sleep(5000);

    }

    @AfterMethod
    //WebDriver driver;

    public void afterTest(){
            DriverProvider.getInstance().removeDriver();
          //  DriverProvider.testLogger.remove();
        //driver.quit();
    }


}
