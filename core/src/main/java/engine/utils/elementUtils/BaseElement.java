package engine.utils.elementUtils;

import engine.drivers.DriverInit;
import engine.helpers.AbstractPage;
import engine.utils.SystemUtils;
import engine.utils.seleniumUtils.ReportingUtils;
import engine.utils.seleniumUtils.WaitHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Andrity Zhuk on 12/14/2016.
 */
public class BaseElement
{
    public DriverInit driver_init;
    public WebDriver driver;
    public SystemUtils utils;
    public WaitHelper waits;
    public ReportingUtils reportingUtils;

    public BaseElement()
    {
        this.driver_init = new DriverInit();
        this.driver = driver_init.get_driver();
        this.utils = new SystemUtils();
        this.waits = new WaitHelper(this.driver);
        this.reportingUtils = new ReportingUtils(this.driver);
    }

    public BaseElement(WebDriver driver)
    {
        this.driver = driver;
        this.utils = new SystemUtils();
        this.waits = new WaitHelper(this.driver);
        this.reportingUtils = new ReportingUtils(this.driver);
    }

    /**
     * Scrolls the page to specific element
     *
     * @param element - element to scroll to
     */
    public void scrollToElementUsingJS(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].focus()", element);
    }
}
