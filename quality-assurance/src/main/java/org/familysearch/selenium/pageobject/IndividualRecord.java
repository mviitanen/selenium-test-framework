package org.familysearch.selenium.pageobject;

import org.familysearch.selenium.helper.ApplicationHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndividualRecord extends ApplicationHelper {

  /*
  * WebElements
  */
  public static final String individualRecordContainer = "#pagekey__search__hr_details";
  @FindBy(css= individualRecordContainer)
  private WebElement individualrecordContainer;

  private WebDriver driver;

  public IndividualRecord(WebDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /*
   * Getter methods
   */
  public WebElement individualRecordContainer() {
    return individualrecordContainer;
  }

  public String getTitleText() {
    return getText(individualRecordContainer + " #main .intro");
  }
}
