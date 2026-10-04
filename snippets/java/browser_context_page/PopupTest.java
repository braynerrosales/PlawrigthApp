package browser_context_page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class PopupTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent("<button onclick=\"window.open('').document.write('<h1>Ayuda</h1>')\">Abrir ayuda</button>");
    }

    // #region example
    @Test
    void laAyudaSeAbreEnUnaVentanaNueva() {
        Page popup = page.waitForPopup(() -> {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Abrir ayuda")).click();
        });

        assertThat(popup.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Ayuda"))).isVisible();

        popup.close();
        assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Abrir ayuda"))).isVisible();
    }
    // #endregion
}
