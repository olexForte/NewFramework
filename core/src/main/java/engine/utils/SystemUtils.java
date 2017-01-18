package engine.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class SystemUtils
{
    private String file_name;
    private String user;

    /**
     * Gets the full path of the used module
     *
     * @return module path
     */
    public static String get_app_root()
    {
        String root = "";

        try
        {
            root = System.getProperty("user.dir");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return root;
    }

    public void set_user(String file_name, String user)
    {
        this.file_name = file_name;
        this.user = user;
    }

    private String get_os()
    {
        return System.getProperty("os.name");
    }

    /**
     * Gets the current user of the system
     *
     * @return current system user
     */
    private String get_computer_user()
    {
        return System.getProperty("user.name");
    }

    public String default_locations()
    {
        String path = "";

        if(this.get_os().contains("Linux"))
        {
            path = "/home" + File.separator + this.get_computer_user() + File.separator +
                    "Documents" + File.separator;
        }
        else if(this.get_os().contains("Mac"))
        {
            path = "/users" + File.separator + this.get_computer_user() + File.separator +
                    "Downloads" + File.separator;
        }
        else if(this.get_os().contains("Windows"))
        {
            path = "C:" + File.separator + "Users" + File.separator + this.get_computer_user() +
                    File.separator + "Downloads" + File.separator;
        }

        return path;
    }

    /**
     * Gets the full path of chrome driver
     *
     * @return chrome driver path
     */
    public String getChromePath()
    {
        String path = "";
        System.out.println(get_os());

        if(this.get_os().contains("Linux"))
        {
            path = get_app_root() + File.separator + ".." + File.separator + "core" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "webdrivers" +
                    File.separator + "chromedrivers" + File.separator + "chromedriver";
        }
        else if(this.get_os().contains("Mac"))
        {
            path = get_app_root() + File.separator + ".." + File.separator + "core" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "webdrivers" +
                    File.separator + "chromedrivers" + File.separator + "macdriver";
        }
        else if(this.get_os().contains("Windows"))
        {
            path = get_app_root() + File.separator + ".." + File.separator + "core" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "webdrivers" +
                    File.separator + "chromedrivers" + File.separator + "chromedriver.exe";
        }

        System.out.println("Path: >> " + path);

        return path;
    }

    /**
     * Gets the full path of firefox driver
     *
     * @return firefox driver path
     */
    public String get_firefox_path()
    {
        String path = "";
        System.out.println(get_os());

        if(this.get_os().contains("Linux"))
        {
            path = get_app_root() + File.separator + "../core" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "webdrivers" +
                    File.separator + "chromedrivers" + File.separator + "chromedriver";
        }
        else if(this.get_os().contains("Mac"))
        {
            path = get_app_root() + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "webdrivers" +
                    File.separator + "chromedrivers" + File.separator + "macdriver";
        }
        else if(this.get_os().contains("Windows"))
        {
            path = get_app_root() + File.separator + ".." + File.separator + "core" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "webdrivers" +
                    File.separator + "firefoxdrivers" + File.separator +  "geckodriver.exe";
        }

        System.out.println("Path: >> " + path);

        return path;
    }

    public String get_date()
    {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyy");
        Date date = new Date();

        return formatter.format(date);
    }

    /**
     * Gets the properties, loaded from specific file
     *
     * @param environment - environment specific folder
     * @param path - path to the property file
     * @return properties
     */
    public static Properties loadProperties(String environment, String path)
    {
        Properties properties = new Properties();
        path = get_app_root() + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "properties"
                + File.separator + environment + File.separator + path;

        try(InputStream inputStream = new FileInputStream(path))
        {
            properties.load(inputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return properties;
    }
}
