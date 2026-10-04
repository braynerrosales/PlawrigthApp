namespace PlaywrightGuide.Snippets.BrowserContextPage;

public class PopupExamples : PageTest
{
    [SetUp]
    public Task LoadFixture() => Page.SetContentAsync(
        """<button onclick="window.open('').document.write('<h1>Ayuda</h1>')">Abrir ayuda</button>""");

    // #region example
    [Test]
    public async Task LaAyudaSeAbreEnUnaVentanaNueva()
    {
        var popup = await Page.RunAndWaitForPopupAsync(async () =>
        {
            await Page.GetByRole(AriaRole.Button, new() { Name = "Abrir ayuda" }).ClickAsync();
        });

        await Expect(popup.GetByRole(AriaRole.Heading, new() { Name = "Ayuda" })).ToBeVisibleAsync();

        await popup.CloseAsync();
        await Expect(Page.GetByRole(AriaRole.Button, new() { Name = "Abrir ayuda" })).ToBeVisibleAsync();
    }
    // #endregion
}
