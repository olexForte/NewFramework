package somepackage.glue.awsion;

import engine.utils.SystemUtils;
import somepackage.entities.TestEntity;
import somepackage.pages.awsion.AwsIonQuestionnairePage;
import java.io.File;
import static somepackage.glue.awsion.Base.ENV;

/**
 * Created by Andrity Zhuk on 12/8/2016.
 */

/**
 * This class contains references to all pages and auxiliary classes
 * in order to avoid 'noise' in DefinitionSteps classes
 */
public class PageRegistry
{
    TestEntity testEntity;
    AwsIonQuestionnairePage awsIonQuestionnairePage;
    public static final String DATASETS_FOLDER = SystemUtils.get_app_root() + File.separator + "src" + File.separator + "test" + File.separator
            + "resources" + File.separator + "datasets" + File.separator + ENV;

    public PageRegistry()
    {
        testEntity = new TestEntity();
        awsIonQuestionnairePage = new AwsIonQuestionnairePage();
    }
}
