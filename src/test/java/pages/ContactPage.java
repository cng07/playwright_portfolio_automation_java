package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import framework.SiteConfig;
import framework.TestHelper;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;

public class ContactPage {
  private final Page page;
  private final TestHelper h;

  private final Locator skipToContentLink;
  private final Locator mainContent;
  private final Locator navContactLink;

  private final Locator contactHeading;
  private final Locator contactSubtitle;
  private final Locator emailHeading;
  private final Locator linkedInHeading;
  private final Locator gitHubHeading;
  private final Locator ieeeHeading;
  private final Locator atsqaHeading;

  private final Locator emailLink;
  private final Locator linkedInLink;
  private final Locator gitHubLink;
  private final Locator ieeeLink;
  private final Locator atsqaLink;
  private final Locator linkedInCtaText;
  private final Locator gitHubCtaText;
  private final Locator publicationsCtaText;
  private final Locator atsqaCtaText;

  private final Locator privacyPolicyLink;
  private final Locator termsAndConditionsLink;

  public ContactPage(Page page) {
    this.page = page;
    this.h = new TestHelper(page);

    skipToContentLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Skip to content"));
    mainContent = page.locator("#main-content");
    navContactLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact"));

    contactHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Get in Touch").setLevel(1));
    contactSubtitle = page.getByText(
      "Let's connect to exchange ideas and discuss topics related to software engineering and innovation.",
      new Page.GetByTextOptions().setExact(true)
    );
    emailHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Email").setLevel(3));
    linkedInHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("LinkedIn").setLevel(3));
    gitHubHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("GitHub").setLevel(3));
    ieeeHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("IEEE Xplore").setLevel(3));
    atsqaHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("AT*SQA Profile").setLevel(3));

    emailLink = mainContent.locator("a[href=\"mailto:carlosng07@gmail.com\"]").first();
    linkedInLink = mainContent.locator("a[href=\"https://www.linkedin.com/in/carlosng07\"]").first();
    gitHubLink = mainContent.locator("a[href=\"https://github.com/cng07\"]").first();
    ieeeLink = mainContent.locator("a[href=\"https://ieeexplore.ieee.org/author/37086553247\"]").first();
    atsqaLink = mainContent
      .locator("a[href=\"https://atsqa.org/certified-testers/profile/6676da6cab1b424aa4070395ff71f490\"]")
      .first();
    linkedInCtaText = page.getByText("Connect on LinkedIn", new Page.GetByTextOptions().setExact(true));
    gitHubCtaText = page.getByText("Follow on GitHub", new Page.GetByTextOptions().setExact(true));
    publicationsCtaText = page.getByText("View Publications", new Page.GetByTextOptions().setExact(true));
    atsqaCtaText = page.getByText("View Certified Tester Profile", new Page.GetByTextOptions().setExact(true));

    privacyPolicyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Privacy Policy"));
    termsAndConditionsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Terms & Conditions"));
  }

  public void goToContactPage() {
    navContactLink.click();
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/contact$"));
    assertThat(page).hasTitle("Carlos Ng | Contact");
  }

  public void goToContactPageDirect() {
    page.navigate(SiteConfig.BASE_URL + "/contact");
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/contact$"));
    assertThat(page).hasTitle("Carlos Ng | Contact");
  }

  public void verifyAccessibilityElements() {
    assertThat(skipToContentLink).hasAttribute("href", "#main-content");
    assertThat(mainContent).isVisible();
  }

  public void verifyContactPageHeader() {
    assertThat(contactHeading).isVisible();
    assertThat(contactSubtitle).isVisible();
  }

  public void verifyContactMethodsSection() {
    assertThat(emailHeading).isVisible();
    assertThat(linkedInHeading).isVisible();
    assertThat(gitHubHeading).isVisible();
    assertThat(ieeeHeading).isVisible();
    assertThat(atsqaHeading).isVisible();

    assertThat(emailLink).isVisible();
    assertThat(linkedInLink).isVisible();
    assertThat(gitHubLink).isVisible();
    assertThat(ieeeLink).isVisible();
    assertThat(atsqaLink).isVisible();

    assertThat(emailLink).hasAttribute("href", "mailto:carlosng07@gmail.com");
    assertThat(linkedInLink).hasAttribute("href", "https://www.linkedin.com/in/carlosng07");
    assertThat(gitHubLink).hasAttribute("href", "https://github.com/cng07");
    assertThat(ieeeLink).hasAttribute("href", "https://ieeexplore.ieee.org/author/37086553247");
    assertThat(atsqaLink).hasAttribute("href", "https://atsqa.org/certified-testers/profile/6676da6cab1b424aa4070395ff71f490");

    assertThat(linkedInCtaText).isVisible();
    assertThat(gitHubCtaText).isVisible();
    assertThat(publicationsCtaText).isVisible();
    assertThat(atsqaCtaText).isVisible();
  }

  public void verifyFooterSection() {
    assertThat(privacyPolicyLink).isVisible();
    assertThat(termsAndConditionsLink).isVisible();
    assertThat(privacyPolicyLink).hasAttribute("href", "/privacy");
    assertThat(termsAndConditionsLink).hasAttribute("href", "/terms");
  }

  public void verifyInternalLinksApiResponses() {
    h.verifyInternalPathsApiResponses(List.of("/contact", "/privacy", "/terms"), 15000);
  }

  public void verifyExternalContactLinksApiResponses() {
    List<String> externalUrls = List.of(
      "https://www.linkedin.com/in/carlosng07",
      "https://github.com/cng07",
      "https://ieeexplore.ieee.org/author/37086553247",
      "https://atsqa.org/certified-testers/profile/6676da6cab1b424aa4070395ff71f490"
    );

    for (String url : externalUrls) {
      APIResponse response = page.request().get(url, RequestOptions.create().setTimeout(30000));
      Assertions.assertTrue(
        response.status() >= 200 && response.status() < 400,
        String.format("Expected external URL to be reachable: %s (status=%d)", url, response.status())
      );
    }
  }

  public void verifyAllContactPageElements() {
    verifyAccessibilityElements();
    verifyContactPageHeader();
    verifyContactMethodsSection();
    verifyFooterSection();
  }

  public void verifyAllContactApiChecks() {
    verifyInternalLinksApiResponses();
    verifyExternalContactLinksApiResponses();
  }
}

