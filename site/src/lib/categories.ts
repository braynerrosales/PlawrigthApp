/**
 * Rutas de aprendizaje (arquitectura §7). Cada `id` es también la carpeta
 * dentro de `src/content/docs/`, y un módulo debe vivir en la carpeta de su categoría.
 */
export const CATEGORIES = [
	{ id: 'fundamentos', label: '1. Empezar' },
	{ id: 'web', label: '2. Automatización Web' },
	{ id: 'api', label: '3. API, Red y Sesión' },
	{ id: 'framework', label: '4. Diseño del Framework' },
	{ id: 'debugging', label: '5. Debugging' },
	{ id: 'cicd', label: '6. Ejecución y CI/CD' },
	{ id: 'selenium', label: '7. Selenium → Playwright' },
	{ id: 'advanced', label: '8. Avanzado' },
	{ id: 'casos', label: 'Casos prácticos' },
	{ id: 'referencia', label: 'Referencia' },
] as const;

export type CategoryId = (typeof CATEGORIES)[number]['id'];

export const CATEGORY_IDS = CATEGORIES.map((c) => c.id) as [CategoryId, ...CategoryId[]];

export const LEVELS = {
	beginner: 'Principiante',
	intermediate: 'Intermedio',
	advanced: 'Avanzado',
} as const;

export type Level = keyof typeof LEVELS;
