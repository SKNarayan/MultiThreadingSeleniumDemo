package generic;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class Operation extends Synchronize{

    public ExtentTest getLog(){
        return DriverProvider.nodeLog.get();
    }

    //By using generic concept of Java we can pass either By or webelement type
    public <T> void clickThis(T element, String elementName){
       try {
           if(element.getClass().getName().contains("By")){
               WebElement foundElement = DriverProvider.getInstance().getDriver().findElement((By) element);
               WebElement ele = clickableCustomWait(foundElement);
               ele.click();
               getLog().info("Clicked element: " + elementName);
           }else {
               WebElement ele = clickableCustomWait((WebElement) element);
               ele.click();
               getLog().info("Clicked this element: " + elementName);
           }
       }
       catch (NoSuchElementException e){
           getLog().fail("This element is not found: " + elementName);
           throw new FailTest(e);
       }
       catch (Exception e1){
           getLog().fail("Unable to click this element: " + elementName);
           throw new FailTest(e1);
       }

    }

    public <T> void writeText(T element, String textToEnter, String elementName){
        try {
            if(element.getClass().getName().contains("By")){
                WebElement foundElement = DriverProvider.getInstance().getDriver().findElement((By) element);
                foundElement.sendKeys(textToEnter);
                getLog().info("Typed this text: " + textToEnter + " : : " + elementName);
            }else {
                ((WebElement) element).sendKeys(textToEnter);
                getLog().info("Typed this text: " + textToEnter + " : : " + elementName);
            }
        }
        catch (NoSuchElementException e){
            getLog().fail("This element is not found: " + elementName);
            throw new FailTest(e);
        }
        catch (Exception e1){
            getLog().fail("Unable to enter text to this element: " + elementName);
            throw new FailTest(e1);
        }

    }

    public <T> String getText(T element, String elementName){
        try {
            if(element.getClass().getName().contains("By")){
                WebElement foundElement = DriverProvider.getInstance().getDriver().findElement((By) element);
                String text = foundElement.getText();
                getLog().info("Fetched this text: " + text + " : from this : " + elementName);
                return text;
            }else {
                String text = ((WebElement) element).getText();
                getLog().info("Fetched this text: " + text + " : from this : " + elementName);
                return text;
            }
        }
        catch (NoSuchElementException e){
            getLog().fail("This element is not found: " + elementName);
            throw new FailTest(e);
        }
        catch (Exception e1){
            getLog().fail("Unable to fetch text to this element: " + elementName);
            throw new FailTest(e1);
        }

    }
    //we will create more action methods






}
