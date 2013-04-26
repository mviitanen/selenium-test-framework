package org.familysearch.selenium.test;

import org.familysearch.selenium.helper.ApplicationHelper;
import org.familysearch.selenium.pageobject.FamilySearch;
import org.familysearch.selenium.pageobject.IndividualRecord;
import org.familysearch.selenium.pageobject.SearchResults;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * Simple TestNG test which demonstrates being instantiated via a DataProvider
 * in order to supply multiple browser combinations.
 */
public class ExampleTest extends ApplicationHelper {

    private String browser;
    private String version;
    private String os;

  public ExampleTest() {
    super(new FirefoxDriver());

  }


  /*
   * Obtain username and key from Sauce Labs account
   */
  @BeforeMethod
  public void setUp() throws Exception {
//    this.driver = new FirefoxDriver();
  }

  @Test
  public void searchUsingCommonLib() throws Exception {

    String givenName = "Thomas Leland";
    String lastName = "Swan";
    String deathDate = "12 Feb 1991";
    String mainTitle = "Free Family History and Genealogy Records — FamilySearch.org";
    String searchResultsTitle = "Search Results — FamilySearch.org";
    String individualRecordPageTitle = "Record Details — FamilySearch.org";
    FamilySearch familySearch = new FamilySearch(driver);

    gotoFamilySearch(familySearch);
    familySearch.clickContinue();
    verifyMainTitle(mainTitle);
    familySearch.clickSearchButton();
    familySearch.clickSearchHistoricalRecordsButton();
    familySearch.setGivenName(givenName);
    familySearch.setLastName(lastName);
    SearchResults searchResults = familySearch.clickDoSearchButton();
    verifySearchResultsTitle(searchResultsTitle);
    verifyResults(givenName + " " + lastName);
    IndividualRecord individualRecord = searchResults.clickResultLink(givenName + " " + lastName);
    verifyResultTitle(individualRecordPageTitle);
    verifyResultName(givenName + " " + lastName);
    verifyResultDeathDate(deathDate);
  }

  @AfterMethod
  public void tearDown() throws Exception {
      driver.quit();
  }

}
