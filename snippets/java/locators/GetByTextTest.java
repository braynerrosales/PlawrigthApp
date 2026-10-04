package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class GetByTextTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void getByTextLocalizaContenidoVisibleSinRolInteractivo() {
        // #region example
        assertThat(page.getByText("Envío gratis en pedidos mayores a $50")).isVisible();

        // Por defecto: subcadena, sin distinguir mayúsculas, espacios normalizados.
        assertThat(page.getByText("envío gratis")).isVisible();

        // setExact(true) exige el texto completo y respeta mayúsculas.
        assertThat(page.getByText("Agotado", new Page.GetByTextOptions().setExact(true))).isVisible();
        // #endregion
    }
}
