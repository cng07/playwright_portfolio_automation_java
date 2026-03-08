package tests;

import framework.BaseUiTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ResumePage;

public class ResumeTest extends BaseUiTest {
  @Test
  void verifyResumePageUiViaNavigation() {
    HomePage homePage = new HomePage(page);
    ResumePage resumePage = new ResumePage(page);

    homePage.goToHomePage();
    resumePage.goToResumePage();
    resumePage.verifyAllResumePageElements();
  }

  @Test
  void verifyResumePdfDownload() {
    ResumePage resumePage = new ResumePage(page);

    resumePage.goToResumePageDirect();
    resumePage.verifyDownloadPdfButton();
    resumePage.downloadPdfAndVerify();
  }

  @Test
  void verifyResumePageApiLinks() {
    ResumePage resumePage = new ResumePage(page);

    resumePage.goToResumePageDirect();
    resumePage.verifyAllResumeApiChecks();
  }
}

