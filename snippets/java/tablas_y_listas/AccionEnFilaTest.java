package tablas_y_listas;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class AccionEnFilaTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("tablas-y-listas"));
    }

    // #region example
    @Test
    void cancelarUnPedidoDesdeSuFila() {
        // La fila se identifica por un dato que el usuario reconoce, no por su posición.
        Locator fila = page.getByRole(AriaRole.ROW).filter(new Locator.FilterOptions().setHasText("PED-1003"));

        // Todas las filas tienen un botón "Cancelar": se busca dentro de la fila.
        fila.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Cancelar")).click();

        assertThat(fila).containsText("Cancelado");
        assertThat(fila.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Cancelar"))).isDisabled();
    }
    // #endregion
}
