package org.familysearch.selenium.helper;

import org.familysearch.selenium.BaseSeleniumPage;
import org.familysearch.selenium.pageobject.FamilySearch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

//import org.openqa.selenium.remote.Augmenter;

public class ApplicationHelper extends BaseSeleniumPage {

  protected WebDriver driver;

  public ApplicationHelper(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  public void verifyMainTitle(String mainTitle) {
    assertEquals(driver.getTitle(), mainTitle);
  }

  public void gotoFamilySearch(FamilySearch familySearch) throws Exception {
    launchURL(driver, "https://familysearch.org/");
    PageFactory.initElements(driver, familySearch);
  }

  public void verifySearchResultsTitle(String searchResultsTitle) {
    assertEquals(driver.getTitle(), searchResultsTitle);
  }

  public void verifyResults(String fullName) {
    Assert.assertTrue(isTextPresent(driver, fullName));
  }

  public void verifyResultDeathDate(String deathDate) {
    Assert.assertTrue(isTextPresent(driver, deathDate));
  }

  public void verifyResultName(String fullName) {
    Assert.assertTrue(isTextPresent(driver, fullName));
  }

  public void verifyResultTitle(String individualRecordPageTitle) {
    assertEquals(driver.getTitle(), individualRecordPageTitle);
  }
}
