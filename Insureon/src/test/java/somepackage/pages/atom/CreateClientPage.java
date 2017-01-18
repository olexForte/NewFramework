package somepackage.pages.atom;

import engine.helpers.AbstractPage;
import org.openqa.selenium.By;

/**
 * Created by Andrity Zhuk on 12/6/2016.
 */
public class CreateClientPage extends AbstractPage
{
    private By businessUnitDropdown= By.name("BusinessUnit");
    private By businessNameInput = By.name("BusinessName");
    private By esourceNameInput = By.xpath("//label[text() = 'Esource Name:']/following-sibling::input[@type = 'text']");
    private By brandDropdown = By.name("Brand");
    private By firstNameInput = By.name("FirstName");
    private By lastNameInput = By.name("LastName");
    private By primaryEmailInput = By.name("PrimaryEmail");
    private By phoneInput = By.name("Phone");
    private By street1Input = By.name("Street1");
    private By street2Input = By.name("Street2");
    private By cityInput = By.name("City");
    private By stateInput = By.name("pState");
    private By countyInput = By.name("County");
    private By zipInput = By.name("PostalCode");
    private By createClientButton = By.xpath("//button[text() = 'Create Client']");

    public void selectBusinessUnit(String unit)
    {
        Dropdown.GetByBy(businessUnitDropdown).selectByVisibleText(unit);
    }

    public void enterBusinessName(String businessName)
    {
        Element.GetBy(businessNameInput).sendKeys(businessName);
    }
//
//    //TODO: This should be re-done. Robot is implemented as workaround
//    public void enterEsourceName(String eSourceName)
//    {
////        InsureonBrowser.getPage().withAction().sendKeys(super.content.findElement(this.esourceNameInput), eSourceName).build().perform();
//        waitABit(8000);
//        super.content.findElement(this.esourceNameInput).sendKeys(eSourceName.substring(0, 4));
//        waitABit(8000);
//        super.content.findElement(this.esourceNameInput).sendKeys(Keys.BACK_SPACE);
//        waitABit(8000);
//        Robot robot = null;
//        try {
//            robot = new Robot();
//            robot.keyPress(KeyEvent.VK_O);
//            waitABit(8000);
//        } catch (AWTException e) {
//
//        }
//
//        super.content.findElement(By.xpath("//label[text() = 'Esource Name:']/following-sibling::ul[1]//a[contains(., '" + eSourceName + "')]")).click();
//    }
//
    public void selectBrand(String brand)
    {
        Dropdown.GetByBy(brandDropdown).selectByVisibleText(brand);
    }

    public void enterFirstName(String fName)
    {
        Element.GetBy(firstNameInput).sendKeys(fName);
    }

    public void enterLastName(String lName)
    {
        Element.GetBy(lastNameInput).sendKeys(lName);
    }

    public void enterPrimaryEmail(String email)
    {
        Element.GetBy(primaryEmailInput).sendKeys(email);
    }

    public void enterPhone(String phone)
    {
        Element.GetBy(phoneInput).sendKeys(phone);
    }

    public void enterPrimaryStreet1(String street)
    {
        Element.GetBy(street1Input, 0).sendKeys(street);
    }

    public void enterPrimaryStreet2(String street)
    {
        Element.GetBy(street2Input, 0).sendKeys(street);
    }

    public void enterPrimaryCity(String city)
    {
        Element.GetBy(cityInput, 0).sendKeys(city);
    }

    public void selectPrimaryState(String state)
    {
        Element.GetBy(stateInput, 0).sendKeys(state);
    }

    public void enterPrimaryCounty(String county)
    {
        Element.GetBy(countyInput, 0).sendKeys(county);
    }

    public void enterPrimaryZip(String zip)
    {
        Element.GetBy(zipInput, 0).sendKeys(zip);
    }


    public void enterMailingStreet1(String street)
    {
        Element.GetBy(street1Input, 1).sendKeys(street);
    }

    public void enterMailingStreet2(String street)
    {
        Element.GetBy(street2Input, 1).sendKeys(street);
    }

    public void enterMailingCity(String city)
    {
        Element.GetBy(cityInput, 1).sendKeys(city);
    }

    public void selectMailingState(String state)
    {
        Element.GetBy(stateInput, 1).sendKeys(state);
    }

    public void enterMailingCounty(String county)
    {
        Element.GetBy(countyInput, 1).sendKeys(county);
    }

    public void enterMailingZip(String zip)
    {
        Element.GetBy(zipInput, 1).sendKeys(zip);
    }

    public void clickOnCreateClientButton()
    {
        Element.GetBy(createClientButton).click();
    }

//    public String getClientID()
//    {
//        String idRaw = super.content.findElement(By.xpath("//li[contains(., 'Client ID:')]")).getText();
//        idRaw = idRaw.substring(idRaw.lastIndexOf(":"), idRaw.length());
//
//        return idRaw;
//    }
}
