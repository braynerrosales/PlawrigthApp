package tablas_y_listas;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class FilaPorContenidoTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("tablas-y-listas"));
    }

    // #region example
    @Test
    void laFilaPorSuContenidoSigueSiendoLaMismaAlOrdenar() {
        Locator pedido = page.getByRole(AriaRole.ROW).filter(new Locator.FilterOptions().setHasText("PED-1003"));
        assertThat(pedido).containsText("Elena Mora");

        // La tabla se ordena y se vuelve a dibujar: la fila cambia de posición.
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cliente")).click();

        // El locator se resuelve otra vez y encuentra la misma fila de datos.
        assertThat(pedido).containsText("Elena Mora");
    }
    // #endregion
}
