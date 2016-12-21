package engine.report;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import engine.utils.SystemUtils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class ComplexReportFactory
{
    public static ExtentReports reporter;
    public static Map<Long, String> threadToExtentTestMap = new HashMap<Long, String>();
    public static Map<String, ExtentTest> nameToTestMap = new HashMap<String, ExtentTest>();

    private synchronized static ExtentReports getExtentReport()
    {
        SystemUtils utils = new SystemUtils();

        if (reporter == null)
        {
            reporter = new ExtentReports(utils.get_app_root() + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "reports" +
                    File.separator + utils.get_date() + "_Report.html", false, DisplayOrder.NEWEST_FIRST);
            reporter.addSystemInfo("Host Name", "Andriy Zhuk")
                    .addSystemInfo("Environment", "QA");
        }
        return reporter;
    }

    public synchronized static ExtentTest getTest(String testName, String testDescription) {

        // if this test has already been created return
        if (!nameToTestMap.containsKey(testName)) {
            Long threadID = Thread.currentThread().getId();
            ExtentTest test = getExtentReport().startTest(testName, testDescription);
            test.assignAuthor("Andriy Zhuk");
            nameToTestMap.put(testName, test);
            threadToExtentTestMap.put(threadID, testName);
        }
        return nameToTestMap.get(testName);
    }

    public synchronized static ExtentTest getTest(String testName, String testDescription, Collection<String> tags) {

        // if this test has already been created return
        if (!nameToTestMap.containsKey(testName)) {
            Long threadID = Thread.currentThread().getId();
            ExtentTest test = getExtentReport().startTest(testName, testDescription);
            test.assignAuthor("Andriy Zhuk");

            for (String tag : tags)
            {
                test.assignCategory(tag);
            }

            nameToTestMap.put(testName, test);
            threadToExtentTestMap.put(threadID, testName);
        }
        return nameToTestMap.get(testName);
    }

    public synchronized static ExtentTest getTest(String testName, Collection<String> tags) {
        return getTest(testName, "", tags);
    }

    public synchronized static ExtentTest getTest(String testName) {
        return getTest(testName, "");
    }

    public synchronized static ExtentTest getTest() {
        Long threadID = Thread.currentThread().getId();

        if (threadToExtentTestMap.containsKey(threadID)) {
            String testName = threadToExtentTestMap.get(threadID);
            return nameToTestMap.get(testName);
        }
        //system log, this shouldnt happen but in this crazy times if it did happen log it.
        return null;
    }

    public synchronized static void closeTest(String testName) {

        if (!testName.isEmpty()) {
            ExtentTest test = getTest(testName);
            getExtentReport().endTest(test);
        }
    }

    public synchronized static void closeTest(ExtentTest test) {
        if (test != null) {
            getExtentReport().endTest(test);
        }
    }

    public synchronized static void closeTest()  {
        ExtentTest test = getTest();
        closeTest(test);
    }

    public synchronized static void closeReport() {
        if (reporter != null) {
            reporter.flush();
//            reporter.close();
        }
    }
}
