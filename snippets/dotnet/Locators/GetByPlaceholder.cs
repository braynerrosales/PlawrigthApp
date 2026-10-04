namespace PlaywrightGuide.Snippets.Locators;

public class GetByPlaceholderExamples : LocatorsTest
{
    [Test]
    public async Task LocalizaCampoSinEtiqueta()
    {
        // #region example
        var search = Page.GetByPlaceholder("Buscar productos");
        await search.FillAsync("teclado");

        await Expect(search).ToHaveValueAsync("teclado");
        // #endregion
    }
}
