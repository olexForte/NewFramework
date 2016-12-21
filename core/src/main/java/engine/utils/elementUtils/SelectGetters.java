package engine.utils.elementUtils;

import engine.drivers.DriverInit;
import engine.utils.seleniumUtils.WaitHelper;
import engine.utils.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class SelectGetters
{
    DriverInit driver_init;
    protected WebDriver driver;
//    protected Reporter reporter;
    SystemUtils utils;
    WaitHelper waits;
    ElementGetters elements;

    public SelectGetters()
    {
        this.driver_init = new DriverInit();
        this.driver = driver_init.get_driver();
//        this.reporter = new Reporter();
        this.utils = new SystemUtils();
        this.waits = new WaitHelper(this.driver);
    }

    public SelectGetters(WebDriver driver)
    {
        this.driver = driver;
//        this.reporter = new Reporter();
        this.utils = new SystemUtils();
        this.waits = new WaitHelper(this.driver);
        this.elements = new ElementGetters(this.driver);
    }

    public Select GetByName(String name, String element_name)
    {
        Select element = null;
        By by_element = By.name(name);

        try
        {
            waits.waitForElementPresent(elements.get_element(by_element), 5);
            element = new Select(elements.get_element(by_element));
            elements.set_element_attribute(name, "name", "element_name", element_name);
            DriverInit.LOGGER.info("Element '" + element_name + "' is found...");
//            reporter.INFO("<b>'" + element_name + "'</b> is found on the page");
        }
        catch(Exception e)
        {
//            DriverInit.LOGGER.info("Element is not found...");
            e.getMessage();
        }

        return  element;
    }

    public Select GetById(String id, String element_name)
    {
        Select element = null;
        By by_element = By.id(id);

        try
        {
            waits.waitForElementPresent(elements.get_element(by_element), 10);
            element = new Select(elements.get_element(by_element));
//            elements.set_element_attribute(id, "id", "element_name", element_name);
//            reporter.INFO("<b>'" + element_name + "'</b> is found on the page");
            DriverInit.LOGGER.info("Element '" + element_name + "' is found...");
        }
        catch(NullPointerException e)
        {
            DriverInit.LOGGER.info("Element is not found...");
            e.getMessage();
        }

        return element;
    }

    public Select GetByXpath(String xpath)
    {
        Select element = null;
        By by_element = By.xpath(xpath);

        try
        {
            waits.waitForElementPresent(elements.get_element(by_element), 5);
            element = new Select(elements.get_element(by_element));
//            elements.set_element_attribute(xpath, "xpath", "element_name", element_name);
//            reporter.INFO("<b>'" + element_name + "'</b> is found on the page");
//            DriverInit.LOGGER.info("Element '" + element_name + "' is found...");
        }
        catch(Exception e)
        {
            DriverInit.LOGGER.info("Element is not found...");
            e.getMessage();
        }

        return  element;
    }

    public Select GetByXpath(String xpath, String element_name)
    {
        Select element = null;
        By by_element = By.xpath(xpath);

        try
        {
            waits.waitForElementPresent(elements.get_element(by_element), 5);
            element = new Select(elements.get_element(by_element));
            elements.set_element_attribute(xpath, "xpath", "element_name", element_name);
//            reporter.INFO("<b>'" + element_name + "'</b> is found on the page");
            DriverInit.LOGGER.info("Element '" + element_name + "' is found...");
        }
        catch(Exception e)
        {
            DriverInit.LOGGER.info("Element is not found...");
            e.getMessage();
        }

        return  element;
    }

    public Select GetByBy(By by)
    {
        Select element = null;

        try
        {
            waits.waitForElementPresent(elements.get_element(by), 5);
            element = new Select(elements.get_element(by));
//            this.set_element_attribute(xpath, "xpath", "element_name", element_name); not currently needed since no EventFiringWebDriver used
//            reporter.INFO("<b>'" + element_name + "'</b> is found on the page");

        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return  element;
    }
}
