import { LANGUAGES, type LanguageId } from './languages';

/**
 * Snippets reales en `/snippets/<lenguaje>/<módulo>/<archivo>`. Son pruebas que compilan y
 * se ejecutan; aquí solo se lee el fragmento marcado con `#region example` / `#endregion`.
 */
const files = import.meta.glob('../../../snippets/*/*/*.{cs,ts,js,java,py}', {
	query: '?raw',
	import: 'default',
	eager: true,
}) as Record<string, string>;

const pascal = (s: string) => s.replace(/(^|-)(\w)/g, (_, __, c: string) => c.toUpperCase());

/**
 * Convención de nombres por lenguaje para el id `modulo/ejemplo` (kebab-case). En TS/JS/Java/Python, un
 * archivo que no es prueba (p. ej. un page object) se publica como `<ejemplo>.ts` / `.js` / `<Ejemplo>.java` /
 * `<ejemplo_con_guiones_bajos>.py`. En Java, las pruebas terminan en `Test` (convención de Maven Surefire).
 */
const FILE_NAMES: Record<LanguageId, (name: string) => string[]> = {
	csharp: (n) => [`${pascal(n)}.cs`],
	typescript: (n) => [`${n}.spec.ts`, `${n}.ts`],
	javascript: (n) => [`${n}.spec.js`, `${n}.js`],
	java: (n) => [`${pascal(n)}Test.java`, `${pascal(n)}.java`],
	python: (n) => [`test_${n.replaceAll('-', '_')}.py`, `${n.replaceAll('-', '_')}.py`],
};

const MODULE_DIR: Record<LanguageId, (module: string) => string> = {
	csharp: pascal,
	typescript: (m) => m,
	javascript: (m) => m,
	// Un paquete de Java no admite guiones.
	java: (m) => m.replaceAll('-', '_'),
	python: (m) => m,
};

const REGION = /^[ \t]*(?:\/\/|#) #region example[^\n]*\n([\s\S]*?)^[ \t]*(?:\/\/|#) #endregion/m;
/** Comentario al final de una línea que la resalta en la guía; no se publica. */
const MARK = /[ \t]*(?:\/\/|#) \[!mark\](?=\r?$)/;

function extract(source: string) {
	const body = source.match(REGION)?.[1] ?? source;
	const lines = body.replace(/\s+$/, '').split('\n');
	const indent = Math.min(...lines.filter((l) => l.trim()).map((l) => l.match(/^[ \t]*/)![0].length));
	const marks: number[] = [];
	const code = lines
		.map((l, i) => {
			if (MARK.test(l)) marks.push(i + 1);
			return l.slice(indent).replace(MARK, '');
		})
		.join('\n');
	return { code, marks };
}

/**
 * Región `# #region <nombre>` … `# #endregion` de un archivo de pipeline (YAML importado con `?raw`), sin la
 * indentación común. Falla el build si la región no existe, para que el texto no quede apuntando a un job borrado.
 */
export function pipelineRegion(source: string, name: string) {
	const match = source.match(new RegExp(`^[ \\t]*# #region ${name}\\r?\\n([\\s\\S]*?)^[ \\t]*# #endregion`, 'm'));
	if (!match) throw new Error(`No existe la región "${name}" en el archivo de pipeline.`);
	return extract(match[1].replaceAll('\r', '')).code;
}

/** Ruta del snippet; si no existe ninguna variante, la convención principal (para mensajes de error). */
export function snippetPath(id: string, lang: LanguageId) {
	const [module, name] = id.split('/');
	if (!module || !name) throw new Error(`Id de snippet inválido "${id}". Usa "modulo/ejemplo".`);
	const dir = LANGUAGES.find((l) => l.id === lang)!.snippetDir;
	const paths = FILE_NAMES[lang](name).map((file) => `snippets/${dir}/${MODULE_DIR[lang](module)}/${file}`);
	return paths.find((path) => `../../../${path}` in files) ?? paths[0];
}

export interface Snippet {
	/** Fragmento publicado, sin los comentarios de resaltado. */
	code: string;
	/** Líneas (desde 1) marcadas con `// [!mark]` o `# [!mark]`. */
	marks: number[];
	/** Nombre del archivo, por ejemplo `Locators/QaCase.cs`. */
	file: string;
	/** Runner que ejecuta el archivo, o `undefined` si no es una prueba (p. ej. un page object). */
	runner?: string;
}

/** Ejemplo para un lenguaje, o `undefined` si esa variante no existe todavía. */
export function getSnippet(id: string, lang: LanguageId): Snippet | undefined {
	const path = snippetPath(id, lang);
	const source = files[`../../../${path}`];
	if (source === undefined) return undefined;
	const language = LANGUAGES.find((l) => l.id === lang)!;
	const isTest = /\.spec\.[jt]s$|\/test_[^/]+\.py$/.test(path) || /\[Test\]|@Test/.test(source);
	return {
		...extract(source),
		file: path.split('/').slice(-2).join('/'),
		runner: isTest ? language.runner : undefined,
	};
}
