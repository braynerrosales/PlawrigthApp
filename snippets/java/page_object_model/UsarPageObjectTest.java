package page_object_model;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.*;
import support.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.fixture;

class UsarPageObjectTest extends TestFixtures {
    @BeforeEach
    void cargarPagina() {
        page.setContent(fixture("acciones"));
    }

    // #region example
    static final DatosRegistro ANA = new DatosRegistro("Ana Pérez", "ana@example.com", "Colombia", "Pro");

    @Test
    void registroDeUnaCuentaPro() {
        RegistroPage registro = new RegistroPage(page);

        registro.registrar(ANA);

        assertThat(registro.confirmacion).hasText("Cuenta Pro creada para Ana Pérez");
    }

    @Test
    void sinAceptarLosTerminosNoSePuedeCrearLaCuenta() {
        RegistroPage registro = new RegistroPage(page);

        registro.completar(ANA);

        assertThat(registro.crearCuenta).isDisabled();
    }
    // #endregion
}
