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
        SystemUtils systemUtils = new SystemUtils();

        if (reporter == null)
        {
            reporter = new ExtentReports(systemUtils.get_app_root() + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "reports" +
                    File.separator + systemUtils.get_date() + "_Report.html", false, DisplayOrder.NEWEST_FIRST);
            reporter.addSystemInfo("Host Name", systemUtils.get_computer_user())
                    .addSystemInfo("Environment", "QA");
        }
        return reporter;
    }

    public synchronized static ExtentTest getTest(String testName, String testDescription) {

        SystemUtils systemUtils = new SystemUtils();

        // if this test has already been created return
        if (!nameToTestMap.containsKey(testName)) {
            Long threadID = Thread.currentThread().getId();
            ExtentTest test = getExtentReport().startTest(testName, testDescription);
            test.assignAuthor(systemUtils.get_computer_user());
            nameToTestMap.put(testName, test);
            threadToExtentTestMap.put(threadID, testName);
        }
        return nameToTestMap.get(testName);
    }

    public synchronized static ExtentTest getTest(String testName, String testDescription, Collection<String> tags) {

        SystemUtils systemUtils = new SystemUtils();

        // if this test has already been created return
        if (!nameToTestMap.containsKey(testName)) {
            Long threadID = Thread.currentThread().getId();
            ExtentTest test = getExtentReport().startTest(testName, testDescription);
            test.assignAuthor(systemUtils.get_computer_user());

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
            reporter.close();
        }
    }
}
