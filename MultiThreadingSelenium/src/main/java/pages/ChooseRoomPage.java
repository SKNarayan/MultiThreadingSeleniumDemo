package pages;

import com.aventstack.extentreports.ExtentTest;
import generic.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChooseRoomPage extends BasePage {

    public ChooseRoomPage(){
        PageFactory.initElements(DriverProvider.getInstance().getDriver(), this);
        ExtentTest node = DriverProvider.testLogger.get().createNode(this.getClass().getSimpleName());
        DriverProvider.nodeLog.set(node);
    }

    @FindBy(xpath = "//div[@id='tab0']//div[contains(@class,'l-room-type-category-section')]//div[1]//div[1]//div[3]//div[1]//div[2]//div[2]//button[1]")
    private WebElement selectButton;

    public ResurvationDetailPage select_room(){
        operation.clickThis(selectButton, "Selecting a room");
        return new ResurvationDetailPage();
    }

}
