package org.familysearch.selenium.suite;

import org.familysearch.selenium.helper.DriverHelper;
import org.familysearch.selenium.test.ExampleTest;
import org.testng.annotations.Test;

/**
 * ExampleSuite
 * This is an example Test Suite. This would normally have a variety of tests in it that all fit into a specific group or category.
 * groups can be used to signify what group or category (groups or categories) that this suite of tests belongs to.
 * (groups = "") or (groups = {"", ""})
 * The environment setup happens in the beforeClass() method. Creating Data, Logging In, Navigating to special URLs or other prep goes here.
 * groups can also be added to each test. If the tests are run in more than one group or category, simply add those groups to each test.
 * You can also add afterClass() to do any clean-up or other special tasks AFTER all the tests have run and the test suite comes to an end.
 *
 * @author mamanakisdm
 */
@Test(groups = {"exampleTest"})
public class ExampleSuite extends DriverHelper {
  private ExampleTest exampleTest;

  /**
   * beforeClass
   * Method Override to set up any necessary bits for the test suite/test cases
   * Creating data, creating page objects, etc.
   *
   * @throws Exception
   */
  @Override
  protected void beforeClass() throws Exception {
    exampleTest = new ExampleTest(getDriver(), getBrowserSession());
  }

  /**
   * This is the TEST.
   * The Test Suite calls a series of things called @Test
   * Each one will use one or more items from the "Test" class, in this case, "ExampleTest".
   *
   * @throws Exception
   */
  @Test//(groups = {""})
  public void runningAnExampleTestFromAnExampleTestSuite() throws Exception {
    exampleTest.searchUsingCommonLib();
  }
}
