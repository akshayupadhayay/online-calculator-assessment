package reporting;

import com.xendit.base.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Listener extends BasePage implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if(result.isSuccess()){
            File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try{
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/src/main/java/successScreenshots";
                File destFile = new File((String)reportDirectory+"/"+methodName+"_"+formatter.format(calendar.getTime())+".png");
                FileUtils.copyFile(scrFile,destFile);
                Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><image src='"+destFile.getAbsolutePath()+"'height='100' width='100'/> </a>");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if(result.isSuccess()){
            File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try{
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/src/main/java/failureScreenshots";
                File destFile = new File((String)reportDirectory+"/"+methodName+"_"+formatter.format(calendar.getTime())+".png");
                FileUtils.copyFile(scrFile,destFile);
                Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><image src='"+destFile.getAbsolutePath()+"'height='100' width='100'/> </a>");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
