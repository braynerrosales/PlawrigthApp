namespace PlaywrightGuide.Snippets.Acciones;

public class DragToExamples : FixtureTest
{
    protected override string Fixture => "acciones";

    // #region example
    [Test]
    public async Task MoverUnaTareaAHecho()
    {
        var tarea = Page.GetByRole(AriaRole.List, new() { Name = "Pendiente" }).GetByText("Revisar reporte");
        var hecho = Page.GetByRole(AriaRole.List, new() { Name = "Hecho" });

        await tarea.DragToAsync(hecho);

        await Expect(hecho.GetByRole(AriaRole.Listitem)).ToHaveTextAsync(new[] { "Revisar reporte" });
    }
    // #endregion
}
