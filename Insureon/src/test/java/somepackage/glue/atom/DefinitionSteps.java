package somepackage.glue.atom;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import somepackage.entities.TestEntity;
import somepackage.modules.atom.LeftMenuModule;
import somepackage.pages.atom.AtomLoginPage;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class DefinitionSteps
{
    //TODO: make base glue class with pages
    TestEntity testEntity = new TestEntity();
    LeftMenuModule leftMenuModule = new LeftMenuModule();
    AtomLoginPage atomLoginPage = new AtomLoginPage();

    @Given("^Open Atom$")
    public void loginToAtom() throws Exception
    {
//        atomLoginPage.navigate_to(PagesUrls.BASE_URL.GetUrl());
    }

    @When("^Login to Atom with username (.*) and password (.*)$")
    public void enterCriteria(String username, String password)
    {
        atomLoginPage.loginToAtom(username, password);
    }

    @When("Open left menu (.*) and submenu (.*)")
    public void openLeftMenu(String item, String subitem)
    {
        leftMenuModule.openLeftMenu(item).openLeftSubmenu(subitem);
    }

//    @When("^Fill out the client fields$")
//    public void fillOutTheClientFields()
//    {
//
//    }
}
