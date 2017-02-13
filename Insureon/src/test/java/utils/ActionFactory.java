package utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Andrity Zhuk on 1/19/2017.
 */
public class ActionFactory
{
    String defaultAction = "POPULATE";
    List<String> actions;
    List<String> datasetElementInfo;
    Object additionalAction;

    /**
     * Receives the list of commands that should be executed over some object
     * and transforms these commands into the list
     *
     * @param commands - commands
     * @return class instance in order to call other methods
     */
    public ActionFactory execute(String... commands)
    {
        this.actions = new ArrayList<>();

        if (commands.length != 0 && !(commands[0].equals("")))
        {
            for (String item : commands)
            {
                this.actions.add(item);
            }
        }

        if (commands == null)
        {
            this.actions.add(defaultAction);
        }

        return this;
    }

    /**
     * Specifies the parameters that are used to execute some commands over specific page elements
     *
     * @param datasetElementInfo - list of parameters
     * @param additionalAction - additional parameter that is used to call the
     *                         corresponding methods (used for methods from @interface IElement)
     * @return class instance in order to call other methods
     */
    public ActionFactory withParameters(List<String> datasetElementInfo, Object additionalAction)
    {
        this.datasetElementInfo = datasetElementInfo;
        this.additionalAction = additionalAction;

        return this;
    }

    /**
     * Takes the formed before list of commands and executes those over some
     * specific page element. This method should be called last from this class.
     *
     * @param object - page element over which the command should be executed
     */
    public void over(Object object)
    {
        Method method;

        for (String item : this.actions)
        {
            try
            {
                if (additionalAction == null)
                {
                    method = object.getClass().getMethod(item.toLowerCase(Locale.ENGLISH), List.class);
                    method.invoke(object, datasetElementInfo);
                }
                else
                {
                    method = object.getClass().getMethod(item.toLowerCase(Locale.ENGLISH), List.class, Object.class);
                    method.invoke(object, datasetElementInfo, additionalAction);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
