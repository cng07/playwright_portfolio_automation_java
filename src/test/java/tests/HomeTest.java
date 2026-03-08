package tests;

import framework.BaseUiTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class HomeTest extends BaseUiTest {
  @Test
  void verifyHomePage() {
    HomePage homePage = new HomePage(page);

    homePage.goToHomePage();
    homePage.verifyAccessibilityElements();
    homePage.verifyNavigationBarSection();
    homePage.verifyHeroSection();
    homePage.verifySocialMediaSection();
    homePage.verifyFeaturedProjectsSection();
    homePage.verifySkillsSection();
    homePage.verifyCertificationsSection();
    homePage.verifyPublicationSection();
    homePage.verifyExperienceSection();
    homePage.verifyFooterSection();
  }
}

