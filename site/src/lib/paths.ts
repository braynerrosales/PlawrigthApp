/**
 * Prefija una ruta interna (`/web/locators/`) con el `base` del sitio (GitHub Pages lo sirve en
 * `/PlawrigthApp/`). Las anclas (`#...`) y las URLs externas se dejan igual.
 */
export function withBase(path: string, base: string = import.meta.env.BASE_URL) {
	if (!path.startsWith('/') || path.startsWith('//')) return path;
	return base.replace(/\/$/, '') + path;
}
