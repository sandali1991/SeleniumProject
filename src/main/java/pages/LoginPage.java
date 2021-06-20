package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
  private WebDriver webDriver;
  @FindBy(name = "uid")
  private WebElement username;
  @FindBy(name = "password")
  private WebElement password;
  @FindBy(name = "btnLogin")
  private WebElement btnLogin;

  public LoginPage(WebDriver driver)
  {
    this.webDriver = driver;
    PageFactory.initElements(this.webDriver, this);
  }

  public void addUsername(String userName)
  {
    username.sendKeys(userName);
  }

  public void addPassword(String passWord)
  {
    password.sendKeys(passWord);
  }

  public void clickLogin()
  {
    btnLogin.click();
  }

  public String getAlertText(){
    return webDriver.switchTo().alert().getText();
  }
}
