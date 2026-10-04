package tablas_y_listas;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class PorPosicionTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("tablas-y-listas"));
    }

    @Test
    void leerUnaCeldaPorSuPosicion() {
        // #region example
        // "Fila 3, columna 2": hoy es el cliente de PED-1003.
        Locator cliente = page.locator("tbody tr:nth-child(3) td:nth-child(2)");
        assertThat(cliente).hasText("Elena Mora");
        // #endregion

        // Fuera del ejemplo: al ordenar por cliente, la misma posición es otro pedido.
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cliente")).click();
        assertThat(cliente).hasText("Carla Gómez");
    }
}
