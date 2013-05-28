package org.familysearch.selenium.pageobject;

import org.familysearch.selenium.helper.ApplicationHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

/**
 * SearchResults Page Object
 * The following methods are all "part of the page".
 * We define the buttons, links, text locations and anything else we want to interact with on the page in this Page Object.
 * For instance:
 * The Strings defined here are all CSS Locators for the Objects on the Search Results Page.
 * We also "extend" the Application Helper, which does anything that is COMMON to ALL tests.
 *
 * @author MamanakisDM
 */
public class SearchResults extends ApplicationHelper {

  /**
   * CSS Locators for several elements on the Family Search Search Page.
   * etc
   *
   */
  public static final String searchResultsContainer = ".app-search .hRResults #subject .search-results #hist-results .results";
  private WebDriver driver;

  /**
   * SearchResults
   * Constructor for the Page Object which makes the DRIVER available to you if you need it.
   *
   * @param driver
   */
  public SearchResults(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * clickResultLink
   * This method clicks on the Results Link. It will take a name, and click on the link that matches the name
   *
   * @param fullName
   * @return New Page Object "IndividualRecord"
   */
  public IndividualRecord clickResultLink(String fullName) {
    WebElement linkText = driver.findElement(By.linkText(fullName));
    String href = getHREFattribute(linkText);
    String locator = searchResultsContainer + " tbody" + " .result-name .person-name[href=\"" + href + "\"]";
    click(locator);
    waitForElementPresent(IndividualRecord.individualRecordContainer);
    return PageFactory.initElements(getDriver(), IndividualRecord.class);
  }

  /**
   * verifySearchResultsPageTitle
   * This method verifies the Page Title for the Search Results Page
   *
   * @param searchResultsTitle
   */
  public void verifySearchResultsPageTitle(String searchResultsTitle) {
    assertEquals(driver.getTitle(), searchResultsTitle);
  }

  /**
   * verifySearchedNameExistsInResults
   * This method verifies the Name you searched for exists in the results on the page
   *
   * @param fullName
   */
  public void verifySearchedNameExistsInResults(String fullName) {
    Assert.assertTrue(isTextPresent(driver, fullName));
  }
}
