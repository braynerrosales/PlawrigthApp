package iframes_y_dialogs;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class IframeTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("iframes-y-dialogs"));
    }

    // #region example
    @Test
    void elPagoSeCompletaDentroDelIframe() {
        FrameLocator pago = page.frameLocator("iframe[title=\"Pago con tarjeta\"]");

        pago.getByLabel("Número de tarjeta").fill("4111 1111 1111 1111");
        pago.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Pagar")).click();

        assertThat(pago.getByRole(AriaRole.STATUS)).hasText("Pago aprobado");
    }
    // #endregion
}
