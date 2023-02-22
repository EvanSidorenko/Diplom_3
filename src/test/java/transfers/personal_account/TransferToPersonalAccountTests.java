package transfers.personal_account;

import api_client.User;
import api_client.UserClient;
import api_client.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import owner.WebDriverProvider;
import page_object_model.LoginPage;
import page_object_model.MainPage;
import page_object_model.ProfilePage;
import urls.ConstantsURLs;

public class TransferToPersonalAccountTests {
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
    @DisplayName("Check transfer to personal account by clicking on button \"Personal account\"")
    public void checkTransferToPersonalAccount() {

        mainPage.clickLoginIntoAccountButton();
        loginPage.inputEmailField(user.getEmail());
        loginPage.inputPasswordField(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.clickPersonalProfileButton();
        profilePage.waitForLogoutButton();
        profilePage.checkOrdersHistoryButtonIsVisible();

    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            client.deleteUser(accessToken);
        }
        driver.quit();
    }
}
