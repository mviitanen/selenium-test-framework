package org.familysearch.selenium.pageobject;

import org.familysearch.selenium.helper.ApplicationHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * IndividualRecord Page Object
 * The following methods are all "part of the page".
 * We define the buttons, links, text locations and anything else we want to interact with on the page in this Page Object.
 * For instance:
 * The Strings defined here are all CSS Locators for the Objects on the Individual Record Page.
 * We also "extend" the Application Helper, which does anything that is COMMON to ALL tests.
 *
 * @author MamanakisDM
 */
public class IndividualRecord extends ApplicationHelper {

  /**
   * CSS Locators for several elements on the Family Search Search Page.
   * etc
   *
   */
  public static final String individualRecordContainer = "#main .row-fluid .row-fluid .result-data";
  private WebDriver driver;

  /**
   * IndividualRecord
   * Constructor for the Page Object which makes the DRIVER available to you if you need it.
   *
   * @param driver
   */
  public IndividualRecord(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * verifyResultDeathDate
   * This method verifies the Death Date of the result we chose
   *
   * @param deathDate
   */
  public void verifyResultDeathDate(String deathDate) {
    Assert.assertTrue(isTextPresent(driver, deathDate));
  }

  /**
   * verifyResultName
   * This method verifies the Name of the result we chose
   *
   * @param fullName
   */
  public void verifyResultName(String fullName) {
    if (!isTextPresent(driver, fullName)) {
      Assert.fail(getText("#main .content .search_error"));
    }
    Assert.assertTrue(isTextPresent(driver, fullName));
  }

  /**
   * verifyResultTitle
   * This method verifies the Title of the Results page
   *
   * @param individualRecordPageTitle
   */
  public void verifyResultTitle(String individualRecordPageTitle) {
    if (!driver.getTitle().contains(individualRecordPageTitle)) {
      Assert.fail(getText("#main .content .search_error"));
    }
    Assert.assertTrue(driver.getTitle().contains(individualRecordPageTitle));
  }
}
