selenium-saucelabs-test-framework-template
==========================================

A sample Maven project that demonstrates how to integrate Sauce Labs with WebDriver tests that run using TestNG

.
├── pom.xml
└── src
    ├── main
    │   └── resources
    └── test
        └── java
            └── org
                └── familysearch
                    └── test
                        ├── ExampleMultipleBrowserTest.java
                        ├── helper
                        │   ├── SauceBrowserDataProvider.java
                        │   ├── SauceOnDemandAuthenticationProvider.java
                        │   ├── SauceOnDemandTestListener.java
                        │   └── SeleniumHelper.java
                        └── pageobject
                            ├── FamilySearch.java
                            └── SearchResults.java
