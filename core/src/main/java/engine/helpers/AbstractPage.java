package engine.helpers;

import engine.drivers.DriverInit;
import engine.utils.elementUtils.ElementGetters;
import engine.utils.elementUtils.SelectGetters;
import engine.utils.seleniumUtils.WaitHelper;
import engine.utils.SystemUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class AbstractPage
{
    DriverInit driver_init;
    protected WebDriver driver;
//    protected Reporter reporter;
    SystemUtils utils;
    protected ElementGetters Element;
    protected SelectGetters Dropdown;
    protected WaitHelper Waits;

    public AbstractPage()
    {
        this.driver_init = new DriverInit();
        driver = driver_init.get_driver();

        //Reporter instance, which is used to call reporting functions
//        this.reporter = Reporter.getInstance();

        //Utils, which contain useful functions like paths to
        //common places
        this.utils = new SystemUtils();

        //Access to class, which provides search of questionnairePageElements
        //by various methods
        this.Element = new ElementGetters(this.driver);
        this.Dropdown = new SelectGetters(this.driver);
        this.Waits = new WaitHelper(this.driver);

//        DriverInit.LOGGER.info("Helpers initialized...");
    }


    //Returns Select object
    protected Select get_select(WebElement element)
    {
        Select select = null;

        try
        {
            select = new Select(element);
        }
        catch(NullPointerException e)
        {
            e.getMessage();
            e.printStackTrace();
        }

        return select;
    }

    //Selects multiple values in drop down
    protected void select_multiselect(WebElement select, List<String> select_values)
    {
        Select dropdown = this.get_select(select);

        try
        {
            for(String item : select_values)
            {
                dropdown.selectByVisibleText(item);
            }
        }
        catch(ElementNotVisibleException e)
        {
            e.printStackTrace();
        }
    }

    //Accepts alerts
    protected void accept_alert()
    {
        try
        {
            Alert alert = this.driver.switchTo().alert();
            alert.accept();
            Thread.sleep(2000);
//            DriverInit.LOGGER.info("Alert accepted...");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    //Verify the that the target page is reached
//    public void PageReached(PageUrls url)
//    {
//        try
//        {
//            Assert.assertEquals(get_curr_url(), url.get_url().toString());
//        }
//        catch (AssertionError assertion_error)
//        {
//            reporter.FAIL("<b>Expected URL:</b> " + url.get_url().toString() + " \n<b>But Actual:</b> " + get_curr_url());
//        }
//        catch(Exception e)
//        {
//            reporter.FAIL("<b>Expected URL:</b> " + url.get_url().toString() + " \n<b>But Actual:</b> " + get_curr_url());
//        }
//    }

    //Refreshes pages
    public void page_refresh()
    {
        try
        {
//            reporter.INFO("Refreshing the page");
            this.driver.navigate().refresh();
//            DriverInit.LOGGER.info("Page refreshed...");
        }
        catch(Exception e)
        {
//            reporter.FAIL("Unable to refresh the page");
        }
    }

    //Goes back
    public void go_back()
    {
        try
        {
            this.driver.navigate().back();
//            DriverInit.LOGGER.info("Navigated back to " + this.get_curr_url());
        }
        catch(Exception e)
        {
            e.getMessage();
//            reporter.FAIL("Unable to go back");
        }
    }


    //Opens URL
//    public void navigate_to(PageUrls url)
//    {
//        try
//        {
//            this.driver.get(url.get_url().toString());
//            reporter.INFO("Navigating to " + PageUrls.SALESFORCE_LOGIN_PAGE.get_url());
//        }
//        catch(Exception e)
//        {
//            e.getMessage();
//            reporter.FAIL("Unable to navigate to " + PageUrls.SALESFORCE_LOGIN_PAGE.get_url());
//        }
//    }

    //Opens URL
    public void navigate_to(String url)
    {
//        reporter.INFO("Navigating to: " + url);
        this.driver.get(url);
//        DriverInit.LOGGER.info("Navigated to " + url);
    }
//

//    //Returns Action object
//    protected Actions get_action()
//    {
//        Actions actions = new Actions(this.driver);
//        return actions;
//    }

    //Return a list of active tabs
    protected List<String> get_window_handles()
    {
        List<String> tabs = new ArrayList<String>(this.driver.getWindowHandles());
        return tabs;
    }

    //Returns textfield text
    protected String get_text(WebElement element)
    {
        return element.getText();// getAttribute("innerhtml");
    }

    //Returns page title
    protected String get_title()
    {
        return this.driver.getTitle();
    }

    protected String get_curr_url()
    {
        return this.driver.getCurrentUrl();
    }

    //Gets frame
    protected WebElement get_frame(WebElement element)
    {
        driver.switchTo().frame(element);

        return element;
    }

    //Return to Default content
    protected void get_default_content()
    {
        this.driver.switchTo().defaultContent();
//        DriverInit.LOGGER.info("Switched to default content...");
    }

    //    //Compares two objects
//    protected boolean compare_objects(Object expected, Object actual)
//    {
//        boolean passed = false;
//
//        if(expected.equals(actual))
//        {
//            passed = true;
//        }
//        else
//        {
//
//        }
//
//        return passed;
//    }
//
    //Takes screenshot
    public byte[] takeScreenshot(String file_name)
    {
        byte[] scrFile = null;

        try
        {
            scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);

//            FileUtils.moveFile(scrFile, new File(utils.get_app_root() + File.separator + "src" +
//                    File.separator + "gather_student_info" + File.separator + "resources" + File.separator +
//                    "screenshots" + File.separator + file_name + ".png"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return scrFile;
    }
}
