import { withBase } from './paths';

interface Context {
	setProperty(node: object, key: string, value: unknown): void;
}

/**
 * Las páginas enlazan los módulos con rutas absolutas (`[Locators](/web/locators/)`), pero Markdown no
 * aplica el `base` del sitio. Este plugin de Sätteri (el procesador de Markdown de Astro) lo agrega a los
 * links de Markdown (los `<a href>` de MDX usan `withBase`).
 */
export function baseLinks(base: string) {
	return {
		name: 'base-links',
		link: (node: { url: string }, ctx: Context) => ctx.setProperty(node, 'url', withBase(node.url, base)),
		definition: (node: { url: string }, ctx: Context) => ctx.setProperty(node, 'url', withBase(node.url, base)),
	};
}
