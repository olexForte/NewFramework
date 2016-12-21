package engine.report;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class Reporter
{
    private ExtentTest extent_test;
    private static Reporter instance = null;

    public static Reporter getInstance()
    {
        if(instance == null)
        {
            instance = new Reporter();
        }

        return instance;
    }

    public Reporter()
    {
        this.extent_test = ComplexReportFactory.getTest();
    }

    public void PASS(String message)
    {
        this.extent_test.log(LogStatus.PASS, message);
    }

    public void FAIL(String message)
    {
        this.extent_test.log(LogStatus.FAIL, message);
    }

    public void INFO(String message)
    {
        this.extent_test.log(LogStatus.INFO, message);
    }

    public void WARN(String message)
    {
        this.extent_test.log(LogStatus.WARNING, message);
    }
}
