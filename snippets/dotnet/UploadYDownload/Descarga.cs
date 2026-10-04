namespace PlaywrightGuide.Snippets.UploadYDownload;

public class DescargaExamples : FixtureTest
{
    protected override string Fixture => "upload-y-download";

    // #region example
    [Test]
    public async Task DescargarElCsvYComprobarSuContenido()
    {
        // Empieza a esperar la descarga, hace el clic y devuelve la descarga.
        var download = await Page.RunAndWaitForDownloadAsync(async () =>
        {
            await Page.GetByRole(AriaRole.Button, new() { Name = "Exportar CSV" }).ClickAsync();
        });

        Assert.That(download.SuggestedFilename, Is.EqualTo("pedidos.csv"));

        // PathAsync espera a que termine la descarga; el archivo se borra al cerrar el contexto.
        var csv = await File.ReadAllTextAsync(await download.PathAsync());
        Assert.That(csv.Split('\n')[0], Is.EqualTo("id,cliente,total"));
        Assert.That(csv, Does.Contain("1,Ana Pérez,120.00"));
    }
    // #endregion
}
