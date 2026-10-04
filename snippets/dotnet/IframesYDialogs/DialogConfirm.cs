namespace PlaywrightGuide.Snippets.IframesYDialogs;

public class DialogConfirmExamples : FixtureTest
{
    protected override string Fixture => "iframes-y-dialogs";

    // #region example
    [Test]
    public async Task AceptarElConfirmEliminaLaTarea()
    {
        var tarea = Page.GetByRole(AriaRole.Listitem).Filter(new() { HasText = "Revisar reporte" });

        // El handler se registra ANTES de la acción que abre el diálogo.
        string? mensaje = null;
        Page.Dialog += async (_, dialog) =>
        {
            mensaje = dialog.Message;
            await dialog.AcceptAsync();
        };
        await tarea.GetByRole(AriaRole.Button, new() { Name = "Eliminar" }).ClickAsync();

        await Expect(tarea).ToBeHiddenAsync();
        Assert.That(mensaje, Is.EqualTo("¿Eliminar «Revisar reporte»?"));
    }
    // #endregion
}
