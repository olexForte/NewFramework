package somepackage.elements;

import engine.utils.elementUtils.ElementGetters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Andrity Zhuk on 12/12/2016.
 */
public class RadioButton extends ElementGetters
{
    private String XPATH = "(//div[contains(., '%s')]/following-sibling::div[@class = 'response']//input[@value = '%s'])[%s]";
    private String ADDRESS_XPATH = "//div[contains(., '%s')]//label[contains(., '%s')]/preceding-sibling::input[@type = 'radio']";
    private String REVERSED_XPATH = "(//label[contains(., '%s')]/preceding-sibling::input[@type = 'radio'])[%s]";

   public RadioButton(WebDriver driver)
   {
       super(driver);
   }

    /**
     * Sets the radiobuttons group to some particular value 'yes/no'
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param value - this is used as value. It is checked and transformed in 'if' condition,
     *              since value in dataset can be different from what is actual value
     * @param index - used to avoid confusion if a couple of similar elements are found on the page
     */
    public void setRadioTo(String caption, String value, int index)
    {
        if (Boolean.parseBoolean(value))
        {
            value = "True";
        }
        else
        {
            value = "False";
        }

        GetByXpath(String.format(XPATH, caption, value, index)).click();
        GetByXpath(String.format(XPATH, caption, value, index)).sendKeys(Keys.TAB);
    }

    /**
     * Address radiobutton is used near address selectors
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param index - used to avoid confusion if a couple of similar elements are found on the page
     * @throws InterruptedException
     */
    public void setAddressRadioTo(String caption, int index) throws InterruptedException
    {
        Thread.sleep(3000);
//        String captionString = caption.toLowerCase();
//        captionString = captionString.substring(0, 1).toUpperCase() + captionString.substring(1);

        GetByXpath(String.format(ADDRESS_XPATH, caption, caption)).click();
    }

    /**
     * Raiobutton element that has its main component (actual radiobuttn) in front of the description
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param index - used to avoid confusion if a couple of similar elements are found on the page
     */
    public void chooseReversedRadioTo(String caption, int index)
    {
        GetByXpath(String.format(REVERSED_XPATH, caption, index)).click();
        GetByXpath(String.format(REVERSED_XPATH, caption, index)).sendKeys(Keys.TAB);
    }
}
