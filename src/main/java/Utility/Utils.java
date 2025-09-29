package Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class Utils {
	// function to wait for element to be click able, returns web element
	public static WebElement waitToBeClickable(WebDriver driver, By selector, int waitInterval) {

		WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(waitInterval)))
				.until(ExpectedConditions.elementToBeClickable(selector));
		return element;
	}

	// function to wait for element to be present, returns web element
	public static WebElement waitForElementPresence(WebDriver driver, By selector, int waitInterval) {
		WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(waitInterval)))
				.until(ExpectedConditions.presenceOfElementLocated(selector));
		return element;
	}
	public static Select waitForSelectElementPresence(WebDriver driver, By selector, int waitInterval) {
		WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(waitInterval)))
				.until(ExpectedConditions.presenceOfElementLocated(selector));
		return new Select(element);
	}

	// function to wait for child element to visible, returns web element
	public static WebElement waitforChild(WebDriver driver, WebElement element, By selector, int waitInterval) {
		WebElement result = (new WebDriverWait(driver, Duration.ofSeconds(waitInterval)))
				.until(ExpectedConditions.visibilityOf(element.findElement(selector)));
		return result;
	}

	// function to wait for elements to visible, returns list of web element
	public static List<WebElement> waitForElementsPresence(WebDriver driver, By selector, int waitInterval) {
		java.util.List<WebElement> element = (new WebDriverWait(driver, Duration.ofSeconds(waitInterval)))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selector));
		return element;
	}

	// function to select value in drop down
	public static boolean dropDownSelect(Select select, String value) {

		List<WebElement> options = select.getOptions();
		for (WebElement option : options) {
			// System.out.println(option.getText());
			if (option.getText().equalsIgnoreCase(value)) {
				option.click();
				return true;
			}
		}
		return false;

	}

	// funcion to take screenshot, takes webDriver and string as parameter
	public static void takeSceenShot(WebDriver driver, String msg) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// System.out.println("time"+timeStamp);
		File SrcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, new File("./screenshots/" + msg + "_" + timeStamp + ".png"));
	}

	// funcion to take screenshot, takes webDriver, element and string as parameter
	public static void takeSceenShotWithScroll(WebDriver driver, WebElement ele, String msg) throws IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// System.out.println(msg + "_" + timeStamp);
		File SrcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, new File("./screenshots/" + msg + "_" + timeStamp + ".png"));
	}

}
