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

    /**
     * Returns select element with specified name
     *
     * @param name - element name to find element by
     * @param element_name - element description
     * @return element
     */
    public Select GetByName(String name, String element_name)
    {
        Select element = null;

        try
        {
            element = new Select(elements.GetByName(name, element_name));
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return  element;
    }

    /**
     *
     * @param id - element id to find element by
     * @param element_name - element description
     * @return element
     */
    public Select GetById(String id, String element_name)
    {
        Select element = null;

        try
        {
            element = new Select(elements.GetById(id, element_name));
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return element;
    }

    /**
     *
     *
     * @param xpath - element xpath to find element by
     * @return select element
     */
    public Select GetByXpath(String xpath)
    {
        Select element = null;

        try
        {
            element = new Select(elements.GetByXpath(xpath));
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return  element;
    }

    /**
     *
     * @param xpath - element xpath to find element by
     * @param element_name element description
     * @return select element
     */
    public Select GetByXpath(String xpath, String element_name)
    {
        Select element = null;

        try
        {
            element = new Select(elements.GetByXpath(xpath, element_name));
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return  element;
    }

    /**
     * Returns element with specified 'By' locator
     *
     * @param by - 'By' locator to find element by
     * @return select element
     */
    public Select GetByBy(By by)
    {
        Select element = null;

        try
        {
            element = new Select(elements.GetBy(by));
        }
        catch(Exception e)
        {
            e.getMessage();
        }

        return  element;
    }
}
