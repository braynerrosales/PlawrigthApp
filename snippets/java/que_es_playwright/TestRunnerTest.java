package que_es_playwright;

// #region example
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

// Integración experimental de Playwright con JUnit: entrega Page como parámetro.
@UsePlaywright
public class TestRunnerTest {
    @Test
    void junitEntregaLaPageLista(Page page) {
        page.navigate("https://playwright.dev/");

        assertThat(page).hasTitle(Pattern.compile("Playwright"));
    }
}
// #endregion
