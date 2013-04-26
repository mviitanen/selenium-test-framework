package org.familysearch.selenium.pageobject;

import org.familysearch.selenium.helper.ApplicationHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FamilySearch extends ApplicationHelper {

  /*
   * WebElements
   */
  private final String givenNameTextBox = ".app-search #main .row-fluid .span12 .search-form-control #record-search #person .exact-field .givenname #hr_pg";
  @FindBy(css = givenNameTextBox)
  private WebElement givennameTextbox;

  private final String lastNameTextBox = ".app-search #main .row-fluid .span12 .search-form-control #record-search #person .exact-field .surname #hr_ps";
  @FindBy(css = lastNameTextBox)
  private WebElement surnameTextbox;

//  private final String searchButton = ".btn.btn-water.searchForm";
  private final String searchButton = ".app-home #main .banner-nav .banner-nav-container .search a";
  @FindBy(css = searchButton)
  private WebElement searchbutton;

  private final String searchHistoricalRecordsButton = ".app-home #main .banner .caroufredsel_wrapper .caroufredsel .search .banner-text a";

  private final String doSearchButton = ".app-search #main .row-fluid .span12 .search-form-control #record-search .form-actions .btn";

  private WebDriver driver;

  public FamilySearch(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /*
   * Getter methods
   */
  public WebElement getGivennameTextbox() {
    return givennameTextbox;
  }

  public WebElement getSearchButton() {
    return searchbutton;
  }

  public WebElement getSurnameTextbox() {
    return surnameTextbox;
  }

  /*
   * Helper methods
   */
  public SearchResults searchByGivenAndSurname(String givenname, String surname) {
    givennameTextbox.clear();
    givennameTextbox.sendKeys(givenname);
    surnameTextbox.clear();
    surnameTextbox.sendKeys(surname);
    searchbutton.click();
    return new SearchResults(getDriver());
  }

  public WebElement waitForSearchButton() {
    return waitForElementPresent(searchButton);
  }

  public void setGivenName(String givenName) {
    setInputValue(givenNameTextBox, givenName);
  }

  public void setLastName(String lastName) {
    setInputValue(lastNameTextBox, lastName);
  }

  public void clickSearchButton() {
    clickAndWaitForElementPresent(searchButton, searchHistoricalRecordsButton);
  }

  public void clickContinue() {
    click(".fancybox-opened .fancybox-outer .fancybox-inner #engage-modal .more-link a");
  }

  public void clickSearchHistoricalRecordsButton() {
    clickAndWaitForElementPresent(searchHistoricalRecordsButton, givenNameTextBox);
  }

  public SearchResults clickDoSearchButton() {
    clickAndWaitForElementPresent(doSearchButton, SearchResults.searchResultsContainer);
    return PageFactory.initElements(getDriver(), SearchResults.class);
  }
}
