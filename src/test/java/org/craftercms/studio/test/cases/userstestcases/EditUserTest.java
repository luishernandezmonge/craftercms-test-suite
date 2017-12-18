package org.craftercms.studio.test.cases.userstestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import org.craftercms.studio.test.cases.BaseTest;


/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class EditUserTest extends BaseTest{

	private String userName;
	private String password;
	private String newUserLastNameId;
	private String newUserEmailId;
	private String newUserFirstNameId;
	private String newUserUserNameId;
	private String newUserPasswordId;
	private String newUserPasswordVerificationId;
	private String newUserButtonXpath;
	private String newUserNewPasswordId;
	private String newUserLastNameCellXpath;
	private String deleteYesButtonXpath;
	private String usersRowsXpath;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		newUserFirstNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.firstname");
		newUserLastNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.lastname");
		newUserEmailId = uiElementsPropertiesManager.getSharedUIElementsLocators().getProperty("general.users.email");
		newUserUserNameId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.username");
		newUserPasswordId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.password");
		newUserPasswordVerificationId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.passwordVerification");
		newUserButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.newuserbutton");
		newUserNewPasswordId = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.newpassword");
		newUserLastNameCellXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.newuserlastnamecell");
		deleteYesButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.deleteyesbutton");
		usersRowsXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersrows");
	}

	public void createUserToEdit() {
		// click on new user button
		usersPage.clickOnNewUser();

		// Follow the form
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserFirstNameId).sendKeys("Name");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserLastNameId).sendKeys("Last Name");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserEmailId)
				.sendKeys("email@email.com");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserUserNameId).sendKeys("username");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserPasswordId).sendKeys("password");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserPasswordVerificationId)
				.sendKeys("password");

		// Save Button
		usersPage.clickOnSaveNewUser();
	

	}

	public void editingUser() {
		// Click on edit option

		usersPage.clickOnEditUserCreated();

		// Follow the form
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserFirstNameId).clear();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserFirstNameId).sendKeys("Test");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserLastNameId).clear();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserLastNameId).sendKeys("Test");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserEmailId).clear();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserEmailId).sendKeys("Test@email.com");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserNewPasswordId).clear();
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("id", newUserNewPasswordId)
				.sendKeys("passwordEdited");

		// Save Button
		usersPage.clickOnSaveNewUser();
		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(newUserButtonXpath);

	}

	@Test(priority = 0)

	public void editUser() {

		// login to application
		loginPage.loginToCrafter(userName, password);

		//Wait for login page to close
		driverManager.waitUntilLoginCloses();
		
		// click On Users option
		createSitePage.clickOnUsersOption();

		// create a new user
		createUserToEdit();

		// wait for element is clickeable
		driverManager.getDriver().navigate().refresh();

		// edit user
		editingUser();

		// Assert
		driverManager.getDriver().navigate().refresh();
		
//		this.driverManager.isElementPresentAndClickableByXpath(newUserButtonXpath);
//		this.driverManager.isElementPresentAndClickableByXpath(newUserButtonXpath);
//		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(newUserButtonXpath);
//		
//		driverManager.getDriver().navigate().refresh();
//		
//		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(newUserLastNameCellXpath);
//		this.driverManager.isElementPresentByXpath(newUserLastNameCellXpath);
//		this.driverManager
//		.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserLastNameCellXpath);
//		
		String nameElementText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath", newUserLastNameCellXpath).getText();

		Assert.assertEquals(nameElementText, "Test");

		// Click on delete user
		driverManager.getDriver().navigate().refresh();
		
		usersPage.clickOnDeleteUserCreated();

		// Confirmation to delete user 
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayedAndClickable("xpath", deleteYesButtonXpath).click();
		
		driverManager.getDriver().navigate().refresh();
		//driverManager.getDriver().navigate().refresh();
		
		// Assert new users created is deleted
		Assert.assertTrue(this.driverManager.elementHasChildsByXPath(usersRowsXpath));

		List<WebElement> usersList = this.driverManager.getDriver().findElements(By.xpath(usersRowsXpath));
		Assert.assertTrue(usersList.size() == 1);

	}
}
