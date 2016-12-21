package somepackage.pages.atom;

import engine.helpers.AbstractPage;
import org.openqa.selenium.By;

/**
 * Created by Andrity Zhuk on 12/2/2016.
 */
public class AtomLoginPage extends AbstractPage
{
    By loginDropdown = By.xpath("//*[@id= 'unauthenticated-shell-dropdown']//a");
    By usernameInput = By.id("loginIdInput");
    By passwordInput = By.id("loginPasswordInput");
    By loginBtn = By.id("loginBtn");


    public void loginToAtom(String username, String password)
    {
        Element.GetByBy(loginDropdown).click();
        Element.GetByBy(usernameInput).sendKeys(username);
        Element.GetByBy(passwordInput).sendKeys(password);
        Element.GetByBy(loginBtn).click();
    }


}
