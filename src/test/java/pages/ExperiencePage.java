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

public class ExperiencePage {
  private final Page page;
  private final TestHelper h;

  private final Locator skipToContentLink;
  private final Locator mainContent;
  private final Locator navExperienceLink;
  private final Locator experienceHeading;
  private final Locator experienceCards;

  private final Locator datacomHeading;
  private final Locator planitHeading;
  private final Locator dxcHeading;
  private final Locator daviHeading;
  private final Locator accentureHeading1;
  private final Locator accentureHeading2;

  private final Locator currentRoleText;
  private final Locator currentDateRangeText;
  private final Locator taguigLocationText;
  private final Locator pasigLocationText;
  private final Locator mandaluyongLocationText;

  private final Locator datacomLink;
  private final Locator planitLink;
  private final Locator dxcLink;
  private final Locator daviLink;
  private final Locator accentureLink1;
  private final Locator accentureLink2;

  private final Locator privacyPolicyLink;
  private final Locator termsAndConditionsLink;

  public ExperiencePage(Page page) {
    this.page = page;
    this.h = new TestHelper(page);

    skipToContentLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Skip to content"));
    mainContent = page.locator("#main-content");
    navExperienceLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Experience"));

    experienceHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Work Experience").setLevel(1));
    experienceCards = page.locator("div.glass");

    datacomHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Datacom").setLevel(3));
    planitHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Planit").setLevel(3));
    dxcHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("DXC Technology").setLevel(3));
    daviHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Data Analytics Ventures, Inc.").setLevel(3)
    );
    accentureHeading1 = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Accenture").setLevel(3)
    ).nth(0);
    accentureHeading2 = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Accenture").setLevel(3)
    ).nth(1);

    currentRoleText = page.getByText("Senior Quality Assurance Automation Engineer");
    currentDateRangeText = page.getByText("April 2025");
    taguigLocationText = page.getByText("Taguig City, Philippines", new Page.GetByTextOptions().setExact(true)).nth(0);
    pasigLocationText = page.getByText("Pasig City, Philippines", new Page.GetByTextOptions().setExact(true));
    mandaluyongLocationText = page.getByText("Mandaluyong City, Philippines", new Page.GetByTextOptions().setExact(true)).nth(0);

    datacomLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Datacom").setExact(true));
    planitLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Planit").setExact(true));
    dxcLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("DXC Technology").setExact(true));
    daviLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Data Analytics Ventures, Inc.").setExact(true));
    accentureLink1 = page.getByRole(
      AriaRole.LINK,
      new Page.GetByRoleOptions().setName("Accenture").setExact(true)
    ).nth(0);
    accentureLink2 = page.getByRole(
      AriaRole.LINK,
      new Page.GetByRoleOptions().setName("Accenture").setExact(true)
    ).nth(1);

    privacyPolicyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Privacy Policy"));
    termsAndConditionsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Terms & Conditions"));
  }

  public void goToExperiencePage() {
    navExperienceLink.click();
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/experience$"));
    assertThat(page).hasTitle("Carlos Ng | Experience");
  }

  public void goToExperiencePageDirect() {
    page.navigate(SiteConfig.BASE_URL + "/experience");
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/experience$"));
    assertThat(page).hasTitle("Carlos Ng | Experience");
  }

  public void verifyAccessibilityElements() {
    assertThat(skipToContentLink).hasAttribute("href", "#main-content");
    assertThat(mainContent).isVisible();
  }

  public void verifyExperiencePageHeader() {
    assertThat(experienceHeading).isVisible();
    Assertions.assertTrue(experienceCards.count() >= 6, "Expected at least 6 experience cards");
  }

  public void verifyExperienceEntries() {
    assertThat(datacomHeading).isVisible();
    assertThat(planitHeading).isVisible();
    assertThat(dxcHeading).isVisible();
    assertThat(daviHeading).isVisible();
    assertThat(accentureHeading1).isVisible();
    assertThat(accentureHeading2).isVisible();

    assertThat(currentRoleText).isVisible();
    assertThat(currentDateRangeText).isVisible();
    assertThat(taguigLocationText).isVisible();
    assertThat(pasigLocationText).isVisible();
    assertThat(mandaluyongLocationText).isVisible();
  }

  public void verifyCompanyLinks() {
    assertThat(datacomLink).isVisible();
    assertThat(planitLink).isVisible();
    assertThat(dxcLink).isVisible();
    assertThat(daviLink).isVisible();
    assertThat(accentureLink1).isVisible();
    assertThat(accentureLink2).isVisible();

    assertThat(datacomLink).hasAttribute("href", "https://datacom.com/nz/en");
    assertThat(planitLink).hasAttribute("href", "https://www.planit.com/");
    assertThat(dxcLink).hasAttribute("href", "https://dxc.com/");
    assertThat(daviLink).hasAttribute("href", "https://www.davi.com.ph/");
    assertThat(accentureLink1).hasAttribute("href", "https://www.accenture.com/ph-en");
    assertThat(accentureLink2).hasAttribute("href", "https://www.accenture.com/ph-en");
  }

  public void verifyFooterSection() {
    assertThat(privacyPolicyLink).isVisible();
    assertThat(termsAndConditionsLink).isVisible();
    assertThat(privacyPolicyLink).hasAttribute("href", "/privacy");
    assertThat(termsAndConditionsLink).hasAttribute("href", "/terms");
  }

  public void verifyInternalLinksApiResponses() {
    h.verifyInternalPathsApiResponses(List.of("/experience", "/privacy", "/terms"), 15000);
  }

  public void verifyExternalCompanyLinksApiResponses() {
    List<String> externalUrls = List.of(
      "https://datacom.com/nz/en",
      "https://www.planit.com/",
      "https://dxc.com/",
      "https://www.davi.com.ph/",
      "https://www.accenture.com/ph-en"
    );

    for (String url : externalUrls) {
      APIResponse response = page.request().get(url, RequestOptions.create().setTimeout(30000));
      Assertions.assertTrue(response.status() >= 200 && response.status() < 500);
      Assertions.assertNotEquals(404, response.status(), "Expected external URL not to be missing: " + url);
    }
  }

  public void verifyAllExperiencePageElements() {
    verifyAccessibilityElements();
    verifyExperiencePageHeader();
    verifyExperienceEntries();
    verifyCompanyLinks();
    verifyFooterSection();
  }

  public void verifyAllExperienceApiChecks() {
    verifyInternalLinksApiResponses();
    verifyExternalCompanyLinksApiResponses();
  }
}

