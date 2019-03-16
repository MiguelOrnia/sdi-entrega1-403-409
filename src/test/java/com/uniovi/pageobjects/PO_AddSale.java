package com.uniovi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_AddSale extends PO_View {
	
	public static void goToPage(WebDriver driver) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sales/add')]");
		elementos.get(0).click();
	}
	
	public static void addSale(WebDriver driver, String title, String text, Double price, String date) {
		WebElement titleForm = driver.findElement(By.name("title"));
		titleForm.click();
		titleForm.clear();
		titleForm.sendKeys(title);
		WebElement textForm = driver.findElement(By.name("details"));
		textForm.click();
		textForm.clear();
		textForm.sendKeys(text);
		WebElement priceForm = driver.findElement(By.name("price"));
		priceForm.click();
		priceForm.clear();
		priceForm.sendKeys(String.format("%f.2", price));
		if (!date.isEmpty()) {
			WebElement dateForm = driver.findElement(By.name("date"));
			dateForm.click();
			dateForm.clear();
			dateForm.sendKeys(date);
		}
		// Pulsar el boton de crear.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}
