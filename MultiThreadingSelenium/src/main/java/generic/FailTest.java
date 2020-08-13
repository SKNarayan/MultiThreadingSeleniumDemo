package generic;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class FailTest extends RuntimeException {

    public FailTest(Throwable throwable){
        super(throwable);
        DriverProvider.nodeLog.get().fail(throwable);
    }

    public FailTest(String customeMessage){
        DriverProvider.nodeLog.get().fail(MarkupHelper.createLabel(customeMessage, ExtentColor.RED));
    }


}
