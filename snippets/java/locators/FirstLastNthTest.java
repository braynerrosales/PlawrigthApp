package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class FirstLastNthTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void firstLastYNthEligenPorPosicion() {
        // #region example
        Locator addButtons = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Agregar al carrito"));
        assertThat(addButtons).hasCount(3);

        addButtons.first().click(); // índice 0
        addButtons.nth(1).click(); // índice 1: nth empieza en cero
        assertThat(addButtons.last()).isDisabled();
        // #endregion
        assertThat(page.getByTestId("cart-count")).hasText("2");
    }
}
