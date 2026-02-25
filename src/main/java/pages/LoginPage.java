package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;
    
    @FindBy(id="Email")
    WebElement usernameField;
    @FindBy(id="Password")
    WebElement passwordField;
    @FindBy(css="button[type='submit']")
    WebElement ButtonameField;
    
    //private By usernameField = By.id("Email");
    //private By passwordField = By.id("Password");
    //private By loginButton = By.cssSelector("button[type='submit']");
    
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void enterUsername(String username) {
    	usernameField.clear();
    	usernameField.sendKeys(username);
    	
       // driver.findElement(usernameField).clear();
       // driver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password) {
    	passwordField.clear();
    	passwordField.sendKeys(password);
       // driver.findElement(passwordField).clear();
        //driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLogin() {
    	ButtonameField.click();
       // driver.findElement(loginButton).click();
    }
}