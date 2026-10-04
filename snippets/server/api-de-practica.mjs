// API de práctica para los snippets de "API y Network": una API de pedidos en memoria y una página
// que la consume con fetch. Node puro, sin dependencias. La comparten TypeScript (webServer en
// playwright.config.ts) y C# (Support/ApiDePractica.cs). Puerto: API_PRACTICA_PORT o 4789.
//
// Las pruebas corren en paralelo contra el mismo proceso: cada una crea sus propios pedidos (ids
// únicos) y solo borra los suyos. Los pedidos semilla (1-3) no se borran ni se modifican.
import { createServer } from 'node:http';

const PORT = Number(process.env.API_PRACTICA_PORT ?? 4789);
const HOST = '127.0.0.1';

const SEMILLA = [
	{ id: 1, cliente: 'Ana Torres', producto: 'Teclado', cantidad: 1, estado: 'pendiente' },
	{ id: 2, cliente: 'Luis Gómez', producto: 'Monitor', cantidad: 2, estado: 'pendiente' },
	{ id: 3, cliente: 'Marta Ruiz', producto: 'Ratón', cantidad: 3, estado: 'pendiente' },
];
const pedidos = new Map(SEMILLA.map((p) => [p.id, p]));
let siguienteId = SEMILLA.length + 1;

/** Devuelve la lista de errores de validación de un pedido nuevo (vacía si es válido). */
function validar(datos) {
	const errores = [];
	if (typeof datos?.cliente !== 'string' || !datos.cliente.trim()) errores.push('cliente es obligatorio');
	if (typeof datos?.producto !== 'string' || !datos.producto.trim()) errores.push('producto es obligatorio');
	if (!Number.isInteger(datos?.cantidad) || datos.cantidad < 1) errores.push('cantidad debe ser un entero mayor que 0');
	return errores;
}

function json(res, status, cuerpo, cabeceras = {}) {
	res.writeHead(status, { 'content-type': 'application/json; charset=utf-8', ...cabeceras });
	res.end(JSON.stringify(cuerpo));
}

async function leerJson(req) {
	let texto = '';
	for await (const trozo of req) texto += trozo;
	try {
		return JSON.parse(texto);
	} catch {
		return undefined;
	}
}

// PNG transparente de 1x1 píxel: la imagen de la promoción.
const PNG = Buffer.from(
	'iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=',
	'base64',
);

const HTML = /* html */ `<!doctype html>
<html lang="es">
<head>
	<meta charset="utf-8">
	<title>Pedidos</title>
	<style>
		body { font-family: system-ui, sans-serif; margin: 2rem; }
		table { border-collapse: collapse; margin-block: 1rem; }
		th, td { border: 1px solid #ccc; padding: .25rem .5rem; text-align: left; }
		form { display: grid; gap: .5rem; max-width: 20rem; }
	</style>
</head>
<body>
	<h1>Pedidos</h1>
	<figure>
		<img src="/img/promo.png" alt="Promoción de la semana" width="120" height="40">
		<figcaption id="promo-estado"></figcaption>
	</figure>

	<p id="estado" role="status">Cargando pedidos…</p>
	<table hidden>
		<thead><tr><th>Id</th><th>Cliente</th><th>Producto</th><th>Cantidad</th><th>Estado</th></tr></thead>
		<tbody></tbody>
	</table>

	<h2>Nuevo pedido</h2>
	<form>
		<label>Cliente <input name="cliente"></label>
		<label>Producto <input name="producto"></label>
		<label>Cantidad <input name="cantidad" type="number" value="1"></label>
		<button type="submit">Crear pedido</button>
	</form>
	<p id="resultado" role="alert"></p>

	<script>
		const estado = document.querySelector('#estado');
		const tabla = document.querySelector('table');
		const cuerpo = document.querySelector('tbody');
		const resultado = document.querySelector('#resultado');

		document.querySelector('img').addEventListener('error', () => {
			document.querySelector('#promo-estado').textContent = 'Imagen no disponible';
		});

		function agregarFila(p) {
			const fila = document.createElement('tr');
			for (const valor of [p.id, p.cliente, p.producto, p.cantidad, p.estado]) {
				const celda = document.createElement('td');
				celda.textContent = valor;
				fila.append(celda);
			}
			cuerpo.append(fila);
		}

		async function cargarPedidos() {
			try {
				const respuesta = await fetch('/api/pedidos');
				if (!respuesta.ok) throw new Error(String(respuesta.status));
				const pedidos = await respuesta.json();
				cuerpo.replaceChildren();
				pedidos.forEach(agregarFila);
				tabla.hidden = pedidos.length === 0;
				estado.textContent = pedidos.length === 0 ? 'No hay pedidos todavía.' : pedidos.length + (pedidos.length === 1 ? ' pedido' : ' pedidos');
			} catch {
				tabla.hidden = true;
				estado.textContent = 'No se pudieron cargar los pedidos. Inténtalo de nuevo más tarde.';
			}
		}

		document.querySelector('form').addEventListener('submit', async (evento) => {
			evento.preventDefault();
			const datos = new FormData(evento.target);
			const respuesta = await fetch('/api/pedidos', {
				method: 'POST',
				headers: { 'content-type': 'application/json' },
				body: JSON.stringify({
					cliente: datos.get('cliente'),
					producto: datos.get('producto'),
					cantidad: Number(datos.get('cantidad')),
				}),
			});
			const cuerpoRespuesta = await respuesta.json();
			if (respuesta.status === 201) {
				agregarFila(cuerpoRespuesta);
				tabla.hidden = false;
				resultado.textContent = 'Pedido #' + cuerpoRespuesta.id + ' creado';
			} else {
				resultado.textContent = 'Revisa el pedido: ' + cuerpoRespuesta.errores.join(', ');
			}
		});

		cargarPedidos();
	</script>
</body>
</html>`;

const RUTA_PEDIDO = /^\/api\/pedidos\/(\d+)$/;

const servidor = createServer(async (req, res) => {
	const { pathname } = new URL(req.url ?? '/', `http://${HOST}`);

	if (req.method === 'GET' && pathname === '/') {
		res.writeHead(200, { 'content-type': 'text/html; charset=utf-8' });
		return res.end(HTML);
	}
	if (req.method === 'GET' && pathname === '/img/promo.png') {
		res.writeHead(200, { 'content-type': 'image/png' });
		return res.end(PNG);
	}

	if (pathname === '/api/pedidos') {
		if (req.method === 'GET') return json(res, 200, [...pedidos.values()]);
		if (req.method === 'POST') {
			const datos = await leerJson(req);
			const errores = validar(datos);
			if (errores.length) return json(res, 400, { errores });
			const pedido = {
				id: siguienteId++,
				cliente: datos.cliente.trim(),
				producto: datos.producto.trim(),
				cantidad: datos.cantidad,
				estado: 'pendiente',
			};
			pedidos.set(pedido.id, pedido);
			return json(res, 201, pedido, { location: `/api/pedidos/${pedido.id}` });
		}
		return json(res, 405, { errores: ['método no permitido'] });
	}

	const coincidencia = pathname.match(RUTA_PEDIDO);
	if (coincidencia) {
		const id = Number(coincidencia[1]);
		const pedido = pedidos.get(id);
		if (req.method === 'GET') return pedido ? json(res, 200, pedido) : json(res, 404, { errores: ['pedido no encontrado'] });
		if (req.method === 'DELETE') {
			if (!pedido) return json(res, 404, { errores: ['pedido no encontrado'] });
			if (id <= SEMILLA.length) return json(res, 403, { errores: ['los pedidos de ejemplo no se pueden borrar'] });
			pedidos.delete(id);
			res.writeHead(204);
			return res.end();
		}
		return json(res, 405, { errores: ['método no permitido'] });
	}

	json(res, 404, { errores: ['ruta no encontrada'] });
});

servidor.listen(PORT, HOST, () => {
	console.log(`API de práctica en http://${HOST}:${PORT}`);
});

// Cierre limpio cuando el runner (Playwright o el SetUpFixture de C#) detiene el proceso.
for (const senal of ['SIGINT', 'SIGTERM']) process.on(senal, () => process.exit(0));
