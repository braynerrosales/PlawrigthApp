package trace_viewer_y_diagnostico;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import com.microsoft.playwright.assertions.LocatorAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class DebugACiegasTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    @Test
    void depurarSubiendoEsperasYTimeouts() {
        // #region example
        // "Falló una vez en CI": se agrega una espera, se sube el timeout y se imprime el HTML.
        page.waitForTimeout(1000);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos"))
            .click(new Locator.ClickOptions().setTimeout(60_000));
        System.out.println(page.getByRole(AriaRole.STATUS).textContent());
        assertThat(page.getByRole(AriaRole.STATUS))
            .hasText("3 pedidos", new LocatorAssertions.HasTextOptions().setTimeout(60_000));
        // #endregion
    }
}
