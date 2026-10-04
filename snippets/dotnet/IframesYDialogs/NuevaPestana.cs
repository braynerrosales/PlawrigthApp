namespace PlaywrightGuide.Snippets.IframesYDialogs;

public class NuevaPestanaExamples : FixtureTest
{
    protected override string Fixture => "iframes-y-dialogs";

    // #region example
    [Test]
    public async Task LosTerminosSeLeenEnOtraPestanaYSeAceptanEnLaOriginal()
    {
        var terminos = await Page.RunAndWaitForPopupAsync(async () =>
        {
            await Page.GetByRole(AriaRole.Button, new() { Name = "Ver términos y condiciones" }).ClickAsync();
        });

        // Las dos pestañas están abiertas y se usan a la vez, sin «cambiar» a ninguna.
        await Expect(terminos).ToHaveTitleAsync("Términos y condiciones");
        await Expect(terminos.GetByText("Versión vigente: octubre de 2026")).ToBeVisibleAsync();
        await Page.GetByLabel("Acepto los términos y condiciones").CheckAsync();
        Assert.That(Context.Pages, Has.Count.EqualTo(2));

        await terminos.CloseAsync();
        Assert.That(Context.Pages, Has.Count.EqualTo(1));
        await Expect(Page.GetByLabel("Acepto los términos y condiciones")).ToBeCheckedAsync();
    }
    // #endregion
}
