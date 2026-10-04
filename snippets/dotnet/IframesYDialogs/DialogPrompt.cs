namespace PlaywrightGuide.Snippets.IframesYDialogs;

public class DialogPromptExamples : FixtureTest
{
    protected override string Fixture => "iframes-y-dialogs";

    // #region example
    [Test]
    public async Task ResponderAlPromptRenombraLaTarea()
    {
        var tareas = Page.GetByRole(AriaRole.List, new() { Name = "Tareas" });

        Page.Dialog += async (_, dialog) =>
        {
            // AcceptAsync con texto es la respuesta que escribiría la persona en el prompt.
            await dialog.AcceptAsync("Revisar reporte final");
        };
        await tareas
            .GetByRole(AriaRole.Listitem)
            .Filter(new() { HasText = "Revisar reporte" })
            .GetByRole(AriaRole.Button, new() { Name = "Renombrar" })
            .ClickAsync();

        await Expect(tareas.GetByRole(AriaRole.Listitem).First).ToContainTextAsync("Revisar reporte final");
    }
    // #endregion
}
