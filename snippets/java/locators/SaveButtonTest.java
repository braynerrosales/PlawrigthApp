package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class SaveButtonTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void botonPorRolYNombreExpresaLaIntencion() {
        // #region example
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Guardar")).click();
        // #endregion
        assertThat(page.getByText("Cambios guardados")).isVisible();
    }
}
