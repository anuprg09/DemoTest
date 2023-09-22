package itSutraGID.DemoOfMaven;

import org.testng.annotations.Test;
import org.testng.internal.IResultListener;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class EappLoginTest {
  WebDriver driver;
	
  @Test
  public void VerifyUserLoginAlertMsgWhenSubmitOnlyUserNameInfo() {
	  String UserName = "admin";
	  String Password = "";
	  
	  driver.findElement(By.id("loginLink")).click();
	  Reporter.log("\r\nHome Login Button clciked and Login Page get Open");
	  
	  driver.findElement(By.id("UserName")).sendKeys(UserName);
	  Reporter.log("User Name getting input");

	  driver.findElement(By.id("Password")).sendKeys(Password);
	  Reporter.log("Password getting input");
	  
	  driver.findElement(By.xpath("//input[@value='Log in']")).click();
	  Reporter.log("click to submit login Credential");
	  
	  WebElement ErrorMsg = driver.findElement(By.xpath("//span[.='The Password field is required.'][@for='Password']"));
	  assertNotNull(ErrorMsg, "Getting Error msg for Password");
	  Reporter.log("\r\nSucess!!!, Getting Error msg for Password");
	  
  }
  
  @Test
  public void verifyUserLoginWithoutUserName()
  {
	  driver.get("https://www.google.com");
	  Reporter.log("verifyUserLoginWithoutUserName is Executed .... AAAAAAAAA!!!!"); 
  }
  
  @Test
  public void verifyUserLoginWithoutPassword()
  {
	  driver.get("https://www.wikipedia.com");
	  Reporter.log("verifyUserLoginWithoutPassword is Executed"); 
  }
 
  @Test
  public void verifyUserLoginWithoutPasswordAndUserName()
  {
	  driver.get("https://www.gmail.com");
	  Reporter.log("verifyUserLoginWithoutPasswordAndUserName is Executed"); 
  }
 
  
  
  @Parameters({"url", "browser"})
  @BeforeTest
  public void SetUp(String url, String browser) throws MalformedURLException {
	  DesiredCapabilities dc = new DesiredCapabilities();
	  
	  if(browser.equals("firefox")) {
		  dc.setBrowserName("");
		  //driver = new FirefoxDriver();	
	}
	else if(browser.equals("chrome")) {
		dc.setBrowserName("chrome");
	}
	else{
		  driver = new EdgeDriver();	
	}
	   driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
	  
	  driver.get(url);
	  driver.manage().window().maximize();
	  Reporter.log("Browser Init and Navigate to http://eaapp.somee.com/Account/Login");
  }

  @AfterTest
  public void Clean() {
	  driver.quit();
  }

  @AfterMethod
  public void failureSetup(ITestResult result) {
      Reporter.setCurrentTestResult(result);

      if(result.getStatus() == 2){ //failed scenaario
          Reporter.log("Failed Test case...!!!", true);

      }
      else {
    	  Reporter.log("Success!!!!", true);
    	  }
    }
  
}
