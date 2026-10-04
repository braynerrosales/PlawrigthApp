# 003 — Ruta de aprendizaje inicial de 15 módulos

- **Estado:** Aceptada
- **Origen:** propuesta de Bray (PO), 2026-10-04
- **Reemplaza:** el número de módulos del MVP en la arquitectura §8 y §26 ("12 módulos")

## Contexto

La arquitectura fijaba un MVP de 12 módulos sin listarlos todos. Bray propuso una ruta inicial explícita, más completa, que separa temas que se estaban agrupando (formularios vs upload/download) y agrega tablas, autenticación, API/red, POM y debugging.

## Decisión

La ruta inicial es:

| # | Módulo | Categoría | Estado |
|---|---|---|---|
| 1 | Introducción (Qué es Playwright) | fundamentos | hecho |
| 2 | Instalación y primer test | fundamentos | hecho |
| 3 | Browser, Context y Page | fundamentos | hecho |
| 4 | Locators | web | hecho (piloto) |
| 5 | Acciones e interacciones | web | hecho |
| 6 | Assertions | web | hecho |
| 7 | Auto-waiting y actionability | web | hecho |
| 8 | Formularios | web | en curso |
| 9 | Tablas y listas dinámicas | web | pendiente |
| 10 | Iframes, popups, tabs y dialogs | web | en curso |
| 11 | Upload y download | web | en curso |
| 12 | Sesión y autenticación | api | pendiente |
| 13 | API y Network | api | pendiente |
| 14 | Page Object Model | framework | pendiente |
| 15 | Debugging (Trace Viewer y diagnóstico) | debugging | pendiente |

La ruta es un orden de lectura que cruza categorías; las categorías de la arquitectura §7 no cambian.

### Puntos resueltos (2026-10-04: Bray dijo "siga" sin objetar; se aplicó la recomendación y puede cambiarse)

1. **Orden Assertions / Auto-waiting.** Recomendación: dejar Auto-waiting antes de Assertions. Assertions se apoya en el concepto de reintento y en la página de práctica de pedidos que presenta Auto-waiting. Si se invierte, hay que reescribir esas referencias.
2. **Navegación.** No está en la lista, pero ya está escrito y es básico (`goto`, `baseURL`, redirecciones, SPA, historial). Recomendación: mantenerlo en la ruta entre Auto-waiting y Formularios.

## Orden aplicado (campo `order`)

1 Qué es Playwright · 2 Instalación · 3 Browser/Context/Page · 4 Locators · 5 Acciones · 6 Auto-waiting · 7 Assertions · 8 Navegación · 9 Formularios · 10 Tablas y listas · 11 Iframes/popups/tabs/dialogs · 12 Upload y download · 13 Sesión y autenticación · 14 API y Network · 15 Page Object Model · 16 Debugging

## Consecuencias

- El MVP pasa de 12 a 16 módulos (los 15 propuestos + Navegación).
- Formularios y Upload/Download son dos módulos separados.
- Cada módulo sigue el patrón del piloto Locators y la regla de C# + TypeScript completos.
