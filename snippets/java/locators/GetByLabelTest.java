package locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class GetByLabelTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("locators"));
    }

    @Test
    void getByLabelLocalizaCamposPorSuEtiqueta() {
        // #region example
        page.getByLabel("Correo electrónico").fill("qa@example.com");
        page.getByLabel("Contraseña").fill("clave-de-prueba");

        assertThat(page.getByLabel("Contraseña")).hasValue("clave-de-prueba");
        // #endregion
    }
}
