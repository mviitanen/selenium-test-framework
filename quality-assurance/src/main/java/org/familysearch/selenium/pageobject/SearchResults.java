package org.familysearch.selenium.pageobject;

import org.familysearch.selenium.helper.ApplicationHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResults extends ApplicationHelper {

    /*
     * WebElements
     */
  public static final String searchResultsContainer = ".results";
  @FindBy(css= searchResultsContainer)
  private WebElement searchresultsContainer;

  private WebDriver driver;

  public SearchResults(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /*
   * Getter methods
   */
  public WebElement searchResultsContainer() {
      return searchresultsContainer;
  }

  public IndividualRecord clickResultLink(String fullName) {
    WebElement linkText = driver.findElement(By.linkText(fullName));
    String href = getHREFattribute(linkText);
    String locator = searchResultsContainer + " tbody" + " .result-name .person-name[href=\"" + href + "\"]";
    click(locator);
    waitForElementPresent(IndividualRecord.individualRecordContainer);
    return PageFactory.initElements(getDriver(), IndividualRecord.class);
  }


}
