package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class PressTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void buscarConEnter() {
        Locator buscador = page.getByRole(AriaRole.SEARCHBOX, new Page.GetByRoleOptions().setName("Buscar en la ayuda"));

        buscador.fill("facturas");
        buscador.press("Enter");

        assertThat(page.getByText("Resultados para «facturas»")).isVisible();
    }
    // #endregion
}
