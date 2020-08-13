package pages;

import com.aventstack.extentreports.ExtentTest;
import generic.DriverProvider;
import generic.FailTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage{

    public SearchResultPage(){
        PageFactory.initElements(DriverProvider.getInstance().getDriver(), this);
        ExtentTest node = DriverProvider.testLogger.get().createNode(this.getClass().getSimpleName());
        DriverProvider.nodeLog.set(node);
    }

    /****************** WebElement Destination *********************/
    @FindBy(className = "t-font-m t-line-height-l t-control-link l-display-inline-block t-dotdotdot")
    private WebElement destination;

    @FindBy(xpath = "//span[contains(text(),'Le Méridien New Delhi')]")
    private WebElement leMeridienHotel;

    @FindBy(xpath = "//div[contains(@class,'l-margin-top-half l-padding-bottom-quarter')]//div[contains(@class,'')][contains(text(),'View Rates')]")
    private WebElement viewRates_LeMeridien;


    public SearchResultPage verifyDestination(String expectedText){
        String destinationText = operation.getText(destination, "Destination Label");
        Boolean isDestinationMatch = check.textMatch(destinationText, expectedText, "verify destination label text");
        if(!isDestinationMatch){
            throw new FailTest("Mismatch in the destination location provided in home page and search result page");
        }
        return this;
    }

    public ChooseRoomPage view_Rates(){
        operation.clickThis(leMeridienHotel, "Name of the hotel");
        DriverProvider.getInstance().getDriver().switchTo().activeElement();
        operation.clickThis(viewRates_LeMeridien, "View Rate of the selected hotel");
        return new ChooseRoomPage();
    }

}
