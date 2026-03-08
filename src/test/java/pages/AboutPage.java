package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import framework.SiteConfig;
import framework.TestHelper;
import java.util.List;
import java.util.regex.Pattern;

public class AboutPage {
  private final Page page;
  private final TestHelper h;

  private final Locator skipToContentLink;
  private final Locator mainContent;
  private final Locator navAboutLink;

  private final Locator aboutHeading;
  private final Locator introHeading;
  private final Locator qaPhilosophyHeading;
  private final Locator nameHeading;
  private final Locator aboutIntroParagraph;
  private final Locator mainToolText;
  private final Locator roleText;

  private final Locator profileImage;
  private final Locator originalImage;

  private final Locator fastExecutionTitle;
  private final Locator fastExecutionDescription;
  private final Locator maintainedCodeTitle;
  private final Locator maintainedCodeDescription;
  private final Locator qaPhilosophyDescription;

  private final Locator githubLink;
  private final Locator linkedInLink;
  private final Locator emailLink;

  private final Locator privacyPolicyLink;
  private final Locator termsAndConditionsLink;

  public AboutPage(Page page) {
    this.page = page;
    this.h = new TestHelper(page);

    skipToContentLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Skip to content"));
    mainContent = page.locator("#main-content");
    navAboutLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("About"));

    aboutHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("About Me").setLevel(1));
    introHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("I'm Carlos Ng").setLevel(2));
    qaPhilosophyHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("QA Philosophy").setLevel(3)
    );
    nameHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Carlos Angelo E. Ng").setLevel(3)
    );
    aboutIntroParagraph = page.getByText("A Test Automation Engineer with 7+ years of experience in software testing");
    mainToolText = page.getByText("Main tool I use these days: Playwright - TypeScript");
    roleText = page.getByText("Senior Quality Assurance Automation Engineer");

    profileImage = page.getByAltText("Carlos Angelo E. Ng - Professional");
    originalImage = page.getByAltText("Carlos Angelo E. Ng - Original");

    fastExecutionTitle = page.getByText("Fast Execution", new Page.GetByTextOptions().setExact(true));
    fastExecutionDescription = page.getByText(
      "Reduced execution time from 19 hours to 4 hours for 300+ test cases.",
      new Page.GetByTextOptions().setExact(true)
    );
    maintainedCodeTitle = page.getByText("Maintained Code", new Page.GetByTextOptions().setExact(true));
    maintainedCodeDescription = page.getByText(
      "Stable, clean, and reliable test suites.",
      new Page.GetByTextOptions().setExact(true)
    );
    qaPhilosophyDescription = page.getByText(
      "My goal in QA is simple: reduce risk, increase confidence, and keep releases smooth.",
      new Page.GetByTextOptions().setExact(true)
    );

    githubLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("@cng07"));
    linkedInLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("@carlosng07"));
    emailLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("carlosng07@gmail.com"));

    privacyPolicyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Privacy Policy"));
    termsAndConditionsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Terms & Conditions"));
  }

  public void goToAboutPage() {
    navAboutLink.click();
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/about$"));
    assertThat(page).hasTitle("Carlos Ng | About");
  }

  public void goToAboutPageDirect() {
    page.navigate(SiteConfig.BASE_URL + "/about");
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/about$"));
    assertThat(page).hasTitle("Carlos Ng | About");
  }

  public void verifyAccessibilityElements() {
    assertThat(skipToContentLink).hasAttribute("href", "#main-content");
    assertThat(mainContent).isVisible();
  }

  public void verifyAboutPageHeader() {
    assertThat(aboutHeading).isVisible();
    assertThat(introHeading).isVisible();
  }

  public void verifyAboutProfileSection() {
    assertThat(aboutIntroParagraph).isVisible();
    assertThat(mainToolText).isVisible();
    assertThat(nameHeading).isVisible();
    assertThat(roleText).isVisible();
    assertThat(profileImage).isVisible();
    assertThat(originalImage).isVisible();
  }

  public void verifyHighlightsAndPhilosophy() {
    assertThat(fastExecutionTitle).isVisible();
    assertThat(fastExecutionDescription).isVisible();
    assertThat(maintainedCodeTitle).isVisible();
    assertThat(maintainedCodeDescription).isVisible();
    assertThat(qaPhilosophyHeading).isVisible();
    assertThat(qaPhilosophyDescription).isVisible();
  }

  public void verifyContactLinks() {
    assertThat(githubLink).isVisible();
    assertThat(linkedInLink).isVisible();
    assertThat(emailLink).isVisible();
    assertThat(githubLink).hasAttribute("href", "https://github.com/cng07");
    assertThat(linkedInLink).hasAttribute("href", "https://www.linkedin.com/in/carlosng07");
    assertThat(emailLink).hasAttribute("href", "mailto:carlosng07@gmail.com");
  }

  public void verifyFooterSection() {
    assertThat(privacyPolicyLink).isVisible();
    assertThat(termsAndConditionsLink).isVisible();
    assertThat(privacyPolicyLink).hasAttribute("href", "/privacy");
    assertThat(termsAndConditionsLink).hasAttribute("href", "/terms");
  }

  public void verifyInternalLinksApiResponses() {
    h.verifyInternalPathsApiResponses(List.of("/about", "/privacy", "/terms"), 15000);
  }

  public void verifyAllAboutPageElements() {
    verifyAccessibilityElements();
    verifyAboutPageHeader();
    verifyAboutProfileSection();
    verifyHighlightsAndPhilosophy();
    verifyContactLinks();
    verifyFooterSection();
  }
}

