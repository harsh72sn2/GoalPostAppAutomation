package Utils;

import Listeners.TestListener;
import com.aventstack.extentreports.Status;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class Assertions extends SoftAssert {

    static String result="";
    @Override
    public void onAssertSuccess(IAssert<?> iAssert) {
        super.onAssertSuccess(iAssert);
        TestListener.test.log(Status.INFO,  "Expected Value: "+iAssert.getExpected()+" ,Actual Value: "+iAssert.getActual());
    }

    @Override
    public void onAssertFailure(IAssert<?> iAssert, AssertionError assertionError) {
        super.onAssertFailure(iAssert, assertionError);
        TestListener.test.log(Status.INFO,  "Expected Value: "+iAssert.getExpected()+" ,Actual Value: "+iAssert.getActual());
        result=iAssert.getActual().toString();
    }

    public String returnData(){
        return result;
    }
}
