package engine.utils.elementUtils;

import engine.utils.seleniumUtils.WaitHelper;
import engine.utils.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class SelectGetters extends BaseElement
{
    ElementGetters elements;

    public SelectGetters()
    {
        super();
    }

    public SelectGetters(WebDriver driver)
    {
        super(driver);
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
            elements.get_element(by_element);
            element = new Select(elements.get_element(by_element));
        }
        catch(Exception e)
        {
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
            elements.get_element(by_element);
            element = new Select(elements.get_element(by_element));
        }
        catch(NullPointerException e)
        {
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
            elements.get_element(by_element);
            element = new Select(elements.get_element(by_element));
        }
        catch(Exception e)
        {
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
            elements.get_element(by_element);
            element = new Select(elements.get_element(by_element));
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return  element;
    }

    public Select GetByBy(By by)
    {
        Select element = null;

        try
        {
            elements.get_element(by);
            element = new Select(elements.get_element(by));
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return  element;
    }
}
