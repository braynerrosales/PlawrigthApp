package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class GetByRoleTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void getByRoleLocalizaPorRolYNombreAccesible() {
        // #region example
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Correo electrónico")).fill("qa@example.com");
        page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Recordarme")).check();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Iniciar sesión")).click();

        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Productos").setLevel(2)))
            .isVisible();
        // #endregion
        assertThat(page.getByRole(AriaRole.STATUS)).hasText("Bienvenido, qa@example.com");
    }
}
