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
  private final String givenNameTextBox = "#hr_pg";
  @FindBy(css = givenNameTextBox)
  private WebElement givennameTextbox;

  private final String lastNameTextBox = "#hr_ps";
  @FindBy(css = lastNameTextBox)
  private WebElement surnameTextbox;

  private final String searchButton = ".btn.btn-water.searchForm";
  @FindBy(css = searchButton)
  private WebElement searchbutton;

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

  public SearchResults clickSearchButton() {
    clickAndWaitForElementPresent(searchButton, SearchResults.searchResultsContainer);
    return PageFactory.initElements(getDriver(),  SearchResults.class);
  }
}
