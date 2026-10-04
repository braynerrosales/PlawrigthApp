package trace_viewer_y_diagnostico;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import com.microsoft.playwright.assertions.LocatorAssertions;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class LeerElErrorTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    @Test
    void unaAsercionQueFallaExplicaQueEsperabaYQueEncontro() {
        AssertionError error = assertThrows(AssertionError.class, () -> {
            // #region example
            // Falta un paso: nadie hizo clic en "Cargar pedidos", así que la lista sigue vacía.
            assertThat(page.getByRole(AriaRole.LIST, new Page.GetByRoleOptions().setName("Pedidos")).getByRole(AriaRole.LISTITEM))
                .hasCount(3, new LocatorAssertions.HasCountOptions().setTimeout(2000));
            // #endregion
        });
        System.out.println(error.getMessage());
        assertTrue(error.getMessage().contains("Locator expected to have count"));
    }
}
