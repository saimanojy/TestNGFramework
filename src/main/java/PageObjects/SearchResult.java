package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utility.Utils;

public class SearchResult {
	//webelements
	WebDriver driver;
	public SearchResult(WebDriver driver) {
		this.driver=driver;
	}
	
	public By promoText=By.xpath("//p[@class='promo_code']");
	public By backBtn=By.xpath("//a[contains(text(),'Back')]");
	public String getPromoText() {
		return Utils.waitForElementPresence(driver, promoText, 3).getText();
	}
	public void clickBackBtn() {
		Utils.waitForElementPresence(driver, backBtn, 3).click();
	}
}
