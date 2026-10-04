package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class DblclickTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void renombrarConDobleClic() {
        page.getByText("Mi lista").dblclick();

        Locator nombre = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Nombre de la lista"));
        assertThat(nombre).isFocused();
        nombre.fill("Pruebas de regresión");
        assertThat(nombre).hasValue("Pruebas de regresión");
    }
    // #endregion
}
