package navegacion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class UrlCompletaTest extends TiendaTest {
    // #region example
    @Test
    void verElCatalogo() {
        page.navigate("https://tienda.test/productos");
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Productos"))).isVisible();
    }

    @Test
    void abrirElInicioDeSesion() {
        // La misma URL completa, repetida en cada prueba: cambiar de ambiente obliga a editarlas todas.
        page.navigate("https://tienda.test/login");
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Iniciar sesión"))).isVisible();
    }
    // #endregion
}
