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

public class EducationPage {
  private final Page page;
  private final TestHelper h;

  private final Locator skipToContentLink;
  private final Locator mainContent;
  private final Locator navMoreButton;
  private final Locator navEducationMenuItem;
  private final Locator educationHeading;
  private final Locator sectionCards;

  private final Locator asiaPacificCollegeHeading;
  private final Locator degreeText;
  private final Locator tertiaryDateText;
  private final Locator tertiaryLocationText;
  private final Locator honorsHeading;
  private final Locator scholarshipHeading;
  private final Locator scholarshipOrgText;

  private final Locator leadershipHeading;
  private final Locator apcSeesText;
  private final Locator iecepText;
  private final Locator scholarsText;
  private final Locator mathSocietyText;

  private final Locator secondaryEducationHeading;
  private final Locator makatiScienceHeading;
  private final Locator secondaryDateText;
  private final Locator secondaryLocationText;

  private final Locator publicationsHeading;
  private final Locator publicationTitle;
  private final Locator doiText;
  private final Locator proceedingsLink;
  private final Locator ieeeXploreLink;

  private final Locator privacyPolicyLink;
  private final Locator termsAndConditionsLink;

  public EducationPage(Page page) {
    this.page = page;
    this.h = new TestHelper(page);

    skipToContentLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Skip to content"));
    mainContent = page.locator("#main-content");
    navMoreButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("More"));
    navEducationMenuItem = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Education"));
    educationHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Education").setLevel(1));
    sectionCards = page.locator("div.glass");

    asiaPacificCollegeHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Asia Pacific College").setLevel(2)
    );
    degreeText = page.getByText("BS Electronics Engineering", new Page.GetByTextOptions().setExact(true));
    tertiaryDateText = page.getByText("June 2013");
    tertiaryLocationText = page.getByText(
      "#3 Humabon Place, Magallanes, Makati City, Philippines",
      new Page.GetByTextOptions().setExact(true)
    );
    honorsHeading = page.getByText("Honors & Achievements", new Page.GetByTextOptions().setExact(true));
    scholarshipHeading = page.getByText("SCHOLARSHIP");
    scholarshipOrgText = page.getByText("SM Foundation, Inc.", new Page.GetByTextOptions().setExact(true));

    leadershipHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Leadership & Involvement").setLevel(2)
    );
    apcSeesText = page.getByText("APC Society of Electronics Engineering Students", new Page.GetByTextOptions().setExact(true));
    iecepText = page.getByText(
      "Institute of Electronics Engineers of the Philippines (IECEP-Manila Student Chapter)",
      new Page.GetByTextOptions().setExact(true)
    );
    scholarsText = page.getByText("APC SM Foundation Inc. Scholars", new Page.GetByTextOptions().setExact(true));
    mathSocietyText = page.getByText("APC Mathematics Society", new Page.GetByTextOptions().setExact(true));

    secondaryEducationHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Secondary Education").setLevel(2)
    );
    makatiScienceHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Makati Science High School").setLevel(2)
    );
    secondaryDateText = page.getByText("June 2009");
    secondaryLocationText = page.getByText("9 Kalayaan Ave, Makati City, Philippines", new Page.GetByTextOptions().setExact(true));

    publicationsHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Publications").setLevel(2)
    );
    publicationTitle = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions()
        .setName("A Development of a Low-Cost 12-Lead Electrocardiogram Monitoring Device Using Android-Based Smartphone")
        .setLevel(3)
    );
    doiText = page.getByText("DOI: 10.1109/GCCE.2018.8574836", new Page.GetByTextOptions().setExact(true));
    proceedingsLink = page.locator("a[href=\"https://ieeexplore.ieee.org/xpl/conhome/8555972/proceeding\"]");
    ieeeXploreLink = page.locator("a[href=\"https://ieeexplore.ieee.org/document/8574836\"]");

    privacyPolicyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Privacy Policy"));
    termsAndConditionsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Terms & Conditions"));
  }

  public void goToEducationPage() {
    assertThat(navMoreButton).isVisible();
    navMoreButton.click();
    assertThat(navEducationMenuItem).isVisible();
    navEducationMenuItem.click();
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/education$"));
    assertThat(page).hasTitle("Carlos Ng | Education");
  }

  public void goToEducationPageDirect() {
    page.navigate(SiteConfig.BASE_URL + "/education");
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/education$"));
    assertThat(page).hasTitle("Carlos Ng | Education");
  }

  public void verifyAccessibilityElements() {
    assertThat(skipToContentLink).hasAttribute("href", "#main-content");
    assertThat(mainContent).isVisible();
  }

  public void verifyEducationPageHeader() {
    assertThat(educationHeading).isVisible();
    Assertions.assertTrue(sectionCards.count() >= 4, "Expected at least 4 section cards");
  }

  public void verifyTertiarySection() {
    assertThat(asiaPacificCollegeHeading).isVisible();
    assertThat(degreeText).isVisible();
    assertThat(tertiaryDateText).isVisible();
    assertThat(tertiaryLocationText).isVisible();
    assertThat(honorsHeading).isVisible();
    assertThat(scholarshipHeading).isVisible();
    assertThat(scholarshipOrgText).isVisible();
  }

  public void verifyLeadershipAndInvolvementSection() {
    assertThat(leadershipHeading).isVisible();
    assertThat(apcSeesText).isVisible();
    assertThat(iecepText).isVisible();
    assertThat(scholarsText).isVisible();
    assertThat(mathSocietyText).isVisible();
  }

  public void verifySecondarySection() {
    assertThat(secondaryEducationHeading).isVisible();
    assertThat(makatiScienceHeading).isVisible();
    assertThat(secondaryDateText).isVisible();
    assertThat(secondaryLocationText).isVisible();
  }

  public void verifyPublicationsSection() {
    assertThat(publicationsHeading).isVisible();
    assertThat(publicationTitle).isVisible();
    assertThat(doiText).isVisible();
    assertThat(proceedingsLink).isVisible();
    assertThat(ieeeXploreLink).isVisible();
    assertThat(proceedingsLink).hasAttribute("href", "https://ieeexplore.ieee.org/xpl/conhome/8555972/proceeding");
    assertThat(ieeeXploreLink).hasAttribute("href", "https://ieeexplore.ieee.org/document/8574836");
  }

  public void verifyFooterSection() {
    assertThat(privacyPolicyLink).isVisible();
    assertThat(termsAndConditionsLink).isVisible();
    assertThat(privacyPolicyLink).hasAttribute("href", "/privacy");
    assertThat(termsAndConditionsLink).hasAttribute("href", "/terms");
  }

  public void verifyInternalLinksApiResponses() {
    h.verifyInternalPathsApiResponses(List.of("/education", "/privacy", "/terms"), 15000);
  }

  public void verifyPublicationLinksApiResponses() {
    List<String> publicationUrls = List.of(
      "https://ieeexplore.ieee.org/xpl/conhome/8555972/proceeding",
      "https://ieeexplore.ieee.org/document/8574836"
    );
    for (String url : publicationUrls) {
      APIResponse response = page.request().get(url, RequestOptions.create().setTimeout(30000));
      Assertions.assertTrue(
        response.status() >= 200 && response.status() < 400,
        String.format("Expected publication URL to be reachable: %s (status=%d)", url, response.status())
      );
    }
  }

  public void verifyAllEducationPageElements() {
    verifyAccessibilityElements();
    verifyEducationPageHeader();
    verifyTertiarySection();
    verifyLeadershipAndInvolvementSection();
    verifySecondarySection();
    verifyPublicationsSection();
    verifyFooterSection();
  }

  public void verifyAllEducationApiChecks() {
    verifyInternalLinksApiResponses();
    verifyPublicationLinksApiResponses();
  }
}

