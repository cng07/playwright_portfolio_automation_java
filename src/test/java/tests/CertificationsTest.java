package tests;

import framework.BaseUiTest;
import org.junit.jupiter.api.Test;
import pages.CertificationsPage;
import pages.HomePage;

public class CertificationsTest extends BaseUiTest {
  @Test
  void verifyCertificationsPageViaNavigationMenu() {
    HomePage homePage = new HomePage(page);
    CertificationsPage certificationsPage = new CertificationsPage(page);

    homePage.goToHomePage();
    certificationsPage.goToCertificationsPageFromHomeNavigation();
    certificationsPage.verifyCertificationsPageHeader();
    certificationsPage.verifyCTFLCertification();
    certificationsPage.verifyDevOpsCertification();
    certificationsPage.verifyAccentureAgileCertification();
    certificationsPage.verifyAutomationAnywhereCertification();
    certificationsPage.verifyCertificationLinks();
  }

  @Test
  void verifyCertificationsPageViaDirectUrl() {
    CertificationsPage certificationsPage = new CertificationsPage(page);

    certificationsPage.goToCertificationsPage();
    certificationsPage.verifyCertificationsPageHeader();
    certificationsPage.verifyCTFLCertification();
    certificationsPage.verifyDevOpsCertification();
    certificationsPage.verifyAccentureAgileCertification();
    certificationsPage.verifyAutomationAnywhereCertification();
    certificationsPage.verifyCertificationLinks();
  }
}

