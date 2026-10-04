package support;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

/**
 * Clase base de los snippets: el patrón de la documentación de Playwright para JUnit
 * (playwright.dev/java/docs/test-runners). Un navegador por clase; contexto y página nuevos por prueba.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestFixtures {
    // Compartidos por todas las pruebas de la clase.
    protected Playwright playwright;
    protected Browser browser;

    // Nuevos en cada prueba.
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    void closeBrowser() {
        playwright.close();
    }

    /** Opciones del contexto de cada prueba; una clase las cambia para fijar baseURL o storageState. */
    protected Browser.NewContextOptions contextOptions() {
        return new Browser.NewContextOptions();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext(contextOptions());
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}
