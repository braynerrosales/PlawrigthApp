package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class PressSequentiallyTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void autocompletarTeclaPorTecla() {
        // El autocompletado escucha eventos de teclado; fill() no los genera.
        page.getByLabel("Ciudad").pressSequentially("Bue");

        Locator sugerencias = page.getByRole(AriaRole.LISTBOX, new Page.GetByRoleOptions().setName("Sugerencias"));
        assertThat(sugerencias.getByRole(AriaRole.OPTION)).hasText(new String[] {"Buenos Aires"});
    }
    // #endregion
}
