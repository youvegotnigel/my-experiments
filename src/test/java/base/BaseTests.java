package base;

import com.extentManager.ExtentManager;
import com.log.Log;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTests {

    private static final String fileSeparator = File.separator;

    @BeforeSuite
    public void runBeforeSuite(){

        DOMConfigurator.configure("log4j.xml");
        Log.info("######################################### START OF TEST SUITE #########################################");
        ExtentManager.setExtent();
    }

    @AfterSuite
    public void runAfterSuite(){
        Log.info("######################################### END OF TEST SUITE #########################################");
        ExtentManager.endReport();
    }




    public static String getExtentReportDirectory() {
        String extentReportDirectory = System.getProperty("user.dir") + fileSeparator + "test-output" + fileSeparator + "html-report";
        return extentReportDirectory;
    }

    public static String getTimestamp() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        return currentDate;
    }
}
