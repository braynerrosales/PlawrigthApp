package assertions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class EstadoTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    // #region example
    @Test
    void visibilidadYEstado() {
        Locator monitor = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Monitor 4K"));

        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Productos"))).isVisible();
        assertThat(page.getByText("Cambios guardados")).isHidden();
        assertThat(monitor.getByRole(AriaRole.BUTTON)).isDisabled();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Guardar"))).isEnabled();
    }
    // #endregion
}
