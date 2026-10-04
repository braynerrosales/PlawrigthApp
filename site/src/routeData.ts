import { defineRouteMiddleware } from '@astrojs/starlight/route-data';
import { getModule } from './lib/modules';

/** Falla el build si un módulo no está en la carpeta de su categoría (la URL y el sidebar dependen de ello). */
export const onRequest = defineRouteMiddleware(({ locals }) => {
	const { entry } = locals.starlightRoute;
	const module = getModule(entry.data);
	if (module && !entry.id.startsWith(`${module.category}/`)) {
		throw new Error(
			`El módulo "${entry.id}" declara category "${module.category}" pero no está en src/content/docs/${module.category}/.`,
		);
	}
});
