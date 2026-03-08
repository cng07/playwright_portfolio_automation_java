package framework;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseUiTest {
  protected Playwright playwright;
  protected Browser browser;
  protected BrowserContext context;
  protected Page page;

  @BeforeEach
  void setUp() {
    playwright = Playwright.create();
    boolean isCi = "true".equalsIgnoreCase(System.getenv("CI"));
    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isCi));
    context = browser.newContext();
    page = context.newPage();
  }

  @AfterEach
  void tearDown() {
    if (context != null) {
      context.close();
    }
    if (browser != null) {
      browser.close();
    }
    if (playwright != null) {
      playwright.close();
    }
  }
}
