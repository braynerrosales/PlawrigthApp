import { getCollection } from 'astro:content';
import { CATEGORIES, type CategoryId } from './categories';
import { getModule, type ModuleMeta } from './modules';
import { withBase } from './paths';

export interface CatalogModule {
	/** Id de la entrada, que es también la clave de progreso (`web/locators`). */
	id: string;
	href: string;
	meta: ModuleMeta;
}

/** Módulos publicados en el orden de la ruta de aprendizaje (campo `order`). */
export async function getModules(): Promise<CatalogModule[]> {
	return (await getCollection('docs'))
		.flatMap((entry) => {
			const meta = getModule(entry.data);
			return meta ? [{ id: entry.id, href: withBase(`/${entry.id}/`), meta }] : [];
		})
		.sort((a, b) => a.meta.order - b.meta.order);
}

/** Nombre de la categoría sin el número de la ruta ("2. Automatización Web" → "Automatización Web"). */
export function categoryName(id: CategoryId) {
	return CATEGORIES.find((c) => c.id === id)!.label.replace(/^\d+\.\s*/, '');
}
