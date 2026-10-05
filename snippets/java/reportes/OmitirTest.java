package reportes;

import org.junit.jupiter.api.*;
import support.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static support.Fixtures.fixture;

class OmitirTest extends TestFixtures {
    // #region example
    @Test
    void exportarLaPaginaAPdf() {
        assumeTrue(browser.browserType().name().equals("chromium"), "page.pdf() solo funciona en Chromium"); // [!mark]

        page.setContent(fixture("pedidos"));
        byte[] pdf = page.pdf();

        assertEquals("%PDF", new String(pdf, 0, 4));
    }
    // #endregion
}
