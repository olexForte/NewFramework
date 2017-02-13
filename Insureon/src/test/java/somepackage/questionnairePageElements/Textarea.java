package somepackage.questionnairePageElements;

import engine.utils.elementUtils.ElementGetters;
import org.openqa.selenium.WebDriver;
import somepackage.glue.awsion.Base;

import java.util.List;

/**
 * Created by Andrity Zhuk on 12/13/2016.
 */
public class Textarea extends ElementGetters implements IElement
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
     */
    public void setValue(String caption, String value, int index)
    {
        waits.sleep(Base.SLEEP_DELAY);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value);
    }

    @Override
    public void validate(List<String> datasetElementInfo) {

    }

    @Override
    public void populate(List<String> datasetElementInfo) {

    }

    @Override
    public void activate(List<String> datasetElementInfo) {

    }

    @Override
    public void verify(List<String> datasetElementInfo) {

    }

    public void populate(List<String> datasetElementInfo, Object randomStringTailLength) {

    }

    @Override
    public void save_value(List<String> datasetElementInfo) {

    }
}
