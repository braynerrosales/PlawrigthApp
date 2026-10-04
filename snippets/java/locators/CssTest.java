package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class CssTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void cssEstableIdsYClasesQueElEquipoControla() {
        // #region example
        Locator loginForm = page.locator("#login-form");
        loginForm.getByLabel("Correo electrónico").fill("qa@example.com");

        assertThat(page.locator("li.product-card")).hasCount(3);
        // #endregion
        assertThat(loginForm.getByLabel("Correo electrónico")).hasValue("qa@example.com");
    }
}
