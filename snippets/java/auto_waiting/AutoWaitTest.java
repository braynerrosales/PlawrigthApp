package auto_waiting;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class AutoWaitTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    // #region example
    @Test
    void exportarCuandoLosDatosEstanListos() {
        // Al empezar, un aviso tapa la página y Exportar está deshabilitado hasta que llegan los datos.
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Exportar")).click();

        assertThat(page.getByRole(AriaRole.STATUS)).hasText("Exportación lista");
    }
    // #endregion
}
