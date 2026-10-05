package modelo_mental;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static support.Fixtures.url;

class LocatorTest extends TestFixtures {
    @BeforeEach
    void abrirPagina() {
        page.navigate(url("pedidos"));
    }

    // #region example
    @Test
    void locatorSeVuelveABuscar() {
        Locator cargar = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos"));
        Locator estado = page.getByRole(AriaRole.STATUS);
        cargar.click();
        assertThat(estado).hasText("3 pedidos");

        Locator primero = page.getByRole(AriaRole.LISTITEM).first();
        assertThat(primero).hasText("PED-1001 · Pagado");

        // La página reemplaza la lista: el Locator encuentra el elemento nuevo.
        cargar.click();
        assertThat(estado).hasText("3 pedidos");
        assertThat(primero).hasText("PED-1001 · Pagado"); // [!mark]
    }
    // #endregion
}
