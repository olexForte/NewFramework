package somepackage.glue.awsion;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import engine.drivers.DriverInit;
import cucumber.api.java.Before;
import engine.utils.SystemUtils;
import somepackage.entities.CommandExecutionResult;
import utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class Base
{
    DriverInit driverInit = new DriverInit();
    FileUtils fileUtils = new FileUtils();
    public static final String ENV = System.getProperty("env");
    public static final Properties PROPERTIES = SystemUtils.loadProperties(ENV, System.getProperty("propertiesFile"));
    public static final String EXECUTION_RESULT_JSON = SystemUtils.get_app_root() + File.separator + "src" + File.separator
            + "test" + File.separator + "resources" + File.separator + "reports" + File.separator + ENV + new SystemUtils().get_date() + "execution.json";
    public static Integer SLEEP_DELAY = null;
    public static Integer QUOTES_DELAY = null;
    public static Integer TRANSITION_DELAY = null;
    public static Scenario SCENARIO;

    public static List<CommandExecutionResult> executionResult = new ArrayList<>();

    static
    {
        try
        {
            SLEEP_DELAY = Integer.parseInt(PROPERTIES.getProperty("delay.sleep"));
            QUOTES_DELAY = Integer.parseInt(PROPERTIES.getProperty("delay.quotes"));
            TRANSITION_DELAY = Integer.parseInt(PROPERTIES.getProperty("delay.transition"));
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Executed before every test
     *
     * @param scenario - contains scenario related information
     */
    @Before
    public void setUp(Scenario scenario)
    {
        this.SCENARIO = scenario;

        String browser = System.getProperty("browser");

        if (browser == null)
        {
            browser = "chrome";
        }

//        this.driverInit = new DriverInit();
        this.driverInit.set_driver(browser, SCENARIO.getName());
    }

    @After
    public void tearDown() throws InterruptedException
    {
        fileUtils.writeListToFile(executionResult, EXECUTION_RESULT_JSON);
        this.driverInit.finish_reporting();
        this.driverInit.close();
    }
}
