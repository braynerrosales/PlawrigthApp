using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

namespace PlaywrightGuide.Snippets.Migracion;

// El «antes» de la migración: Selenium WebDriver con NUnit.

// #region example
public class PedidosConSelenium
{
    private IWebDriver driver = null!;

    [SetUp]
    public void AbrirNavegador()
    {
        var options = new ChromeOptions();
        options.AddArgument("--headless=new");
        driver = new ChromeDriver(options);
    }

    [TearDown]
    public void CerrarNavegador() => driver.Quit();

    [Test]
    public void ExportarPedidos()
    {
        driver.Navigate().GoToUrl(Fixtures.Url("pedidos"));
        driver.FindElement(By.XPath("//button[text()='Cargar pedidos']")).Click();

        // Exportar se habilita cuando llegan los datos. Un clic sobre un botón deshabilitado no da error: no hace nada.
        var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
        var exportar = driver.FindElement(By.XPath("//button[text()='Exportar']"));
        wait.Until(_ => exportar.Enabled);
        exportar.Click();

        var estado = driver.FindElement(By.CssSelector("[role='status']"));
        wait.Until(_ => estado.Text == "Exportación lista");
        Assert.That(driver.FindElements(By.CssSelector("ul[aria-label='Pedidos'] li")), Has.Count.EqualTo(3));
    }
}
// #endregion
