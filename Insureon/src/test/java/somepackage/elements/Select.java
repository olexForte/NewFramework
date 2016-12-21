package somepackage.elements;

import engine.utils.elementUtils.SelectGetters;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

/**
 * Created by Andrity Zhuk on 12/13/2016.
 */
public class Select extends SelectGetters
{
    private static Select instance = null;
    private String XPATH = "(//div[contains(., '%s')]/following-sibling::div[@class = 'response']//select)[%s]";

    public Select(WebDriver driver)
    {
        super(driver);
    }

    public void selectOption(String caption, String value, int index)
    {
        try
        {
            GetByXpath(String.format(XPATH, caption, index)).selectByVisibleText(value);
        }
        catch (Exception e)
        {
            Logger.getLogger(Select.class).error("Caption: " + caption);
            Logger.getLogger(Select.class).error(e.getStackTrace());
        }
    }
}
