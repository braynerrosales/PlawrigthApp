package iframes_y_dialogs;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class IframeSinFrameTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("iframes-y-dialogs"));
    }

    @Test
    void buscarElCampoDesdeLaPaginaPrincipalNuncaLoEncuentra() {
        TimeoutError error = assertThrows(TimeoutError.class, () -> {
            // #region example
            // El campo está dentro del iframe: desde `page` no existe, así que la espera nunca termina.
            page.getByLabel("Número de tarjeta").fill("4111 1111 1111 1111", new Locator.FillOptions().setTimeout(2000));
            // #endregion
        });
        assertTrue(error.getMessage().contains("Timeout 2000ms exceeded"));
    }
}
