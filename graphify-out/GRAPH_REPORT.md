# Graph Report - PlawrigthApp  (2026-10-03)

## Corpus Check
- Corpus is ~9,642 words - fits in a single context window. You may not need a graph.

## Summary
- 296 nodes · 460 edges · 33 communities (24 shown, 9 thin omitted)
- Extraction: 90% EXTRACTED · 10% INFERRED · 0% AMBIGUOUS · INFERRED: 45 edges (avg confidence: 0.82)
- Token cost: 120,102 input · 0 output

## Community Hubs (Navigation)
- Rutas y portada de la guía
- Página de práctica y snippets TS/JS
- Integración Astro + Starlight
- Componentes de código y lenguajes
- Dependencias del sitio
- Workspace raíz
- Base de snippets C#
- Config TypeScript de snippets
- Proyecto .NET de snippets
- Snippet C#: strictness
- Config TypeScript del sitio
- Snippet C#: Chaining
- Snippet C#: Css
- Snippet C#: Filter
- Snippet C#: FirstLastNth
- Snippet C#: FragileCss
- Snippet C#: GetByLabel
- Snippet C#: GetByRole
- Snippet C#: GetByTestId
- Snippet C#: LazyLocator
- Snippet C#: SaveButton
- Snippet C#: Xpath
- Ruta API, Red y Sesión
- Ruta Debugging
- Test C# 24
- Test C# 25
- Test C# 26
- Test C# 27
- Ruta Casos prácticos
- Ruta CI/CD
- Ruta Referencia
- Favicon
- Ruta Avanzado

## God Nodes (most connected - your core abstractions)
1. `Locators module` - 33 edges
2. `@playwright/test` - 23 edges
3. `Tienda QA practice page (locators fixture)` - 22 edges
4. `LocatorsTest` - 19 edges
5. `PlaywrightGuide.Snippets.Locators` - 17 edges
6. `locatorsHtml` - 17 edges
7. `compilerOptions` - 11 edges
8. `Locator priority order (role, label, placeholder, text, testid, CSS/XPath)` - 9 edges
9. `Locator (lazy element description)` - 8 edges
10. `scripts` - 7 edges

## Surprising Connections (you probably didn't know these)
- `Locators module` --references--> `Tienda QA practice page (locators fixture)`  [INFERRED]
  site/src/content/docs/web/locators.mdx → snippets/fixtures/locators.html
- `Resilient vs fragile locators` --conceptually_related_to--> `Intentional legacy div structure`  [INFERRED]
  site/src/content/docs/web/locators.mdx → snippets/fixtures/locators.html
- `Locator (lazy element description)` --conceptually_related_to--> `rerender() SPA-style node replacement`  [INFERRED]
  site/src/content/docs/web/locators.mdx → snippets/fixtures/locators.html
- `CSS selectors` --references--> `#login-form login form`  [EXTRACTED]
  site/src/content/docs/web/locators.mdx → snippets/fixtures/locators.html
- `CSS selectors` --references--> `.product-card product list`  [EXTRACTED]
  site/src/content/docs/web/locators.mdx → snippets/fixtures/locators.html

## Import Cycles
- None detected.

## Hyperedges (group relationships)
- **User-facing locator selection hierarchy** — site_src_content_docs_web_locators_locator_priority, site_src_content_docs_web_locators_get_by_role, site_src_content_docs_web_locators_get_by_label, site_src_content_docs_web_locators_get_by_placeholder, site_src_content_docs_web_locators_get_by_text, site_src_content_docs_web_locators_get_by_test_id, site_src_content_docs_web_locators_css_selector, site_src_content_docs_web_locators_xpath_selector [EXTRACTED 1.00]
- **Resolving strict mode violations without positional hacks** — site_src_content_docs_web_locators_strictness, site_src_content_docs_web_locators_filter, site_src_content_docs_web_locators_chaining, site_src_content_docs_web_locators_first_last_nth [EXTRACTED 1.00]
- **Selenium to Playwright mental-model shift** — site_src_content_docs_web_locators_selenium_vs_playwright_mapping, site_src_content_docs_web_locators_stale_element_reference, site_src_content_docs_web_locators_auto_waiting, site_src_content_docs_selenium_index_webelement_to_locator, site_src_content_docs_selenium_index_waits_to_auto_waiting [INFERRED 0.85]

## Communities (33 total, 9 thin omitted)

### Community 0 - "Rutas y portada de la guía"
Cohesion: 0.08
Nodes (42): Diseno del Framework (route index), Fixtures and test data, Page Object Model, Empezar / Fundamentos (route index), Browser, Context and Page (upcoming), Installation and first test (upcoming), What is Playwright (upcoming), Guia de Playwright (home) (+34 more)

### Community 1 - "Página de práctica y snippets TS/JS"
Cohesion: 0.17
Nodes (10): ref_node_fs, @playwright/test, CSS selectors, Resilient vs fragile locators, Tienda QA practice page (locators fixture), Intentional legacy div structure, #login-form login form, .product-card product list (+2 more)

### Community 2 - "Integración Astro + Starlight"
Cohesion: 0.11
Nodes (21): astro, ref_astro_content, @astrojs/starlight, module, module, collections, CATEGORIES, CATEGORY_IDS (+13 more)

### Community 3 - "Componentes de código y lenguajes"
Cohesion: 0.13
Nodes (13): module, variants, DEFAULT_LANGUAGE, Language, LANGUAGE_IDS, LANGUAGE_STORAGE_KEY, LANGUAGES, extract() (+5 more)

### Community 4 - "Dependencias del sitio"
Cohesion: 0.10
Nodes (19): @astrojs/check, sharp, dependencies, astro, @astrojs/starlight, sharp, devDependencies, @astrojs/check (+11 more)

### Community 5 - "Workspace raíz"
Cohesion: 0.11
Nodes (17): devDependencies, @playwright/test, @types/node, typescript, name, private, scripts, build (+9 more)

### Community 6 - "Base de snippets C#"
Cohesion: 0.17
Nodes (9): PlaywrightGuide.Snippets.Locators, PageTest, SetUp, FragileXpathExamples, GetByPlaceholderExamples, GetByTextExamples, QaCaseExamples, Task (+1 more)

### Community 7 - "Config TypeScript de snippets"
Cohesion: 0.15
Nodes (12): compilerOptions, allowImportingTsExtensions, allowJs, checkJs, module, moduleResolution, noEmit, skipLibCheck (+4 more)

### Community 8 - "Proyecto .NET de snippets"
Cohesion: 0.33
Nodes (5): net10.0, Microsoft.NET.Test.Sdk (18.10.1), Microsoft.Playwright.NUnit (1.63.0), NUnit3TestAdapter (6.3.0), Microsoft.NET.Sdk

### Community 9 - "Snippet C#: strictness"
Cohesion: 0.40
Nodes (3): PlaywrightException, Test, StrictViolationExamples

### Community 10 - "Config TypeScript del sitio"
Cohesion: 0.40
Nodes (4): astro/tsconfigs/strict, exclude, extends, include

### Community 11 - "Snippet C#: Chaining"
Cohesion: 0.40
Nodes (3): Task, Test, ChainingExamples

### Community 12 - "Snippet C#: Css"
Cohesion: 0.40
Nodes (3): Task, Test, CssExamples

### Community 13 - "Snippet C#: Filter"
Cohesion: 0.40
Nodes (3): Task, Test, FilterExamples

### Community 14 - "Snippet C#: FirstLastNth"
Cohesion: 0.40
Nodes (3): Task, Test, FirstLastNthExamples

### Community 15 - "Snippet C#: FragileCss"
Cohesion: 0.40
Nodes (3): Task, Test, FragileCssExamples

### Community 16 - "Snippet C#: GetByLabel"
Cohesion: 0.40
Nodes (3): Task, Test, GetByLabelExamples

### Community 17 - "Snippet C#: GetByRole"
Cohesion: 0.40
Nodes (3): Task, Test, GetByRoleExamples

### Community 18 - "Snippet C#: GetByTestId"
Cohesion: 0.40
Nodes (3): Task, Test, GetByTestIdExamples

### Community 19 - "Snippet C#: LazyLocator"
Cohesion: 0.40
Nodes (3): Task, Test, LazyLocatorExamples

### Community 20 - "Snippet C#: SaveButton"
Cohesion: 0.40
Nodes (3): Task, Test, SaveButtonExamples

### Community 21 - "Snippet C#: Xpath"
Cohesion: 0.40
Nodes (3): Task, Test, XpathExamples

### Community 22 - "Ruta API, Red y Sesión"
Cohesion: 0.50
Nodes (4): API, Red y Sesion (route index), API testing, Network interception, Session reuse

### Community 23 - "Ruta Debugging"
Cohesion: 0.50
Nodes (4): Debugging (route index), Playwright Inspector, Test results and reports module (upcoming), Trace Viewer

## Knowledge Gaps
- **79 isolated node(s):** `name`, `private`, `type`, `workspaces`, `dev` (+74 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 123 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **9 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Locators module` connect `Rutas y portada de la guía` to `Página de práctica y snippets TS/JS`, `Componentes de código y lenguajes`?**
  _High betweenness centrality (0.205) - this node is a cross-community bridge._
- **Why does `@playwright/test` connect `Página de práctica y snippets TS/JS` to `Workspace raíz`?**
  _High betweenness centrality (0.092) - this node is a cross-community bridge._
- **Why does `astro` connect `Integración Astro + Starlight` to `Dependencias del sitio`?**
  _High betweenness centrality (0.067) - this node is a cross-community bridge._
- **Are the 4 inferred relationships involving `Locators module` (e.g. with `BadPractice.astro` and `BestPractice.astro`) actually correct?**
  _`Locators module` has 4 INFERRED edges - model-reasoned connections that need verification._
- **Are the 17 inferred relationships involving `Tienda QA practice page (locators fixture)` (e.g. with `Locators module` and `chaining.spec.ts`) actually correct?**
  _`Tienda QA practice page (locators fixture)` has 17 INFERRED edges - model-reasoned connections that need verification._
- **What connects `name`, `private`, `type` to the rest of the system?**
  _79 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Rutas y portada de la guía` be split into smaller, more focused modules?**
  _Cohesion score 0.08478513356562137 - nodes in this community are weakly interconnected._