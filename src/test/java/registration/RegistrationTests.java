package registration;

import api_client.User;
import api_client.UserClient;
import api_client.UserCredentials;
import api_client.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import owner.WebDriverProvider;
import page_object_model.LoginPage;
import page_object_model.MainPage;
import page_object_model.RegistrationPage;
import urls.ConstantsURLs;

public class RegistrationTests {
    private WebDriver driver = new WebDriverProvider().get();
    private final UserClient client = new UserClient();
    private RegistrationPage registrationPage;
    private UserGenerator userGenerator = new UserGenerator();
    private User user = userGenerator.getUserWithRandomCreds();
    private String accessToken;

    @Before
    public void setUp() {
        registrationPage = new RegistrationPage(driver);
        driver.get(ConstantsURLs.MAIN_URL);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Check registration with valid user data")
    public void checkRegistrationWithValidData() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ValidatableResponse loginResponse = client.loginUser(UserCredentials.from(user));
        accessToken = loginResponse.extract().path("accessToken");

        mainPage.clickPersonalProfileButton();
        mainPage.clickRegisterButton();
        registrationPage.inputNameField(user.getName());
        registrationPage.inputEmailField(user.getEmail());
        registrationPage.inputPasswordField(user.getPassword());
        registrationPage.clickSignUpButton();

        loginPage.waitForSignUpButton();
        loginPage.checkSignUpButtonIsVisible();

    }

    @Test
    @DisplayName("Check registration with invalid password with less than 6 symbols")
    public void checkRegistrationWithIncorrectPassword() {
        MainPage mainPage = new MainPage(driver);
        ValidatableResponse loginResponse = client.loginUser(UserCredentials.from(user));
        accessToken = loginResponse.extract().path("accessToken");

        mainPage.clickPersonalProfileButton();
        mainPage.clickRegisterButton();
        registrationPage.inputNameField(user.getName());
        registrationPage.inputEmailField(user.getEmail());
        registrationPage.inputPasswordField(user.setPassword(RandomStringUtils.randomAlphanumeric(5)));
        registrationPage.clickSignUpButton();
        registrationPage.checkPasswordErrorTextIsDisplayed();

    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            client.deleteUser(accessToken);
        }
        driver.quit();
    }


}
