package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utility.Utils;

public class HomePage {
	//webelements
	WebDriver driver;
	By departure=By.id("departing");
	By returning=By.id("returning");
	By promoCode=By.id("promotional_code");
	By search=By.xpath("//input[@value='Search']");
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	public void setDepartureAndReturn(String from, String to) {
		Utils.waitForSelectElementPresence(driver, departure, 3).selectByVisibleText(from);
		Utils.waitForSelectElementPresence(driver, returning, 3).selectByVisibleText(to);
		
	}
	public void setPromoCode(String promo) {
		Utils.waitForElementPresence(driver, promoCode, 3).clear();
		Utils.waitForElementPresence(driver, promoCode, 3).sendKeys(promo);
	}
	public void clickSearch() {
		Utils.waitForElementPresence(driver, search, 3).click();
	}

}
