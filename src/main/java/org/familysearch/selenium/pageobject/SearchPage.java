package org.familysearch.selenium.pageobject;

import org.familysearch.selenium.helper.ApplicationHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * SearchPage Page Object
 * The following methods are all "part of the page".
 * We define the buttons, links, text locations and anything else we want to interact with on the page in this Page Object.
 * For instance:
 * The Strings defined here are all CSS Locators for the Objects on the Search Page.
 * We also "extend" the Application Helper, which does anything that is COMMON to ALL tests.
 *
 * @author MamanakisDM
 */
public class SearchPage extends ApplicationHelper {

  /**
   * CSS Locators for several elements on the Family Search Search Page.
   * etc
   *
   */
  private final String givenNameTextBox = ".app-search #main .row-fluid .span12 .search-form-control #record-search #person .exact-field .givenname #hr_pg";
  private final String lastNameTextBox = ".app-search #main .row-fluid .span12 .search-form-control #record-search #person .exact-field .surname #hr_ps";
  private final String doSearchButton = ".app-search #main .row-fluid .span12 .search-form-control #record-search .form-actions .btn";
  private WebDriver driver;

  /**
   * FamilySearch
   * Constructor for the Page Object which makes the DRIVER available to you if you need it.
   *
   * @param driver
   */
  public SearchPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * setGivenName
   * This puts the "Given Name" string, defined in the Test, into the Given Name Text Box on the Search Page
   *
   * @param givenName
   */
  public void setGivenName(String givenName) {
    setInputValue(givenNameTextBox, givenName);
  }

  /**
   * setLastName
   * This puts the "Last Name" string, defined in the Test, into the Last Name Text Box on the Search Page
   *
   * @param lastName
   */
  public void setLastName(String lastName) {
    setInputValue(lastNameTextBox, lastName);
  }

  /**
   * clickDoSearchButton
   * This clicks Search
   *
   * @return New Page Object "SearchResults"
   */
  public SearchResults clickDoSearchButton() {
    clickAndWaitForElementPresent(doSearchButton, SearchResults.searchResultsContainer);
    return PageFactory.initElements(getDriver(), SearchResults.class);
  }
}
