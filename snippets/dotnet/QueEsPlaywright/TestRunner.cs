// #region example
using System.Text.RegularExpressions;

namespace PlaywrightTests;

public class TestRunnerExamples : PageTest
{
    [Test]
    public async Task PlaywrightTestEntregaLaPageLista()
    {
        await Page.GotoAsync("https://playwright.dev");

        await Expect(Page).ToHaveTitleAsync(new Regex("Playwright"));
    }
}
// #endregion
