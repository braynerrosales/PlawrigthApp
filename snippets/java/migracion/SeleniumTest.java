package migracion;

import java.time.Duration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;
import static support.Fixtures.url;

// El «antes» de la migración: Selenium WebDriver con JUnit 5.

// #region example
class SeleniumTest {
    WebDriver driver;

    @BeforeEach
    void abrirNavegador() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
    }

    @AfterEach
    void cerrarNavegador() {
        driver.quit();
    }

    @Test
    void exportarPedidos() {
        driver.get(url("pedidos"));
        driver.findElement(By.xpath("//button[text()='Cargar pedidos']")).click();

        // Exportar se habilita cuando llegan los datos. Un clic sobre un botón deshabilitado no da error: no hace nada.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement exportar = driver.findElement(By.xpath("//button[text()='Exportar']"));
        wait.until(ExpectedConditions.elementToBeClickable(exportar)).click();

        By estado = By.cssSelector("[role='status']");
        wait.until(ExpectedConditions.textToBe(estado, "Exportación lista"));
        assertEquals(3, driver.findElements(By.cssSelector("ul[aria-label='Pedidos'] li")).size());
    }
}
// #endregion
