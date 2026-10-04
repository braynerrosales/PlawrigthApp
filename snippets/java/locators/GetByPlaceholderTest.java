package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class GetByPlaceholderTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void getByPlaceholderLocalizaUnCampoSinEtiqueta() {
        // #region example
        Locator search = page.getByPlaceholder("Buscar productos");
        search.fill("teclado");

        assertThat(search).hasValue("teclado");
        // #endregion
    }
}
