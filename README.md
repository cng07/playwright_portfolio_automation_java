# playwright_portfolio_automation_java

Automated UI testing framework for a portfolio website using Playwright, Java, Maven, and JUnit 5, built with the Page Object Model (POM) pattern.

This is the Java implementation of the TypeScript project: [playwright_portfolio_automation_typescript](https://github.com/cng07/playwright_portfolio_automation_typescript)

## Overview

This project automates testing of a portfolio website with comprehensive coverage across multiple pages including home, about, contact, education, experience, projects, resume, and certifications sections. Tests validate page navigation, content rendering, and user interactions.

## Prerequisites
- Java 21+ (`java -version`)
- Maven 3.6+ (`mvn -v`)

## Setup

### 1. Install Playwright browsers
```powershell
mvn exec:java "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=install"
```

### 2. Run tests
```powershell
mvn test
```

### 3. View test results
Test reports are generated in: `target/surefire-reports/`

## IDE Setup
- **IntelliJ IDEA**: Open the project folder and Maven will auto-configure
- **VS Code**: Install "Extension Pack for Java" extension
- **Eclipse**: Install "Maven Integration for Eclipse"

## Project Structure
```
src/test/java/
├── framework/
│   ├── BaseUiTest.java       (Abstract base with Playwright setup/teardown)
│   ├── SiteConfig.java        (Configuration and constants)
│   └── TestHelper.java        (Utility methods)
├── pages/                      (Page Object Model classes)
│   ├── HomePage.java
│   ├── AboutPage.java
│   ├── ContactPage.java
│   ├── EducationPage.java
│   ├── ExperiencePage.java
│   ├── ProjectsPage.java
│   ├── ResumePage.java
│   └── CertificationsPage.java
└── tests/                      (JUnit test suites)
    ├── HomeTest.java
    ├── AboutTest.java
    ├── ContactTest.java
    ├── EducationTest.java
    ├── ExperienceTest.java
    ├── ProjectsTest.java
    ├── ResumeTest.java
    └── CertificationsTest.java
```

## Framework Components

- **BaseUiTest**: Abstract base class that handles Playwright browser initialization, context creation, and cleanup. Detects CI environment for headless mode.
- **SiteConfig**: Centralized configuration for URLs, timeouts, and other constants.
- **TestHelper**: Common utility methods for test interactions.
- **Page Objects**: Each page class encapsulates selectors and methods for interacting with specific pages.

## CI/CD Environment

To run tests in CI/CD pipelines (GitHub Actions, Jenkins, etc.), set the environment variable:
```powershell
$env:CI = "true"
```

The framework will automatically run in headless mode.

## Troubleshooting

- **Playwright browsers not found**: Run `mvn exec:java "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=install"`
- **Test timeouts**: Increase timeout values in `SiteConfig.java`
- **Port conflicts**: Ensure port 3000 (or configured port) is available
- **Clear browser cache**: Delete `~/.cache/ms-playwright/` or `%APPDATA%\Local\ms-playwright` (Windows)
