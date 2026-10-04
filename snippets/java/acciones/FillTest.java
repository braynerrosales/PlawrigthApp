package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class FillTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void completarYLimpiarCampos() {
        Locator nombre = page.getByLabel("Nombre");

        nombre.fill("Ana Pérez");
        assertThat(nombre).hasValue("Ana Pérez");

        nombre.clear();
        assertThat(nombre).hasValue("");
    }
    // #endregion
}
