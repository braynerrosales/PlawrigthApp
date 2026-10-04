namespace PlaywrightGuide.Snippets.UploadYDownload;

public class FileChooserExamples : FixtureTest
{
    protected override string Fixture => "upload-y-download";

    // #region example
    [Test]
    public async Task SubirUnaFotoConUnBotonPersonalizado()
    {
        // Ejecuta el clic y espera el diálogo de archivos que ese clic abre.
        var fileChooser = await Page.RunAndWaitForFileChooserAsync(async () =>
        {
            await Page.GetByRole(AriaRole.Button, new() { Name = "Subir foto" }).ClickAsync();
        });

        await fileChooser.SetFilesAsync(new FilePayload
        {
            Name = "perfil.png",
            MimeType = "image/png",
            Buffer = System.Text.Encoding.UTF8.GetBytes("foto"),
        });

        await Expect(Page.GetByText("Foto: perfil.png")).ToBeVisibleAsync();
    }
    // #endregion
}
