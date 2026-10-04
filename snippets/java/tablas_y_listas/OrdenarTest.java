package tablas_y_listas;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class OrdenarTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("tablas-y-listas"));
    }

    // #region example
    @Test
    void ordenarPorClienteYComprobarElOrden() {
        Locator tabla = page.getByRole(AriaRole.TABLE, new Page.GetByRoleOptions().setName("Pedidos"));
        // Solo las filas con celdas de datos: la fila del encabezado tiene columnheader, no cell.
        Locator filas = tabla.getByRole(AriaRole.ROW).filter(new Locator.FilterOptions().setHas(page.getByRole(AriaRole.CELL)));

        tabla.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Cliente")).click();

        assertThat(tabla.getByRole(AriaRole.COLUMNHEADER, new Locator.GetByRoleOptions().setName("Cliente")))
            .hasAttribute("aria-sort", "ascending");
        // Un arreglo comprueba la cantidad de filas y el texto de cada una, en orden.
        assertThat(filas).hasText(new Pattern[] {
            Pattern.compile("Ana Torres"),
            Pattern.compile("Bruno Ríos"),
            Pattern.compile("Carla Gómez"),
            Pattern.compile("Diego Ruiz"),
            Pattern.compile("Elena Mora"),
        });
    }
    // #endregion
}
