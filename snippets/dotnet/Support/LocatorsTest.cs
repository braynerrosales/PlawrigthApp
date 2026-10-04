namespace PlaywrightGuide.Snippets.Locators;

/// <summary>Carga la página de práctica compartida con los snippets de TypeScript y JavaScript.</summary>
public abstract class LocatorsTest : PageTest
{
    private static readonly string Html =
        File.ReadAllText(Path.Combine(AppContext.BaseDirectory, "fixtures", "locators.html"));

    [SetUp]
    public Task LoadFixture() => Page.SetContentAsync(Html);
}
