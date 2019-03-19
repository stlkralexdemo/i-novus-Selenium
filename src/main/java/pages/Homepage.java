//package pages;
//
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
//public class HomePage extends AnyPage {
//
//    @FindBy(id = "reg_submit")
//    private WebElement submitButton;
//
//    @FindBy(id = "email")
//    private WebElement emailField;
//
//    @FindBy(id = "username")
//    private WebElement usernameField;
//
//    @FindBy(id = "password")
//    private WebElement passwordField;
//
//    @FindBy(id = "invite")
//    private WebElement inviteField;
//
//    @FindBy(id = "dogovor")
//    private WebElement dogovorCheckbox;
//
//    @FindBy(id = "pers")
//    private WebElement persCheckbox;
//
//    @FindBy(id = "error_block")
//    private WebElement errorText;
//
//    public RegistrationPage(PageManager pages) {
//        super(pages);
//    }
//
//    public RegistrationPage setEmail(String email) {
//        emailField.sendKeys(email);
//        return this;
//    }
//
//    public RegistrationPage setUsername(String username) {
//        usernameField.sendKeys(username);
//        return this;
//    }
//
//    public RegistrationPage setPassword(String password) {
//        passwordField.sendKeys(password);
//        return this;
//    }
//
//    public RegistrationPage setInvite(String invite) {
//        inviteField.sendKeys(invite);
//        return this;
//    }
//
//    public RegistrationPage setDogovor() {
//        if (!dogovorCheckbox.isSelected()) {
//            dogovorCheckbox.click();
//        }
//        return this;
//    }
//
//    public RegistrationPage setPersonal() {
//        if (!persCheckbox.isSelected()) {
//            persCheckbox.click();
//        }
//        return this;
//    }
//
//    public void clickSubmitButton() {
//        submitButton.click();
//    }
//
//    public boolean isError() {
//        if (errorText.isDisplayed()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
//
