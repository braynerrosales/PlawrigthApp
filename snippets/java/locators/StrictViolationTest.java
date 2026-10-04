package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class StrictViolationTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void unaAccionSobreUnLocatorAmbiguoFallaPorStrictness() {
        PlaywrightException error = assertThrows(PlaywrightException.class, () -> {
            // #region example
            // Hay 3 botones con ese nombre: Playwright se niega a adivinar.
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Agregar al carrito")).click();
            // #endregion
        });
        assertTrue(error.getMessage().contains("strict mode violation"));
    }
}
