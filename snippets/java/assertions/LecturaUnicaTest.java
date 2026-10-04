package assertions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class LecturaUnicaTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    @Test
    void leerElTextoUnaVezFallaConDatosQueLleganTarde() {
        assertThrows(AssertionError.class, () -> {
            // #region example
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();
            String texto = page.getByRole(AriaRole.STATUS).textContent();
            assertEquals("3 pedidos", texto); // falla: leyó «Cargando…» y no vuelve a intentarlo
            // #endregion
        });
    }
}
