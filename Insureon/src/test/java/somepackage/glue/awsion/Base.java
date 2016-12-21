package somepackage.glue.awsion;

import cucumber.api.java.After;
import engine.drivers.DriverInit;
import cucumber.api.java.Before;
import engine.utils.SystemUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

import java.util.Properties;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class Base
{
    DriverInit driverInit;
    public static final String ENV = System.getProperty("env");
    public static final Properties PROPERTIES = SystemUtils.loadProperties(ENV, System.getProperty("propertiesFile"));

    @Before
    public void setUp()
    {
        String browser = System.getProperty("browser");

        if (browser == null)
        {
            browser = "chrome";
        }

        this.driverInit = new DriverInit();
        this.driverInit.set_driver(browser, "some scenario");
    }

//    @AfterClass
//    public void tearDownTestNG(ITestResult result)
//    {
//        System.out.println("TEST RESULT");
//        System.out.println(result.getTestName() + " " + result.getStatus());
//    }

    @After
    public void tearDown()
    {
        System.out.println("TEST RESULT");

        this.driverInit.close();
    }
}
