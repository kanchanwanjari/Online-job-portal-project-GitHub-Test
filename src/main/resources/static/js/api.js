// Base URL - since the frontend is served by the same Spring Boot app, a relative path works.
const API_BASE = '/api';

async function apiGet(path) {
    const res = await fetch(API_BASE + path);
    if (!res.ok) throw new Error(await res.text());
    return res.json();
}

async function apiPost(path, body) {
    const res = await fetch(API_BASE + path, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
    });
    const text = await res.text();
    let data;
    try { data = text ? JSON.parse(text) : {}; } catch { data = { message: text }; }
    if (!res.ok) throw new Error(data.message || 'Request failed');
    return data;
}

async function apiPut(path, body) {
    const res = await fetch(API_BASE + path, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
    });
    const text = await res.text();
    let data;
    try { data = text ? JSON.parse(text) : {}; } catch { data = { message: text }; }
    if (!res.ok) throw new Error(data.message || 'Request failed');
    return data;
}

async function apiDelete(path) {
    const res = await fetch(API_BASE + path, { method: 'DELETE' });
    if (!res.ok) throw new Error(await res.text());
}

function showMsg(el, message, type) {
    el.textContent = message;
    el.className = 'msg ' + type;
    el.classList.remove('hidden');
}

// ---- simple session helpers (sessionStorage, per-tab) ----
function saveSession(session) {
    sessionStorage.setItem('jobportal_session', JSON.stringify(session));
}
function getSession() {
    const raw = sessionStorage.getItem('jobportal_session');
    return raw ? JSON.parse(raw) : null;
}
function clearSession() {
    sessionStorage.removeItem('jobportal_session');
}
function requireSession(role, redirectTo) {
    const s = getSession();
    if (!s || s.role !== role) {
        window.location.href = redirectTo;
        return null;
    }
    return s;
}
