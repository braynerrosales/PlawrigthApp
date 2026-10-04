# 001 — Sin pruebas automatizadas de la propia web

- **Estado:** Aceptada
- **Origen:** `docs/ARQUITECTURA_FINAL_GUIA_PLAYWRIGHT.md`, sección "Restricción sobre pruebas de la propia página"

## Contexto

La plantilla de agentes asigna a QA tests unit/e2e por feature. En esta guía eso significaría automatizar el propio sitio, lo que consume tiempo, ejecución y tokens sin aportar al objetivo del MVP.

## Decisión

No se crean ni mantienen, salvo que Bray lo pida:

- suite E2E de la guía;
- tests de navegación, dark mode, tabs de lenguaje, progreso, responsive o buscador;
- tests visuales de la guía;
- reportes de ejecución de la propia web.

Sí se mantiene la **validación de los ejemplos de código** (`snippets/`): cada snippet publicado compila y se ejecuta contra la versión de Playwright declarada. Eso valida el contenido, no el sitio.

El contenido educativo sobre resultados y reportes de Playwright (passed/failed/skipped, reporters, HTML, JUnit, traces, evidencias, CI) **sí forma parte de la guía**.

## Consecuencias

- El pipeline de CI del MVP es: Checkout → Install → Build → Validación de contenido → Publicar.
- `npm run build` es la puerta de calidad del sitio: falla con metadata incompleta, categoría incorrecta o snippets faltantes.
- Los cambios de UI se verifican manualmente en `npm run preview`.
