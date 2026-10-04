import { defineCollection } from 'astro:content';
import { docsLoader, i18nLoader } from '@astrojs/starlight/loaders';
import { docsSchema, i18nSchema } from '@astrojs/starlight/schema';
import { applyModuleOrder, moduleFields, refineModule } from './lib/modules';

export const collections = {
	docs: defineCollection({
		loader: docsLoader(),
		schema: (context) =>
			docsSchema({ extend: moduleFields })(context)
				.superRefine(refineModule)
				.transform(applyModuleOrder),
	}),
	i18n: defineCollection({ loader: i18nLoader(), schema: i18nSchema() }),
};
