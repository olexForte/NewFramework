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
public class InsureonElementUtils
{
    ActionFactory actionFactory = new ActionFactory();
    String[] commandsArray = new String[5];

    /**
     * Reads data from dataset file and returns it as list of lists (which represents each line)
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
                    if (line.contains("::"))
                    {
                        datasetDataList = Arrays.stream(line.substring((line.lastIndexOf("::") + 2)).split(";")).collect(Collectors.toList());

                        if (!(datasetDataList.size() > 3))
                        {
                            //Setting default element index
                            datasetDataList.add(3, "1");
                        }

                        datasetDataList.add(line.split("::")[0].replace(" ", ""));
                    }
                    else
                    {
                        datasetDataList = Arrays.stream(line.split(";")).collect(Collectors.toList());

                        if (!(datasetDataList.isEmpty()))
                        {
                            if (!(datasetDataList.size() > 3))
                            {
                                //Setting default element index
                                datasetDataList.add(3, "1");
                            }

                            datasetDataList.add("POPULATE");
                        }
                    }

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
     * @param datasetElementInfo - represents dataset line, which contains caption, value and element type
     * @param driver - needed to pass to Insureon specific element types
     */
    public void processElementType(List<String> datasetElementInfo, WebDriver driver)
    {
        final short CAPTION_INDEX = 0;
        final short VALUE_INDEX = 1;
        final short TYPE_INDEX = 2;
        final short ELEMENTINDEX_INDEX = 3;

        //If command element is present in the list
        if (datasetElementInfo.size() >= 4)
        {
            this.commandsArray = datasetElementInfo.get(4).split(",");
        }

        int index = 1; //needed to find one element out of the list of elements with same caption

        //If element index is present in the list
        if (datasetElementInfo.size() > 3)
        {
            if (datasetElementInfo.get(ELEMENTINDEX_INDEX) != null)
            {
                index = Integer.parseInt(datasetElementInfo.get(3));
            }
        }

        Base.SCENARIO.write("Caption: " + datasetElementInfo.get(CAPTION_INDEX) + "\n" + "Value: " +
                datasetElementInfo.get(VALUE_INDEX) + "\n" + "Element Type: " + datasetElementInfo.get(TYPE_INDEX));

//        InsureonElementTypes insureonElementTypes = InsureonElementTypes.valueOf((datasetElementInfo.get(2)).toUpperCase(Locale.ENGLISH));

        switch (InsureonElementTypes.valueOf((datasetElementInfo.get(TYPE_INDEX)).toUpperCase(Locale.ENGLISH)))
        {
            case INPUT:
            case INPUTBOX:
                actionFactory
                        .execute(this.commandsArray)
                        .withParameters(datasetElementInfo, null)
                        .over(new Input(driver));
//                new Input(driver).setValue(datasetElementInfo.get(CAPTION_INDEX), datasetElementInfo.get(VALUE_INDEX), index);
                break;
            case RNDDATAINPUTBOX:
                actionFactory
                        .execute(this.commandsArray)
                        .withParameters(datasetElementInfo, new Integer(5))
                        .over(new Input(driver));
//                new Input(driver).setValueWithRandomTail(datasetElementInfo.get(CAPTION_INDEX), datasetElementInfo.get(VALUE_INDEX), index, 5);
                break;
            case INPUTBOXWITHTAB:
                actionFactory
                        .execute(this.commandsArray)
                        .withParameters(datasetElementInfo, new Boolean(true))
                        .over(new Input(driver));
//                new Input(driver).setValueAndTab(datasetElementInfo.get(CAPTION_INDEX), datasetElementInfo.get(VALUE_INDEX), index);
                break;
            case TEXTAREA:
                new Textarea(driver).setValue(datasetElementInfo.get(CAPTION_INDEX), datasetElementInfo.get(VALUE_INDEX), index);
                break;
            case SELECT:
            case DROPDOWNBOX:
                new Select(driver).selectOption(datasetElementInfo.get(CAPTION_INDEX), datasetElementInfo.get(VALUE_INDEX), index);
                break;
            case BUTTON:
                new Button(driver).clickOn(datasetElementInfo.get(CAPTION_INDEX), index);
                break;
            case CHECKBOX:
                new Checkbox(driver).check(datasetElementInfo.get(CAPTION_INDEX), index);
                break;
            case REVERSEDCHECKBOX:
                new Checkbox(driver).checkReversed(datasetElementInfo.get(CAPTION_INDEX), index);
                break;
            case YESNORADIOBUTTON:
                new RadioButton(driver).setRadioTo(datasetElementInfo.get(CAPTION_INDEX), datasetElementInfo.get(VALUE_INDEX), index);
                break;
            case ADDRESSRADIO:
                new RadioButton(driver).setAddressRadioTo(datasetElementInfo.get(CAPTION_INDEX), index);
                break;
            case REVERSEDRADIO:
                new RadioButton(driver).chooseReversedRadioTo(datasetElementInfo.get(CAPTION_INDEX), index);
                break;
        }
    }
}
