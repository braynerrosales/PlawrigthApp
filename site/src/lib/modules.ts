import { z } from 'astro/zod';
import { CATEGORY_IDS, LEVELS, type CategoryId, type Level } from './categories';
import { COVERAGE, LANGUAGE_IDS, type Coverage, type LanguageId } from './languages';

const coverage = z.enum(Object.keys(COVERAGE) as [Coverage, ...Coverage[]]);

/**
 * Metadata de un módulo (arquitectura §6). Todos los campos son opcionales a nivel de
 * frontmatter porque las páginas que no son módulos (portada, índices de categoría) no
 * los usan; `refineModule` exige el conjunto completo en cuanto aparece cualquiera.
 */
export const moduleFields = z.object({
	category: z.enum(CATEGORY_IDS).optional(),
	level: z.enum(Object.keys(LEVELS) as [Level, ...Level[]]).optional(),
	order: z.number().int().positive().optional(),
	readingTime: z.number().int().positive().optional(),
	tags: z.array(z.string().min(1)).min(1).optional(),
	prerequisites: z.array(z.string().min(1)).optional(),
	languages: z.object(Object.fromEntries(LANGUAGE_IDS.map((id) => [id, coverage]))).strict().optional(),
	validatedWith: z
		.string()
		.regex(/^\d+\.\d+$/, 'Usa el formato mayor.menor de Playwright, por ejemplo "1.63".')
		.optional(),
	lastReviewed: z.coerce.date().optional(),
});

const REQUIRED = ['category', 'level', 'order', 'readingTime', 'tags', 'languages', 'validatedWith', 'lastReviewed'] as const;

type ModuleFields = z.infer<typeof moduleFields>;

/** Falla el build si una página declara metadata de módulo incompleta. */
export function refineModule(data: ModuleFields & { description?: string }, ctx: z.RefinementCtx) {
	const declared = REQUIRED.filter((key) => data[key] !== undefined);
	if (declared.length === 0) return;
	for (const key of [...REQUIRED, 'description'] as const) {
		if (data[key] === undefined) {
			ctx.addIssue({
				code: 'custom',
				path: [key],
				message: `Módulo incompleto: falta "${key}". Un módulo debe declarar ${REQUIRED.join(', ')} y description.`,
			});
		}
	}
	if (data.languages && data.languages.csharp !== 'complete') {
		ctx.addIssue({ code: 'custom', path: ['languages', 'csharp'], message: 'C# es el lenguaje principal: debe ser "complete".' });
	}
}

/** Usa `order` como orden del sidebar para no duplicar el dato en `sidebar.order`. */
export function applyModuleOrder<T extends ModuleFields & { sidebar: { order?: number } }>(data: T): T {
	if (data.order === undefined || data.sidebar.order !== undefined) return data;
	return { ...data, sidebar: { ...data.sidebar, order: data.order } };
}

export interface ModuleMeta {
	title: string;
	description: string;
	category: CategoryId;
	level: Level;
	order: number;
	readingTime: number;
	tags: string[];
	prerequisites: string[];
	languages: Record<LanguageId, Coverage>;
	validatedWith: string;
	lastReviewed: Date;
}

/** Devuelve la metadata tipada si la página es un módulo, o `undefined` si no lo es. */
export function getModule(data: Record<string, any>): ModuleMeta | undefined {
	if (data.category === undefined) return undefined;
	return { prerequisites: [], ...data } as unknown as ModuleMeta;
}
