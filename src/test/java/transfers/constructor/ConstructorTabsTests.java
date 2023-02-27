package transfers.constructor;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import owner.WebDriverProvider;
import page.object.model.MainPage;
import urls.ConstantsURLs;

public class ConstructorTabsTests {
    private WebDriver driver = new WebDriverProvider().get();
    private MainPage mainPage;


    @Before
    public void setUp() {

        mainPage = new MainPage(driver);
        driver.get(ConstantsURLs.MAIN_URL);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check transfer to the Buns tab")
    public void checkTransferToTheBunsTab() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        mainPage.checkBunsTabIsVisible();
    }

    @Test
    @DisplayName("Check transfer to the sauces tab")
    public void checkTransferToTheSaucesTab() {
        mainPage.clickSaucesTab();
        mainPage.checkSaucesTabIsVisible();
    }

    @Test
    @DisplayName("Check transfer to the sauces tab")
    public void checkTransferToTheFillingsTab() {
        mainPage.clickFillingsTab();
        mainPage.checkFillingsTabIsVisible();
    }

    @After
    public void deleteUser() {
        driver.quit();
    }
}
