package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import framework.SiteConfig;
import framework.TestHelper;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;

public class CertificationsPage {
  private final Page page;
  @SuppressWarnings("unused")
  private final TestHelper h;

  private final Locator navMore;
  private final Locator navCertificationsLink;
  private final Locator certificationsHeading;

  private final Locator ctflHeading;
  private final Locator devOpsHeading;
  private final Locator accentureAgileHeading;
  private final Locator automationAnywhereHeading;

  private final Locator ctflDate;
  private final Locator ctflExpiry;
  private final Locator ctflIssuer;
  private final Locator ctflCredentialIdLabel;
  private final Locator ctflCredentialIdValue;

  private final Locator devOpsDate;
  private final Locator devOpsExpiry;
  private final Locator devOpsIssuer;
  private final Locator devOpsCredentialIdLabel;
  private final Locator devOpsCredentialIdValue;

  private final Locator accentureSubHeading;
  private final Locator accentureDate;
  private final Locator accentureExpiry;
  private final Locator accentureIssuer;
  private final Locator accentureCertificateNumberLabel;
  private final Locator accentureCertificateNumberValue;

  private final Locator automationAnywhereSubHeading;
  private final Locator automationAnywhereDate;
  private final Locator automationAnywhereExpiry;
  private final Locator automationAnywhereIssuer;
  private final Locator automationAnywhereCertificateNumberLabel;
  private final Locator automationAnywhereCertificateNumberValue;

  private final Locator officialProfileLink;
  private final Locator viewCertificateLinks;

  public CertificationsPage(Page page) {
    this.page = page;
    this.h = new TestHelper(page);

    navMore = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("More"));
    navCertificationsLink = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Certifications"));
    certificationsHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Certifications").setLevel(1));

    ctflHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("ISTQB Certified Tester Foundation Level (CTFL)").setLevel(2)
    );
    devOpsHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Certified Tester, AT*SQA DevOps Testing").setLevel(2)
    );
    accentureAgileHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Accenture Agile Certification Program").setLevel(2)
    );
    automationAnywhereHeading = page.getByRole(
      AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Automation Anywhere Certified Advanced RPA Professional").setLevel(2)
    );

    ctflDate = page.getByText("April 2024", new Page.GetByTextOptions().setExact(true));
    ctflExpiry = page.getByText("No Expiry", new Page.GetByTextOptions().setExact(true)).nth(0);
    ctflIssuer = page.getByText("ASTQB - ISTQB in the U.S.", new Page.GetByTextOptions().setExact(true)).nth(0);
    ctflCredentialIdLabel = page.getByText("Credential ID:", new Page.GetByTextOptions().setExact(true)).nth(0);
    ctflCredentialIdValue = page.getByText("24-CTFL-01347-USA", new Page.GetByTextOptions().setExact(true));

    devOpsDate = page.getByText("January 2023").first();
    devOpsExpiry = page.getByText("Expired", new Page.GetByTextOptions().setExact(true)).nth(0);
    devOpsIssuer = page.getByText("ASTQB - ISTQB in the U.S.", new Page.GetByTextOptions().setExact(true)).nth(1);
    devOpsCredentialIdLabel = page.getByText("Credential ID:", new Page.GetByTextOptions().setExact(true)).nth(1);
    devOpsCredentialIdValue = page.getByText("23-AT*DevOps-00002-USA", new Page.GetByTextOptions().setExact(true));

    accentureSubHeading = page.getByText("Agile Professional Certified", new Page.GetByTextOptions().setExact(true));
    accentureDate = page.getByText("June 2020", new Page.GetByTextOptions().setExact(true));
    accentureExpiry = page.getByText("No Expiry", new Page.GetByTextOptions().setExact(true)).nth(1);
    accentureIssuer = page.getByText("Accenture", new Page.GetByTextOptions().setExact(true));
    accentureCertificateNumberLabel = page.getByText("Certificate Number:", new Page.GetByTextOptions().setExact(true)).nth(0);
    accentureCertificateNumberValue = page.getByText("CNAG0000009961", new Page.GetByTextOptions().setExact(true));

    automationAnywhereSubHeading = page.getByText(
      "Robotic Process Automation Professional (V11.0)",
      new Page.GetByTextOptions().setExact(true)
    );
    automationAnywhereDate = page.getByText("July 2020").first();
    automationAnywhereExpiry = page.getByText("Expired", new Page.GetByTextOptions().setExact(true)).nth(1);
    automationAnywhereIssuer = page.getByText("Automation Anywhere", new Page.GetByTextOptions().setExact(true));
    automationAnywhereCertificateNumberLabel = page.getByText("Certificate Number:", new Page.GetByTextOptions().setExact(true)).nth(1);
    automationAnywhereCertificateNumberValue = page.getByText("AAADVC-21147163", new Page.GetByTextOptions().setExact(true));

    officialProfileLink = page.getByRole(
      AriaRole.LINK,
      new Page.GetByRoleOptions().setName("Official U.S. List of Certified & Credentialed Software Testers")
    );
    viewCertificateLinks = page.locator("a:has-text(\"View Certificate\")");
  }

  public void goToCertificationsPage() {
    page.navigate(SiteConfig.BASE_URL + "/certifications");
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasTitle("Carlos Ng | Certifications");
  }

  public void goToCertificationsPageFromHomeNavigation() {
    assertThat(navMore).isVisible();
    navMore.click();
    assertThat(navCertificationsLink).isVisible();
    navCertificationsLink.click();
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/certifications$"));
    assertThat(page).hasTitle("Carlos Ng | Certifications");
  }

  public void verifyCertificationsPageHeader() {
    assertThat(certificationsHeading).isVisible();
  }

  public void verifyCTFLCertification() {
    assertThat(ctflHeading).isVisible();
    assertThat(ctflDate).isVisible();
    assertThat(ctflExpiry).isVisible();
    assertThat(ctflIssuer).isVisible();
    assertThat(ctflCredentialIdLabel).isVisible();
    assertThat(ctflCredentialIdValue).isVisible();
  }

  public void verifyDevOpsCertification() {
    assertThat(devOpsHeading).isVisible();
    assertThat(devOpsDate).isVisible();
    assertThat(devOpsExpiry).isVisible();
    assertThat(devOpsIssuer).isVisible();
    assertThat(devOpsCredentialIdLabel).isVisible();
    assertThat(devOpsCredentialIdValue).isVisible();
  }

  public void verifyAccentureAgileCertification() {
    assertThat(accentureAgileHeading).isVisible();
    assertThat(accentureSubHeading).isVisible();
    assertThat(accentureDate).isVisible();
    assertThat(accentureExpiry).isVisible();
    assertThat(accentureIssuer).isVisible();
    assertThat(accentureCertificateNumberLabel).isVisible();
    assertThat(accentureCertificateNumberValue).isVisible();
  }

  public void verifyAutomationAnywhereCertification() {
    assertThat(automationAnywhereHeading).isVisible();
    assertThat(automationAnywhereSubHeading).isVisible();
    assertThat(automationAnywhereDate).isVisible();
    assertThat(automationAnywhereExpiry).isVisible();
    assertThat(automationAnywhereIssuer).isVisible();
    assertThat(automationAnywhereCertificateNumberLabel).isVisible();
    assertThat(automationAnywhereCertificateNumberValue).isVisible();
  }

  public void verifyCertificationLinks() {
    assertThat(officialProfileLink).isVisible();
    Assertions.assertEquals(4, viewCertificateLinks.count());
  }

  public void verifyAllCertificationsPageElements() {
    verifyCertificationsPageHeader();
    verifyCTFLCertification();
    verifyDevOpsCertification();
    verifyAccentureAgileCertification();
    verifyAutomationAnywhereCertification();
    verifyCertificationLinks();
  }
}

