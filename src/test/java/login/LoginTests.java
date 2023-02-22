package login;

import api_client.User;
import api_client.UserClient;
import api_client.UserCredentials;
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
import page_object_model.RegistrationPage;
import urls.ConstantsURLs;

public class LoginTests {
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
    @DisplayName("Check the login via \"Log in into account\" button on the main page")
    public void checkLoginViaSignInButtonOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginIntoAccountButton();

        loginPage.inputEmailField(user.getEmail());
        loginPage.inputPasswordField(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForMakeOrderButton();
        mainPage.checkMakeOrderButtonIsVisible();

        ValidatableResponse loginResponse = client.loginUser(UserCredentials.from(user));

        accessToken = loginResponse.extract().path("accessToken");

    }

    @Test
    @DisplayName("Check the login via \"Personal profile\" button on the main page")

    public void checkLoginViaPersonalProfileButtonOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        mainPage.clickPersonalProfileButton();
        mainPage.clickRegisterButton();
        registrationPage.clickSignInButton();
        loginPage.inputEmailField(user.getEmail());
        loginPage.inputPasswordField(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForMakeOrderButton();
        mainPage.checkMakeOrderButtonIsVisible();

        ValidatableResponse loginResponse = client.loginUser(UserCredentials.from(user));

        accessToken = loginResponse.extract().path("accessToken");

    }

    @Test
    @DisplayName("Check the login via the button in registration form")

    public void checkLoginViaButtonInRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        mainPage.clickPersonalProfileButton();
        mainPage.clickRegisterButton();
        registrationPage.clickSignInButton();
        loginPage.inputEmailField(user.getEmail());
        loginPage.inputPasswordField(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForMakeOrderButton();
        mainPage.checkMakeOrderButtonIsVisible();

        ValidatableResponse loginResponse = client.loginUser(UserCredentials.from(user));

        accessToken = loginResponse.extract().path("accessToken");

    }
    @Test
    @DisplayName("Check the login via the password reset form")

    public void checkLoginViaPasswordResetForm() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        mainPage.clickPersonalProfileButton();
        loginPage.clickRecoverPasswordButton();
        registrationPage.clickSignInButton();
        loginPage.inputEmailField(user.getEmail());
        loginPage.inputPasswordField(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForMakeOrderButton();
        mainPage.checkMakeOrderButtonIsVisible();

        ValidatableResponse loginResponse = client.loginUser(UserCredentials.from(user));

        accessToken = loginResponse.extract().path("accessToken");

    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            client.deleteUser(accessToken);
        }
        driver.quit();
    }
}
