package com.uniovi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.utils.SeleniumUtils;

public class PO_SearchView extends PO_View {

	public static void goToPage(WebDriver driver) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//a[contains(@href, 'sales/search')]");
		elementos.get(0).click();
	}

	public static void searchForSale(WebDriver driver, String title) {
		WebElement inputSearch = driver.findElement(By.name("searchText"));
		inputSearch.click();
		inputSearch.sendKeys(title);
		List<WebElement> btnSearch = SeleniumUtils.EsperaCargaPagina(driver,
				"id", "search", getTimeout());
		btnSearch.get(0).click();
	}

	public static void buyOffer(WebDriver driver, String title) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"free",
				String.format(
						"//td[contains(text(), '%s')]/following-sibling::*[4]",
						title),
				getTimeout());
		elementos.get(0).click();
	}

	public static void sendMessageToSale(WebDriver driver, String title) {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver,
				"free",
				String.format(
						"//td[contains(text(), '%s')]/following-sibling::*[3]",
						title),
				getTimeout());
		elementos.get(0).click();
	}

}
