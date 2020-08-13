package generic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.awt.event.KeyEvent;

public class Scroll {
    public static JavascriptExecutor jse = (JavascriptExecutor) DriverProvider.getInstance().getDriver();
  //  Robot robot = new Robot();

    public void scrollDownUsingPixel() {
        try {
            jse.executeScript("window.scrollBy(0, 250)");
        }
//        catch (RuntimeException e){
//            jse.executeScript("scroll(0, 250);");
//        }
        catch (Exception e1){
            DriverProvider.nodeLog.get().fail("Not able to scroll down the page using pixel: ");
            throw new FailTest(e1);
        }
    }

    public void scrollUpUsingPixel(){
        try {
            jse.executeScript("window.scrollBy(0,-250)");

        }
//        catch (RuntimeException e){
//            jse.executeScript("scroll(0, -250);");
//        }
        catch (Exception e1){
            DriverProvider.nodeLog.get().fail("Not able to scroll Up the page using pixel: ");
            throw new FailTest(e1);
        }
    }

    public void scrollToBottom(){
        try{
            //By using JavaScript executor
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

            //By pressing ctrl+end
            /*
            DriverProvider.getInstance().getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
*/
            //By using Java Robot class

            /*
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_END);
            robot.keyRelease(KeyEvent.VK_END);
            robot.keyRelease(KeyEvent.VK_CONTROL);
*/
        }catch (Exception e){
            DriverProvider.nodeLog.get().fail("NOt able to scroll up to bottom of the page: ");
            throw new FailTest(e);
        }
    }

}
