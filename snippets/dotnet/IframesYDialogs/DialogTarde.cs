namespace PlaywrightGuide.Snippets.IframesYDialogs;

public class DialogTardeExamples : FixtureTest
{
    protected override string Fixture => "iframes-y-dialogs";

    // #region example
    [Test]
    public async Task RegistrarElHandlerDespuesDelClicLlegaTarde()
    {
        var tarea = Page.GetByRole(AriaRole.Listitem).Filter(new() { HasText = "Revisar reporte" });

        await tarea.GetByRole(AriaRole.Button, new() { Name = "Eliminar" }).ClickAsync();
        // Demasiado tarde: sin listener, Playwright ya descartó el confirm (confirm devolvió false).
        Page.Dialog += async (_, dialog) => await dialog.AcceptAsync();

        // La tarea no se eliminó.
        await Expect(tarea).ToBeVisibleAsync();
    }
    // #endregion
}
