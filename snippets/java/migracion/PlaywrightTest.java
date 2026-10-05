package migracion;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static support.Fixtures.url;

// #region example
class PlaywrightTest extends TestFixtures {
    @Test
    void exportarPedidos() {
        page.navigate(url("pedidos"));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cargar pedidos")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Exportar")).click();

        assertThat(page.getByRole(AriaRole.STATUS)).hasText("Exportación lista");
        assertThat(page.getByRole(AriaRole.LISTITEM)).hasCount(3);
    }
}
// #endregion
