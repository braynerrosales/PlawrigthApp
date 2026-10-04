package api_y_network;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class BloquearRecursosTest extends TestFixtures {
    @Override
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions().setBaseURL(ApiPractica.url());
    }

    // #region example
    @Test
    void cargarLosPedidosSinImagenes() {
        page.route("**/*.{png,jpg,jpeg,webp}", route -> route.abort());

        page.navigate("/");

        assertThat(page.getByText("Imagen no disponible")).isVisible();
        assertThat(page.getByRole(AriaRole.ROW).filter(new Locator.FilterOptions().setHasText("Ana Torres"))).isVisible();
    }
    // #endregion
}
