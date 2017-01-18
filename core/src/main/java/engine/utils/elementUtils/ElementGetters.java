package engine.utils.elementUtils;

import engine.report.Reporter;
import engine.utils.seleniumUtils.WaitHelper;
import engine.utils.SystemUtils;
import org.openqa.selenium.*;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class ElementGetters extends BaseElement
{
    public ElementGetters()
    {
        super();
    }

    public ElementGetters(WebDriver driver)
    {
        super(driver);
        this.utils = new SystemUtils();
        this.waits = new WaitHelper(this.driver);
    }

    /**
     * Takes by locator and converts it to WebElement
     *
     * @param elem - element to be found and converted to WebElement
     * @return element
     */
    protected WebElement get_element(By elem)
    {
        WebElement element;

        element = waits.waitForElement(elem, 7);

        return element;
    }

    /**
     * TODO: To be reconsidered
     *
     * @param elem
     * @param index
     * @return
     */
    protected WebElement get_element_by_index(By elem, int index)
    {
        WebElement element = null;

        try
        {
            element = this.driver.findElements(elem).get(index);
        }
        catch(Exception e)
        {
            System.out.println("Element cannot be found");
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.getMessage();
        }

        return element;
    }

    /**
     * Returns element with specified name
     *
     * @param name - element name to find element by
     * @return element
     */
    protected WebElement GetByName(String name)
    {
        WebElement element = null;
        By by_element = By.name(name);

        try
        {
            element = this.get_element(by_element);
            Reporter.getInstance().PASS("Element with NAME '" + name + "' is found...");
        }
        catch(Exception e)
        {
            Reporter.getInstance().FAIL("Element with NAME '" + name + "' cannot be found...");
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.getMessage();
        }

        return  element;
    }

    /**
     * Returns element with specified name
     *
     * @param name - element name to find element by
     * @param element_name - element description
     * @return
     */
    public WebElement GetByName(String name, String element_name)
    {
        WebElement element = null;
        By by_element = By.name(name);

        try
        {
            element = this.get_element(by_element);
            Reporter.getInstance().PASS("Element '" + element_name + "' is found...");
        }
        catch(Exception e)
        {
            Reporter.getInstance().FAIL("Element '" + element_name + "' cannot be found...");
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.getMessage();
        }

        return  element;
    }

    /**
     * Returns element with specified id
     *
     * @param id - element id to find element by
     * @return element
     */
    public WebElement GetById(String id)
    {
        WebElement element = null;
        By by_element = By.id(id);

        try
        {
            element = this.get_element(by_element);
            Reporter.getInstance().PASS("Element with ID '" + id + "' is found...");
        }
        catch(Exception e)
        {
            Reporter.getInstance().FAIL("Element with ID '" + id + "' cannot be found...");
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.getMessage();
        }

        return  element;
    }

    /**
     * Returns element with specified id
     *
     * @param id - element id to find element by
     * @param element_name
     * @return element
     */
    public WebElement GetById(String id, String element_name)
    {
        WebElement element = null;
        By by_element = By.id(id);

        try
        {
            element = this.get_element(by_element);
            Reporter.getInstance().PASS("Element '" + element_name + "' is found...");
        }
        catch(Exception e)
        {
            Reporter.getInstance().FAIL("Element '" + element_name + "' cannot be found...");
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.getMessage();
        }

        return element;
    }

    /**
     * Returns element with specified xpath
     *
     * @param xpath - element xpath to find element by
     * @return element
     */
    public WebElement GetByXpath(String xpath)
    {
        WebElement element = null;
        By by_element = By.xpath(xpath);

        try
        {
            element = get_element(by_element);
            Reporter.getInstance().PASS("Element with XPATH '" + xpath + "' is found...");
        }
        catch(Exception e)
        {
            Reporter.getInstance().FAIL("Element with XPATH '" + xpath + "' cannot be found...");
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.printStackTrace();
        }

        return  element;
    }

    /**
     * Returns element with specified xpath
     *
     * @param xpath - element xpath to find element by
     * @param element_name - element description
     * @return element
     */
    public WebElement GetByXpath(String xpath, String element_name)
    {
        WebElement element = null;
        By by_element = By.xpath(xpath);

        try
        {
            element = get_element(by_element);
            Reporter.getInstance().PASS("Element '" + element_name + "' is found...");
        }
        catch(Exception e)
        {
            Reporter.getInstance().FAIL("Element '" + element_name + "' cannot be found...");
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.printStackTrace();
        }

        return  element;
    }

    /**
     * Returns element with specified 'By' locator
     *
     * @param by - 'By' locator to find element by
     * @return element
     */
    public WebElement GetBy(By by)
    {
        WebElement element = null;

        try
        {
            element = get_element(by);
            Reporter.getInstance().PASS("Element with BY '" + by.toString() + "' is found...");
        }
        catch(Exception e)
        {
            Reporter.getInstance().FAIL("Element with BY '" + by.toString() + "' cannot be found...");
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.getMessage();
        }

        return  element;
    }

    /**
     * Returns element with specified 'By' locator
     *
     * @param by - 'By' locator to find element by
     * @param index - element index to find element by
     * @return element
     */
    public WebElement GetBy(By by, int index)
    {
        WebElement element = null;

        try
        {
            element = get_element_by_index(by, index);
            Reporter.getInstance().PASS("Element with BY '" + by.toString() + "' is found...");
        }
        catch(Exception e)
        {
            Reporter.getInstance().FAIL("Element with BY '" + by.toString() + "' cannot be found...");
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.getMessage();
        }

        return  element;
    }

    //TODO: refactor
    public WebElement Link(String link_text, String element_name)
    {
        WebElement element = null;
        By by_element = new By.ByLinkText(link_text);

        try
        {
            waits.waitForElementPresent(this.get_element(by_element), 5);
            element = driver.findElement(By.partialLinkText(link_text));
        }
        catch(ElementNotVisibleException e)
        {
            Reporter.getInstance().CAPTURE_SCREENSHOT(reportingUtils.takeScreenshot(String.valueOf(System.currentTimeMillis())).getAbsolutePath());
            e.printStackTrace();
        }

        return element;
    }
}
