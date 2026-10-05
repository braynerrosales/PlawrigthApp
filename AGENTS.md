# AGENTS.md

Contexto compartido para todos los agentes de IA que trabajan en este repo (Claude, Codex, Gemini).
**Léelo completo antes de cualquier tarea.** Para Claude y Gemini, este archivo se referencia desde `CLAUDE.md` y `GEMINI.md`.

---

## 1. Proyecto

- **Nombre:** Guía de Playwright
- **Objetivo:** guía web técnica para QA que enseña a diseñar, implementar, ejecutar, depurar y mantener automatización con Playwright. Cada ejemplo explica qué hace, por qué se hace así y qué problema evita.
- **Estado:** MVP — base del sitio y módulo piloto **Locators** construidos, pendientes de validación por Bray.
- **Fuente de requisitos:** [`docs/ARQUITECTURA_FINAL_GUIA_PLAYWRIGHT.md`](docs/ARQUITECTURA_FINAL_GUIA_PLAYWRIGHT.md). No se rediseña; los cambios pasan por un ADR.

## 2. Stack

- **Frontend:** Astro 7 + Starlight 0.42 + MDX + TypeScript (sitio estático, búsqueda con Pagefind)
- **Backend:** ninguno (sin auth ni base de datos; preferencias y progreso en `localStorage`)
- **Base de datos:** ninguna
- **Tests:** solo de los **ejemplos de código** publicados: `@playwright/test` 1.63 (TS/JS), Microsoft.Playwright.NUnit 1.63 (.NET 10) pytest-playwright con Playwright 1.63 (Python) y Playwright 1.63 con JUnit 5 (Java, Maven Wrapper). El «antes» de la ruta Selenium → Playwright usa Selenium 4.50 en los cinco lenguajes y necesita Chrome instalado. **No hay tests de la propia web** (ver [ADR 001](docs/adr/001-sin-pruebas-de-la-propia-web.md)).
- **CI/CD:** pendiente. Pipeline previsto: Checkout → Install → Build → Validación de contenido → Publicar.

### Comandos

```bash
# instalar (workspace raíz + site)
npm install
npx playwright install chromium   # una vez, para los snippets
# correr local
npm run dev                       # desarrollo
npm run preview                   # build servido; la búsqueda solo funciona aquí
# tests (validación de snippets)
npm run snippets:ts               # typecheck + ejecuta snippets TS/JS
npm run snippets:dotnet           # compila + ejecuta snippets C#
npm run snippets:python           # ejecuta snippets Python (venv activado, ver README)
npm run snippets:java             # compila + ejecuta snippets Java (requiere JDK 17+, ver README)
# lint / validación de contenido
npm run check                     # astro check
npm run build                     # check + build; falla si la metadata o los snippets no cumplen
```

## 3. Roles

| Rol | Responsable | Hace | No hace |
|---|---|---|---|
| Product Owner | **Bray (humano)** | Ideas, prioridades, aprueba PRs, merge a `main` | — |
| Arquitecto / Dev principal | **Claude** | Diseño técnico, implementación de features, refactors | Merge a `main` |
| Reviewer | **Codex (GPT)** | Code review de PRs, detección de bugs, tareas pequeñas acotadas | Cambios de arquitectura |
| QA / Docs | **Gemini** | Snippets de ejemplo y su validación, revisión técnica del contenido, documentación | Lógica de negocio nueva; tests E2E de la propia web |

> La decisión final siempre es de Bray. Si un agente no está de acuerdo con otro, deja la objeción en el PR y Bray decide.

En este proyecto, "QA" significa comprobar que **el contenido es correcto**: que cada snippet compila y se ejecuta contra la versión de Playwright indicada en `validatedWith`, y que no se inventan equivalencias entre bindings. No significa automatizar la navegación del sitio.

## 4. Flujo de trabajo

1. **Bray** agrega la idea a `TASKS.md` (estado `TODO`).
2. **Claude** la refina en historia + criterios de aceptación (Given/When/Then), crea la rama `feat/<id>-<slug>` e implementa.
3. **Codex** revisa el PR y deja comentarios. Claude corrige.
4. **Gemini** agrega o actualiza los snippets, su validación y los docs en la misma rama.
5. **Bray** valida y hace el merge.

Solo un agente trabaja en una rama a la vez. Antes de empezar, actualiza el estado en `TASKS.md`.

**Regla de producto:** no se desarrollan más módulos hasta que Bray valide el piloto de Locators; después, cada módulo nuevo sigue su patrón.

## 5. Convenciones

- **Ramas:** `feat/`, `fix/`, `test/`, `docs/`, `refactor/` + `<id>-<slug>`
- **Commits:** Conventional Commits (`feat: ...`, `fix: ...`, `test: ...`) y firma el agente en el cuerpo (`Agent: Claude|Codex|Gemini`)
- **Código:**
  - Un módulo = un `.mdx` en `site/src/content/docs/<categoria>/` con la metadata completa (ver `web/locators.mdx`). No se crean páginas a mano.
  - Solo los componentes acordados: `ModuleHeader`, `CodeExample`, `LanguageTabs`, `BestPractice`, `BadPractice`, `Progress`. Evitar sobreingeniería.
  - C# es el lenguaje principal (siempre `complete`); TypeScript, JavaScript, Java y Python también con cobertura completa. Un lenguaje sin snippet muestra su estado, nunca una traducción inventada.
  - Cada `CodeExample` lleva `what`, `why` y `avoids` (y `alternative` cuando exista).
  - Nombres de snippets: ver `README.md` → "Añadir un módulo". El fragmento publicado va entre `// #region example` y `// #endregion`.
  - Textos de la guía en español; código y nombres de API en inglés.
- **Tests:** cada ejemplo publicado es un snippet que compila y se ejecuta (`snippets:ts`, `snippets:dotnet`, `snippets:python`, `snippets:java`). Los criterios de aceptación del sitio se verifican con `npm run build` y revisión manual, no con E2E.
- **Nunca:** commitear secretos, push directo a `main`, borrar o desactivar snippets para que pase el build, añadir tests E2E del sitio sin un ADR aprobado.

## 6. Estructura

```
site/                      sitio Astro + Starlight
  src/content/docs/        módulos (.mdx), una carpeta por ruta/categoría
  src/components/          los 6 componentes + overrides de Starlight
  src/lib/                 categorías, lenguajes, schema de metadata, lector de snippets
snippets/                  ejemplos de código ejecutables (fixtures/, server/, dotnet/, typescript/, javascript/, python/, java/)
                           server/: API de práctica en Node; la arrancan snippets:ts, snippets:dotnet, snippets:python y snippets:java (ver README)
docs/                      arquitectura y decisiones (adr/)
graphify-out/              grafo de conocimiento del repo (generado)
TASKS.md                   backlog y estado
AGENTS.md                  este archivo
```

## 7. Decisiones técnicas (ADR)

Las decisiones importantes se registran en `/docs/adr/NNN-titulo.md` (contexto, decisión, consecuencias). Ningún agente revierte un ADR sin aprobación de Bray.

- [001 — Sin pruebas automatizadas de la propia web](docs/adr/001-sin-pruebas-de-la-propia-web.md)
- [002 — Decisiones cerradas del MVP](docs/adr/002-decisiones-cerradas-del-mvp.md)
- [003 — Ruta de aprendizaje inicial de 15 módulos](docs/adr/003-ruta-inicial-de-15-modulos.md)

## 8. Formato de `TASKS.md`

```markdown
| ID | Tarea | Estado | Asignado | Rama |
|----|-------|--------|----------|------|
| 001 | Login con email | IN_PROGRESS | Claude | feat/001-login |
```

Estados: `TODO` → `IN_PROGRESS` → `REVIEW` → `TESTING` → `DONE`
