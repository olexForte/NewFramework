package somepackage.pages.awsion;

import engine.helpers.AbstractPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import somepackage.glue.awsion.Base;
import utils.InsureonElementUtils;

import java.util.*;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class AwsIonQuestionnairePage extends AbstractPage
{
    By questionnaireInput;
    By businessDropdown = By.xpath("//*[@id = 'SelectedClassId_chosen']");
    By gettingStartedBtn = By.xpath("//span[text() = 'Getting Started']");
    By forwardArrow = By.xpath("//img[@alt = 'Next']/../..");
    By submitApplicationButton = By.xpath("//span[text() = 'Submit Application']");
    By generatedClientID = By.xpath("//span[@class = 'client_id_frame'][contains(., 'Client ID')]");
    By compilingWindow = By.xpath("//div[contains(., 'We're Compiling Quotes now')]");

    public void selectBusiness(String business)
    {
        Element.GetBy(businessDropdown).click();
        Element.GetBy(businessDropdown).findElement(By.xpath("//li[contains(., '" + business + "')]")).click();
        Element.GetBy(gettingStartedBtn).click();
    }

    /**
     * Takes elements according to specified dataset and takes action over those
     *
     * @param dataset - full path of the dataset that should be processed
     */
    public void answerQuestions(String dataset)
    {
        List<List<String>> datasetParentList = new InsureonElementUtils().getDataSetData(dataset);

        try
        {
            datasetParentList.stream().forEach(x ->
            {
                try
                {
                    new InsureonElementUtils().processElementType(x, driver);
                }
                catch (IndexOutOfBoundsException e)
                {
                    Element.GetBy(forwardArrow).click();
                    Waits.sleep(Base.TRANSITION_DELAY);
                }
            });
        }
        catch (NullPointerException e)
        {
            System.out.println("Taking screenshot...");
            Base.SCENARIO.embed(takeScreenshot("Screen"), "image/png");
            Assert.fail();
        }
    }

    public void submitApplication()
    {
        Element.GetBy(submitApplicationButton).click();
    }

    public String getGeneratedID()
    {
        Waits.sleep(Base.QUOTES_DELAY);

        String id = Element.GetBy(generatedClientID).getText();
        id = id.split(":")[1].trim();

        return id;
    }
}
