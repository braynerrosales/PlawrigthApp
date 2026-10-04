package assertions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class TextoYValorTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    // #region example
    @Test
    void textoValorYAtributos() {
        assertThat(page.getByTestId("cart-count")).hasText("0");
        assertThat(page.getByText("Envío gratis")).containsText("mayores a $50");

        Locator buscador = page.getByPlaceholder("Buscar productos");
        assertThat(buscador).hasAttribute("type", "search");
        buscador.fill("teclado");
        assertThat(buscador).hasValue("teclado");
    }
    // #endregion
}
