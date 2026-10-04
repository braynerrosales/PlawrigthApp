# Guía de Playwright

Guía técnica de Playwright para QA, construida con Astro + Starlight + MDX. La fuente de requisitos es [`docs/ARQUITECTURA_FINAL_GUIA_PLAYWRIGHT.md`](docs/ARQUITECTURA_FINAL_GUIA_PLAYWRIGHT.md); el flujo entre agentes está en [`AGENTS.md`](AGENTS.md).

## Comandos

```bash
npm install                 # instala site + herramientas de snippets (workspace)
npm run dev                 # servidor local
npm run build               # astro check + build + índice de búsqueda (Pagefind)
npm run preview             # sirve el build (la búsqueda solo funciona aquí)
npm run snippets:ts         # typecheck + ejecuta los snippets TypeScript/JavaScript
npm run snippets:dotnet     # compila + ejecuta los snippets C#
npm run snippets:python     # ejecuta los snippets Python (con el entorno virtual activado)
npm run snippets:java       # compila + ejecuta los snippets Java (Maven Wrapper, JUnit 5)
```

Los navegadores para los snippets se instalan una vez con `npx playwright install chromium`.

Para los snippets de Python (requiere Python 3.9+), una vez:

```bash
python -m venv .venv
.venv/Scripts/activate        # Windows (Git Bash); en macOS/Linux: source .venv/bin/activate
pip install -r snippets/python/requirements.txt
```

`requirements.txt` fija Playwright 1.63, la misma versión que TS y C#, así que reutiliza el Chromium ya descargado. Si falta, `playwright install chromium`.

Para los snippets de Java hace falta un **JDK** 17 o superior (un JRE no trae el compilador) y `JAVA_HOME` apuntando a él. No hace falta instalar Maven: `snippets/java/mvnw` (Maven Wrapper) lo descarga la primera vez en `~/.m2`, junto con Playwright 1.63 y JUnit 5.

## Estructura

```text
site/                      Astro + Starlight
  src/content/docs/<ruta>/ un módulo = un .mdx en la carpeta de su categoría
  src/components/          CodeExample, LanguageTabs, BestPractice, BadPractice, ModuleHeader, Progress
  src/components/overrides PageTitle (añade ModuleHeader) y Footer (añade Progress) en cada módulo
  src/lib/                 categorías, lenguajes, schema de metadata, lector de snippets
snippets/
  fixtures/                HTML de práctica compartido por todos los lenguajes
  dotnet/<Modulo>/         C# (NUnit + Microsoft.Playwright.NUnit)
  typescript/<modulo>/     TypeScript (@playwright/test)
  javascript/<modulo>/     JavaScript (@playwright/test, tipos en JSDoc)
  python/<modulo>/         Python (pytest + pytest-playwright); support.py y conftest.py compartidos
  java/<modulo_con_guiones_bajos>/  Java (JUnit 5, Maven); support/ con la clase base TestFixtures y los sitios de práctica
```

## Añadir un módulo

1. Crea `site/src/content/docs/<categoria>/<slug>.mdx` con la metadata completa (ver `web/locators.mdx`). El build falla si falta un campo, si `category` no coincide con la carpeta o si C# no es `complete`.
2. Escribe cada ejemplo como una prueba real en `snippets/`, marcando el fragmento publicado con `// #region example` y `// #endregion`. Convención de nombres para el id `modulo/ejemplo`:
   - C#: `snippets/dotnet/Modulo/Ejemplo.cs`
   - TypeScript: `snippets/typescript/modulo/ejemplo.spec.ts` (o `ejemplo.ts` si no es una prueba, p. ej. un page object que importan los specs)
   - JavaScript: `snippets/javascript/modulo/ejemplo.spec.js`
   - Java: `snippets/java/modulo_con_guiones_bajos/EjemploTest.java` (o `Ejemplo.java` si no es una prueba, p. ej. un page object). La carpeta es el paquete, por eso no lleva guiones.
   - Python: `snippets/python/modulo/test_ejemplo.py`

   Para resaltar una línea en la guía, termínala con `// [!mark]` (`# [!mark]` en Python). El comentario no se publica.
3. Referéncialo desde el MDX con la explicación obligatoria:

   ```mdx
   <CodeExample id="modulo/ejemplo" title="...">
     <Fragment slot="what">Qué hace.</Fragment>
     <Fragment slot="why">Por qué se hace así.</Fragment>
     <Fragment slot="avoids">Qué problema evita.</Fragment>
     <Fragment slot="alternative">Alternativa (opcional).</Fragment>
   </CodeExample>
   ```

   Si el módulo declara un lenguaje como `complete` y falta su snippet, el build falla. Los lenguajes sin snippet muestran su estado (`partial`, `pending`, `not-applicable`) en lugar de una traducción inventada.

   Si una funcionalidad no existe en un binding (por ejemplo, `expect.soft` solo existe en Playwright Test), márcalo en el ejemplo: `<CodeExample id="..." notApplicable={['csharp']}>`. La tab muestra "No aplica" y el build no exige ese snippet.

   Las páginas de práctica viven en `snippets/fixtures/<nombre>.html` y las comparten todos los lenguajes: en TypeScript se cargan con `fixture('<nombre>')` de `support.ts`; en C#, heredando de `FixtureTest` y sobrescribiendo `Fixture`; en Python, con `fixture("<nombre>")` de `support.py`; en Java, con `fixture("<nombre>")` de `support.Fixtures` en un `@BeforeEach` de una clase que hereda de `TestFixtures`.

   Si el ejemplo necesita un servidor HTTP real (por ejemplo, el cliente `request` / `IAPIRequestContext`, que no pasa por `route`), usa la API de práctica `snippets/server/api-de-practica.mjs` (Node puro, puerto `API_PRACTICA_PORT` o 4789). La arrancan `webServer` en `snippets/playwright.config.ts` y el `[SetUpFixture]` `Support/ApiDePractica.cs` en C# (requiere `node` en el PATH); en Python, el fixture de sesión `api_practica` de `snippets/python/conftest.py`; en Java, `support.ApiPractica.url()` la arranca la primera vez que se pide. La URL está en `API_PRACTICA` (TS y Python), `ApiDePractica.Url` (C#) y `ApiPractica.url()` (Java). Su estado vive en memoria y las pruebas corren en paralelo: cada prueba crea sus propios datos y no depende de ids ni del orden.
4. Ejecuta `npm run snippets:ts`, `npm run snippets:dotnet`, `npm run snippets:python`, `npm run snippets:java` y `npm run build`.

## Fuera de alcance (por decisión de arquitectura)

Sin backend, autenticación ni base de datos: el lenguaje preferido y el progreso viven en `localStorage`. No hay suite E2E de la propia web.
