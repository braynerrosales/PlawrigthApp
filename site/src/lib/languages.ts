/**
 * Lenguajes soportados por la guía (arquitectura §4). El orden define el orden de las tabs;
 * C# es el lenguaje principal y la selección por defecto.
 */
export const LANGUAGES = [
	{
		id: 'csharp',
		label: 'C#',
		snippetDir: 'dotnet',
		code: 'csharp',
		runner: 'NUnit',
		setup: ['dotnet add package Microsoft.Playwright.NUnit'],
		docs: 'https://playwright.dev/dotnet/docs/intro',
	},
	{
		id: 'typescript',
		label: 'TypeScript',
		snippetDir: 'typescript',
		code: 'ts',
		runner: 'Playwright Test',
		setup: ['npm init playwright@latest'],
		docs: 'https://playwright.dev/docs/intro',
	},
	{
		id: 'javascript',
		label: 'JavaScript',
		snippetDir: 'javascript',
		code: 'js',
		runner: 'Playwright Test',
		setup: ['npm init playwright@latest'],
		docs: 'https://playwright.dev/docs/intro',
	},
	{
		id: 'java',
		label: 'Java',
		snippetDir: 'java',
		code: 'java',
		runner: 'JUnit',
		// Dependencia de Maven: groupId:artifactId.
		setup: ['com.microsoft.playwright:playwright'],
		docs: 'https://playwright.dev/java/docs/intro',
	},
	{
		id: 'python',
		label: 'Python',
		snippetDir: 'python',
		code: 'python',
		runner: 'pytest',
		setup: ['pip install pytest-playwright', 'playwright install'],
		docs: 'https://playwright.dev/python/docs/intro',
	},
] as const;

export type Language = (typeof LANGUAGES)[number];
export type LanguageId = Language['id'];

export const LANGUAGE_IDS = LANGUAGES.map((l) => l.id) as [LanguageId, ...LanguageId[]];

export const DEFAULT_LANGUAGE: LanguageId = 'csharp';

/** Clave de localStorage compartida por todas las LanguageTabs. */
export const LANGUAGE_STORAGE_KEY = 'pw-guide:language';

/** Evento de `document` que avisa a tabs y selector del header de un cambio de lenguaje. */
export const LANGUAGE_CHANGE_EVENT = 'pw-guide:language-change';

/** Estados de cobertura permitidos (arquitectura §5). */
export const COVERAGE = {
	complete: 'Completo',
	partial: 'Parcial',
	pending: 'Pendiente',
	'not-applicable': 'No aplica',
} as const;

export type Coverage = keyof typeof COVERAGE;

/** Glifo de cada estado; siempre acompaña al texto, el color nunca es el único indicador. */
export const COVERAGE_GLYPH: Record<Coverage, string> = {
	complete: '✓',
	partial: '◐',
	pending: '—',
	'not-applicable': '∅',
};
