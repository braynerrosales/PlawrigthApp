namespace PlaywrightGuide.Snippets.UploadYDownload;

public class UploadMemoriaExamples : FixtureTest
{
    protected override string Fixture => "upload-y-download";

    // #region example
    [Test]
    public async Task SubirVariosArchivosCreadosEnMemoriaYQuitarlos()
    {
        var adjuntos = Page.GetByLabel("Adjuntos");
        var archivos = Page.GetByRole(AriaRole.List, new() { Name = "Archivos seleccionados" }).GetByRole(AriaRole.Listitem);

        await adjuntos.SetInputFilesAsync(new[]
        {
            new FilePayload { Name = "notas.txt", MimeType = "text/plain", Buffer = System.Text.Encoding.UTF8.GetBytes("Entregar por la tarde") },
            new FilePayload { Name = "datos.csv", MimeType = "text/csv", Buffer = System.Text.Encoding.UTF8.GetBytes("id,total\n1,120.00\n") },
        });
        await Expect(archivos).ToHaveTextAsync(new[] { "notas.txt (21 bytes)", "datos.csv (18 bytes)" });

        // Un arreglo vacío deja el input sin archivos.
        await adjuntos.SetInputFilesAsync(Array.Empty<string>());
        await Expect(archivos).ToHaveCountAsync(0);
    }
    // #endregion
}
