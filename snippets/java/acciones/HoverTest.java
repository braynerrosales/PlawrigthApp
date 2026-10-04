package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class HoverTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void mostrarUnTooltip() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Más información")).hover();

        assertThat(page.getByRole(AriaRole.TOOLTIP)).hasText("Respondemos en menos de 24 horas");
    }
    // #endregion
}
