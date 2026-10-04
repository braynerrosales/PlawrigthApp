/**
 * Progreso local (localStorage, sin backend), compartido por los scripts de cliente de
 * Progress, el sidebar y la portada. Un módulo se identifica por su id de entrada (`web/locators`).
 */
const KEY = 'pw-guide:progress';
const CHANGE_EVENT = 'pw-guide:progress-change';

export function readProgress(): Set<string> {
	try {
		const value = JSON.parse(localStorage.getItem(KEY) ?? '[]');
		return new Set(Array.isArray(value) ? value.filter((v) => typeof v === 'string') : []);
	} catch {
		return new Set();
	}
}

export function toggleModule(id: string) {
	const done = readProgress();
	if (!done.delete(id)) done.add(id);
	try {
		localStorage.setItem(KEY, JSON.stringify([...done]));
	} catch {}
	document.dispatchEvent(new Event(CHANGE_EVENT));
}

/** Ejecuta `render` ahora y cada vez que cambia el progreso (en esta pestaña o en otra). */
export function onProgressChange(render: (done: Set<string>) => void) {
	const run = () => render(readProgress());
	document.addEventListener(CHANGE_EVENT, run);
	window.addEventListener('storage', (event) => event.key === KEY && run());
	run();
}

/** Marca con `data-done` cada elemento con `data-progress-module` dentro de `root`. */
export function markDone(root: ParentNode, done: Set<string>) {
	for (const el of root.querySelectorAll<HTMLElement>('[data-progress-module]')) {
		el.dataset.done = String(done.has(el.dataset.progressModule!));
	}
}
