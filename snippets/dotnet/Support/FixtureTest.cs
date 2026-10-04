namespace PlaywrightGuide.Snippets;

/// <summary>Carga una página de práctica de <c>snippets/fixtures</c>, compartida con los snippets de TypeScript.</summary>
public abstract class FixtureTest : PageTest
{
    protected abstract string Fixture { get; }

    [SetUp]
    public async Task LoadFixture() =>
        await Page.SetContentAsync(await File.ReadAllTextAsync(
            Path.Combine(AppContext.BaseDirectory, "fixtures", $"{Fixture}.html")));
}
