namespace PlaywrightGuide.Snippets.Locators;

public class GetByTextExamples : LocatorsTest
{
    [Test]
    public async Task LocalizaContenidoVisible()
    {
        // #region example
        await Expect(Page.GetByText("Envío gratis en pedidos mayores a $50")).ToBeVisibleAsync();

        // Por defecto: subcadena, sin distinguir mayúsculas, espacios normalizados.
        await Expect(Page.GetByText("envío gratis")).ToBeVisibleAsync();

        // Exact = true exige el texto completo y respeta mayúsculas.
        await Expect(Page.GetByText("Agotado", new() { Exact = true })).ToBeVisibleAsync();
        // #endregion
    }
}
