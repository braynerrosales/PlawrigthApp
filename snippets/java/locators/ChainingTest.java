package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ChainingTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void encadenarLocatorsAcotaLaBusquedaAUnaRegion() {
        // #region example
        Locator productList = page.getByTestId("product-list");
        Locator mouseCard = productList.getByRole(AriaRole.LISTITEM)
            .filter(new Locator.FilterOptions().setHasText("Mouse inalámbrico"));

        mouseCard.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Agregar al carrito")).click();

        assertThat(page.getByTestId("cart-count")).hasText("1");
        // #endregion
    }
}
