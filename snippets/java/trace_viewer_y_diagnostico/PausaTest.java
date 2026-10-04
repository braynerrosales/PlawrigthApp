package trace_viewer_y_diagnostico;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class PausaTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    @Test
    void marcaDondeDetenerseAlDepurar() {
        // #region example
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();

        // Descomenta solo mientras depuras y ejecuta en modo headed con PWDEBUG=1.
        // Nunca lo subas al repositorio: en CI la prueba quedaría detenida hasta el timeout.
        // page.pause();

        assertThat(page.getByRole(AriaRole.STATUS)).hasText("3 pedidos");
        // #endregion
    }
}
