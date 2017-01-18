package engine.utils.seleniumUtils;

import engine.utils.SystemUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by Andrity Zhuk on 1/18/2017.
 */
public class ReportingUtils
{
    WebDriver driver;
    SystemUtils systemUtils = new SystemUtils();

    public ReportingUtils(WebDriver driver)
    {
            this.driver = driver;
    }

    /**
     * Takes screenshot and returns it as a file
     *
     * @param file_name - desired file name
     * @return scrFile
     */
    public File takeScreenshot(String file_name)
    {
        File scrFile = null;
        String newPath = systemUtils.get_app_root() + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator +
                "reports" + File.separator + "screenshots" + File.separator + file_name + ".png";

        try
        {
            scrFile = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.moveFile(scrFile, new File(newPath));
            scrFile = new File(newPath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return scrFile;
    }
}
