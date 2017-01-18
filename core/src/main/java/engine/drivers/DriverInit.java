package engine.drivers;

import engine.report.ComplexReportFactory;
import engine.report.Reporter;
import engine.utils.SystemUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class DriverInit
{
    SystemUtils utils = new SystemUtils();

    private static String test_title;
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
        test_title = scenario;

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
                System.setProperty("webdriver.chrome.driver", utils.getChromePath());

                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("no-sandbox");
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

                this.driver = new ChromeDriver();
            }
        }
        else
        {
            System.setProperty("webdriver.chrome.driver", utils.getChromePath());
            this.driver = new ChromeDriver();
        }

        ComplexReportFactory.getTest(test_title);
        this.driver.manage().window().maximize();

        threadLocalDriver.set(this.driver);
        ComplexReportFactory.getTest(test_title);
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
        Reporter.getInstance().INFO("Closing browser...");
        finish_reporting();
        this.get_driver().close();
    }

    public void finish_reporting()
    {
        ComplexReportFactory.closeTest(test_title);
        ComplexReportFactory.closeReport();
    }

}
