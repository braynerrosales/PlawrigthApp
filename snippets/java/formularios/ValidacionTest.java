package formularios;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ValidacionTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("formularios"));
    }

    // #region example
    @Test
    void unCorreoInvalidoMuestraElErrorDeLaApp() {
        Locator correo = page.getByLabel("Correo electrónico");

        correo.fill("ana@correo");
        page.getByLabel("Fecha de entrega").fill("2026-10-04");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Enviar solicitud")).click();

        // El mensaje es de la app, no del navegador: es estable y se puede comprobar.
        assertThat(page.getByRole(AriaRole.ALERT)).hasText("Ingresa un correo válido");
        assertThat(correo).hasAttribute("aria-invalid", "true");
        assertThat(page.getByRole(AriaRole.STATUS)).isEmpty();
    }
    // #endregion
}
