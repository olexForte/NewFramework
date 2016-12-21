package engine.utils;

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

    //Return application root
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

    public String get_chrome_path()
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
                    File.separator + "chromedrivers" + File.separator +  "chromedriver.exe";
        }

        System.out.println("Path: >> " + path);

        return path;
    }

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
