package pages;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import framework.SiteConfig;
import framework.TestHelper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;

public class ResumePage {
  private final Page page;
  private final TestHelper h;

  private final Locator skipToContentLink;
  private final Locator mainContent;
  private final Locator resumePage;
  private final Locator resumeHeading;
  private final Locator downloadPdfButton;
  private final Locator resumePdfObject;
  private final Locator resumePdfIframe;
  private final Locator privacyPolicyLink;
  private final Locator termsAndConditionsLink;

  public ResumePage(Page page) {
    this.page = page;
    this.h = new TestHelper(page);

    skipToContentLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Skip to content"));
    mainContent = page.locator("#main-content");
    resumePage = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Resume"));
    resumeHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Resume").setLevel(1));
    downloadPdfButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Download PDF"));
    resumePdfObject = page.locator("object[type=\"application/pdf\"]");
    resumePdfIframe = page.locator("object iframe[title=\"Carlos Ng Resume\"]");
    privacyPolicyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Privacy Policy"));
    termsAndConditionsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Terms & Conditions"));
  }

  public void goToResumePage() {
    resumePage.click();
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/resume$"));
    assertThat(page).hasTitle("Carlos Ng | Resume");
  }

  public void goToResumePageDirect() {
    page.navigate(SiteConfig.BASE_URL + "/resume");
    page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    assertThat(page).hasURL(Pattern.compile("/resume$"));
    assertThat(page).hasTitle("Carlos Ng | Resume");
  }

  public void verifyAccessibilityElements() {
    assertThat(skipToContentLink).hasAttribute("href", "#main-content");
    assertThat(mainContent).isVisible();
  }

  public void verifyResumePageHeader() {
    assertThat(resumeHeading).isVisible();
  }

  public void verifyDownloadPdfButton() {
    assertThat(downloadPdfButton).isVisible();
    assertThat(downloadPdfButton).isEnabled();
  }

  public void verifyResumeViewerSection() {
    assertThat(resumePdfObject).isVisible();
    assertThat(resumePdfObject).hasAttribute("data", "/Carlos_Ng_Resume.pdf");
    Assertions.assertTrue(resumePdfIframe.count() > 0, "Expected embedded resume iframe to exist");
    assertThat(resumePdfIframe).hasAttribute("src", Pattern.compile("docs\\.google\\.com/viewer"));
    assertThat(resumePdfIframe).hasAttribute("src", Pattern.compile("Carlos_Ng_Resume\\.pdf"));
  }

  public void downloadPdfAndVerify() {
    Download download = page.waitForDownload(downloadPdfButton::click);
    String filename = download.suggestedFilename();
    Assertions.assertEquals("Carlos_Ng_Resume.pdf", filename);
    Assertions.assertTrue(download.url().contains("/Carlos_Ng_Resume.pdf"));

    APIResponse apiResponse = page.request().get(download.url(), RequestOptions.create().setTimeout(30000));
    Assertions.assertEquals(200, apiResponse.status());
    Assertions.assertTrue(getHeader(apiResponse.headers(), "content-type").contains("application/pdf"));

    Path filePath = download.path();
    Assertions.assertNotNull(filePath, "Download path is empty");

    try {
      double fileSizeInKB = Files.size(filePath) / 1024.0;
      Assertions.assertTrue(fileSizeInKB > 500, "Expected file size > 500KB");
      Assertions.assertTrue(fileSizeInKB < 1000, "Expected file size < 1000KB");
      Files.deleteIfExists(filePath);
    } catch (IOException e) {
      throw new RuntimeException("Unable to verify downloaded file", e);
    }
  }

  public void verifyFooterSection() {
    assertThat(privacyPolicyLink).isVisible();
    assertThat(termsAndConditionsLink).isVisible();
    assertThat(privacyPolicyLink).hasAttribute("href", "/privacy");
    assertThat(termsAndConditionsLink).hasAttribute("href", "/terms");
  }

  public void verifyResumePdfApiResponse() {
    APIResponse response = page.request().get(
      SiteConfig.BASE_URL + "/Carlos_Ng_Resume.pdf",
      RequestOptions.create().setTimeout(30000)
    );
    Assertions.assertEquals(200, response.status());
    Assertions.assertTrue(getHeader(response.headers(), "content-type").contains("application/pdf"));
    long contentLength = parseLong(getHeader(response.headers(), "content-length"));
    Assertions.assertTrue(contentLength > 500_000L, "Expected PDF content-length > 500000");
  }

  public void verifyResumeInternalLinksApiResponses() {
    h.verifyInternalPathsApiResponses(List.of("/resume", "/privacy", "/terms"), 15000);
  }

  public void verifyAllResumePageElements() {
    verifyAccessibilityElements();
    verifyResumePageHeader();
    verifyDownloadPdfButton();
    verifyResumeViewerSection();
    verifyFooterSection();
  }

  public void verifyAllResumeApiChecks() {
    verifyResumePdfApiResponse();
    verifyResumeInternalLinksApiResponses();
  }

  private String getHeader(Map<String, String> headers, String key) {
    for (Map.Entry<String, String> entry : headers.entrySet()) {
      if (entry.getKey().toLowerCase(Locale.ROOT).equals(key.toLowerCase(Locale.ROOT))) {
        return entry.getValue();
      }
    }
    return "";
  }

  private long parseLong(String value) {
    try {
      return Long.parseLong(value);
    } catch (NumberFormatException ex) {
      return 0L;
    }
  }
}
