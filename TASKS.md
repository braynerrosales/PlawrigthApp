# TASKS

| ID | Tarea | Estado | Asignado | Rama |
|----|-------|--------|----------|------|
| 001 | Base del sitio (Astro + Starlight, metadata, selector de lenguaje, componentes) y módulo piloto Locators | DONE | Claude | — |
| 002 | Inicializar git y repositorio remoto para el flujo de ramas y PRs | DONE | Bray | — |
| 003 | Pipeline CI del MVP: Checkout → Install → Build → Validación de contenido → Publicar | TODO | — | — |
| 004 | Ruta 1. Empezar: módulos 01 Qué es Playwright, 02 Instalación y primer test, 03 Browser, Context y Page | DONE | Claude | — |
| 005 | Ruta 2. Automatización Web: Acciones e interacciones, Auto-waiting y actionability, Assertions | REVIEW | Bray | — |
| 006 | Ruta 2. Automatización Web: Navegación; Formularios; Upload y download; Iframes, popups, tabs y dialogs (ADR 003) | REVIEW | Bray | — |
| 007 | Tablas y listas dinámicas (ADR 003, order 10) | REVIEW | Bray | — |
| 008 | Sesión y autenticación (ADR 003, order 13) | REVIEW | Bray | — |
| 009 | API y Network (ADR 003, order 14) | REVIEW | Bray | — |
| 010 | Page Object Model (ADR 003, order 15) | REVIEW | Bray | — |
| 011 | Debugging: Trace Viewer y diagnóstico (ADR 003, order 16) | REVIEW | Bray | — |
| 012 | Sistema visual «Trace» (propuesta aprobada): Home, módulo Locators y componentes que usan | REVIEW | Bray | — |
| 013 | Snippets Python en los 16 módulos (pytest-playwright 1.63), cobertura `complete` | REVIEW | Bray | — |
| 014 | Snippets Java en los 16 módulos (Playwright 1.63 + JUnit 5, Maven Wrapper), cobertura `complete` | REVIEW | Bray | — |
| 015 | Snippets JavaScript en los 16 módulos (Playwright Test 1.63, tipos con JSDoc), cobertura `complete` | REVIEW | Bray | — |
| 016 | Publicar en GitHub Pages: `base` `/PlawrigthApp/`, links internos con el base y workflow de build + deploy | REVIEW | Bray | feat/016-github-pages |
| 017 | Dejar un solo workflow de Pages (quitar static.yml) | DONE | Claude | fix/017-un-solo-workflow-pages |
| 018 | Ruta 7. Selenium → Playwright: Modelo mental y Migración de una prueba, con el «antes» en Selenium 4.50 ejecutable en los 5 lenguajes | REVIEW | Bray | feat/018-selenium-a-playwright |

Estados: `TODO` → `IN_PROGRESS` → `REVIEW` → `TESTING` → `DONE`
