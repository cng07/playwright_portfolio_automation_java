package framework;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.RequestOptions;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;

public class TestHelper {
  private final Page page;

  public TestHelper(Page page) {
    this.page = page;
  }

  public int getRandomNumber(int min, int max) {
    return (int) (Math.random() * (max - min + 1)) + min;
  }

  public List<String> buildInternalUrls(List<String> paths) {
    URI current = URI.create(page.url());
    String origin = current.getScheme() + "://" + current.getHost();
    List<String> urls = new ArrayList<>();
    for (String path : paths) {
      String safePath = path.startsWith("/") ? path : "/" + path;
      urls.add(origin + safePath);
    }
    return urls;
  }

  public void verifyUrlsApiResponses(List<String> urls, int timeout, String urlType) {
    for (String url : urls) {
      APIResponse response = page.request().get(url, RequestOptions.create().setTimeout((double) timeout));
      Assertions.assertTrue(
        response.status() >= 200 && response.status() < 400,
        String.format("Expected %s to be reachable: %s (status=%d)", urlType, url, response.status())
      );
    }
  }

  public void verifyInternalPathsApiResponses(List<String> paths, int timeout) {
    List<String> urls = buildInternalUrls(paths);
    verifyUrlsApiResponses(urls, timeout, "internal URL");
  }
}
