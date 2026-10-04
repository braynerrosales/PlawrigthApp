package tablas_y_listas;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ListaConRetrasoTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("tablas-y-listas"));
    }

    // #region example
    @Test
    void esperarUnaListaQueSeCargaConRetraso() {
        Locator movimientos = page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName("Últimos movimientos"))
            .getByRole(AriaRole.LISTITEM);

        // Reintenta hasta que la lista tiene exactamente estos elementos, en este orden.
        assertThat(movimientos).hasText(new String[] {
            "PED-1003 pagado", "PED-1007 enviado", "PED-1001 entregado", "PED-1005 cancelado",
        });

        // Leer los textos solo cuando necesitas los datos, y después de la aserción: allTextContents no espera.
        List<String> textos = movimientos.allTextContents();
        assertEquals(List.of("PED-1005 cancelado"), textos.stream().filter(t -> t.endsWith("cancelado")).toList());
    }
    // #endregion
}
