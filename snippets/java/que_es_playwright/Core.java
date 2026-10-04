package que_es_playwright;

import org.junit.jupiter.api.Test;

// #region example
import com.microsoft.playwright.*;

public class Core {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://playwright.dev/");
            System.out.println(page.title());

            browser.close();
        }
    }
}
// #endregion

class CoreTest {
    @Test
    void playwrightCoreSinTestRunner() {
        Core.main(new String[0]);
    }
}
