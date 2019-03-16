package com.uniovi.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_UserList extends PO_View {

	public static void goToPage(WebDriver driver) {
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();
	}

	public static void deleteUser(WebDriver driver, int... indexes) {
		List<WebElement> checkboxes = PO_View.checkElement(driver, "class", "checkBox");
		for(int i : indexes)
			checkboxes.get(i).click();
		List<WebElement> btnDelete = PO_View.checkElement(driver, "id", "delete");
		btnDelete.get(0).click();
	}

}
