// Service Worker for Nedian Connect Institute Desktop & Mobile PWA
const CACHE_NAME = 'nedian-connect-v1';

self.addEventListener('install', (event) => {
  self.skipWaiting();
});

self.addEventListener('activate', (event) => {
  event.waitUntil(self.clients.claim());
});

self.addEventListener('fetch', (event) => {
  // Network first strategy with offline fallback
  event.respondWith(
    fetch(event.request).catch(() => caches.match(event.request))
  );
});
