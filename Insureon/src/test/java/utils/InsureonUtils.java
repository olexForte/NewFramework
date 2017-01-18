package utils;

import org.openqa.selenium.WebDriver;
import somepackage.glue.awsion.Base;
import somepackage.questionnairePageElements.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by Andrity Zhuk on 12/28/2016.
 */
public class InsureonUtils
{
    /**
     * Reads data from dataset file and returns it as list of lists (which represent each line)
     *
     * @param dataset - full path of the dataset that should be processed
     * @return parentList
     */
    public List<List<String>> getDataSetData(String dataset)
    {
        List<List<String>> parentList = new ArrayList<>();
        List<String> datasetDataList = new ArrayList<>();
        int dataSetLineIndexPosition = 3;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(dataset)))
        {
            String line;

            while ((line = bufferedReader.readLine()) != null)
            {
                //Don't add to list if comment (starts with '#')
                if (!(line.startsWith("#")))
                {
                    datasetDataList = Arrays.stream(line.split(";")).collect(Collectors.toList());
                    parentList.add(datasetDataList);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return parentList;
    }

    /**
     * Takes action over specific element type
     *
     * @param datasetElementInfo - reprresents dataset line, which contains caption, value and element type
     * @param driver - needed to pass to Insureon specific element types
     */
    public void processElementType(List<String> datasetElementInfo, WebDriver driver)
    {
        int index = 1; //needed to find one element out of the list of elements with same caption

        if (datasetElementInfo.size() > 3)
        {
            if (datasetElementInfo.get(3) != null)
            {
                index = Integer.parseInt(datasetElementInfo.get(3));
            }
        }

        Base.SCENARIO.write("Caption: " + datasetElementInfo.get(0) + "\n" + "Value: " +
                datasetElementInfo.get(1) + "\n" + "Element Type: " + datasetElementInfo.get(2));

//        InsureonElementTypes insureonElementTypes = InsureonElementTypes.valueOf((datasetElementInfo.get(2)).toUpperCase(Locale.ENGLISH));

        switch (InsureonElementTypes.valueOf((datasetElementInfo.get(2)).toUpperCase(Locale.ENGLISH)))
        {
            case INPUT:
            case INPUTBOX:
                new Input(driver).setValue(datasetElementInfo.get(0), datasetElementInfo.get(1), index);
                break;
            case RNDDATAINPUTBOX:
                new Input(driver).setValueWithRandomTail(datasetElementInfo.get(0), datasetElementInfo.get(1), index, 5);
                break;
            case INPUTBOXWITHTAB:
                new Input(driver).setValueAndTab(datasetElementInfo.get(0), datasetElementInfo.get(1), index);
                break;
            case TEXTAREA:
                new Textarea(driver).setValue(datasetElementInfo.get(0), datasetElementInfo.get(1), index);
                break;
            case SELECT:
            case DROPDOWNBOX:
                new Select(driver).selectOption(datasetElementInfo.get(0), datasetElementInfo.get(1), index);
                break;
            case BUTTON:
                new Button(driver).clickOn(datasetElementInfo.get(0), index);
                break;
            case CHECKBOX:
                new Checkbox(driver).check(datasetElementInfo.get(0), index);
                break;
            case REVERSEDCHECKBOX:
                new Checkbox(driver).checkReversed(datasetElementInfo.get(0), index);
                break;
            case YESNORADIOBUTTON:
                new RadioButton(driver).setRadioTo(datasetElementInfo.get(0), datasetElementInfo.get(1), index);
                break;
            case ADDRESSRADIO:
                new RadioButton(driver).setAddressRadioTo(datasetElementInfo.get(0), index);
                break;
            case REVERSEDRADIO:
                new RadioButton(driver).chooseReversedRadioTo(datasetElementInfo.get(0), index);
                break;
        }
    }
}
