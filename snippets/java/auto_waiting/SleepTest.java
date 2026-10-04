package auto_waiting;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class SleepTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    // #region example
    @Test
    void esperaFijaAntesDeActuar() {
        page.waitForTimeout(1000); // "por si el aviso no se fue"
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();
        page.waitForTimeout(2000); // "por si los datos tardan"

        assertThat(page.getByRole(AriaRole.LISTITEM)).hasCount(3);
    }
    // #endregion
}
