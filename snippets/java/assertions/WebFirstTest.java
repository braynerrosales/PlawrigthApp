package assertions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class WebFirstTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("pedidos"));
    }

    // #region example
    @Test
    void laAsercionEsperaAQueLleguenLosDatos() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();

        assertThat(page.getByRole(AriaRole.STATUS)).hasText("3 pedidos");
    }
    // #endregion
}
