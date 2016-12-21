package somepackage.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by Andrity Zhuk on 12/7/2016.
 */

@CucumberOptions(features = {
        "src/test/resources/features/LoginToAtomStory.feature"
},
        glue = "somepackage.glue",
        format = {"pretty"})
public class RunnerTwo extends AbstractTestNGCucumberTests
{
}
