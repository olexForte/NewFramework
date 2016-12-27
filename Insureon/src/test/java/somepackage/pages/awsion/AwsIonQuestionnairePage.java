package somepackage.pages.awsion;

import engine.helpers.AbstractPage;
import org.openqa.selenium.By;
import org.testng.log4testng.Logger;
import somepackage.elements.*;
import somepackage.glue.awsion.Base;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        Element.GetByBy(businessDropdown).click();
        Element.GetByBy(businessDropdown).findElement(By.xpath("//li[contains(., '" + business + "')]")).click();
        Element.GetByBy(gettingStartedBtn).click();
    }

    public void answerQuestions(String dataset)
    {
        List<String> separatedList = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(dataset)))
        {
            String line = "";

            while ((line = bufferedReader.readLine()) != null)
            {
                int index = 1;

                if (!(line.startsWith("#")))
                {
                    separatedList = Arrays.stream(line.split(";")).collect(Collectors.toList());

                    if (separatedList.size() > 3)
                    {
                        if (separatedList.get(3) != null)
                        {
                            index = Integer.parseInt(separatedList.get(3));
                        }
                    }

                    try
                    {
                        Base.SCENARIO.write("Caption: " + separatedList.get(0) + "\n" + "Value: " +
                                separatedList.get(1) + "\n" + "Element Type: " + separatedList.get(2));

                        switch (separatedList.get(2))
                        {
                            case "input":
                            case "inputbox":
                                new Input(driver).setValue(separatedList.get(0), separatedList.get(1), index);
                                break;
                            case "rnddatainputbox":
                                new Input(driver).setValueWithRandomTail(separatedList.get(0), separatedList.get(1), index, 5);
                                break;
                            case "inputboxwithtab":
                                new Input(driver).setValueAndTab(separatedList.get(0), separatedList.get(1), index);
                                break;
                            case "textarea":
                                new Textarea(driver).setValue(separatedList.get(0), separatedList.get(1), index);
                                break;
                            case "select":
                                new Select(driver).selectOption(separatedList.get(0), separatedList.get(1), index);
                                break;
                            case "button":
                                new Button(driver).clickOn(separatedList.get(0), index);
                                break;
                            case "checkbox":
                                new Checkbox(driver).check(separatedList.get(0), index);
                                break;
                            case "reversecheckbox":
                                new Checkbox(driver).checkReversed(separatedList.get(0), index);
                                break;
                            case "yesnoradiobutton":
                                new RadioButton(driver).setRadioTo(separatedList.get(0), separatedList.get(1), index);
                                break;
                            case "addressradio":
                                new RadioButton(driver).setAddressRadioTo(separatedList.get(0), index);
                                break;
                            case "reversedradio":
                                new RadioButton(driver).chooseReversedRadioTo(separatedList.get(0), index);
                        }
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        Element.GetByBy(forwardArrow).click();

                        Thread.sleep(Base.TRANSITION_DELAY);

                        continue;
                    }
                }
            }
        }
        catch (IOException e)
        {

        }
        catch (InterruptedException x)
        {

        }
        catch (NullPointerException e)
        {
            Logger.getLogger(AwsIonQuestionnairePage.class).error(separatedList.get(0));
            System.out.println("Taking screenshot...");
            Base.SCENARIO.embed(takeScreenshot("Screen"), "image/png");
        }
    }

    public void submitApplication()
    {
        Element.GetByBy(submitApplicationButton).click();
    }

    public String getGeneratedID()
    {
        try {
            Thread.sleep(Base.QUOTES_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String id = Element.GetByBy(generatedClientID).getText();
        id = id.split(":")[1].trim();

        return id;
    }
}
