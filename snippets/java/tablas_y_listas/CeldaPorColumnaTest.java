package tablas_y_listas;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class CeldaPorColumnaTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("tablas-y-listas"));
    }

    // #region example
    @Test
    void leerElEstadoDeUnPedidoPorElNombreDeLaColumna() {
        Locator tabla = page.getByRole(AriaRole.TABLE, new Page.GetByRoleOptions().setName("Pedidos"));

        // El índice sale del encabezado: si se agrega o se mueve una columna, se recalcula.
        List<String> encabezados = tabla.getByRole(AriaRole.COLUMNHEADER).allTextContents();
        int columnaEstado = encabezados.indexOf("Estado");

        // setHas + setExact: la fila cuya celda es exactamente PED-1003.
        Locator fila = tabla.getByRole(AriaRole.ROW).filter(new Locator.FilterOptions()
            .setHas(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("PED-1003").setExact(true))));
        assertThat(fila.getByRole(AriaRole.CELL).nth(columnaEstado)).hasText("Pendiente");
    }
    // #endregion
}
