package auto_waiting;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class ForceTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    @Test
    void forceHaceClicAunqueElBotonEsteDeshabilitado() {
        // #region example
        // "Pagar" está deshabilitado. setForce se salta las comprobaciones: el clic "pasa" y no hace nada.
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Pagar"))
            .click(new Locator.ClickOptions().setForce(true));
        // #endregion
        assertThat(page.getByRole(AriaRole.STATUS)).isEmpty();
    }
}
