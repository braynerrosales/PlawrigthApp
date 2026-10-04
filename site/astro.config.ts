import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';
import { CATEGORIES } from './src/lib/categories';

// https://astro.build/config
export default defineConfig({
	integrations: [
		starlight({
			title: 'Guía de Playwright',
			description: 'Guía práctica de Playwright para QA: C#, TypeScript, JavaScript, Java y Python.',
			defaultLocale: 'root',
			locales: { root: { label: 'Español', lang: 'es' } },
			// Una ruta por categoría; cada carpeta añade sus módulos automáticamente.
			sidebar: CATEGORIES.map((category) => ({
				label: category.label,
				items: [{ autogenerate: { directory: category.id } }],
			})),
			customCss: [
				'@fontsource-variable/archivo/wdth.css',
				'@fontsource-variable/ibm-plex-sans',
				'@fontsource-variable/jetbrains-mono',
				'./src/styles/guide.css',
			],
			// Código oscuro en ambos temas con un único tema de sintaxis: un ejemplo se ve igual en claro y oscuro.
			expressiveCode: {
				themes: ['github-dark-default'],
				useStarlightDarkModeSwitch: false,
				styleOverrides: {
					borderRadius: '8px',
					borderColor: 'var(--pg-code-border)',
					codeBackground: 'var(--pg-code-bg)',
					codeFontFamily: 'var(--__sl-font-mono)',
					codeFontSize: '0.84rem',
					codeLineHeight: '1.65',
					uiFontFamily: 'var(--__sl-font)',
					frames: {
						frameBoxShadowCssValue: 'none',
						editorTabBarBackground: 'var(--pg-code-head)',
						editorTabBarBorderBottomColor: 'var(--pg-code-border)',
						editorActiveTabBackground: 'var(--pg-code-bg)',
						editorActiveTabForeground: 'var(--pg-code-muted)',
						editorActiveTabIndicatorTopColor: 'transparent',
						editorActiveTabIndicatorBottomColor: 'transparent',
						terminalTitlebarBackground: 'var(--pg-code-head)',
						terminalBackground: 'var(--pg-code-bg)',
					},
					textMarkers: {
						markBackground: 'var(--pg-code-mark)',
						markBorderColor: 'var(--pg-code-accent)',
					},
				},
			},
			components: {
				PageTitle: './src/components/overrides/PageTitle.astro',
				Footer: './src/components/overrides/Footer.astro',
				Hero: './src/components/overrides/Hero.astro',
				SiteTitle: './src/components/overrides/SiteTitle.astro',
				Sidebar: './src/components/overrides/Sidebar.astro',
				TableOfContents: './src/components/overrides/TableOfContents.astro',
				ThemeSelect: './src/components/overrides/ThemeSelect.astro',
			},
			routeMiddleware: './src/routeData.ts',
		}),
	],
});
