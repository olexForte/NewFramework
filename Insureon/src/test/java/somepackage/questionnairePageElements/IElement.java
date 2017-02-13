package somepackage.questionnairePageElements;

import java.util.List;

/**
 * Created by Andrity Zhuk on 12/9/2016.
 */
public interface IElement
{
    /**
     * Compares the expected value (from dataset) with the actual one (from page)
     * but doesn't fail the test execution while runtime
     *
     * @param datasetElementInfo - list of parameters
     */
    public void validate(List<String> datasetElementInfo);

    /**
     * Populates the field (of any type) with specified data
     *
     * @param datasetElementInfo - list of parameters
     */
    public void populate(List<String> datasetElementInfo);

    /**
     *
     *
     * @param datasetElementInfo - list of parameters
     */
    public void activate(List<String> datasetElementInfo);

    /**
     * Compares the expected value (from dataset) with the actual one (from page)
     * and fails the test execution while runtime
     *
     * @param datasetElementInfo - list of parameters
     */
    public void verify(List<String> datasetElementInfo);

    /**
     * Takes the entered value of the field and saves it
     *
     * @param datasetElementInfo - list of parameters
     */
    public void save_value(List<String> datasetElementInfo);
}
