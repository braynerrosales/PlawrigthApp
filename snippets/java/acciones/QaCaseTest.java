package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class QaCaseTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void registroDeUnaCuentaPro() {
        page.getByLabel("Nombre").fill("Ana Pérez");
        page.getByLabel("Correo electrónico").fill("ana@example.com");
        page.getByLabel("País").selectOption(new SelectOption().setLabel("Colombia"));
        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Pro")).check();
        page.getByLabel("Acepto los términos").check();

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Crear cuenta")).click();

        assertThat(page.getByRole(AriaRole.STATUS)).hasText("Cuenta Pro creada para Ana Pérez");
    }
    // #endregion
}
