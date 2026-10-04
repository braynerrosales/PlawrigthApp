package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class JsValueTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void asignarElValorConJavascriptNoDisparaEventos() {
        page.getByLabel("Nombre").evaluate("el => el.value = 'Ana Pérez'");
        page.getByLabel("Correo electrónico").evaluate("el => el.value = 'ana@example.com'");
        page.getByLabel("Acepto los términos").check();

        // La app nunca recibió eventos input en los campos de texto: el botón sigue deshabilitado.
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Crear cuenta"))).isDisabled();
    }
    // #endregion
}
