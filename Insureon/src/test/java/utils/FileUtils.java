package utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import somepackage.entities.CommandExecutionResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Andrity Zhuk on 1/30/2017.
 */
public class FileUtils
{
    /**
     * Takes the list of objects that should be converted to json object and than written to file
     *
     * @param objectToWrite - the list of objects that should be written to file
     * @param destination - destination file to write to
     */
    public void writeListToFile(List<CommandExecutionResult> objectToWrite, String destination)
    {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CommandExecutionResult>>(){}.getType();

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destination)))
        {
            String jsonObject = gson.toJson(objectToWrite, type);

            bufferedWriter.append(jsonObject);
            bufferedWriter.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
