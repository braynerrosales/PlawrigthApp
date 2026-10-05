using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;

namespace PlaywrightGuide.Snippets.ModeloMental;

public class WebElementExamples
{
    private IWebDriver driver = null!;

    [SetUp]
    public void AbrirPagina()
    {
        var options = new ChromeOptions();
        options.AddArgument("--headless=new");
        driver = new ChromeDriver(options);
        driver.Navigate().GoToUrl(Fixtures.Url("pedidos"));
    }

    [TearDown]
    public void CerrarNavegador() => driver.Quit();

    // #region example
    [Test]
    public void WebElementQuedaObsoleto()
    {
        var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
        var cargar = driver.FindElement(By.XPath("//button[text()='Cargar pedidos']"));
        var estado = driver.FindElement(By.CssSelector("[role='status']"));
        cargar.Click();
        wait.Until(_ => estado.Text == "3 pedidos");

        var primero = driver.FindElement(By.CssSelector("ul[aria-label='Pedidos'] li"));
        Assert.That(primero.Text, Is.EqualTo("PED-1001 · Pagado"));

        // La página reemplaza la lista: el elemento que se guardó ya no existe en el DOM.
        cargar.Click();
        wait.Until(_ => estado.Text == "3 pedidos");
        Assert.Throws<StaleElementReferenceException>(() => _ = primero.Text); // [!mark]
    }
    // #endregion
}
