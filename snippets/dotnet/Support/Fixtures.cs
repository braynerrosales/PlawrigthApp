namespace PlaywrightGuide.Snippets;

/// <summary>Páginas de práctica de <c>snippets/fixtures</c>, copiadas junto a los binarios.</summary>
public static class Fixtures
{
    /// <summary>URL <c>file://</c> de la página de práctica: la abren igual Selenium y Playwright.</summary>
    public static string Url(string name) =>
        new Uri(Path.Combine(AppContext.BaseDirectory, "fixtures", $"{name}.html")).AbsoluteUri;
}
