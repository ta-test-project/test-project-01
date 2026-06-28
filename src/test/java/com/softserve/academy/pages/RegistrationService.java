package com.softserve.academy.pages;

import com.softserve.academy.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationService {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By SIGN_UP_BUTTON = By.cssSelector(".header_sign-up-btn > span");
    private static final By EMAIL_FIELD = By.id("email");
    private static final By FIRST_NAME_FIELD = By.id("firstName");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By REPEAT_PASSWORD_FIELD = By.id("repeatPassword");
    private static final By SUBMIT_BUTTON = By.cssSelector(".greenStyle");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".mdc-snackbar__label");

    public RegistrationService(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public RegistrationService openRegistrationModal() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SUCCESS_MESSAGE));
        click(SIGN_UP_BUTTON);
        waitForVisible(EMAIL_FIELD);
        return this;
    }

    public RegistrationService fillRegistrationForm(User user) {
        type(EMAIL_FIELD, user.getEmail());
        type(FIRST_NAME_FIELD, user.getName());
        type(PASSWORD_FIELD, user.getPassword());
        type(REPEAT_PASSWORD_FIELD, user.getRepeatPassword());
        return this;
    }

    public void submitRegistration() {
        WebElement btnSubmit = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON));
        if (btnSubmit.isEnabled()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSubmit);
        }
    }

    public String getSuccessMessageText() {
        return waitForVisible(SUCCESS_MESSAGE).getText();
    }

    private WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void type(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
}