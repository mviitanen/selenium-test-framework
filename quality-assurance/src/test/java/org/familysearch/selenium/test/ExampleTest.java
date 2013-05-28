package org.familysearch.selenium.test;

import org.familysearch.selenium.helper.ApplicationHelper;
import org.familysearch.selenium.helper.BrowserHelper;
import org.familysearch.selenium.helper.DriverHelper;
import org.familysearch.selenium.pageobject.FamilySearch;
import org.familysearch.selenium.pageobject.IndividualRecord;
import org.familysearch.selenium.pageobject.SearchPage;
import org.familysearch.selenium.pageobject.SearchResults;
import org.openqa.selenium.WebDriver;

/**
 * ExampleTest
 * This class holds the actual test. You can define the test here by including the methods included in the Page Objects (in this case the FamilySearch page object)
 * You can define your variables, as seen below with the initialization of the 6 Strings and the Page Object.
 * When you are done, you have a test that reads simply: goto family search, verify family search main page title, click search button... etc.
 * It makes the tests very easy to follow, easy to understand and easier to debug.
 * All verifications also happen in the Page Object. You call them here, like "verifySearchResultsPageTitle" and pass in the values you want to check.
 *
 * @author MamanakisDM
 */
public class ExampleTest extends ApplicationHelper {

  /**
   * ExampleTest
   * Constructor for the class.
   *
   * @param driver instantiation the driver for your requested settings
   * @param browserSession instantiation the browser session for your requested settings
   */
  public ExampleTest(WebDriver driver, BrowserHelper browserSession) {
    super(driver, browserSession);
  }

  /**
   * searchUsingCommonLib
   * This test specifically runs against Family Search SEARCH page and it uses the Common Lib to do it.
   * You will notice there are a couple different ways that we create a Page Object.
   * They both work equally well.
   * The first is to simply "new up" the page.
   * EXAMPLE: FamilySearch familySearch = new FamilySearch(driver);
   * The second is to "new up" the page in the page object, after an action is performed, like clicking on a button
   * EXAMPLE: SearchResults searchResults = familySearch.clickDoSearchButton();
   * The second method is a little "cleaner".
   * BUT the "gotoTestLocation" method would return any page object, updated.
   *
   * @throws Exception
   */
  public void searchUsingCommonLib() throws Exception {
    DriverHelper driverHelper = new DriverHelper();

    String givenName = "Thomas Leland";
    String lastName = "Swan";
    String deathDate = "12 Feb 1991";
    String mainTitle = "Free Family History and Genealogy Records — FamilySearch.org";
    String searchResultsTitle = "Search Results — FamilySearch.org";
    String individualRecordPageTitle = "Thomas Leland Swan, \"California, Death Index, 1940-1997\"";
    FamilySearch familySearch = new FamilySearch(driver);
    String url = driverHelper.getWorkingUrl();

    //Go to the starting point
    gotoTestLocation(familySearch, url);
    //Verify the user is on the correct page, fail if it is NOT the right page
    familySearch.verifyFamilySearchMainPageTitle(mainTitle);
    //If it is the right page, we continue to execute actions against the page
    SearchPage searchPage = familySearch.clickSearchButton();
    searchPage.setGivenName(givenName);
    searchPage.setLastName(lastName);
    //We create a new page object (Search Results) based on a particular action (Click Do Search Button)
    SearchResults searchResults = searchPage.clickDoSearchButton();
    //Verify that the right page is achieved and that our target exists on that page
    searchResults.verifySearchResultsPageTitle(searchResultsTitle);
    searchResults.verifySearchedNameExistsInResults(givenName + " " + lastName);
    //Getting the Individual Record Page Object ready for further testing, after doing a particular action to take us to that page (Click Results Link)
    IndividualRecord individualRecord = searchResults.clickResultLink(givenName + " " + lastName);
    //Verify the Title of the Page is correct and that it belongs to the subject of the search. You can also verify many other things, here we check the date.
    individualRecord.verifyResultTitle(individualRecordPageTitle);
    individualRecord.verifyResultName(givenName + " " + lastName);
    individualRecord.verifyResultDeathDate(deathDate);
  }

}
