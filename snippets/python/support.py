"""Soporte compartido de los snippets de Python: las mismas páginas y sitios de práctica que usan
TypeScript (`typescript/support.ts`) y C# (`dotnet/Support`). Nada sale a la red."""

import json
import os
import re
from pathlib import Path
from urllib.parse import urlparse

from playwright.sync_api import Browser, BrowserContext, Route

FIXTURES = Path(__file__).resolve().parent.parent / "fixtures"


def fixture(name: str) -> str:
    """Página de práctica de `snippets/fixtures`."""
    return (FIXTURES / f"{name}.html").read_text(encoding="utf-8")


def fixture_url(name: str) -> str:
    """URL `file://` de la página de práctica: la abren igual Selenium y Playwright."""
    return (FIXTURES / f"{name}.html").as_uri()


# Origen ficticio del sitio de práctica `fixtures/tienda`.
TIENDA = "https://tienda.test"

_TIENDA_PAGES = {
    "/": "index",
    "/productos": "productos",
    "/login": "login",
    "/cuenta": "cuenta",
    "/cuenta/pedidos": "cuenta",
}


def serve_tienda(context: BrowserContext) -> None:
    """Sirve `fixtures/tienda` en https://tienda.test interceptando las peticiones del contexto."""

    def handle(route: Route) -> None:
        page = _TIENDA_PAGES.get(urlparse(route.request.url).path)
        route.fulfill(
            status=200 if page else 404,
            content_type="text/html; charset=utf-8",
            body=fixture(f"tienda/{page or 'no-encontrada'}"),
        )

    context.route(f"{TIENDA}/**", handle)


# API de pedidos de `server/api-de-practica.mjs`; la arranca el fixture `api_practica` de conftest.py.
API_PRACTICA = f"http://127.0.0.1:{os.environ.get('API_PRACTICA_PORT', '4789')}"

# Sitio de práctica `fixtures/sesion` (Portal QA). Cuentas y claves ficticias.
PORTAL = "https://portal.test"
_SESION = FIXTURES / "sesion"
CUENTAS = json.loads((_SESION / "cuentas.json").read_text(encoding="utf-8"))


def _sesion(name: str) -> str:
    return (_SESION / name).read_text(encoding="utf-8")


def serve_portal(context: BrowserContext) -> None:
    """Simula el servidor: login con cookie de sesión y un panel privado que manda a /login sin ella."""

    def handle(route: Route) -> None:
        request = route.request
        path = urlparse(request.url).path

        if path == "/api/login" and request.method == "POST":
            datos = request.post_data_json
            cuenta = CUENTAS.get(datos["usuario"])
            if not cuenta or cuenta["clave"] != datos["clave"]:
                return route.fulfill(status=401, json={"error": "credenciales"})
            return route.fulfill(
                status=200,
                headers={"Set-Cookie": f"sesion={cuenta['token']}; Path=/; HttpOnly; Secure; SameSite=Lax"},
                json={"nombre": cuenta["nombre"]},
            )

        if path == "/login":
            return route.fulfill(content_type="text/html; charset=utf-8", body=_sesion("login.html"))

        if path == "/panel":
            match = re.search(r"(?:^|;\s*)sesion=([^;]+)", request.header_value("cookie") or "")
            token = match.group(1) if match else None
            cuenta = next((c for c in CUENTAS.values() if c["token"] == token), None)
            if not cuenta:
                return route.fulfill(status=401, content_type="text/html; charset=utf-8", body=_sesion("sin-sesion.html"))
            html = _sesion("panel.html").replace("{{nombre}}", cuenta["nombre"]).replace("{{rol}}", cuenta["rol"])
            if cuenta["rol"] != "admin":
                html = re.sub(r"<!-- admin -->[\s\S]*<!-- /admin -->", "", html)
            return route.fulfill(content_type="text/html; charset=utf-8", body=html)

        return route.fulfill(status=404, content_type="text/plain; charset=utf-8", body="No encontrada")

    context.route(f"{PORTAL}/**", handle)


def save_session(browser: Browser, usuario: str, path: Path) -> None:
    """Inicia sesión por la interfaz en un contexto nuevo y guarda su estado en `path`."""
    context = browser.new_context(base_url=PORTAL)
    serve_portal(context)
    page = context.new_page()
    page.goto("/login")
    page.get_by_label("Usuario").fill(usuario)
    page.get_by_label("Contraseña").fill(CUENTAS[usuario]["clave"])
    page.get_by_role("button", name="Ingresar").click()
    page.wait_for_url("**/panel")
    context.storage_state(path=path)
    context.close()
