package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class LazyLocatorTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void unLocatorSeResuelveDeNuevoEnCadaUso() {
        // #region example
        // Describe CÓMO encontrar el elemento; todavía no busca nada en la página.
        Locator status = page.getByRole(AriaRole.STATUS);
        assertThat(status).isEmpty();

        page.getByLabel("Correo electrónico").fill("qa@example.com");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Iniciar sesión")).click();

        // La app reemplazó el nodo; el mismo Locator encuentra el nuevo.
        assertThat(status).hasText("Bienvenido, qa@example.com");
        // #endregion
    }
}
