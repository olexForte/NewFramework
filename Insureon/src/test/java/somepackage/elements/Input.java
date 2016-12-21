package somepackage.elements;

import engine.utils.elementUtils.ElementGetters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrity Zhuk on 12/9/2016.
 */
public class Input extends ElementGetters
{
    private String XPATH = "(//div[contains(., '%s')]/following-sibling::div[@class = 'response']//input[@type = 'text'])[%s]";

    public Input(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Enters the valuee into input
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param value - value that shoud be entered into the input
     * @param index - used to avoid confusion if a couple of similar elements are found on the page
     * @throws InterruptedException
     */
    public void setValue(String caption, String value, int index) throws InterruptedException
    {
        Thread.sleep(2000);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value);

        Thread.sleep(2000);
    }

//    public void setValueWithDelay(String caption, String value)
//    {
//        GetByXpath(String.format(XPATH, caption)).sendKeys(value);
//    }

    /**
     * Enters the value into the input and invokes Tab action int order to avoid freezing
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param value - value that shoud be entered into the input
     * @param index - used to avoid confusion if a couple of similar elements are found on the page
     * @throws InterruptedException
     */
    public void setValueAndTab(String caption, String value, int index) throws InterruptedException
    {
        Thread.sleep(2000);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value, Keys.TAB);

        Thread.sleep(2000);
    }

    public String getValue(String caption)
    {
        return GetByXpath(String.format(XPATH, caption)).getText();
    }


}
