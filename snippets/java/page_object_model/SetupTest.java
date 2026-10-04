package page_object_model;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class SetupTest extends TestFixtures {
    // #region example
    RegistroPage registro;

    // JUnit ejecuta primero los @BeforeEach de la clase base: page ya existe aquí.
    @BeforeEach
    void crearPageObject() {
        page.setContent(fixture("acciones")); // En tu proyecto: page.navigate("/registro")
        registro = new RegistroPage(page);
    }

    @Test
    void registroDeUnaCuentaGratis() {
        registro.registrar(new DatosRegistro("Luis Gómez", "luis@example.com", "Chile", "Gratis"));

        assertThat(registro.confirmacion).hasText("Cuenta Gratis creada para Luis Gómez");
    }
    // #endregion
}
