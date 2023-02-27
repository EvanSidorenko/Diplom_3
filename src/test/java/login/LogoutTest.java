package login;

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
import page.object.model.RegistrationPage;
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