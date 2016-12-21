package somepackage.glue;

import cucumber.api.java.After;
import engine.drivers.DriverInit;
import cucumber.api.java.Before;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class Base
{
    DriverInit driverInit;

    @Before
    public void setUp()
    {
        String browser = System.getProperty("browser");

        if (browser == null)
        {
            browser = "chrome";
        }

        this.driverInit = new DriverInit();
        this.driverInit.set_driver(browser, "some scenario");
    }

    @After
    public void tearDown()
    {
        this.driverInit.close();
    }
}
