package engine.utils.seleniumUtils;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import engine.drivers.DriverInit;
import engine.report.Reporter;
import engine.utils.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class WaitHelper
{
    DriverInit driver_init;
    protected WebDriver driver;
//    protected Reporter reporter;
    SystemUtils utils;

    public WaitHelper()
    {
        this.driver_init = new DriverInit();
        this.driver = driver_init.get_driver();
//        this.reporter = new Reporter();
        this.utils = new SystemUtils();
    }

    public WaitHelper(WebDriver driver)
    {
        this.driver = driver;
//        this.reporter = new Reporter();
        this.utils = new SystemUtils();
    }

    public void gen_pass()
    {

    }

    public void implicit_wait(long time)
    {
        this.driver.manage().timeouts().implicitlyWait(time, SECONDS);
    }

    public void page_load(long time)
    {
        this.driver.manage().timeouts().pageLoadTimeout(time, SECONDS);
    }

    public boolean waitForElementPresent(final WebElement element, int timeout)
    {
        Boolean present = false;

        try
        {
            WebDriverWait wait = (WebDriverWait) new WebDriverWait(this.driver, timeout)
                    .ignoring(StaleElementReferenceException.class)
                    .withTimeout(timeout, SECONDS)
                    .pollingEvery(1, SECONDS);
            present = wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return element.isDisplayed();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return present;
    }

    public WebElement waitForElement(final By by, int timeout)
    {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(this.driver, timeout)
                .ignoring(StaleElementReferenceException.class)
                .withTimeout(timeout, SECONDS)
                .pollingEvery(1, SECONDS);

        WebElement element = wait.until((ExpectedCondition<WebElement>) driver -> {
//                try {
//                    wait(timeout);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            return driver.findElement(by);
        });

        return element;
    }

    //Sleeps
    public void sleep(long time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
