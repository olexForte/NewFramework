package somepackage.questionnairePageElements;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import engine.utils.DataUtils;
import engine.utils.elementUtils.ElementGetters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import somepackage.entities.CommandExecutionResult;
import somepackage.glue.awsion.Base;

import java.util.List;

/**
 * Created by Andrity Zhuk on 12/9/2016.
 */
public class Input extends ElementGetters implements IElement
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
     * @param index - used to avoid confusion if a couple of similar questionnairePageElements are found on the page
     */
    public void setValue(String caption, String value, int index)
    {
        waits.sleep(Base.SLEEP_DELAY);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value);

        waits.sleep(Base.SLEEP_DELAY);
    }

    /**
     * Enters the value into the input and invokes Tab action int order to avoid freezing
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param value - value that should be entered into the input
     * @param index - used to avoid confusion if a couple of similar questionnairePageElements are found on the page
     */
    public void setValueAndTab(String caption, String value, int index)
    {
        waits.sleep(Base.SLEEP_DELAY);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value, Keys.TAB);

        waits.sleep(Base.SLEEP_DELAY);
    }

    /**
     * Returns the element value
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @return the value of an element
     */
    public String getValue(String caption, int index)
    {
        return GetByXpath(String.format(XPATH, caption, index)).getAttribute("value");
    }

    /**
     * Enters the value into input and adds random characters of specified length
     *
     * @param caption - text by which the element is found
     *                this should be revised with Firebug because what user sees on the page
     *                can be different from the real caption value is
     * @param value - value that should be entered into the input
     * @param index - used to avoid confusion if a couple of similar questionnairePageElements are found on the page
     * @param tailLength - specifies how many random characters should be added
     */
    public void setValueWithRandomTail(String caption, String value, int index, Integer tailLength)
    {
        DataUtils dataUtils = new DataUtils();

        waits.sleep(Base.SLEEP_DELAY);

        GetByXpath(String.format(XPATH, caption, index)).clear();
        GetByXpath(String.format(XPATH, caption, index)).sendKeys(value + dataUtils.getRandomChars(tailLength));
    }

    @Override
    public void validate(List<String> datasetElementInfo) {

    }

    @Override
    public void populate(List<String> datasetElementInfo)
    {
        this.setValue(datasetElementInfo.get(0), datasetElementInfo.get(1), Integer.parseInt(datasetElementInfo.get(3)));
        System.out.println("POPULATE");
    }

    @Override
    public void activate(List<String> datasetElementInfo) {

    }

    @Override
    public void verify(List<String> datasetElementInfo) {

    }

    public void populate(List<String> datasetElementInfo, Object additionalParameter)
    {
        if (additionalParameter instanceof Integer)
        {
            this.setValueWithRandomTail(datasetElementInfo.get(0), datasetElementInfo.get(1), Integer.parseInt(datasetElementInfo.get(3)), Integer.valueOf((Integer) additionalParameter));
        }
        else if (additionalParameter instanceof Boolean && (Boolean) additionalParameter)
        {
            this.setValueAndTab(datasetElementInfo.get(0), datasetElementInfo.get(1), Integer.parseInt(datasetElementInfo.get(3)));
        }
    }

    @Override
    public void save_value(List<String> datasetElementInfo)
    {
        Base.executionResult.add(new CommandExecutionResult(datasetElementInfo.get(0), getValue(datasetElementInfo.get(0), Integer.parseInt(datasetElementInfo.get(3))), true));
        System.out.println("SAVE_VALUE");
    }
}
