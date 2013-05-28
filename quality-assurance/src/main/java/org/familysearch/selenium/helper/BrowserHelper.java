package org.familysearch.selenium.helper;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * BrowserHelper
 * A service for getting a BrowserSession.
 *
 * @author PabstEC
 * @author MamanakisDM
 */
public class BrowserHelper extends ApplicationHelper{

  /**
   * Variables, Statics, Commons, etc
   * etc
   *
   */
  protected static final Logger LOG = Logger.getLogger(BrowserHelper.class);
  public static final String BROWSER_HELPER = "browserSession";
  private final String browser;
  private final String seleniumServerUrl;
  private final boolean runLocally;
  private final WebDriver driver;
  private final ApplicationHelper applicationHelper;
  private final String testNodeIP;

  /**
   * getInstance
   * Override = Get the BrowserHelper for the given ITestContext or creates one if not present.
   *
   * @param testContext The ITestContext which will store our reference to the WebDriver instance.
   * @param browser The browser to test with.
   * @param seleniumServerUrl The path to the remote selenium server if we are running our tests remotely.
   * @param runLocally A flag indicating if we should run locally or remotely.
   * @return browserHelper (BrowserHelper)
   */
  public static BrowserHelper getInstance(ITestContext testContext, String browser, String seleniumServerUrl, boolean runLocally) {
    BrowserHelper browserHelper = findInstance(testContext);
    if (browserHelper == null) {
      browserHelper = new BrowserHelper(browser, seleniumServerUrl, runLocally);
      testContext.setAttribute(BROWSER_HELPER, browserHelper);
    }
    return browserHelper;
  }

  /**
   * findInstance
   * Override = Get the BrowserHelper instance for the given ITestContext or returns null.
   *
   * @param testContext
   * @return browserHelper (BrowserHelper)
   */
  public static BrowserHelper findInstance(ITestContext testContext) {
    return (BrowserHelper) testContext.getAttribute(BROWSER_HELPER);
  }

  /**
   * BrowserHelper
   * Override Constructor = sets up the BrowserHelper
   *
   * @param browser
   * @param seleniumServerUrl
   * @param runLocally
   */
  private BrowserHelper(String browser, String seleniumServerUrl, boolean runLocally) {
    super(null);
    this.browser = browser;
    this.seleniumServerUrl = seleniumServerUrl;
    this.runLocally = runLocally;
    this.driver = getDriver();
    this.testNodeIP = getTestNodeIP();
    this.applicationHelper = new ApplicationHelper(driver, this);
  }

  /**
   * BrowserHelper
   * Override Constructor = sets up the BrowserHelper
   *
   * @param driver
   * @param browser
   * @param seleniumServerUrl
   * @param testNodeIP
   * @param runLocally
   */
  private BrowserHelper(WebDriver driver, String browser, String seleniumServerUrl, String testNodeIP, boolean runLocally) {
    super(null, null);
    this.browser = browser;
    this.seleniumServerUrl = seleniumServerUrl;
    this.runLocally = runLocally;
    this.driver = driver;
    this.applicationHelper = new ApplicationHelper(driver, this);
    this.testNodeIP = testNodeIP;
  }

  /**
   * createBrowserSession
   * Creates the actual Browser Session.
   *
   * @return browserSession (BrowserSession)
   */
  public BrowserHelper createBrowserSession() {
    BrowserHelper browserSession;
    DriverHelper driverHelper = new DriverHelper();
    WebDriver driver;
    String testNodeIP;
    try {
      driver = driverHelper.createWebDriver(runLocally, browser, seleniumServerUrl);
      testNodeIP = getTestNodeIP(runLocally, seleniumServerUrl, driver);
    } catch (MalformedURLException e) {
      throw new IllegalStateException(e);
    }
    browserSession = new BrowserHelper(driver, browser, seleniumServerUrl, testNodeIP, runLocally);
    Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
    return browserSession;
  }

  /**
   * quit
   * This stops the WebDriver, shuts down the session.
   *
   */
  public void quit() {
    if (driver != null) {
      // Quit the selenium session
      try {
        driver.quit();
      } catch (RuntimeException e) {
        LOG.info("Ignoring error trying to quit the WebDriver: " + e);
      }
    }
  }

  /**
   * getTestNodeIP
   * Getter for the Test Node IP
   *
   * @return testNodeIP (String)
   */
  public String getTestNodeIP() {
    return testNodeIP;
  }

  /**
   * getDriver
   * Getter for the Driver
   *
   * @return driver (WebDriver)
   */
  public WebDriver getDriver() {
    return driver;
  }

  /**
   * getTestNodeIP
   * Getter/creator for the Test Node IP
   *
   * @param runLocally
   * @param seleniumServerUrl
   * @param driver
   * @return testNodeIp (String)
   * @throws MalformedURLException
   */
  public String getTestNodeIP(boolean runLocally, String seleniumServerUrl, WebDriver driver) throws MalformedURLException {
    String testNodeIp;
    if (runLocally || "${selenium.server}".equals(seleniumServerUrl)) {
      testNodeIp = "localhost";
    } else if (seleniumServerUrl != null && seleniumServerUrl.contains("saucelabs")) {
      testNodeIp = "Sauce Labs";
    } else {
      URL hubUrl = new URL(seleniumServerUrl);
      String hostIp = hubUrl.getHost();
      int hubPort = hubUrl.getPort();

      String[] testNodeIP = getTestNodeIP(hostIp, hubPort, ((RemoteWebDriver) driver).getSessionId());

      testNodeIp = testNodeIP[0];
    }
    return testNodeIp;
  }

  /**
   * getTestNodeIP
   * Getter/Creator for the Test Node IP
   *
   * @param hostName
   * @param port
   * @param session
   * @return hostAndPort (String)
   */
  public String[] getTestNodeIP(String hostName, int port, SessionId session) {

    String[] hostAndPort = new String[2];
    String errorMsg = "Failed to acquire remote webdriver node and port info.Root cause: ";

    try {
      HttpHost host = new HttpHost(hostName, port);
      DefaultHttpClient client = new DefaultHttpClient();
      URL sessionURL = new URL("http://" + hostName + ":" + port + "/grid/api/testsession?session=" + session);
      BasicHttpEntityEnclosingRequest r = new BasicHttpEntityEnclosingRequest("POST", sessionURL.toExternalForm());
      HttpResponse response = client.execute(host, r);
      JSONObject object = extractObject(response);
      URL myURL = new URL(object.getString("proxyId"));

      if ((myURL.getHost() != null) && (myURL.getPort() != -1)) {
        hostAndPort[0] = myURL.getHost();
        hostAndPort[1] = Integer.toString(myURL.getPort());
      }

    } catch (Exception e) {
      throw new RuntimeException(errorMsg, e);
    }
    return hostAndPort;
  }

  /**
   * extractObject
   * Extracts one object into another to easy access.
   *
   * @param resp
   * @return objToReturn (JSONObject)
   * @throws IOException
   * @throws JSONException
   */
  private static JSONObject extractObject(HttpResponse resp) throws IOException, JSONException {
    BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
    StringBuffer s = new StringBuffer();
    String line;
    while ((line = rd.readLine()) != null) {
      s.append(line);
    }
    rd.close();
    JSONObject objToReturn = new JSONObject(s.toString());
    return objToReturn;
  }

  /**
   * maximizeWindow
   * Used to Maximize the Browser Windows to prevent issues with the driver not finding elements outside of the viewable space
   *
   * @param driver the WebDriver
   */
  private void maximizeWindow(WebDriver driver) {
//    executeJavascript("window.moveTo(0,0); window.resizeTo(screen.width,screen.height);");
    Set<String> handles = driver.getWindowHandles();
    String script = "if (window.screen){var win = window.open(window.location); win.moveTo(0,0);win.resizeTo(window.screen.availWidth,window.screen.availHeight);};";
    ApplicationHelper.executeJavascript(script, driver);
    Set<String> newHandles = driver.getWindowHandles();
    newHandles.removeAll(handles);
    driver.switchTo().window(newHandles.iterator().next());
  }
}
