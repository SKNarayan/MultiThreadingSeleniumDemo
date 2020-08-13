package pages;

import com.aventstack.extentreports.ExtentTest;
import generic.DriverProvider;
import generic.FailTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(DriverProvider.getInstance().getDriver(), this);
//        ExtentTest node = DriverProvider.testLogger.get().createNode(this.getClass().getSimpleName());
//        DriverProvider.nodeLog.set(node);
    }

    /******************* WebElement Declaration ***********************/
    @FindBy(name = "destinationAddress.destination")
    private WebElement searchTextBox;

    @FindBy(css = "body.t-bg-extralightgrey.is-form-takeover div.page-container.fixed-header:nth-child(6) div.id-content-wrapper div.l-row:nth-child(1) section.mi-sub-section.sub-section.t-bg-standard-20.l-margin-subsection-bottom-large.l-margin-tile-vertical-default.l-padding-tile-vertical-default.l-padding-subsection-vertical-none:nth-child(2) div.l-print-fullbleed.l-container div.l-s-col-4.l-m-col-8.l-s-col-last.l-m-col-last.l-l-col-12.l-xl-col-12.l-l-col-last.l-xl-col-last div.is-new-ux div.tile-hsearch-homepage.m-homepage-hsearch.l-hsearch-2.l-hsearch-takeover.l-hsearch-cntnr.l-hsearch-bottom.animate-search-form:nth-child(4) div.clearfix.homepage-search-form.l-form-container.l-clear form.l-em-reset.mi-special-rates-drawer.has-flexdate.validate-search-form.js-location-nearme.mi-placeholder:nth-child(2) div.l-form-group.m-field-wrap.l-hsearch-labels.clearfix:nth-child(73) div.l-xs-col-4.l-xl-col-4.l-xl-last-col.l-hsearch-find.l-find-top.js-hform-fields.l-hsearch-find-homepage:nth-child(9) > button.analytics-click.js-is-roomkey-enabled.m-button.m-button-primary")
    private WebElement findHotelButton;

    @FindBy(xpath = "//span[contains(text(),'Sign In or Join')]")
    private WebElement signInOrJoin;

    @FindBy(id = "user-id")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@name='submitButton']")
    private WebElement signInButton;

//    @FindBy(xpath = "//li[@class='js-navigation-click active']//a[contains(text(),'Profile')]")
//    private WebElement myProfile;

    By myProfile = By.xpath("//li[@class='js-navigation-click active']//a[contains(text(),'Profile')]");

    /******************* WebElement Operation **********************/
    public HomePage enterDestination(String destination) throws InterruptedException {
       // synchronize.elementDisplayed(searchTextBox);
        scroll.scrollDownUsingPixel();
        synchronize.clickableCustomWait(searchTextBox);
        operation.writeText(searchTextBox, destination, "Destination Search Text Box");
        List<WebElement> listedElements = DriverProvider.getInstance().getDriver().findElements(By.name("destinationAddress.destination"));
        selectItem.selectFromAutoSuggestList(listedElements, destination);

        return this;
    }

    public SearchResultPage findHotels() {
        operation.clickThis(findHotelButton, "Find Hotels Button");
        return new SearchResultPage();
    }

    public void signIn(String userNameText, String passwordText){
        operation.clickThis(signInOrJoin, "Sign-In or Join");
        operation.writeText(userName, userNameText, "User name text box");
        operation.writeText(password, passwordText, "Password text box");
        operation.clickThis(signInButton, "sign-in button");
        Boolean isExist = check.isElementExists(myProfile, "Profile element after login");
        if(!isExist){
            throw new FailTest("login failed");
        }else {
            DriverProvider.testLogger.get().pass("Login Successful");
        }
    }



}