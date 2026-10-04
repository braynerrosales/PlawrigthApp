package formularios;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class EnvioExitosoTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("formularios"));
    }

    // #region example
    @Test
    void enviarConEnterYComprobarElResultado() {
        Locator correo = page.getByLabel("Correo electrónico");
        Locator fecha = page.getByLabel("Fecha de entrega");

        fecha.fill("2026-10-04");
        correo.fill("ana@example.com");
        // Enter dentro de un campo envía el formulario, como lo haría una persona.
        correo.press("Enter");

        // El resultado visible y el estado posterior del formulario.
        assertThat(page.getByRole(AriaRole.STATUS)).hasText("Solicitud PED-1042 enviada");
        assertThat(page.getByRole(AriaRole.ALERT)).hasCount(0);
        assertThat(correo).isEmpty();
        assertThat(fecha).isEmpty();
    }
    // #endregion
}
