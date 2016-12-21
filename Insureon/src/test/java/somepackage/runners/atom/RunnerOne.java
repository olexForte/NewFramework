package somepackage.runners.atom;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */

@CucumberOptions(features = {
    "src/test/resources/features/LoginToAtomStory.feature",
        "src/test/resources/features/CreateAtomClientStory.feature",
},
        glue = "somepackage.glue",
        format = {"pretty"})
public class RunnerOne extends AbstractTestNGCucumberTests
{
}
