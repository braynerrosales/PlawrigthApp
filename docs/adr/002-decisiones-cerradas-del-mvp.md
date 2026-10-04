# 002 — Decisiones cerradas del MVP

- **Estado:** Aceptada
- **Origen:** decisiones cerradas por Bray al iniciar el proyecto

## Decisión

- C# es el lenguaje principal; TypeScript tiene cobertura completa.
- JavaScript, Python y Java se agregan progresivamente.
- MVP pequeño y publicable.
- Progreso guardado en `localStorage`; sin backend, autenticación ni base de datos.
- Buscador disponible desde la V1.
- Se diferencia Playwright Core de Playwright Test.
- No se asume equivalencia entre bindings: un lenguaje sin ejemplo muestra su estado, no una traducción.
- Se muestran buenas y malas prácticas; cada ejemplo explica qué hace, por qué y qué problema evita.
- Selenium → Playwright es una ruta importante.
- Locators es el patrón para los módulos siguientes; no se desarrollan los 12 módulos antes de validar el piloto.
- Evitar sobreingeniería.

## Consecuencias

- El schema de metadata (`site/src/lib/modules.ts`) exige `languages.csharp: complete`.
- `CodeExample` falla el build si un lenguaje `complete` no tiene snippet o si falta la explicación.
