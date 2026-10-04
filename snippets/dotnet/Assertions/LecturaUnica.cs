namespace PlaywrightGuide.Snippets.Assertions;

public class LecturaUnicaExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    [Test]
    public async Task LeerElTextoUnaVezDevuelveElValorDeEseInstante()
    {
        // #region example
        await Page.GetByRole(AriaRole.Button, new() { Name = "Cargar pedidos" }).ClickAsync();
        var texto = await Page.GetByRole(AriaRole.Status).TextContentAsync();
        // Assert.That(texto, Is.EqualTo("3 pedidos")) fallaría: leyó «Cargando…» y no vuelve a intentarlo.
        // #endregion
        Assert.That(texto, Is.EqualTo("Cargando…"));
    }
}
