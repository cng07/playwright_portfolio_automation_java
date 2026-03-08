package tests;

import framework.BaseUiTest;
import org.junit.jupiter.api.Test;
import pages.AboutPage;
import pages.HomePage;

public class AboutTest extends BaseUiTest {
  @Test
  void verifyAboutPageUiViaNavigation() {
    HomePage homePage = new HomePage(page);
    AboutPage aboutPage = new AboutPage(page);

    homePage.goToHomePage();
    aboutPage.goToAboutPage();
    aboutPage.verifyAllAboutPageElements();
  }

  @Test
  void verifyAboutPageUiViaDirectUrl() {
    AboutPage aboutPage = new AboutPage(page);

    aboutPage.goToAboutPageDirect();
    aboutPage.verifyAllAboutPageElements();
  }

  @Test
  void verifyAboutPageApiLinks() {
    AboutPage aboutPage = new AboutPage(page);

    aboutPage.goToAboutPageDirect();
    aboutPage.verifyInternalLinksApiResponses();
  }
}

