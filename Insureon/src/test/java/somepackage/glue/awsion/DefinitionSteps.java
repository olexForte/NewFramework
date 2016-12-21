package somepackage.glue.awsion;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.io.File;

import static somepackage.glue.awsion.Base.PROPERTIES;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class DefinitionSteps extends PageRegistry
{
    @Given("^Open AWSION$")
    public void openAwsIon() throws Exception
    {
        awsIonQuestionnairePage.navigate_to(PROPERTIES.getProperty("awsion.url"));
    }

    @When("^Select business (.*)$")
    public void selectBusiness(String business)
    {
        awsIonQuestionnairePage.selectBusiness(business);
    }

    @When("Answer questions from dataset (.*)")
    public void answerQuestions(String dataset)
    {
        dataset = DATASETS_FOLDER + File.separator + dataset;
        awsIonQuestionnairePage.answerQuestions(dataset);
        awsIonQuestionnairePage.submitApplication();
    }

    @When("^Get generated Client ID$")
    public void getGeneratedClientID()
    {
        System.out.println(awsIonQuestionnairePage.getGeneratedID());
    }

    @When("^Fill out the client fields$")
    public void fillOutTheClientFields()
    {

    }
}
