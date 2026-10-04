import subprocess
import time
import urllib.request
from pathlib import Path

import pytest

from support import API_PRACTICA


def _responde() -> bool:
    try:
        with urllib.request.urlopen(f"{API_PRACTICA}/api/pedidos", timeout=1) as response:
            return response.status == 200
    except OSError:
        return False


@pytest.fixture(scope="session")
def api_practica():
    """Arranca `server/api-de-practica.mjs` (el mismo script que usan TS y C#); si ya responde, lo reutiliza."""
    if _responde():
        yield API_PRACTICA
        return
    script = Path(__file__).resolve().parent.parent / "server" / "api-de-practica.mjs"
    server = subprocess.Popen(["node", str(script)], stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL)
    try:
        limite = time.monotonic() + 15
        while not _responde():
            if server.poll() is not None or time.monotonic() > limite:
                raise RuntimeError(f"La API de práctica no respondió en {API_PRACTICA}.")
            time.sleep(0.1)
        yield API_PRACTICA
    finally:
        server.kill()
        server.wait(timeout=5)
