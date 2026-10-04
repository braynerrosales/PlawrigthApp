package primer_test;

// #region example
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import java.util.regex.Pattern;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ExampleTest {
    // Compartidos por todas las pruebas de la clase.
    static Playwright playwright;
    static Browser browser;

    // Nuevos en cada prueba.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void tieneTitulo() {
        page.navigate("https://playwright.dev/");

        assertThat(page).hasTitle(Pattern.compile("Playwright"));
    }

    @Test
    void linkGetStarted() {
        page.navigate("https://playwright.dev/");

        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get started")).click();

        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Installation"))).isVisible();
    }
}
// #endregion
