package tests;

import framework.BaseUiTest;
import org.junit.jupiter.api.Test;
import pages.ExperiencePage;
import pages.HomePage;

public class ExperienceTest extends BaseUiTest {
  @Test
  void verifyExperiencePageUiViaNavigation() {
    HomePage homePage = new HomePage(page);
    ExperiencePage experiencePage = new ExperiencePage(page);

    homePage.goToHomePage();
    experiencePage.goToExperiencePage();
    experiencePage.verifyAllExperiencePageElements();
  }

  @Test
  void verifyExperiencePageUiViaDirectUrl() {
    ExperiencePage experiencePage = new ExperiencePage(page);

    experiencePage.goToExperiencePageDirect();
    experiencePage.verifyAllExperiencePageElements();
  }

  @Test
  void verifyExperiencePageApiLinks() {
    ExperiencePage experiencePage = new ExperiencePage(page);

    experiencePage.goToExperiencePageDirect();
    experiencePage.verifyAllExperienceApiChecks();
  }
}

