package tablas_y_listas;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class PaginacionTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("tablas-y-listas"));
    }

    // #region example
    @Test
    void buscarUnPedidoPaginaPorPagina() {
        Locator pedido = page.getByRole(AriaRole.ROW).filter(new Locator.FilterOptions().setHasText("PED-1011"));
        Locator siguiente = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Siguiente"));
        Locator indicador = page.getByRole(AriaRole.NAVIGATION, new Page.GetByRoleOptions().setName("Paginación"))
            .getByRole(AriaRole.STATUS);

        // Bucle acotado: si el pedido no aparece en 10 páginas, la prueba falla con un motivo claro.
        int pagina = 1;
        while (!pedido.isVisible()) {
            if (pagina == 10) {
                fail("PED-1011 no aparece en las primeras 10 páginas");
            }
            siguiente.click();
            // isVisible no espera: antes de mirar otra vez, se espera a que cargue la página nueva.
            assertThat(indicador).hasText(Pattern.compile("^Página " + (pagina + 1) + " de"));
            pagina++;
        }

        assertThat(pedido).containsText("Karen Sosa");
    }
    // #endregion
}
