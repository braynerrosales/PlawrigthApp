package assertions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ListasTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    // #region example
    @Test
    void cantidadYContenidoDeUnaLista() {
        Locator productos = page.getByTestId("product-list").getByRole(AriaRole.LISTITEM);

        assertThat(productos).hasCount(3);
        assertThat(productos.getByRole(AriaRole.HEADING))
            .hasText(new String[] {"Teclado mecánico", "Mouse inalámbrico", "Monitor 4K"});
    }
    // #endregion
}
