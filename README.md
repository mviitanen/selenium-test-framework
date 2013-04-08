selenium-test-framework-template
==========================================

A sample Maven project that, when downloaded and built will give the Test Team a functional, basic, WebDriver test framework. It will run Selenium using TestNG.

.
├── quality-assurance
│   ├── pom.xml
│   ├── quality-assurance.iml
│   ├── src
│   │   ├── main
│   │   │   └── java
│   │   │       └── org
│   │   │           └── familysearch
│   │   │               └── selenium
│   │   │                   ├── helper
│   │   │                   │   └── ApplicationHelper.java
│   │   │                   └── pageobject
│   │   │                       ├── FamilySearch.java
│   │   │                       ├── IndividualRecord.java
│   │   │                       └── SearchResults.java
│   │   └── test
│   │       ├── java
│   │       │   └── org
│   │       │       └── familysearch
│   │       │           └── selenium
│   │       │               └── test
│   │       │                   └── ExampleTest.java
│   │       └── resources
│   │           └── TestRunner.xml
│   └── target
│       ├── classes
│       │   └── org
│       │       └── familysearch
│       │           └── selenium
│       │               ├── helper
│       │               │   └── ApplicationHelper.class
│       │               └── pageobject
│       │                   ├── FamilySearch.class
│       │                   ├── IndividualRecord.class
│       │                   └── SearchResults.class
│       ├── generated-sources
│       │   └── annotations
│       ├── generated-test-sources
│       │   └── test-annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       ├── maven-status
│       │   └── maven-compiler-plugin
│       │       ├── compile
│       │       │   └── default-compile
│       │       │       └── createdFiles.lst
│       │       └── testCompile
│       │           └── default-testCompile
│       │               └── createdFiles.lst
│       ├── quality-assurance-1.0-SNAPSHOT.jar
│       └── test-classes
│           ├── org
│           │   └── familysearch
│           │       └── selenium
│           │           └── test
│           │               └── ExampleTest.class
│           └── TestRunner.xml
└── README.md

