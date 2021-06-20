package helper;

import dataProvider.ConfigFileReader;
import java.io.File;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class PageObjectHelper
{
  private static WebDriver drive=null;
  public WebDriver driver;
  private WebDriverWait wait;
  ConfigFileReader configFileReader=new ConfigFileReader();

  public void  Browser_SetUp(){
    System.setProperty("webdriver.chrome.driver", configFileReader.getPropertyValue("driverPath"));
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver,15);

  }

  private static WebDriver getDrive() {
    return drive;
  }

  public static void setDrive(WebDriver drive) {
    PageObjectHelper.drive = drive;
  }
  private static WebElement FindElement(By locator)
  {
    int timeOut = 10;
    return (new WebDriverWait(getDrive(), timeOut))
      .until(ExpectedConditions.presenceOfElementLocated(locator));
  }

  public void openUrl(String url)
  {
    driver.get(url);
  }

  public void click(By locator)
  {
    try
    {
      WebElement element =FindElement(locator);
      element.click();


    }
    catch(Exception ex)
    {
      System.out.println("Error clicking the object");
      ex.printStackTrace();
    }
  }

  public void select(By locator  ,String text)
  {
    Select selectByValue = new Select(driver.findElement(locator));
    selectByValue.selectByValue(text);
    //        try
    //        {
    //            new Select(getDrive().findElement(locator)).selectByVisibleText(text);
    //        }
    //        catch(Exception ex)
    //        {
    //            System.out.println("Error select  the object");
    //            ex.printStackTrace();
    //        }
  }

  public void type(By locator, String value)
  {
    try
    {
      WebElement element = getDrive().findElement(locator);
      element.sendKeys(value);
    }
    catch(Exception ex)
    {
      System.out.println("Error select  the object");
      ex.printStackTrace();
    }

  }

  public void waitforsometime()
  {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {

      e.getMessage();
    }
  }

  public void takeSnapShot(String filePath) throws Exception{
//    //Convert web driver object to TakeScreenshot
//    TakesScreenshot scrShot =((TakesScreenshot)drive);
//    //Call getScreenshotAs method to create image file
//    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
//    //Move image file to new destination
//    File DestFile=new File(fileWithPath);
//    //Copy file at destination
//    FileUtils.copyFile(SrcFile, DestFile);
    //Screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
    Screenshot screenshot = new AShot().takeScreenshot(driver);
    ImageIO.write(screenshot.getImage(), "jpg", new File(filePath));
  }
}
