package transfers.from.personal.account.to.constructor;

import api.client.User;
import api.client.UserClient;
import api.client.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import owner.WebDriverProvider;
import page.object.model.LoginPage;
import page.object.model.MainPage;
import page.object.model.ProfilePage;
import urls.ConstantsURLs;

public class TransferFromPersonalAccountToConstructorTests {
    private WebDriver driver = new WebDriverProvider().get();
    private final UserClient client = new UserClient();
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private UserGenerator userGenerator = new UserGenerator();
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        user = userGenerator.getUserWithRandomCreds();
        ValidatableResponse createResponse = client.createUser(user);
        accessToken = createResponse.extract().path("accessToken");


        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        driver.get(ConstantsURLs.MAIN_URL);
        driver.manage().window().maximize();
    }
    @Test
    @DisplayName("Check the transfer from personal account to constructor")
    public void checkTransferFromPersonalAccountToConstructor() {

        mainPage.clickLoginIntoAccountButton();
        loginPage.inputEmailField(user.getEmail());
        loginPage.inputPasswordField(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.clickPersonalProfileButton();
        mainPage.clickConstructorButton();
        mainPage.waitForMakeBurgerText();
        mainPage.checkMakeBurgerTextIsVisible();

    }

    @Test
    @DisplayName("Check the transfer from personal account by clicking on logo")
    public void checkTransferFromPersonalAccountBy() {

        mainPage.clickLoginIntoAccountButton();
        loginPage.inputEmailField(user.getEmail());
        loginPage.inputPasswordField(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.clickPersonalProfileButton();
        mainPage.clickMainLogo();
        mainPage.waitForMakeBurgerText();
        mainPage.checkMakeBurgerTextIsVisible();
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            client.deleteUser(accessToken);
        }
        driver.quit();
    }
}
