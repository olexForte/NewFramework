package engine.drivers;

import engine.report.ComplexReportFactory;
import engine.utils.SystemUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import sun.util.logging.PlatformLogger;

import java.util.Collection;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class DriverInit
{
    SystemUtils utils = new SystemUtils();

    private String test_title;
    public final static Logger LOGGER = Logger.getLogger(DriverInit.class);
//    private String test_description;

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
    public WebDriver driver = threadLocalDriver.get();

    /**
     *
     * @param driver - webdriver value, which comes from configuration
     * @param scenario - will be deleted later
     */
    public void set_driver(String driver, String scenario)
    {
        this.test_title = scenario;

        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.WARN);

        LOGGER.setLevel(Level.OFF);

        LOGGER.info("Starting " + driver.toUpperCase() + " driver...");

        if(driver != null)
        {
            if (driver.toLowerCase().equals("firefox"))
            {
                System.setProperty("webdriver.gecko.driver", utils.get_firefox_path());
                DesiredCapabilities capabilities=DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                this.driver = new FirefoxDriver();
            }
            else if(driver.toLowerCase().equals("chrome"))
            {
                System.setProperty("webdriver.chrome.driver", utils.get_chrome_path());

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("no-sandbox");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

                this.driver = new ChromeDriver();
            }
        }
        else
        {
            System.setProperty("webdriver.chrome.driver", utils.get_chrome_path());
            this.driver = new ChromeDriver();
        }

        LOGGER.trace("Maximizing window");
        this.driver.manage().window().maximize();

        threadLocalDriver.set(this.driver);
        ComplexReportFactory.getTest(this.test_title);
    }

    //Returns the WebDriver instance
    public WebDriver get_driver()
    {
        return threadLocalDriver.get();
    }

    //TODO: remove
//    public Logger getLogger()
//    {
//        return null;
//    }

    //Close the browser
    public void close()
    {
        LOGGER.trace("Closing the driver");
        finish_reporting(this.test_title);
        this.get_driver().close();
    }

    //TODO: remove
    private void finish_reporting(String test_title)
    {
//        ComplexReportFactory.closeTest(test_title);
//        ComplexReportFactory.closeReport();
    }

}
