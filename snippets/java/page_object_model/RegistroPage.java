package page_object_model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

// Page object de la página de práctica fixtures/acciones.html. Lo usan las pruebas del módulo.
// #region example
record DatosRegistro(String nombre, String correo, String pais, String plan) {}

public class RegistroPage {
    private final Page page;
    public final Locator nombre;
    public final Locator correo;
    public final Locator pais;
    public final Locator terminos;
    public final Locator crearCuenta;
    public final Locator confirmacion;

    public RegistroPage(Page page) {
        this.page = page;
        nombre = page.getByLabel("Nombre");
        correo = page.getByLabel("Correo electrónico");
        pais = page.getByLabel("País");
        terminos = page.getByLabel("Acepto los términos");
        crearCuenta = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Crear cuenta"));
        confirmacion = page.getByRole(AriaRole.STATUS);
    }

    public Locator plan(String nombre) {
        return page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName(nombre));
    }

    public void completar(DatosRegistro datos) {
        nombre.fill(datos.nombre());
        correo.fill(datos.correo());
        pais.selectOption(new SelectOption().setLabel(datos.pais()));
        plan(datos.plan()).check();
    }

    public void registrar(DatosRegistro datos) {
        completar(datos);
        terminos.check();
        crearCuenta.click();
    }
}
// #endregion
