package somepackage.glue.atom;

import engine.utils.SystemUtils;
import somepackage.entities.TestEntity;
import somepackage.modules.atom.LeftMenuModule;

import java.io.File;

/**
 * Created by Andrity Zhuk on 12/8/2016.
 */
public class PageRegistry
{
    TestEntity testEntity;
    LeftMenuModule leftMenuModule;
    public static final String DATASETS_FOLDER = SystemUtils.get_app_root() + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "datasets";

    public PageRegistry()
    {
        testEntity = new TestEntity();
        leftMenuModule = new LeftMenuModule();
    }
}
