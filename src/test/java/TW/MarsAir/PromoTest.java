package TW.MarsAir;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.SearchResult;
import Utility.TestDataProviders;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PromoTest {
	public Properties prop;
	String url;
	String browser;
	public WebDriver driver;
	public HomePage homepage;
	public SearchResult searchResult;
	@BeforeTest
	public void setBroswer() throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream("./src/test/resources/input.properties"));
		url = prop.getProperty("URL");
		browser=prop.getProperty("Browser");
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		homepage=new HomePage(driver);
		searchResult=new SearchResult(driver);
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Test(dataProvider="getPromos", dataProviderClass=TestDataProviders.class)
	public void PromoCodes(String promo, String expectedString) {
		homepage.setDepartureAndReturn("July", "December (two years from now)");
		homepage.setPromoCode(promo);
		homepage.clickSearch();
		String actual=searchResult.getPromoText();
		Reporter.log("Promo Code Used: " + promo, true);
	    Reporter.log("Expected: " + expectedString, true);
	    Reporter.log("Actual: " + actual, true);
		searchResult.clickBackBtn();
		Assert.assertTrue(actual.contains(expectedString));
	}
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
