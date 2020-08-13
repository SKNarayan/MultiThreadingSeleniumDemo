package generic;

import org.openqa.selenium.WebElement;

import java.util.List;

public class SelectItem {

    public void selectFromAutoSuggestList(List<WebElement> listElement, String elementToBeSearched){
        try{
            for(WebElement element : listElement){
                if(element.getText().equalsIgnoreCase(elementToBeSearched)){
                    System.out.println(element.getText());
                    element.click();
                    break;
                }else if(element.getText().trim().equalsIgnoreCase(elementToBeSearched.trim())){
                    element.click();
                    break;
                }
            }
        }catch (RuntimeException e){
            throw new FailTest(e);
        }
    }

}
