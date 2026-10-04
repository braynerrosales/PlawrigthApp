package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class GetByTestIdTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void getByTestIdLocalizaPorUnAtributoDePruebas() {
        // #region example
        assertThat(page.getByTestId("cart-count")).hasText("0");
        assertThat(page.getByTestId("product-list").getByRole(AriaRole.LISTITEM)).hasCount(3);
        // #endregion
    }
}
