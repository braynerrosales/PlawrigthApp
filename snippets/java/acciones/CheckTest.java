package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class CheckTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void marcarCheckboxesYRadios() {
        page.getByLabel("Acepto los términos").check();
        page.getByLabel("Recibir novedades").uncheck();
        page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Pro")).check();

        assertThat(page.getByLabel("Acepto los términos")).isChecked();
        assertThat(page.getByLabel("Recibir novedades")).not().isChecked();
        assertThat(page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Gratis"))).not().isChecked();
    }
    // #endregion
}
