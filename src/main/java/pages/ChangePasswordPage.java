package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage
{
  private WebDriver webDriver;

  @FindBy(xpath = "//a[@href='PasswordInput.php']")
  private WebElement changePwdMenu;
  @FindBy(xpath = "//input[@type='password' and @name='oldpassword']")
  private WebElement oldPwd;
  @FindBy(xpath = "//input[@type='password' and @name='newpassword']")
  private WebElement newPwd;
  @FindBy(xpath = "//input[@type='password' and @name='confirmpassword']")
  private WebElement confirmPwd;
  @FindBy(xpath = "//input[@type='submit' and @name='sub']")
  private WebElement btnSubmit;
  @FindBy(xpath = "//p[@class='heading3']")
  private WebElement title;

  public ChangePasswordPage(WebDriver webDriver)
  {
    this.webDriver = webDriver;
    PageFactory.initElements(this.webDriver, this);
  }

  public void navigateToChangePasswordPage()
  {
    changePwdMenu.click();
  }

  public void addOldPassword(String passWord)
  {
    oldPwd.sendKeys(passWord);
  }

  public void addNewPassword(String passWord)
  {
    newPwd.sendKeys(passWord);
  }

  public void addConfirmPassword(String passWord)
  {
    confirmPwd.sendKeys(passWord);
  }
  public void clickSubmit()
  {
    btnSubmit.click();
  }

  public String getAlertText(){
    return webDriver.switchTo().alert().getText();
  }

  public void clickAlertOkBtn(){
    webDriver.switchTo().alert().accept();
  }

  public String getTitle(){
    return title.getText();
  }
}
