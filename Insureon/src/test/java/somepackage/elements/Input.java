package somepackage.elements;

import engine.utils.DataUtils;
import engine.utils.elementUtils.ElementGetters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import somepackage.glue.awsion.Base;

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
     * Enters the value into input
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param value - value that should be entered into the input
     * @param index - used to avoid confusion if a couple of similar elements are found on the page
     * @throws InterruptedException
     */
    public void setValue(String caption, String value, int index) throws InterruptedException
    {
        Thread.sleep(Base.SLEEP_DELAY);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value);

        Thread.sleep(Base.SLEEP_DELAY);
    }

    /**
     * Enters the value into the input and invokes Tab action int order to avoid freezing
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param value - value that should be entered into the input
     * @param index - used to avoid confusion if a couple of similar elements are found on the page
     * @throws InterruptedException
     */
    public void setValueAndTab(String caption, String value, int index) throws InterruptedException
    {
        Thread.sleep(Base.SLEEP_DELAY);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value, Keys.TAB);

        Thread.sleep(Base.SLEEP_DELAY);
    }

    /**
     * Returns the element value
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @return the value of an element
     */
    public String getValue(String caption)
    {
        return GetByXpath(String.format(XPATH, caption)).getText();
    }

    /**
     * Enters the value into input and adds random characters of specified length
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param value - value that should be entered into the input
     * @param index - used to avoid confusion if a couple of similar elements are found on the page
     * @param tailLength - specifies how many random characters should be added
     * @throws InterruptedException
     */
    public void setValueWithRandomTail(String caption, String value, int index, int tailLength) throws InterruptedException
    {
        DataUtils dataUtils = new DataUtils();

        Thread.sleep(Base.SLEEP_DELAY);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value + dataUtils.getRandomChars(tailLength));
    }
}
