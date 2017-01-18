package somepackage.questionnairePageElements;

import engine.utils.elementUtils.ElementGetters;
import org.openqa.selenium.WebDriver;

/**
 * Created by Andrity Zhuk on 12/9/2016.
 */
public class Button extends ElementGetters
{
    private String XPATH = "(//input[contains(@value, '%s')])[%s]";

    public Button(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Clicks on the button
     *
     * @param value - text, written on the button
     * @param index - used to avoid confusion if a couple of similar questionnairePageElements are found on the page
     */
    public void clickOn(String value, int index)
    {
        GetByXpath(String.format(XPATH, value, index)).click();
    }
}
