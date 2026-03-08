package tests;

import framework.BaseUiTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProjectsPage;

public class ProjectsTest extends BaseUiTest {
  @Test
  void verifyProjectsPageViaNavigation() {
    HomePage homePage = new HomePage(page);
    ProjectsPage projectsPage = new ProjectsPage(page);

    homePage.goToHomePage();
    projectsPage.goToProjectsPage();
    projectsPage.verifyAccessibilityElements();
    projectsPage.verifyProjectsPageHeader();
    projectsPage.verifyProject1();
    projectsPage.verifyProject2();
    projectsPage.verifyProject3();
    projectsPage.verifyProject4();
    projectsPage.verifyRepositoryLinks();
    projectsPage.verifyMoreProjectsComingSection();
    projectsPage.verifyFooterSection();
  }

  @Test
  void verifyProjectsPageUiViaDirectUrl() {
    ProjectsPage projectsPage = new ProjectsPage(page);

    projectsPage.goToProjectsPageDirect();
    projectsPage.verifyAllProjectsPageElements();
  }

  @Test
  void verifyProjectsPageApiLinks() {
    ProjectsPage projectsPage = new ProjectsPage(page);

    projectsPage.goToProjectsPageDirect();
    projectsPage.verifyRepositoryLinks();
    projectsPage.verifyAllProjectsApiChecks();
  }
}

