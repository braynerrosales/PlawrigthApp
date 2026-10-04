package tablas_y_listas;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ConteoInmediatoTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("tablas-y-listas"));
    }

    @Test
    void contarUnaVezYRecorrerConIndicesMientrasLaListaCarga() {
        Locator movimientos = page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName("Últimos movimientos"))
            .getByRole(AriaRole.LISTITEM);

        // #region example
        // count() no espera: la lista todavía está cargando y devuelve 0.
        int total = movimientos.count();
        for (int i = 0; i < total; i++) {
            assertThat(movimientos.nth(i)).containsText("PED-");
        }
        // Fin de la prueba: pasa en verde sin haber comprobado ningún movimiento.
        // #endregion

        // Fuera del ejemplo: demuestra que el bucle no recorrió nada y que la lista sí tenía datos.
        assertEquals(0, total);
        assertThat(movimientos).hasCount(4);
    }
}
