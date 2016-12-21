package somepackage.modules;

import engine.helpers.AbstractPage;

/**
 * Created by Andrity Zhuk on 12/6/2016.
 */
public class LeftMenuModule extends AbstractPage
{
    private String menuItem;

    public LeftMenuModule openLeftMenu(String menuItem)
    {
        this.menuItem = menuItem;
        Element.GetByXpath("//li/a[contains(., '" + menuItem + "')]", "Left Menu Item").click();

        return this;
    }

    public void openLeftSubmenu(String subitem)
    {
        Element.GetByXpath("//li/a[contains(., '" + menuItem + "')]/following-sibling::ul//li//a[contains(., '" + subitem + "')]", "Left Menu Item " + menuItem).click();
    }
}
