package page_object_model;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class LocatorsDuplicadosTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void registroDeUnaCuentaGratis() {
        page.getByLabel("Nombre").fill("Ana Pérez");
        page.getByLabel("Correo electrónico").fill("ana@example.com");
        page.getByLabel("Acepto los términos").check();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Crear cuenta")).click();

        assertThat(page.getByRole(AriaRole.STATUS)).hasText("Cuenta Gratis creada para Ana Pérez");
    }

    @Test
    void sinAceptarLosTerminosNoSePuedeCrearLaCuenta() {
        page.getByLabel("Nombre").fill("Ana Pérez");
        page.getByLabel("Correo electrónico").fill("ana@example.com");

        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Crear cuenta"))).isDisabled();
    }
    // #endregion
}
