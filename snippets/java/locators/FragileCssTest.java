package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class FragileCssTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void cssPosicionalSeRompeConCualquierCambioDeLayout() {
        // #region example
        page.locator("body > div:nth-child(2) > div:nth-child(4) > button").click();
        // #endregion
        assertThat(page.locator("#settings-state")).hasText("Cambios guardados");
    }
}
