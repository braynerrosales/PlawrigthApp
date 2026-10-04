namespace PlaywrightGuide.Snippets.Formularios;

public class ReadonlyDeshabilitadoExamples : FixtureTest
{
    protected override string Fixture => "formularios";

    // #region example
    [Test]
    public async Task CamposDeSoloLecturaYDeshabilitados()
    {
        var pedido = Page.GetByLabel("Número de pedido");
        var codigo = Page.GetByLabel("Código de descuento");

        // readonly: se ve y se envía, pero no se puede editar.
        await Expect(pedido).ToHaveValueAsync("PED-1042");
        await Expect(pedido).Not.ToBeEditableAsync();

        // disabled: la app lo habilita solo cuando marcas la casilla.
        await Expect(codigo).ToBeDisabledAsync();
        await Page.GetByLabel("Tengo un cupón").CheckAsync();
        await Expect(codigo).ToBeEnabledAsync();
        await codigo.FillAsync("QA10");
        await Expect(codigo).ToHaveValueAsync("QA10");
    }
    // #endregion
}
