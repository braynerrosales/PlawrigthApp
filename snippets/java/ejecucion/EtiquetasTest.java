package ejecucion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static support.Fixtures.fixture;

class EtiquetasTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    // #region example
    @Test
    @Tag("smoke") // [!mark]
    void exportarPedidos() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Exportar")).click();

        assertThat(page.getByRole(AriaRole.STATUS)).hasText("Exportación lista");
    }
    // #endregion
}
