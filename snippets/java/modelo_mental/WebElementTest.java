package modelo_mental;

import java.time.Duration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.url;

class WebElementTest {
    WebDriver driver;

    @BeforeEach
    void abrirPagina() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
        driver.get(url("pedidos"));
    }

    @AfterEach
    void cerrarNavegador() {
        driver.quit();
    }

    // #region example
    @Test
    void webElementQuedaObsoleto() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cargar = driver.findElement(By.xpath("//button[text()='Cargar pedidos']"));
        By estado = By.cssSelector("[role='status']");
        cargar.click();
        wait.until(ExpectedConditions.textToBe(estado, "3 pedidos"));

        WebElement primero = driver.findElement(By.cssSelector("ul[aria-label='Pedidos'] li"));
        assertEquals("PED-1001 · Pagado", primero.getText());

        // La página reemplaza la lista: el elemento que se guardó ya no existe en el DOM.
        cargar.click();
        wait.until(ExpectedConditions.textToBe(estado, "3 pedidos"));
        assertThrows(StaleElementReferenceException.class, primero::getText); // [!mark]
    }
    // #endregion
}
