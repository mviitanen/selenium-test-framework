package org.familysearch.selenium.pageobject;

import org.familysearch.selenium.helper.ApplicationHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;

/**
 * FamilySearch Page Object
 * The following methods are all "part of the page".
 * We define the buttons, links, text locations and anything else we want to interact with on the page in this Page Object.
 * For instance:
 * The Strings defined here are all CSS Locators for the Objects on the Family Search Page.
 * We also "extend" the Application Helper, which does anything that is COMMON to ALL tests.
 *
 * @author MamanakisDM
 */
public class FamilySearch extends ApplicationHelper {

  /**
   * CSS Locators for several elements on the Family Search Search Page.
   * etc
   *
   */
  private final String searchButton = ".app-home #main .banner-nav .banner-nav-container .banner-nav-list .search a";
  private final String doSearchButton = ".app-search #main .row-fluid .span12 .search-form-control #record-search .form-actions .btn";
  private WebDriver driver;

  /**
   * FamilySearch
   * Constructor for the Page Object which makes the DRIVER available to you if you need it.
   *
   * @param driver
   */
  public FamilySearch(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * clickSearchButton
   * This clicks on the Search Button on the Main Page to get to the Search Page
   *
   * @return New Page Object "SearchPage"
   */
  public SearchPage clickSearchButton() {
    clickAndWaitForElementPresent(searchButton, doSearchButton);
    return PageFactory.initElements(getDriver(), SearchPage.class);
  }

  /**
   * verifyFamilySearchMainPageTitle
   * This will verify the Page Title on FamilySearch.org
   *
   * @param mainTitle
   */
  public void verifyFamilySearchMainPageTitle(String mainTitle) {
    assertEquals(driver.getTitle(), mainTitle);
  }
}
