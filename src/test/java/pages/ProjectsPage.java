package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import framework.SiteConfig;
import framework.TestHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;

public class ProjectsPage {
  private final Page page;
  private final TestHelper h;

  private final Locator skipToContentLink;
  private final Locator mainContent;
  private final Locator navProjectsLink;
  private final Locator projectsHeading;
  private final Locator textHighlights;
  private final Locator textTechnologies;
  private final Locator projectTitles;
  private final Locator projectStatusActive;
  private final Locator repositoryLinks;

  private final Locator projectJavaScriptTitle;
  private final Locator projectJavaScriptCard;
  private final Locator projectTypeScriptTitle;
  private final Locator projectTypeScriptCard;
  private final Locator projectPythonTitle;
  private final Locator projectPythonCard;
  private final Locator projectQaPracticeTitle;
  private final Locator projectQaPracticeCard;

  private final Locator moreProjectsComingHeading;
  private final Locator moreProjectsComingText;

  private final Locator privacyPolicyLink;
  private final Locator termsAndConditionsLink;

  public ProjectsPage(Page page) {
    this.page = page;
    this.h = new TestHelper(page);

    skipToContentLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Skip to content"));
    mainContent = page.locator("#main-content");
    navProjectsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Projects").setExact(true));

    projectsHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Projects").setLevel(1));
    textHighlights = page.getByText("Highlights");
    textTechnologies = page.getByText("Technologies");
    projectTitles = page.locator("h3");
    projectStatusActive = page.getByText("Active", new Page.GetByTextOptions().setExact(true));
    repositoryLinks = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Repository"));

    projectJavaScriptTitle = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Portfolio Website Automation (JavaScript)").setLevel(3)
    );
    projectJavaScriptCard = page.locator("div.glass").filter(new Locator.FilterOptions().setHas(projectJavaScriptTitle));
    projectTypeScriptTitle = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Portfolio Website Automation (TypeScript)").setLevel(3)
    );
    projectTypeScriptCard = page.locator("div.glass").filter(new Locator.FilterOptions().setHas(projectTypeScriptTitle));
    projectPythonTitle = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Portfolio Website Automation (Python)").setLevel(3)
    );
    projectPythonCard = page.locator("div.glass").filter(new Locator.FilterOptions().setHas(projectPythonTitle));
    projectQaPracticeTitle = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("QA Practice Framework").setLevel(3)
    );
    projectQaPracticeCard = page.locator("div.glass").filter(new Locator.FilterOptions().setHas(projectQaPracticeTitle));

    moreProjectsComingHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("More Projects Coming").setLevel(2)
    );
    moreProjectsComingText = page.getByText("Check back soon for updates!", new Page.GetByTextOptions().setExact(true));

    privacyPolicyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Privacy Policy"));
    termsAndConditionsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Terms & Conditions"));
  }

  public void goToProjectsPage() {
    navProjectsLink.click();
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/projects$"));
    assertThat(page).hasTitle("Carlos Ng | Projects");
  }

  public void goToProjectsPageDirect() {
    page.navigate(SiteConfig.BASE_URL + "/projects");
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/projects$"));
    assertThat(page).hasTitle("Carlos Ng | Projects");
  }

  public void verifyAccessibilityElements() {
    assertThat(skipToContentLink).hasAttribute("href", "#main-content");
    assertThat(mainContent).isVisible();
  }

  public void verifyProjectsPageHeader() {
    assertThat(projectsHeading).isVisible();
    Assertions.assertEquals(4, projectTitles.count());
    Assertions.assertEquals(4, projectStatusActive.count());
    Assertions.assertEquals(4, textHighlights.count());
    Assertions.assertEquals(4, textTechnologies.count());
  }

  public void verifyProject1() {
    assertThat(projectJavaScriptTitle).isVisible();
    assertThat(projectJavaScriptCard.getByText("Comprehensive Playwright automation test suite for this portfolio website.")).isVisible();
    assertThat(textHighlights.nth(0)).isVisible();
    assertThat(projectJavaScriptCard.getByText("Cross-browser testing (Chrome, Firefox, Safari, Edge)")).isVisible();
    assertThat(projectJavaScriptCard.getByText("Link integrity checks")).isVisible();
    assertThat(projectJavaScriptCard.getByText("CI/CD integration with GitHub Actions")).isVisible();
    assertThat(textTechnologies.nth(0)).isVisible();
    assertThat(projectJavaScriptCard.getByText("JavaScript", new Locator.GetByTextOptions().setExact(true))).isVisible();
    assertThat(projectJavaScriptCard.getByText("GitHub Actions", new Locator.GetByTextOptions().setExact(true))).isVisible();
  }

  public void verifyProject2() {
    assertThat(projectTypeScriptTitle).isVisible();
    assertThat(projectTypeScriptCard.getByText("Advanced Playwright automation framework using TypeScript with containerization and CI/CD support.")).isVisible();
    assertThat(textHighlights.nth(1)).isVisible();
    assertThat(projectTypeScriptCard.getByText("Strongly typed test architecture with TypeScript")).isVisible();
    assertThat(projectTypeScriptCard.getByText("Page Object Model (POM) implementation")).isVisible();
    assertThat(projectTypeScriptCard.getByText("Docker containerization for consistent test environments")).isVisible();
    assertThat(projectTypeScriptCard.getByText("Multi-platform CI/CD support (Jenkins & GitHub Actions)")).isVisible();
    assertThat(textTechnologies.nth(1)).isVisible();
    assertThat(projectTypeScriptCard.getByText("TypeScript", new Locator.GetByTextOptions().setExact(true))).isVisible();
    assertThat(projectTypeScriptCard.getByText("Jenkins", new Locator.GetByTextOptions().setExact(true))).isVisible();
    assertThat(projectTypeScriptCard.getByText("Docker", new Locator.GetByTextOptions().setExact(true))).isVisible();
  }

  public void verifyProject3() {
    assertThat(projectPythonTitle).isVisible();
    assertThat(projectPythonCard.getByText("UI automation for this portfolio site using Playwright and pytest.")).isVisible();
    assertThat(textHighlights.nth(2)).isVisible();
    assertThat(projectPythonCard.getByText("Page Object Model (POM) for Home & Projects pages")).isVisible();
    assertThat(projectPythonCard.getByText("pytest + playwright integration")).isVisible();
    assertThat(projectPythonCard.getByText("HTML report generation via pytest-html")).isVisible();
    assertThat(textTechnologies.nth(2)).isVisible();
    assertThat(projectPythonCard.getByText("Python", new Locator.GetByTextOptions().setExact(true))).isVisible();
    assertThat(projectPythonCard.getByText("pytest", new Locator.GetByTextOptions().setExact(true))).isVisible();
  }

  public void verifyProject4() {
    assertThat(projectQaPracticeTitle).isVisible();
    assertThat(projectQaPracticeCard.getByText("Automated end-to-end test suites written in TypeScript using Playwright.")).isVisible();
    assertThat(textHighlights.nth(3)).isVisible();
    assertThat(projectQaPracticeCard.getByText("Data-driven testing via CSV integration")).isVisible();
    assertThat(projectQaPracticeCard.getByText("Page Object Model (POM) architecture")).isVisible();
    assertThat(projectQaPracticeCard.getByText("Automated form validation & edge case handling")).isVisible();
    assertThat(textTechnologies.nth(3)).isVisible();
  }

  public void verifyRepositoryLinks() {
    Assertions.assertEquals(4, repositoryLinks.count());
    List<String> hrefs = new ArrayList<>();
    for (int i = 0; i < repositoryLinks.count(); i++) {
      hrefs.add(repositoryLinks.nth(i).getAttribute("href"));
    }
    Assertions.assertEquals(
      List.of(
        "https://github.com/cng07/playwright_portfolio_automation_javascript",
        "https://github.com/cng07/playwright_portfolio_automation_typescript",
        "https://github.com/cng07/playwright_portfolio_automation_python",
        "https://github.com/cng07/qaPractice"
      ),
      hrefs
    );
  }

  public void verifyRepositoryLinksApiResponses() {
    List<String> hrefs = new ArrayList<>();
    for (int i = 0; i < repositoryLinks.count(); i++) {
      String href = repositoryLinks.nth(i).getAttribute("href");
      if (href != null && !href.isBlank()) {
        hrefs.add(href);
      }
    }
    h.verifyUrlsApiResponses(hrefs, 30000, "repository URL");
  }

  public void verifyMoreProjectsComingSection() {
    assertThat(moreProjectsComingHeading).isVisible();
    assertThat(moreProjectsComingText).isVisible();
  }

  public void verifyFooterSection() {
    assertThat(privacyPolicyLink).isVisible();
    assertThat(termsAndConditionsLink).isVisible();
    assertThat(privacyPolicyLink).hasAttribute("href", "/privacy");
    assertThat(termsAndConditionsLink).hasAttribute("href", "/terms");
  }

  public void verifyInternalLinksApiResponses() {
    h.verifyInternalPathsApiResponses(List.of("/projects", "/privacy", "/terms"), 15000);
  }

  public void verifyAllProjectsPageElements() {
    verifyAccessibilityElements();
    verifyProjectsPageHeader();
    verifyProject1();
    verifyProject2();
    verifyProject3();
    verifyProject4();
    verifyRepositoryLinks();
    verifyMoreProjectsComingSection();
    verifyFooterSection();
  }

  public void verifyAllProjectsApiChecks() {
    verifyRepositoryLinksApiResponses();
    verifyInternalLinksApiResponses();
  }
}
