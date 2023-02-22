package login;

import api_client.User;
import api_client.UserClient;
import api_client.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import owner.WebDriverProvider;
import page_object_model.LoginPage;
import page_object_model.MainPage;
import page_object_model.ProfilePage;
import page_object_model.RegistrationPage;
import urls.ConstantsURLs;

public class LogoutTest {
    private WebDriver driver = new WebDriverProvider().get();
    private final UserClient client = new UserClient();
    private RegistrationPage registrationPage;
    private UserGenerator userGenerator = new UserGenerator();
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        user = userGenerator.getUserWithRandomCreds();
        ValidatableResponse createResponse = client.createUser(user);
        accessToken = createResponse.extract().path("accessToken");

        registrationPage = new RegistrationPage(driver);
        driver.get(ConstantsURLs.MAIN_URL);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check logout via button \"Logout\" in personal account")
    public void checkLogout() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginIntoAccountButton();

        loginPage.inputEmailField(user.getEmail());
        loginPage.inputPasswordField(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.clickPersonalProfileButton();
        profilePage.waitForLogoutButton();
        profilePage.clickLogoutButton();
        loginPage.waitForSignUpButton();
        loginPage.checkSignUpButtonIsVisible();

    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            client.deleteUser(accessToken);
        }
        driver.quit();
    }
}