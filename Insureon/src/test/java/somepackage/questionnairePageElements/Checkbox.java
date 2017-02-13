package somepackage.questionnairePageElements;

import engine.utils.elementUtils.ElementGetters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import somepackage.glue.awsion.Base;

import java.util.List;

/**
 * Created by Andrity Zhuk on 12/9/2016.
 */
public class Checkbox extends ElementGetters implements IElement
{
    private String XPATH = "(//div[contains(., '%s')]/following-sibling::div[@class = 'response']//input[@type = 'checkbox'])[%s]";
    private String REVERSED_XPATH = "(//label[contains(., '%s')]/preceding-sibling::input[@type = 'checkbox'])[%s]";

    public Checkbox(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Clicks on checkbox to check it
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param index - used to avoid confusion if a couple of similar questionnairePageElements are found on the page
     */
    public void check(String caption, int index)
    {
        GetByXpath(String.format(XPATH, caption, index)).click();
    }

    /**
     * Checks the reversed (actual component goes before the label) checkbox
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param index - used to avoid confusion if a couple of similar questionnairePageElements are found on the page
     */
    public void checkReversed(String caption, int index)
    {
        waits.sleep(Base.SLEEP_DELAY);

        GetByXpath(String.format(REVERSED_XPATH, caption, index)).click();
        GetByXpath(String.format(REVERSED_XPATH, caption, index)).sendKeys(Keys.TAB);
    }

    @Override
    public void validate(List<String> datasetElementInfo) {

    }

    @Override
    public void populate(List<String> datasetElementInfo)
    {
        this.check(datasetElementInfo.get(0), Integer.parseInt(datasetElementInfo.get(3)));
    }

    @Override
    public void activate(List<String> datasetElementInfo) {

    }

    @Override
    public void verify(List<String> datasetElementInfo) {

    }

    @Override
    public void save_value(List<String> datasetElementInfo) {

    }
}
