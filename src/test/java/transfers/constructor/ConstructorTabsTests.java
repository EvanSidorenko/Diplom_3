package transfers.constructor;

import api_client.UserClient;
import api_client.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import owner.WebDriverProvider;
import page_object_model.LoginPage;
import page_object_model.MainPage;
import page_object_model.ProfilePage;
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
