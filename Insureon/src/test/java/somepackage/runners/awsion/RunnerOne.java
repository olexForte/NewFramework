package somepackage.runners.awsion;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */

@CucumberOptions(features = {
    "src/test/resources/features/awsion/LoginToAwsIonStory.feature"
},
        glue = "somepackage.glue.awsion",
        format = {"json:target/cucumber.json", "html:target/Destination"})
public class RunnerOne extends AbstractTestNGCucumberTests
{
}
