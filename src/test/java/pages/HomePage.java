package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import framework.SiteConfig;
import framework.TestHelper;
import org.junit.jupiter.api.Assertions;

public class HomePage {
  private final Page page;
  @SuppressWarnings("unused")
  private final TestHelper h;

  private final Locator skipToContentLink;
  private final Locator mainContent;

  private final Locator navLogo;
  private final Locator navHomeLink;
  private final Locator navProjectsLink;
  private final Locator navResumeLink;
  private final Locator navCertificationsLink;
  private final Locator navEducationLink;
  private final Locator navAboutLink;
  private final Locator navContactLink;
  private final Locator navMore;

  private final Locator profileImage;
  private final Locator heroNameHeading;
  private final Locator heroTaglineHeading;
  private final Locator heroRoleText;
  private final Locator heroIntroText;

  private final Locator linkedInButton;
  private final Locator gitHubButton;
  private final Locator ieeeButton;
  private final Locator astqbButton;

  private final Locator featuredProjectsSectionTitle;
  private final Locator portfolioTypeScriptProjectTitle;
  private final Locator portfolioPythonProjectTitle;
  private final Locator projectRepositoryLinks;
  private final Locator viewAllProjectsLink;

  private final Locator skillsSectionTitle;
  private final Locator testAutomationCard;
  private final Locator programmingLanguagesCard;
  private final Locator cicdCard;
  private final Locator manualTestingCard;
  private final Locator otherToolsCard;
  private final Locator aiToolsCard;

  private final Locator certificationsSectionTitle;
  private final Locator ctflCertificationPreview;
  private final Locator viewCertificateLink;
  private final Locator viewAllCertificationsLink;

  private final Locator publicationSectionTitle;
  private final Locator publicationTitle;
  private final Locator publicationDate;
  private final Locator viewPaperLink;

  private final Locator experienceSectionTitle;
  private final Locator datacomCompanyLink;
  private final Locator planitCompanyLink;
  private final Locator dxcCompanyLink;
  private final Locator daviCompanyLink;
  private final Locator accentureCompanyLink1;
  private final Locator accentureCompanyLink2;

  private final Locator homeButton;

  public HomePage(Page page) {
    this.page = page;
    this.h = new TestHelper(page);

    skipToContentLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Skip to content"));
    mainContent = page.locator("#main-content");

    navLogo = page.locator("a.nav-logo");
    navHomeLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
    navProjectsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Projects").setExact(true));
    navResumeLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Resume"));
    navCertificationsLink = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Certifications"));
    navEducationLink = page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Education"));
    navAboutLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("About"));
    navContactLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact"));
    navMore = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("More"));

    profileImage = page.getByAltText("Carlos Angelo E. Ng");
    heroNameHeading = page.getByRole(AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Carlos Angelo E. Ng").setLevel(2));
    heroTaglineHeading = page.getByRole(AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Automating Quality Delivering Excellence").setLevel(1));
    heroRoleText = page.getByText("Senior Quality Assurance Automation Engineer at Datacom");
    heroIntroText = page.getByText("Hi, I'm Carlos Ng.");

    linkedInButton = page.getByAltText("LinkedIn Logo");
    gitHubButton = page.getByAltText("GitHub Logo");
    ieeeButton = page.getByAltText("IEEE Logo");
    astqbButton = page.getByAltText("ASTQB Logo");

    featuredProjectsSectionTitle = page.getByRole(AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Featured Projects").setLevel(2));
    portfolioTypeScriptProjectTitle = page.getByRole(AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Portfolio Website Automation (TypeScript)").setLevel(3));
    portfolioPythonProjectTitle = page.getByRole(AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Portfolio Website Automation (Python)").setLevel(3));
    projectRepositoryLinks = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Repository"));
    viewAllProjectsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View All Projects"));

    skillsSectionTitle = page.locator("h2:has-text(\"Technical Skills\")");
    testAutomationCard = page.locator("h3:has-text(\"Test Automation\")");
    programmingLanguagesCard = page.locator("h3:has-text(\"Programming Languages\")");
    cicdCard = page.locator("h3:has-text(\"CI/CD\")");
    manualTestingCard = page.locator("h3:has-text(\"Manual Testing\")");
    otherToolsCard = page.locator("h3:has-text(\"Other Tools\")");
    aiToolsCard = page.locator("h3:has-text(\"AI Tools\")");

    certificationsSectionTitle = page.getByRole(AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Certifications").setLevel(2));
    ctflCertificationPreview = page.getByText(
      "ISTQB Certified Tester Foundation Level (CTFL)",
      new Page.GetByTextOptions().setExact(true)
    );
    viewCertificateLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View Certificate"));
    viewAllCertificationsLink = page.getByRole(
      AriaRole.LINK,
      new Page.GetByRoleOptions().setName("View All 4 Certifications")
    );

    publicationSectionTitle = page.getByRole(AriaRole.HEADING,
      new Page.GetByRoleOptions().setName("Publication").setLevel(2));
    publicationTitle = page.getByText(
      "A Development of a Low-Cost 12-Lead Electrocardiogram Monitoring Device Using Android-based Smartphone",
      new Page.GetByTextOptions().setExact(true)
    );
    publicationDate = page.getByText("Published in IEEE, 2018", new Page.GetByTextOptions().setExact(true));
    viewPaperLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("View Paper"));

    experienceSectionTitle = page.locator("h2:has-text(\"Experience\")");
    datacomCompanyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Datacom").setExact(true));
    planitCompanyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Planit").setExact(true));
    dxcCompanyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("DXC Technology").setExact(true));
    daviCompanyLink = page.getByRole(
      AriaRole.LINK,
      new Page.GetByRoleOptions().setName("Data Analytics Ventures, Inc.").setExact(true)
    );
    accentureCompanyLink1 = page.getByRole(
      AriaRole.LINK,
      new Page.GetByRoleOptions().setName("Accenture").setExact(true)
    ).nth(0);
    accentureCompanyLink2 = page.getByRole(
      AriaRole.LINK,
      new Page.GetByRoleOptions().setName("Accenture").setExact(true)
    ).nth(1);

    homeButton = page.getByText("home");
  }

  public void goToHomePage() {
    page.navigate(SiteConfig.BASE_URL + "/");
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasTitle("Carlos Ng | Portfolio");
    assertThat(homeButton).isVisible();
    assertThat(linkedInButton).isVisible();
  }

  public void verifyAccessibilityElements() {
    assertThat(skipToContentLink).hasAttribute("href", "#main-content");
    assertThat(mainContent).isVisible();
  }

  public void verifyNavigationBarSection() {
    assertThat(navLogo).isVisible();
    assertThat(navHomeLink).isVisible();
    assertThat(navProjectsLink).isVisible();
    assertThat(navResumeLink).isVisible();
    assertThat(navAboutLink).isVisible();
    assertThat(navContactLink).isVisible();
    assertThat(navProjectsLink).hasAttribute("href", "/projects");
    assertThat(navResumeLink).hasAttribute("href", "/resume");
    assertThat(navAboutLink).hasAttribute("href", "/about");
    assertThat(navContactLink).hasAttribute("href", "/contact");
    assertThat(navMore).isVisible();
    navMore.click();
    assertThat(navCertificationsLink).isVisible();
    assertThat(navEducationLink).isVisible();
    assertThat(navCertificationsLink).hasAttribute("href", "/certifications");
    assertThat(navEducationLink).hasAttribute("href", "/education");
  }

  public void verifyHeroSection() {
    assertThat(profileImage).isVisible();
    assertThat(heroNameHeading).isVisible();
    assertThat(heroTaglineHeading).isVisible();
    assertThat(heroRoleText).isVisible();
    assertThat(heroIntroText).isVisible();
  }

  public void verifySocialMediaSection() {
    assertThat(linkedInButton).isVisible();
    assertThat(gitHubButton).isVisible();
    assertThat(ieeeButton).isVisible();
    assertThat(astqbButton).isVisible();
  }

  public void verifyFeaturedProjectsSection() {
    assertThat(featuredProjectsSectionTitle).isVisible();
    assertThat(portfolioTypeScriptProjectTitle).isVisible();
    assertThat(portfolioPythonProjectTitle).isVisible();
    assertThat(viewAllProjectsLink).isVisible();
    assertThat(viewAllProjectsLink).hasAttribute("href", "/projects");

    int repositoryLinkCount = projectRepositoryLinks.count();
    Assertions.assertTrue(repositoryLinkCount >= 2, "Expected at least 2 repository links");
    assertThat(projectRepositoryLinks.nth(0)).isVisible();
    assertThat(projectRepositoryLinks.nth(1)).isVisible();
  }

  public void verifySkillsSection() {
    assertThat(skillsSectionTitle).isVisible();
    assertThat(skillsSectionTitle).containsText("Technical Skills");
    assertThat(testAutomationCard).isVisible();
    assertThat(programmingLanguagesCard).isVisible();
    assertThat(cicdCard).isVisible();
    assertThat(manualTestingCard).isVisible();
    assertThat(otherToolsCard).isVisible();
    assertThat(aiToolsCard).isVisible();
  }

  public void verifyCertificationsSection() {
    assertThat(certificationsSectionTitle).isVisible();
    assertThat(ctflCertificationPreview).isVisible();
    assertThat(viewCertificateLink).isVisible();
    assertThat(viewAllCertificationsLink).isVisible();
    assertThat(viewAllCertificationsLink).hasAttribute("href", "/certifications");
  }

  public void verifyPublicationSection() {
    assertThat(publicationSectionTitle).isVisible();
    assertThat(publicationTitle).isVisible();
    assertThat(publicationDate).isVisible();
    assertThat(viewPaperLink).isVisible();
    assertThat(viewPaperLink).hasAttribute("href", java.util.regex.Pattern.compile("ieeexplore\\.ieee\\.org"));
  }

  public void verifyExperienceSection() {
    assertThat(experienceSectionTitle).isVisible();
    assertThat(experienceSectionTitle).containsText("Experience");
    assertThat(datacomCompanyLink).isVisible();
    assertThat(planitCompanyLink).isVisible();
    assertThat(dxcCompanyLink).isVisible();
    assertThat(daviCompanyLink).isVisible();
    assertThat(accentureCompanyLink1).isVisible();
    assertThat(accentureCompanyLink2).isVisible();
  }

  public void verifyFooterSection() {
    Locator privacyPolicy = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Privacy Policy"));
    Locator terms = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Terms & Conditions"));
    assertThat(privacyPolicy).isVisible();
    assertThat(terms).isVisible();
    assertThat(privacyPolicy).hasAttribute("href", "/privacy");
    assertThat(terms).hasAttribute("href", "/terms");
  }
}

