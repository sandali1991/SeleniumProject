import dataProvider.ConfigFileReader;
import helper.PageObjectHelper;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.ChangePasswordPage;
import pages.LoginPage;

public class VerifyChangePasswordPageAndLogin
{
  private PageObjectHelper pageObjectHelper;
  private LoginPage loginPage;
  private ChangePasswordPage changePasswordPage;
  private ConfigFileReader configFileReader = new ConfigFileReader();

  @BeforeTest
  public void setUp(){
    this.pageObjectHelper = new PageObjectHelper();
    this.pageObjectHelper.Browser_SetUp();
  }

  @BeforeMethod
  public void beforeMethods() {
    loginPage= new LoginPage(this.pageObjectHelper.driver);
    changePasswordPage = new ChangePasswordPage(this.pageObjectHelper.driver);
  }

  @AfterTest
  public void teardown () {
    pageObjectHelper. driver.quit();
  }

  @Test
  public void successfullyLoginToPage() throws Exception
  {
    pageObjectHelper.openUrl(configFileReader.getPropertyValue("url"));
    pageObjectHelper.waitforsometime();
    loginPage.addUsername(configFileReader.getPropertyValue("username"));
    loginPage.addPassword(configFileReader.getPropertyValue("password"));
    loginPage.clickLogin();
    pageObjectHelper.waitforsometime();
    changePasswordPage.navigateToChangePasswordPage();
    changePasswordPage.addOldPassword("ucsc@");
    changePasswordPage.addNewPassword("123456@");
    changePasswordPage.addConfirmPassword("123456@");
    changePasswordPage.clickSubmit();
    Assert.assertEquals(changePasswordPage.getAlertText(),"Old Password is incorrect");
    changePasswordPage.clickAlertOkBtn();
    Assert.assertEquals(changePasswordPage.getTitle(),"Change Password");
    pageObjectHelper.takeSnapShot(configFileReader.getPropertyValue("screenshotPath")+"\\ChangePwdScreenshot.jpg");
  }
}
