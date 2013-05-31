package org.familysearch.selenium.helper;

/**
 *
 */
public enum TestingPlatform {

  /**
   *
   */
  REMOTE_GRID("REMOTE"),
  LOCAL_HOST("LOCAL"),
  SAUCE_LABS("SAUCE");

  /**
   *
   */
  private final String value;

  /**
   *
   * @param value
   */
  private TestingPlatform(String value) {
    this.value = value;
  }

  /**
   *
   * @return
   */
  @Override
  public String toString() {
    return value;
  }

}
