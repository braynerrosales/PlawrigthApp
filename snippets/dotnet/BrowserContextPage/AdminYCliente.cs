namespace PlaywrightGuide.Snippets.BrowserContextPage;

public class AdminYClienteExamples : BrowserTest
{
    // #region example
    [Test]
    public async Task AdminYClienteNoCompartenSesion()
    {
        var adminContext = await Browser.NewContextAsync();
        var clienteContext = await Browser.NewContextAsync();

        await adminContext.AddCookiesAsync(new[]
        {
            new Cookie { Name = "sesion", Value = "admin-123", Url = "https://tienda-qa.example" },
        });

        Assert.That(await adminContext.CookiesAsync(), Has.Count.EqualTo(1));
        Assert.That(await clienteContext.CookiesAsync(), Is.Empty);

        await adminContext.CloseAsync();
        await clienteContext.CloseAsync();
    }
    // #endregion
}
