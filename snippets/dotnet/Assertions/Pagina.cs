namespace PlaywrightGuide.Snippets.Assertions;

public class PaginaExamples : FixtureTest
{
    protected override string Fixture => "locators";

    // #region example
    [Test]
    public async Task TituloDeLaPagina()
    {
        await Expect(Page).ToHaveTitleAsync("Tienda QA");
    }
    // #endregion
}
