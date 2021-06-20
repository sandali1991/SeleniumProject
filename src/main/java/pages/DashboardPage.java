package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage
{
  private WebDriver webDriver;

  @FindBy(xpath = "//h2[contains(text(),'Guru99 Bank')]")
  private WebElement title;

  @FindBy(xpath = "//td[contains(text(),'Manger Id')]")
  public static WebElement managerId;

  public DashboardPage(WebDriver driver)
  {
    this.webDriver = driver;
    PageFactory.initElements(this.webDriver, this);
  }

  public String getHomePageTitle(){
    return title.getText();
  }

  public String getManagerID(){
    return managerId.getText().substring(12);
  }
}
