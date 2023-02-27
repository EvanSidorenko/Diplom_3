package page.object.model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private final WebDriver driver;

    //---Locators---//

     private final By inputNameField = By.xpath(".//fieldset[1]//input");

    private final By inputEmailField = By.xpath(".//fieldset[2]//input");

    private final By inputPasswordField = By.xpath(".//fieldset[3]//input");

    private final By passwordIncorrectText = By.xpath(".//p[text()='Некорректный пароль']");

    private final By signUpButton = By.xpath(".//div/main/div/form/button");

    private final By signInButton = By.xpath(".//div/main//p/a");



    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //---Methods---//



    public void inputNameField(String name) {
        driver.findElement(inputNameField).sendKeys(name);
    }

    public void inputEmailField(String email) {
        driver.findElement(inputEmailField).sendKeys(email);
    }

    public void inputPasswordField(String password) {
        driver.findElement(inputPasswordField).sendKeys(password);
    }

    public void checkPasswordErrorTextIsDisplayed() {
        String actualErrorText =  driver.findElement(passwordIncorrectText).getText();
        Assert.assertEquals("Некорректный пароль", actualErrorText);

    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }




}
