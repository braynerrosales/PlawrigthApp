# Arquitectura final — Guía web de Playwright

## 1. Objetivo

Construir una guía web moderna de Playwright orientada a perfiles QA, especialmente personas que:

- Están iniciando en automatización.
- Vienen de Selenium.
- Trabajan como QA Automation.
- Necesitan una referencia técnica diaria.
- Quieren comparar Playwright entre distintos lenguajes.

La guía debe enseñar no solo **qué comando usar**, sino también:

- Qué problema resuelve.
- Por qué se utiliza.
- Cuándo utilizarlo.
- Cuándo evitarlo.
- Qué errores son comunes.
- Cómo se implementa en un proyecto real.
- Qué diferencias existen entre bindings y test runners.

---

# 2. Enfoque principal

La página debe funcionar como:

- Curso.
- Documentación.
- Cheat sheet.
- Guía de migración desde Selenium.
- Catálogo de errores.
- Biblioteca de ejemplos.
- Referencia de buenas prácticas.
- Material interno de capacitación QA.

La prioridad es enseñar a:

> Diseñar, implementar, ejecutar, depurar y mantener automatización profesional con Playwright.

---

# 3. Stack recomendado

## Astro

Astro será la base técnica del sitio.

Se utilizará para:

- Generar las páginas.
- Gestionar rutas.
- Renderizar contenido.
- Integrar componentes.
- Mantener una web rápida.
- Reducir JavaScript innecesario.

## Starlight

Starlight se utilizará sobre Astro como base de documentación.

Proporciona:

- Sidebar.
- Tabla de contenidos.
- Breadcrumbs.
- Navegación anterior/siguiente.
- Dark mode / light mode.
- Diseño responsive.
- Búsqueda.
- Estructura de documentación.
- Integración con Markdown y MDX.

## MDX

El contenido de los módulos se escribirá principalmente en MDX.

Esto permitirá mezclar:

```text
Markdown
+
Componentes interactivos
```

Ejemplo:

```mdx
# Locators

Los locators permiten identificar elementos de una página.

<BestPractice>
Utilizar locators basados en roles accesibles.
</BestPractice>

<CodeExample id="locators/save-button" />
```

## TypeScript

Se utilizará para:

- Componentes.
- Validaciones.
- Scripts.
- Metadata.
- Testing de la propia web.

---

# 4. Lenguajes de la guía

La guía soportará:

- C# / .NET.
- TypeScript.
- JavaScript.
- Java.
- Python.

## Prioridad

### C#

Lenguaje principal.

Debe tener cobertura completa en los módulos principales.

### TypeScript

Segundo lenguaje principal.

Se utilizará además para explicar funcionalidades específicas de Playwright Test.

### JavaScript

Cobertura progresiva.

### Python

Cobertura progresiva.

### Java

Cobertura progresiva.

---

# 5. Regla importante sobre bindings

No asumir que todas las capacidades existen igual en todos los lenguajes.

Cada módulo debe indicar disponibilidad.

Ejemplo:

```text
UI Mode

TypeScript
✓ Disponible mediante Playwright Test

C#
✕ No disponible de la misma manera

Alternativa:
Trace Viewer + Inspector + Debug
```

Cada módulo debe tener metadata de disponibilidad.

Ejemplo:

```yaml
languages:
  csharp: complete
  typescript: complete
  javascript: partial
  java: pending
  python: pending
```

Estados permitidos:

```text
complete
partial
pending
not-applicable
```

---

# 6. Metadata de cada módulo

Cada módulo debe incluir información similar a:

```yaml
title: "Locators"
description: "Cómo identificar elementos de forma resistente."
category: "web"
level: "beginner"
order: 4
readingTime: 15

tags:
  - locators
  - getByRole
  - strictness

languages:
  csharp: complete
  typescript: complete
  javascript: partial
  java: pending
  python: pending

validatedWith: "1.63"
lastReviewed: "2026-10-03"
```

## validatedWith

Debe indicar la versión de Playwright contra la que fue validado el contenido.

Ejemplo:

```text
Validado con Playwright 1.63
```

Esto ayudará a detectar contenido desactualizado.

---

# 7. Arquitectura de información

La navegación principal tendrá las siguientes categorías:

```text
Playwright
│
├── 1. Empezar
│
├── 2. Automatización Web
│
├── 3. API, Red y Sesión
│
├── 4. Diseño del Framework
│
├── 5. Debugging
│
├── 6. Ejecución y CI/CD
│
├── 7. Selenium → Playwright
│
├── 8. Avanzado
│
├── Casos prácticos
│
└── Referencia
```

La dificultad no será una categoría.

Se manejará como metadata:

```text
Beginner
Intermediate
Advanced
```

---

# 8. MVP

La primera versión tendrá 12 módulos principales.

## Módulo 01 — Qué es Playwright

Contenido:

- Qué es.
- Arquitectura.
- Browser.
- BrowserContext.
- Page.
- Locator.
- Navegadores.
- Casos de uso.

---

## Módulo 02 — Instalación y primer test

Por lenguaje:

- C#.
- TypeScript.
- JavaScript.
- Java.
- Python.

Incluir:

- Requisitos.
- Instalación.
- Browsers.
- Primer test.
- Ejecución.

---

## Módulo 03 — Browser, Context y Page

Explicar:

```text
Browser
   ↓
BrowserContext
   ↓
Page
```

Ejemplos:

- Sesiones independientes.
- Admin y cliente.
- Varias pestañas.
- Contextos aislados.

---

## Módulo 04 — Locators + Strictness

Debe ser uno de los módulos principales.

Cubrir:

- getByRole.
- getByText.
- getByLabel.
- getByPlaceholder.
- getByTestId.
- CSS.
- XPath.
- filter.
- nth.
- first.
- last.
- chaining.
- strictness.

Debe mostrar:

```text
Locator frágil
vs
Locator resistente
```

Ejemplo:

### Evitar

```text
//div[3]/div[2]/button
```

### Recomendado

```text
getByRole("button", name="Guardar")
```

---

# 9. Estructura interna de cada módulo

Cada módulo debe seguir una estructura flexible:

```text
Título
Descripción
Nivel
Tiempo de lectura
Prerrequisitos

Qué aprenderás

Concepto

Ejemplo básico

Código por lenguaje

Qué hace

Por qué se hace así

Caso QA real

Buena práctica

Mala práctica

Errores comunes

Selenium vs Playwright
(si aplica)

Siguiente módulo
```

No todos los módulos tienen que incluir obligatoriamente todas las secciones.

---

# 10. Estrategia de código

## MVP

Código compilable obligatorio:

```text
C#
TypeScript
```

Cobertura documental progresiva:

```text
JavaScript
Python
Java
```

## V2

Agregar validación automática de:

```text
Python
```

## V3

Validar automáticamente:

```text
C#
TypeScript
JavaScript
Java
Python
```

---

# 11. Estructura de snippets

Primera versión:

```text
snippets/
├── dotnet/
└── typescript/
```

Futuro:

```text
snippets/
├── dotnet/
├── typescript/
├── javascript/
├── python/
└── java/
```

Cada ejemplo importante debe existir como código real.

El MDX debe referenciar el ejemplo.

Ejemplo:

```mdx
<CodeExample id="locators/save-button" />
```

---

# 12. Componente CodeExample

Debe permitir tabs:

```text
C# | TypeScript | JavaScript | Java | Python
```

C# será la selección inicial.

La selección del usuario debe persistir.

Ejemplo:

```text
Lenguaje preferido: C#
```

Todos los bloques de código del módulo deben cambiar al mismo lenguaje cuando sea posible.

---

# 13. Buena práctica vs mala práctica

Cada módulo debe enseñar contraste.

Ejemplo:

## Evitar

```csharp
await Page.Locator(
    "body > div:nth-child(2) > div:nth-child(4) > button"
).ClickAsync();
```

## Recomendado

```csharp
await Page
    .GetByRole(AriaRole.Button, new() { Name = "Guardar" })
    .ClickAsync();
```

## Por qué

El primer selector depende de la estructura del DOM.

El segundo expresa intención funcional y es más resistente a cambios internos de layout.

---

# 14. Explicación de código

Cada ejemplo debe responder:

```text
¿Qué hace?
¿Por qué se hace así?
¿Qué problema evita?
¿Qué alternativa existe?
```

No limitarse a describir literalmente el código.

---

# 15. Selenium → Playwright

Crear una ruta específica.

Ejemplo:

```text
Trabajo con Selenium
        ↓
Modelo mental
        ↓
WebElement → Locator
        ↓
Waits → Auto-waiting
        ↓
Driver → Browser / Context / Page
        ↓
POM
        ↓
Migración de una prueba
```

Comparaciones:

| Selenium | Playwright |
|---|---|
| WebElement | Locator |
| FindElement | Locator |
| WebDriverWait | Auto-waiting + assertions |
| Thread.Sleep | Evitar espera fija |
| Driver | Browser / Context / Page |

No presentar equivalencias engañosas.

---

# 16. Buscador

Debe existir desde el MVP.

Debe buscar:

- Módulos.
- APIs.
- Métodos.
- Errores.
- Conceptos.
- Tags.
- Código.

Ejemplo:

```text
storageState
```

Resultado esperado:

```text
Sesión y autenticación
```

Ejemplo:

```text
strict mode violation
```

Resultado:

```text
Locators
└── Strictness
```

---

# 17. Catálogo de errores

Crear una sección de referencia.

Cada error tendrá:

```text
Síntoma
Causa probable
Cómo diagnosticar
Cómo corregir
Qué evitar
```

Ejemplos:

- strict mode violation.
- timeout.
- element not visible.
- page closed.
- browser closed.
- popup no capturado.
- request no interceptado.
- test funciona local pero falla en CI.

---

# 18. Progreso

Para MVP utilizar:

```text
localStorage
```

No utilizar:

- Backend.
- Login.
- OAuth.
- Base de datos.

Funciones:

- Marcar módulo completado.
- Porcentaje global.
- Porcentaje por ruta.
- Persistencia local.

Ejemplo:

```text
Fundamentos
██████████ 100%

Automatización Web
██████░░░░ 60%

API
██░░░░░░░░ 20%
```

---

# 19. Playground

Crear una aplicación pequeña específicamente diseñada para enseñar Playwright.

Rutas posibles:

```text
/playground/login
/playground/forms
/playground/table
/playground/modal
/playground/iframe
/playground/download
/playground/upload
/playground/dynamic-content
/playground/network
/playground/dashboard
```

## dynamic-content

Puede incluir:

- Elemento que tarda en aparecer.
- Loader.
- Botones duplicados.
- DOM que cambia.
- Request lento.

Esto permitirá enseñar:

- Auto-waiting.
- Strictness.
- Locators.
- waitForResponse.
- Timeouts.

El playground debe mantenerse pequeño en V1.

---

# 20. Casos prácticos

## Caso 01 — Login

Validar:

- Usuario correcto.
- Password incorrecto.
- Usuario bloqueado.
- Campos requeridos.
- Redirección.
- Sesión.

## Caso 02 — CRUD

```text
Crear
 ↓
Consultar
 ↓
Modificar
 ↓
Eliminar
```

## Caso 03 — API + UI

```text
Crear registro por API
        ↓
Abrir UI
        ↓
Buscar registro
        ↓
Validar datos
```

---

# 21. Debugging

Debe ser una sección fuerte.

Incluir:

- Headed.
- Inspector.
- Trace Viewer.
- Screenshots.
- Network.
- Console.
- Logs.

Flujo:

```text
Test falla
   ↓
¿Locator?
   ↓
¿Datos?
   ↓
¿Network?
   ↓
¿Timing?
   ↓
¿Ambiente?
   ↓
Trace
```

---

# Resultados y reportes de pruebas

La guía debe incluir contenido educativo sobre cómo interpretar y utilizar los resultados de ejecución de Playwright.

Cubrir:

- Passed.
- Failed.
- Skipped.
- Duration.
- Error message.
- Stack trace.
- Screenshot de fallo.
- Video.
- Trace.
- HTML Report.
- JUnit.
- JSON cuando aplique.
- Evidencias generadas.
- Resultados en ejecución local.
- Resultados en CI/CD.

Explicar diferencias según lenguaje y test runner.

Ejemplo conceptual:

```text
Test Run

10 Passed
2 Failed
1 Skipped
```

El objetivo de esta sección es enseñar al QA a:

- Leer resultados.
- Investigar fallos.
- Identificar evidencia útil.
- Diferenciar un fallo funcional de un problema de automatización.
- Utilizar reportes y traces para diagnóstico.

Esta sección es **informativa** y no implica crear tests para probar la propia página de la guía.

---

# 23. CI/CD

Para MVP:

```text
Checkout
   ↓
Install
   ↓
Build
   ↓
Content validation
   ↓
Publish
```

Posteriormente:

```text
Validate C#
Validate TS
Validate Python
Validate Java
Validate JS
```

---

# 24. Estructura propuesta del repositorio

```text
playwright-guide/
│
├── site/
│   ├── src/
│   │   ├── content/
│   │   │   ├── fundamentos/
│   │   │   ├── web/
│   │   │   ├── api/
│   │   │   ├── framework/
│   │   │   ├── debugging/
│   │   │   ├── cicd/
│   │   │   ├── selenium/
│   │   │   └── advanced/
│   │   │
│   │   ├── components/
│   │   │   ├── CodeExample
│   │   │   ├── LanguageTabs
│   │   │   ├── BestPractice
│   │   │   ├── BadPractice
│   │   │   ├── ModuleHeader
│   │   │   └── Progress
│   │   │
│   │   └── styles/
│   │
│   └── Astro + Starlight
│
├── snippets/
│   ├── dotnet/
│   └── typescript/
│
└── playground/
```

---

# 25. Diseño UX/UI

La página debe sentirse como documentación técnica.

## Desktop

```text
┌─────────────┬──────────────────────────────────┬────────────┐
│ Sidebar     │ Contenido                        │ TOC        │
│             │                                  │            │
│             │ Texto                            │            │
│             │                                  │            │
│             │ ┌──────────────────────────────┐ │            │
│             │ │ C# TS JS Java Python        │ │            │
│             │ │                              │ │            │
│             │ │ Código                       │ │            │
│             │ │                              │ │            │
│             │ └──────────────────────────────┘ │            │
└─────────────┴──────────────────────────────────┴────────────┘
```

Prioridades:

- Lectura.
- Código.
- Navegación.
- Búsqueda.
- Comprensión.

Evitar:

- Gradientes excesivos.
- Animaciones decorativas.
- Cards innecesarias.
- Exceso de iconos.
- Apariencia de landing comercial.

---

# 26. Fases

## MVP

- Astro + Starlight.
- 12 módulos.
- C# completo.
- TypeScript completo.
- Tabs de lenguaje.
- Búsqueda.
- Dark/light mode.
- Progreso.
- Selenium → Playwright.
- Catálogo de errores.
- Buenas/malas prácticas.
- Playground básico.

## V2

- Python validado.
- Quiz.
- Ejercicios.
- Favoritos.
- Más casos prácticos.
- Visual testing.
- Paralelismo.
- Docker.

## V3

- Java.
- JavaScript completo.
- Component Testing.
- Agentes.
- MCP.
- IA.
- Playground avanzado.
- Estadísticas.
- Cuenta de usuario si realmente aporta valor.

---


# Restricción sobre pruebas de la propia página

La guía **no debe incluir pruebas automatizadas de la propia web como parte del alcance de construcción inicial**.

No generar ni mantener para este proyecto, salvo que se solicite después:

- Suite E2E para validar la propia guía.
- Tests de navegación de la página.
- Tests para dark mode.
- Tests para tabs de lenguaje.
- Tests para progreso.
- Tests para responsive.
- Tests para buscador.
- Tests visuales de la propia guía.
- Reportes de ejecución de la propia web.

Motivo:

> Evitar consumo innecesario de tiempo, ejecución y tokens durante la construcción inicial.

Esto **no significa eliminar el contenido educativo sobre resultados de pruebas de Playwright**.

La guía sí debe explicar, como información para el usuario:

- Test Results.
- Passed.
- Failed.
- Skipped.
- Reporters.
- HTML Report.
- JUnit.
- JSON cuando aplique.
- Screenshots.
- Videos.
- Traces.
- Evidencias de fallos.
- Resultados en CI/CD.
- Publicación de resultados en herramientas como Azure DevOps cuando corresponda.

La diferencia es:

```text
SÍ:
Enseñar cómo Playwright maneja y presenta resultados de pruebas.

NO:
Crear pruebas automatizadas para comprobar que la propia guía web funciona.
```


# 27. Regla de producto

No desarrollar todos los módulos de una vez.

Primero construir un módulo piloto:

```text
LOCATORS
```

Debe contener:

- Diseño definitivo.
- C#.
- TypeScript.
- Tabs.
- Buenas prácticas.
- Malas prácticas.
- Explicación.
- Caso QA.
- Búsqueda.
- Progreso.
- Responsive.

Si el módulo piloto funciona correctamente, se utilizará como molde para los demás.

---

# 28. Resultado esperado

La primera versión debe permitir que un QA pueda:

```text
Entrar
 ↓
Elegir una ruta
 ↓
Aprender un concepto
 ↓
Ver código
 ↓
Cambiar lenguaje
 ↓
Entender por qué funciona
 ↓
Ver qué no debe hacer
 ↓
Probarlo
 ↓
Consultar errores
 ↓
Continuar aprendiendo
```

La prioridad no es tener la mayor cantidad de contenido.

La prioridad es tener una guía técnica:

- Clara.
- Correcta.
- Actualizable.
- Útil.
- Mantenible.
- Profesional.
