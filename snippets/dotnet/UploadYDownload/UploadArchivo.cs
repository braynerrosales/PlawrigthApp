using System.Text.RegularExpressions;

namespace PlaywrightGuide.Snippets.UploadYDownload;

public class UploadArchivoExamples : FixtureTest
{
    protected override string Fixture => "upload-y-download";

    // #region example
    [Test]
    public async Task SubirUnArchivoDesdeElDisco()
    {
        // Ruta absoluta desde la carpeta de salida: no depende de dónde se ejecute la prueba.
        var factura = Path.Combine(AppContext.BaseDirectory, "fixtures", "archivos", "factura.txt");

        await Page.GetByLabel("Adjuntos").SetInputFilesAsync(factura);

        var archivos = Page.GetByRole(AriaRole.List, new() { Name = "Archivos seleccionados" }).GetByRole(AriaRole.Listitem);
        await Expect(archivos).ToHaveTextAsync(new[] { new Regex(@"^factura\.txt \(\d+ bytes\)$") });
    }
    // #endregion
}
