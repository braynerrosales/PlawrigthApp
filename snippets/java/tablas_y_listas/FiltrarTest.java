package tablas_y_listas;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class FiltrarTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("tablas-y-listas"));
    }

    // #region example
    @Test
    void filtrarYComprobarQueSoloQuedanLosPedidosEsperados() {
        Locator filas = page.getByRole(AriaRole.TABLE, new Page.GetByRoleOptions().setName("Pedidos"))
            .getByRole(AriaRole.ROW).filter(new Locator.FilterOptions().setHas(page.getByRole(AriaRole.CELL)));

        page.getByLabel("Filtrar pedidos").fill("Pendiente");

        // La app aplica el filtro 300 ms después: las aserciones reintentan hasta verlo.
        assertThat(filas).hasCount(3);
        assertThat(filas).hasText(new Pattern[] {
            Pattern.compile("PED-1003"), Pattern.compile("PED-1006"), Pattern.compile("PED-1009"),
        });
    }
    // #endregion
}
