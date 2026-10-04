package formularios;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class SinVerificarEnvioTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("formularios"));
    }

    @Test
    void enviarElFormularioSinComprobarElResultado() {
        // #region example
        page.getByLabel("Correo electrónico").fill("ana@correo");
        page.getByLabel("Fecha de entrega").fill("2026-10-04");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Enviar solicitud")).click();
        // Fin de la prueba: pasa en verde, aunque la app rechazó el correo.
        // #endregion

        // Fuera del ejemplo: demuestra que el envío en realidad falló.
        assertThat(page.getByRole(AriaRole.ALERT)).hasText("Ingresa un correo válido");
    }
}
