package org.craftercms.studio.test.cases.dashboardtestcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.craftercms.studio.test.pages.DashboardPage;
import org.craftercms.studio.test.pages.HomePage;
import org.craftercms.studio.test.pages.LoginPage;
import org.craftercms.studio.test.utils.ConstantsPropertiesManager;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class ShowHideSiteContentTest {

	WebDriver driver;

	private WebDriverManager driverManager;

	private LoginPage loginPage;
	
	private HomePage homePage;

	private DashboardPage dashboardPage;

	
	private String userName;
	private String password;
	private String adminConsoleXpath;
	

	@BeforeClass
	public void beforeTest() {
		this.driverManager = new WebDriverManager();

		UIElementsPropertiesManager UIElementsPropertiesManager = new UIElementsPropertiesManager(
				FilesLocations.UIELEMENTSPROPERTIESFILEPATH);
		ConstantsPropertiesManager constantsPropertiesManager = new ConstantsPropertiesManager(
				FilesLocations.CONSTANTSPROPERTIESFILEPATH);
		
		this.driverManager.setConstantsPropertiesManager(constantsPropertiesManager);
		
		this.loginPage = new LoginPage(driverManager, UIElementsPropertiesManager);
		this.homePage = new HomePage(driverManager, UIElementsPropertiesManager);
		this.dashboardPage = new DashboardPage(driverManager, UIElementsPropertiesManager);
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		adminConsoleXpath = UIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
	
	}

	@AfterClass
	public void afterTest() {
		driverManager.closeConnection();
	}

	@Test(priority = 0)
	public void verifyThatTheSiteContentIsDisplayedOrHiddenWhenClicksOnSiteContentTest() {

		// login to application

		loginPage.loginToCrafter(userName, password);

		// go to dashboard page
		homePage.goToDashboardPage();

		dashboardPage.clickOnSiteContentOption();

		// Assert that the site content is expanded
		String siteContentExpanded = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", adminConsoleXpath).getText();
		
		Assert.assertEquals(siteContentExpanded, "Site Config");

		// Collapse the site content panel
		dashboardPage.clickOnSiteContentOption();

		// Assert that the site content is Collapsed
		Assert.assertFalse(this.driverManager.isElementPresentByXpath(adminConsoleXpath));
	}

}