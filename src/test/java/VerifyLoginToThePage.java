import dataProvider.ConfigFileReader;
import helper.PageObjectHelper;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class VerifyLoginToThePage
{
  private PageObjectHelper pageObjectHelper;
  private LoginPage loginPage;
  private DashboardPage dashboardPage;
  private ConfigFileReader configFileReader = new ConfigFileReader();

  @BeforeTest
  public void setUp(){
    this.pageObjectHelper = new PageObjectHelper();
    this.pageObjectHelper.Browser_SetUp();
  }

  @BeforeMethod
  public void beforeMethods() {
    loginPage= new LoginPage(this.pageObjectHelper.driver);
    dashboardPage = new DashboardPage(this.pageObjectHelper.driver);
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
    Assert.assertEquals(dashboardPage.getHomePageTitle(), "Guru99 Bank");
    Assert.assertEquals(dashboardPage.getManagerID(), configFileReader.getPropertyValue("username"));
    pageObjectHelper.takeSnapShot(configFileReader.getPropertyValue("screenshotPath")+"\\ElementScreenshot.jpg");
  }

  @Test
  public void enterInvalidUserIdAndValidPassword(){
    pageObjectHelper.openUrl(configFileReader.getPropertyValue("url"));
    pageObjectHelper.waitforsometime();
    loginPage.addUsername("invalidUserName");
    loginPage.addPassword(configFileReader.getPropertyValue("password"));
    loginPage.clickLogin();
    pageObjectHelper.waitforsometime();
    Assert.assertEquals(loginPage.getAlertText(),"User or Password is not valid");
  }

  @Test
  public void enterValidUserIdAndInvalidPassword(){
    pageObjectHelper.openUrl(configFileReader.getPropertyValue("url"));
    pageObjectHelper.waitforsometime();
    loginPage.addUsername(configFileReader.getPropertyValue("username"));
    loginPage.addPassword("1234");
    loginPage.clickLogin();
    pageObjectHelper.waitforsometime();
    Assert.assertEquals(loginPage.getAlertText(),"User or Password is not valid");
  }

  @Test
  public void enterInvalidUserIdAndInvalidPassword()
  {
    pageObjectHelper.openUrl(configFileReader.getPropertyValue("url"));
    pageObjectHelper.waitforsometime();
    loginPage.addUsername("invalidUserName");
    loginPage.addPassword("1234");
    loginPage.clickLogin();
    pageObjectHelper.waitforsometime();
    Assert.assertEquals(loginPage.getAlertText(),"User or Password is not valid");
  }
}
