package browser_context_page;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class AdminYClienteTest extends TestFixtures {
    // #region example
    @Test
    void adminYClienteNoCompartenSesion() {
        BrowserContext adminContext = browser.newContext();
        BrowserContext clienteContext = browser.newContext();

        adminContext.addCookies(List.of(
            new Cookie("sesion", "admin-123").setUrl("https://tienda-qa.example")));

        assertEquals(1, adminContext.cookies().size());
        assertEquals(0, clienteContext.cookies().size());

        adminContext.close();
        clienteContext.close();
    }
    // #endregion
}
