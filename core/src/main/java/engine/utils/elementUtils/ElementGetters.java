package engine.utils.elementUtils;

//import com.gargoylesoftware.htmlunit.ElementNotFoundException;
//import engine.drivers.DriverInit;
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

    protected WebElement get_element(By elem)
    {
        WebElement element = null;

        try
        {
//            element = waits.waitForElement(elem, 7);
            element = driver.findElement(elem);
        }
        catch(Exception e)
        {
            System.out.println("Element cannot be found");
            e.getMessage();
        }

        return element;
    }

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
            e.getMessage();
        }

        return element;
    }

    protected WebElement GetByName(String name)
    {
        WebElement element = null;
        By by_element = By.name(name);

        try
        {
            element = this.get_element(by_element);
        }
        catch(NullPointerException e)
        {
            e.getMessage();
        }

        return  element;
    }

    public WebElement GetByName(String name, String element_name)
    {
        WebElement element = null;
        By by_element = By.name(name);

        try
        {
            element = this.get_element(by_element);
        }
        catch(NullPointerException e)
        {
            e.getMessage();
        }

        return  element;
    }

    public WebElement GetById(String id)
    {
        WebElement element = null;
        By by_element = By.id(id);

        try
        {
            element = this.get_element(by_element);
        }
        catch(NullPointerException e)
        {
            e.getMessage();
        }

        return  element;
    }

    public WebElement GetById(String id, String element_name)
    {
        WebElement element = null;
        By by_element = By.id(id);
        try
        {
            element = this.get_element(by_element);
        }
        catch(NullPointerException e)
        {
            e.getMessage();
        }

        return element;
    }

    public WebElement GetByXpath(String xpath)
    {
        WebElement element = null;
        By by_element = By.xpath(xpath);

        try
        {
            element = get_element(by_element);
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }

        return  element;
    }

    public WebElement GetByXpath(String xpath, String element_name)
    {
        WebElement element = null;
        By by_element = By.xpath(xpath);

        try
        {
            element = get_element(by_element);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return  element;
    }

    public WebElement GetByBy(By by)
    {
        WebElement element = null;

        try
        {
            element = get_element(by);
        }
        catch(NullPointerException e)
        {
            e.getMessage();
        }

        return  element;
    }

    public WebElement GetByBy(By by, int index)
    {
        WebElement element = null;

        try
        {
            element = get_element_by_index(by, index);
        }
        catch(NullPointerException e)
        {
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
            e.printStackTrace();
        }

        return element;
    }

    //Sets 'element_name' attribute to element, which is retreived later
    //for reporting - the name is used in reports as user-friendly name for element
    void set_element_attribute(String element_id, String locator_type, String attr, String value)
    {
        String script = "";
        String method = "";
        if(locator_type.toLowerCase().equals("id"))
        {
            method = "getElementById";
        }
        else if(locator_type.toLowerCase().equals("name"))
        {
            method = "getElementsByName";
        }
        else if(locator_type.toLowerCase().equals("xpath"))
        {
            method = "getElementByXPath";

        }
        else if(locator_type.toLowerCase().equals("css"))
        {
            method = "getElementByCssSelector";
        }

        if (method == "getElementsByName")
        {
            script = "document." + method + "('" + element_id + "')[0].setAttribute('" + attr + "', '" + value + "')";
        }
        else
        {
            script = "document." + method + "('" + element_id + "').setAttribute('" + attr + "', '" + value + "')";
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }

//    private javascript_xpath(String xpath)
//    {
//        String script = null;
//        script = "document.evaluate(" + xpath  + "), document, null, XPathResult.ANY_TYPE, null";
//
//        JavascriptExecutor js = (JavascriptExecutor) this.driver;
//        js.executeScript(script);
//    }
}
