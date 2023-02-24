package page_object_model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    //---Locators---//
    private final By loginIntoAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");

    private final By personalProfileButton = By.xpath((".//div/header/nav/a/p"));

    private final By createOrderButton = By.xpath((".//div/main/section[2]/div/button"));

    private final By bunsTab = By.xpath(".//div/span[text()='Булки']");

    private final By saucesTab = By.xpath(".//div/span[text()='Соусы']");

    private final By fillingsTab = By.xpath(".//div/span[text()='Соусы']");

    private final By mainLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");

    private final By createBurgerText = By.xpath((".//h1[text()='Соберите бургер']"));

    private final By registrationButton = By.xpath(".//div/main//p/a[@class='Auth_link__1fOlj']");

    private final By makeOrderButton = By.xpath((".//div/main/section[2]/div/button"));

    private final By makeBurgerText = By.xpath((".//h1[text()='Соберите бургер']"));



    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //---Methods---//

    public void clickLoginIntoAccountButton() {
        driver.findElement(loginIntoAccountButton).click();
    }

    public void clickPersonalProfileButton() {
        driver.findElement(personalProfileButton).click();
    }

    public void clickCreateOrderButton() {
        driver.findElement(createOrderButton).click();
    }

    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    public void clickMainLogo() {
        driver.findElement(mainLogo).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickCreateBurgerText() {
        driver.findElement(createBurgerText).click();
    }
    public void clickRegisterButton() {
        driver.findElement(registrationButton).click();
    }
    public void checkMakeOrderButtonIsVisible() {
        boolean isMakeOrderButtonDisplayed = driver.findElement(makeOrderButton).isDisplayed();
        Assert.assertEquals(true, isMakeOrderButtonDisplayed);
    }
    public void waitForMakeOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
    }

    public void checkMakeBurgerTextIsVisible() {
        boolean isConstructorButtonDisplayed = driver.findElement(makeBurgerText).isDisplayed();
        Assert.assertEquals(true, isConstructorButtonDisplayed);
    }

    public void waitForMakeBurgerText() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(makeBurgerText));
    }

    public void checkBunsTabIsVisible() {
        boolean isBunsTabDisplayed = driver.findElement(bunsTab).isDisplayed();
        Assert.assertEquals(true, isBunsTabDisplayed);
    }

    public void checkSaucesTabIsVisible() {
        boolean isSaucesTabTabDisplayed = driver.findElement(saucesTab).isDisplayed();
        Assert.assertEquals(true, isSaucesTabTabDisplayed);
    }
    public void checkFillingsTabIsVisible() {
        boolean isFillingsTabDisplayed = driver.findElement(fillingsTab).isDisplayed();
        Assert.assertEquals(true, isFillingsTabDisplayed);
    }
}
