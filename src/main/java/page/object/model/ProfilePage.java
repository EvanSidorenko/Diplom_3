package page.object.model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;

    //---Locators---//

    private final By profileButton = By.linkText("Профиль");

    private final By ordersHistoryButton = By.xpath(".//a[text()='История заказов']");

    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    //---Methods---//

    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    public void clickOrdersHistoryButton() {
        driver.findElement(ordersHistoryButton).click();
    }


    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
    public void checkOrdersHistoryButtonIsVisible() {
        boolean isOrdersHistoryButtonDisplayed = driver.findElement(ordersHistoryButton).isDisplayed();
        Assert.assertEquals(true, isOrdersHistoryButtonDisplayed);
    }
    public void waitForOrdersHistoryButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ordersHistoryButton));
    }

    public void waitForLogoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
    }


}
