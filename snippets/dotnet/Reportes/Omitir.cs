using System.Text;

namespace PlaywrightGuide.Snippets.Reportes;

public class OmitirExamples : FixtureTest
{
    protected override string Fixture => "pedidos";

    // #region example
    [Test]
    public async Task ExportarLaPaginaAPdf()
    {
        if (BrowserName != "chromium") Assert.Ignore("PdfAsync() solo funciona en Chromium"); // [!mark]

        var pdf = await Page.PdfAsync();

        Assert.That(Encoding.ASCII.GetString(pdf, 0, 4), Is.EqualTo("%PDF"));
    }
    // #endregion
}
