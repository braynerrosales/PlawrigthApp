package formularios;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class FechaTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("formularios"));
    }

    // #region example
    @Test
    void completarUnCampoDeFecha() {
        Locator fecha = page.getByLabel("Fecha de entrega");

        // Siempre en formato ISO (yyyy-mm-dd), sin importar cómo lo muestre el navegador.
        fecha.fill("2026-10-04");

        assertThat(fecha).hasValue("2026-10-04");
        assertThat(page.getByText("Entrega: 04/10/2026")).isVisible();
    }
    // #endregion
}
