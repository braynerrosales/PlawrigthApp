package acciones;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class SelectOptionTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    @Test
    void elegirUnaOpcionPorSuTexto() {
        Locator pais = page.getByLabel("País");

        pais.selectOption(new SelectOption().setLabel("Chile"));

        assertThat(pais).hasValue("cl");
    }
    // #endregion
}
