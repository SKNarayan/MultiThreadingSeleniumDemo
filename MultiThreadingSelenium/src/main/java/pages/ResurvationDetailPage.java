package pages;

import com.aventstack.extentreports.ExtentTest;
import generic.DriverProvider;
import org.openqa.selenium.support.PageFactory;

public class ResurvationDetailPage extends BasePage{

    public ResurvationDetailPage(){
        PageFactory.initElements(DriverProvider.getInstance().getDriver(), this);
        ExtentTest node = DriverProvider.testLogger.get().createNode(this.getClass().getSimpleName());
        DriverProvider.nodeLog.set(node);
    }



}
