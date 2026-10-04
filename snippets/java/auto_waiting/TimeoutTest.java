package auto_waiting;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class TimeoutTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    @Test
    void unaAccionQueNuncaPuedeEjecutarseTerminaEnTimeout() {
        TimeoutError error = assertThrows(TimeoutError.class, () -> {
            // #region example
            // "Pagar" nunca se habilita: Playwright espera hasta el timeout y explica qué faltó.
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Pagar"))
                .click(new Locator.ClickOptions().setTimeout(2000));
            // #endregion
        });
        assertTrue(error.getMessage().contains("Timeout 2000ms exceeded"));
    }
}
