package page.object.model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    //---Locators---//
    private final By signInButton = By.xpath(".//button[text()='Войти']");

    private final By emailField = By.xpath(".//div/form/fieldset[1]//input");

    private final By passwordField = By.xpath(".//div/form/fieldset[2]//input");

    private final By recoverPasswordButton = By.xpath(".//div/main//p[2]/a");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //---Methods---//

    public void clickSignInButton() {
        driver.findElement(signInButton).click();

    }

    public void inputEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void inputPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }

    public void checkSignUpButtonIsVisible() {
        boolean isSignInButtonDisplayed = driver.findElement(signInButton).isDisplayed();
        Assert.assertEquals(true, isSignInButtonDisplayed);
    }

    public void waitForSignUpButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(signInButton));
    }


}
