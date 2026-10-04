package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class FilterTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void filterAcotaUnaListaPorTextoOPorContenido() {
        // #region example
        Locator keyboard = page.getByRole(AriaRole.LISTITEM)
            .filter(new Locator.FilterOptions().setHasText("Teclado mecánico"));
        keyboard.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Agregar al carrito")).click();
        assertThat(page.getByTestId("cart-count")).hasText("1");

        // setHas: el item debe contener otro Locator.
        Locator soldOut = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions()
            .setHas(page.getByText("Agotado", new Page.GetByTextOptions().setExact(true))));
        assertThat(soldOut.getByRole(AriaRole.HEADING)).hasText("Monitor 4K");
        // #endregion
    }
}
