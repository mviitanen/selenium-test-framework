package org.familysearch.selenium.helper;

import org.apache.log4j.Logger;
import org.familysearch.selenium.BaseSeleniumPage;
import org.familysearch.selenium.pageobject.FamilySearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * ApplicationHelper
 * This class is a bit of "common to everything" methods.
 *
 * @author MamanakisDM
 */
public class ApplicationHelper extends BaseSeleniumPage {

  /**
   * Variables, Statics, Commons, etc
   * etc
   *
   */
  protected static final Logger LOG = Logger.getLogger(ApplicationHelper.class);
  protected WebDriver driver;
  private BrowserHelper browserHelper;

  /**
   * ApplicationHelper Constructor
   *
   * @param webDriver
   * @param browserHelper
   */
  public ApplicationHelper(WebDriver webDriver, BrowserHelper browserHelper) {
    super(webDriver);

    this.browserHelper = browserHelper;
    this.driver = webDriver;
  }

  /**
   * ApplicationHelper Constructor
   *
   * @param webDriver
   */
  public ApplicationHelper(WebDriver webDriver) {
    super(webDriver);

    this.driver = webDriver;
  }

  /**
   * getBrowserSession
   * Will return the Browser Session
   *
   * @return BrowserHelper
   */
  protected BrowserHelper getBrowserSession() {
    return browserHelper;
  }

  /**
   * setBrowserSession
   * Will set the Browser Session to be retrieved later.
   *
   * @param browserSession
   */
  protected void setBrowserSession(BrowserHelper browserSession) {
    this.browserHelper = browserSession;
  }

  /**
   * gotoTestLocation
   * This is the GOTO method to take the user to the Family Search page.
   *
   * @param familySearch
   * @param url
   * @throws Exception
   */
  public void gotoTestLocation(FamilySearch familySearch, String url) throws Exception {
    launchURL(driver, url);
    PageFactory.initElements(driver, familySearch);
  }

  /**
   * launchURL
   * A TEMPORARY method with UPDATED bits to OVERRIDE the SeleniumCommonLib until we get that code updated with this.
   *
   * @param driver
   * @param url
   * @throws Exception
   */
  //TODO: remove this after the Selenium Common Lib has been updated with this code
  //TODO: For now, we need the extra logging to debug
  public void launchURL(WebDriver driver, String url) throws Exception {
    try {
      driver.navigate().to(url);
    } catch (Exception e) {
      String message = "navigateToUrl could not navigate the url: " + url;
      throw new Exception(message + " : " + e);
    }
  }
}
