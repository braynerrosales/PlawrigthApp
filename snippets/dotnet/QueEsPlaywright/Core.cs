namespace PlaywrightGuide.Snippets.QueEsPlaywright;

public class CoreExamples
{
    [Test]
    public async Task PlaywrightCoreSinClaseBase()
    {
        // #region example
        using var playwright = await Playwright.CreateAsync();
        await using var browser = await playwright.Chromium.LaunchAsync();
        var context = await browser.NewContextAsync();
        var page = await context.NewPageAsync();

        await page.GotoAsync("https://playwright.dev");
        Console.WriteLine(await page.TitleAsync());
        // #endregion
    }
}
