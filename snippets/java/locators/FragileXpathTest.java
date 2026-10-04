package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class FragileXpathTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void xpathAbsolutoCopiadoDeDevtools() {
        // #region example
        page.locator("xpath=/html/body/div/div[4]/button").click();
        // #endregion
        assertThat(page.locator("#settings-state")).hasText("Cambios guardados");
    }
}
