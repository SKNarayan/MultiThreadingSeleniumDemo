package base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import generic.CreateLink;
import generic.DriverProvider;
import generic.Reporting;
import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class BaseTest extends Reporting {
    public HomePage home;
    public ConcurrentHashMap<String, String> location;
    public ConcurrentHashMap<String, String> userName;
    public ConcurrentHashMap<String, String> password;

    @BeforeSuite
    public void startReporting(){
        initializeReport();
    }

    @AfterSuite
    public void endReporting(){
        generateReport();
    }

    @BeforeMethod
    public void beforeTest(Method reportResult) throws InterruptedException{

      //  DriverProvider.getInstance().getDriver().get("https://www.bhphotovideo.com/");
        DriverProvider.getInstance().getDriver().get("https://www.marriott.com/");
       // Thread.sleep(20000);
        DriverProvider.getInstance().getDriver().manage().window().maximize();

        DriverProvider.getInstance().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //we will provide the test case name by using reflection concept
        ExtentTest testLogger = report.createTest(reportResult.getName());

        //assign logger object to particular thread
        DriverProvider.nodeLog.set(testLogger);

        home = new HomePage();

    }

    @AfterMethod
    public void afterTest(){
       String status = DriverProvider.nodeLog.get().getStatus().toString();
        if(status.equals("fail")){
            captureScreenshot();
        }
        DriverProvider.getInstance().removeDriver();
        //  DriverProvider.testLogger.remove();
        //driver.quit();
    }

    @BeforeClass
    public void readTestData() throws ClassNotFoundException {
       String pathOfJsonFile = System.getProperty("user.dir") + "/testData/testdata.json";
       JsonPath jsonPath = new JsonPath(new File(pathOfJsonFile));
          location = (ConcurrentHashMap<String, String>) jsonPath.getObject("Location",Class.forName("java.util.concurrent.ConcurrentHashMap"));
          userName = (ConcurrentHashMap<String, String>) jsonPath.getObject
                   ("UserName", Class.forName("java.util.concurrent.ConcurrentHashMap"));
          password = (ConcurrentHashMap<String, String>) jsonPath.getObject
                   ("Password", Class.forName("java.util.concurrent.ConcurrentHashMap"));

    }

    //here synchronized keyword will make this method as thread safe
    private synchronized void captureScreenshot(){
        String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/reports/screenshot" + "timestamp" + ".jpg";
        File sourceFile = ((TakesScreenshot)DriverProvider.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(screenshotPath));
            CreateLink createLink = new CreateLink(screenshotPath, "Screenshot");
            DriverProvider.nodeLog.get().log(Status.FAIL, createLink);
        } catch (IOException e) {
            DriverProvider.nodeLog.get().fail("Unable to take screenshot");
        }

    }

}
