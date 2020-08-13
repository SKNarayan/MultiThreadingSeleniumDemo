package generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CustomeAssert {

    public boolean pageTitle(String expectedTitle) {
        String title = null;
        try {
            title = DriverProvider.getInstance().getDriver().getTitle();
            assertThat(title, containsString(expectedTitle));
            return true;
        } catch (AssertionError e) {
            DriverProvider.nodeLog.get().fail("Page title mismatch: " + title);
            return false;
        } catch (Exception e1) {
            throw new FailTest(e1);

        }

    }

    public Boolean textMatch(String actualText, String expectedText, String elementName){
       try {
           assertThat(actualText, equalTo(expectedText));
           DriverProvider.nodeLog.get().pass("Text Match Succesful: " + actualText + expectedText + elementName);
           return true;
       }catch (AssertionError e){
           DriverProvider.nodeLog.get().fail("Text Mismatch: " + actualText + expectedText + elementName);
           return false;
       }
    }

    public Boolean isElementExists(By element, String elementName){
        List<WebElement> elements = DriverProvider.getInstance().getDriver().findElements(element);
        if(elements.size() == 0){
            DriverProvider.nodeLog.get().fail("Element does not exists");
            return false;
        }else {
            DriverProvider.nodeLog.get().pass("Element exists: " + elementName);
            return true;
        }
    }

}
