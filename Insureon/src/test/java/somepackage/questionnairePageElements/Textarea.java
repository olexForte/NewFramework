package somepackage.questionnairePageElements;

import engine.utils.elementUtils.ElementGetters;
import org.openqa.selenium.WebDriver;
import somepackage.glue.awsion.Base;

/**
 * Created by Andrity Zhuk on 12/13/2016.
 */
public class Textarea extends ElementGetters
{
    private String XPATH = "(//div[contains(., '%s')]/following-sibling::div[@class = 'response']//textarea)[%s]";

    public Textarea(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Enters the value into textarea
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param value - value that shoud be entered into the input
     * @param index - used to avoid confusion if a couple of similar questionnairePageElements are found on the page
     */
    public void setValue(String caption, String value, int index)
    {
        waits.sleep(Base.SLEEP_DELAY);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value);
    }
}
