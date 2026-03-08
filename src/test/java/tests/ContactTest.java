package tests;

import framework.BaseUiTest;
import org.junit.jupiter.api.Test;
import pages.ContactPage;
import pages.HomePage;

public class ContactTest extends BaseUiTest {
  @Test
  void verifyContactPageUiViaNavigation() {
    HomePage homePage = new HomePage(page);
    ContactPage contactPage = new ContactPage(page);

    homePage.goToHomePage();
    contactPage.goToContactPage();
    contactPage.verifyAllContactPageElements();
  }

  @Test
  void verifyContactPageUiViaDirectUrl() {
    ContactPage contactPage = new ContactPage(page);

    contactPage.goToContactPageDirect();
    contactPage.verifyAllContactPageElements();
  }

  @Test
  void verifyContactPageApiLinks() {
    ContactPage contactPage = new ContactPage(page);

    contactPage.goToContactPageDirect();
    contactPage.verifyAllContactApiChecks();
  }
}

