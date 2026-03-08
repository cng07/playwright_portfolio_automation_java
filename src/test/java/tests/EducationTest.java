package tests;

import framework.BaseUiTest;
import org.junit.jupiter.api.Test;
import pages.EducationPage;
import pages.HomePage;

public class EducationTest extends BaseUiTest {
  @Test
  void verifyEducationPageUiViaNavigation() {
    HomePage homePage = new HomePage(page);
    EducationPage educationPage = new EducationPage(page);

    homePage.goToHomePage();
    educationPage.goToEducationPage();
    educationPage.verifyAllEducationPageElements();
  }

  @Test
  void verifyEducationPageUiViaDirectUrl() {
    EducationPage educationPage = new EducationPage(page);

    educationPage.goToEducationPageDirect();
    educationPage.verifyAllEducationPageElements();
  }

  @Test
  void verifyEducationPageApiLinks() {
    EducationPage educationPage = new EducationPage(page);

    educationPage.goToEducationPageDirect();
    educationPage.verifyAllEducationApiChecks();
  }
}

