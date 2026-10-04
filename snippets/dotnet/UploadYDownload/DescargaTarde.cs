namespace PlaywrightGuide.Snippets.UploadYDownload;

public class DescargaTardeExamples : FixtureTest
{
    protected override string Fixture => "upload-y-download";

    [Test]
    public void EsperarLaDescargaDespuesDelClicLlegaTarde()
    {
        var error = Assert.CatchAsync<TimeoutException>(async () =>
        {
            // #region example
            await Page.GetByRole(AriaRole.Button, new() { Name = "Exportar CSV" }).ClickAsync();
            // La descarga ya empezó durante el clic: esta espera no la ve y vence.
            var download = await Page.WaitForDownloadAsync(new() { Timeout = 2000 });
            // #endregion
        });
        Assert.That(error!.Message, Does.Contain("Timeout 2000ms exceeded"));
    }
}
