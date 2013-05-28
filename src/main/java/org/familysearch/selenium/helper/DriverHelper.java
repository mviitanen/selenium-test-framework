package org.familysearch.selenium.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * DriverHelper
 * Used to create the Selenium WebDriver/RemoteWebDriver and obtain properties
 *
 * @author PabstEC
 * @author MamanakisDM
 */
public class DriverHelper extends ApplicationHelper{

  /**
   * Variables, Statics, Commons, etc
   * etc
   *
   */
  private BrowserHelper browserHelper;
  private static String workingUrl;

  /**
   *  DriverHelper
   *  Constructor for Driver Helper
   *
   */
  public DriverHelper() {
    super(null, null);
  }

  /**
   * getBrowserSession
   * Getter for the Browser Session
   *
   * @return  BrowserHelper
   */
  @Override
  protected BrowserHelper getBrowserSession() {
    if (super.getBrowserSession() == null) {
      setBrowserSession(browserHelper.createBrowserSession());
    }
    return super.getBrowserSession();
  }

  /**
   * getDriver
   * Getter for the Driver
   *
   * @return  WebDriver
   */
  @Override
  protected WebDriver getDriver() {
    return getBrowserSession().getDriver();
  }

  /**
   * setupClass
   * Method to start-up the WebDriver - calls beforeClass
   *
   * @param context
   * @param browser What browser the test is using. Pulled from the TestRunner.xml
   * @param seleniumServerUrl The URL of the Selenium Server. Pulled from the TestRunner.xml
   * @param localRun If the test is to be run locally, or on the grid, or on SauceLabs.
   * @param workingUrl This is the environment the tests will run against.
   * @param apiHost This is the public API host for the environment found in WorkingUrl.
   * @param ctHost This is the CT Public API host for the environment found in WorkingUrl.
   * @param linksHost This is the Links API host for the environment found in WorkingUrl.(if you don't have one, this won't make any difference, ignore it or remove it)
   * @throws Exception
   */
  @BeforeClass(alwaysRun = true)
  @Parameters({"selenium.browser", "selenium.server", "run.local", "working.url", "selenium.api.host", "selenium.ct.host",
      "selenium.links.host"})
  public final void setupClass(ITestContext context, @Optional String browser, @Optional String seleniumServerUrl,
                               @Optional String localRun, @Optional String workingUrl, @Optional String apiHost, @Optional String ctHost,
                               @Optional String linksHost) throws Exception {
    this.browserHelper = BrowserHelper.getInstance(context, browser, seleniumServerUrl, Boolean.parseBoolean(localRun));
    this.workingUrl = workingUrl;
    beforeClass();
    // Make sure one is allocated for this test class.
    BrowserHelper browserSession = getBrowserSession();
    LOG.info("Using Browser on host= " + browserSession.getTestNodeIP());
  }

  /**
   * getWorkingUrl
   * Returns the Location that we want to run the tests against. This is imported above in setupClass.
   *
   * @return String (workingUrl)
   */
  public String getWorkingUrl() {
    return this.workingUrl;
  }

  /**
   * tearDownClass
   * The method to tear-down the WebDriver and clean up after the test run - calls afterClass
   *
   * @throws Exception
   */
  @AfterClass(alwaysRun = true)
  public final void tearDownClass() throws Exception {
    afterClass();
    LOG.info("Done with testClass=" + getClass().getSimpleName());
  }

  /**
   * beforeClass
   * The method override that can be used to run special preps BEFORE a test or set of tests are run
   *
   */
  protected void beforeClass() throws Exception {
    // Override this method in the test class if you need to do work before the class executes
  }

  /**
   * beforeMethod
   * Runs before each test method. Will reload the page to make sure each test method starts fresh. It prevents the browser from getting into a bad state.
   *
   */
  @BeforeMethod(alwaysRun = true)
  protected void beforeMethod() throws Exception {
    refresh();
  }

  /**
   * afterClass
   * The method override that can be used to run special clean-ups in this space AFTER a test/set of tests are run
   *
   */
  protected void afterClass() throws Exception {
    if (isBaseInitialized()) {
      getBrowserSession().quit();
    }
  }

  /**
   * isBaseInitialized
   * This method reports if Browser Session and Driver are both initialized (not null).
   *
   * @return boolean
   */
  protected boolean isBaseInitialized() {
    return browserHelper != null && super.getBrowserSession() != null && getDriver() != null;
  }

  /**
   * createWebDriver
   * Creator of the Web Driver  (Local, Remote, Sauce)
   *
   * @param runLocal The environment that we want the Driver to be initialized for.
   * @param browser The type of browser to be used in the tests.
   * @param serverUrl The location of the environment for the tests.
   * @return webDriver (WebDriver or RemoteWebDriver)
   * @throws MalformedURLException
   */
  protected WebDriver createWebDriver(boolean runLocal, String browser, String serverUrl) throws MalformedURLException {
    WebDriver webDriver;

    if (!runLocal) {
      if ("firefox".equalsIgnoreCase(browser)) {
        webDriver = new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.firefox());
      }
      else if ("ie".equalsIgnoreCase(browser)) {
        webDriver = new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.internetExplorer());
      }
      else if ("chrome".equalsIgnoreCase(browser)) {
        webDriver = new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.chrome());
      }
      else if (browser == null || browser.trim().length() == 0) {
        webDriver = new RemoteWebDriver(new URL(serverUrl), DesiredCapabilities.firefox());
      } else {
        throw new IllegalArgumentException("Unknown Browser: " + browser);
      }
    } else {
      if ("firefox".equalsIgnoreCase(browser)) {

        //The following code will need some modification, but it could/should provide a way to run FIREBUG in our TEST
        //Browsers. Currently, it isn't enabling FireBug.
//        String path = System.getProperty("user.home") + File.separator + "firebug-1.8.4.xpi";
//        File file = new File(path);
//        FirefoxProfile firefoxProfile = new FirefoxProfile();
//
//        try {
//          firefoxProfile.addExtension(file);
//        }
//        catch (IOException e) {
//          e.printStackTrace();
//        }
//
//        firefoxProfile.setPreference("extensions.firebug.currentVersion", "1.8.1"); // Avoid startup screen
//        webDriver = new FirefoxDriver(firefoxProfile);

        webDriver = new FirefoxDriver();
      } else if ("ie".equalsIgnoreCase(browser)) {
        webDriver = new InternetExplorerDriver();
      } else if ("chrome".equalsIgnoreCase(browser)) {
        String executableName;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
          executableName = "chromedriver.exe";
        } else {
          executableName = "chromedriver";
        }
        System.setProperty("webdriver.chrome.driver", new File(System.getProperty("user.home") + File.separator
            + executableName).getPath());
        webDriver = new ChromeDriver();
      } else if (browser == null || browser.trim().length() == 0) {
        webDriver = new FirefoxDriver();
      } else {
        throw new IllegalArgumentException("Unknown Browser: " + browser);
      }
    }
    return webDriver;
  }
}