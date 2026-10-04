package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class QaCaseTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    // #region example
    @Test
    void elClienteAgregaUnProductoDisponibleAlCarrito() {
        page.getByLabel("Correo electrónico").fill("qa@example.com");
        page.getByLabel("Contraseña").fill("clave-de-prueba");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Iniciar sesión")).click();
        assertThat(page.getByRole(AriaRole.STATUS)).hasText("Bienvenido, qa@example.com");

        Locator product = page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Mouse inalámbrico")); // [!mark]
        product.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Agregar al carrito")).click(); // [!mark]

        assertThat(page.getByTestId("cart-count")).hasText("1");
    }
    // #endregion
}
