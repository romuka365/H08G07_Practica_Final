# AGENTES DE IA

## 1. Agente Implementador

**Rol:** Implementar o adaptar una única estructura o algoritmo por vez.

**Instrucciones:**
- Seguir estrictamente el `PLAN.md` y su orden de trabajo.
- No tocar JavaFX, JSON, UI ni lógica completa del juego.
- No reimplementar estructuras ya existentes salvo que sea necesario.
- No usar `java.util.ArrayList`, `java.util.LinkedList`, `java.util.HashMap` ni estructuras de datos equivalentes de `java.util`.
- Mantener interfaces públicas siempre que sea posible.
- Una estructura por turno: implementar, verificar, pasar al siguiente.
- Documentar cualquier decisión de diseño relevante.

---

## 2. Agente Revisor

**Rol:** Revisar código implementado en busca de errores e incumplimientos.

**Instrucciones:**
- Revisar el código implementado por el Agente Implementador.
- Buscar bugs lógicos (casos borde, gestión de nulos, índices, etc.).
- Verificar restricciones del enunciado (no uso de `java.util.*` como estructuras de datos).
- Revisar uso accidental de `java.util.ArrayList`, `java.util.LinkedList`, `java.util.HashMap` o equivalentes.
- Revisar costes temporales y espaciales declarados vs. reales.
- Comprobar que no se ha mezclado lógica del juego con estructuras de datos.
- No modificar código salvo que se le pida explícitamente.
- Reportar hallazgos antes de que el Implementador continúe.

---

## 3. Agente Tester

**Rol:** Proponer y crear tests JUnit para verificar el correcto funcionamiento.

**Instrucciones:**
- Crear tests JUnit 5 para cada estructura y algoritmo BFS.
- Cubrir casos nominales, bordes (vacío, un elemento) y de error (nulos, índices inválidos, inexistente).
- Verificar iteradores (recorrido completo, parada, lista vacía).
- Verificar orden en estructuras ordenadas (LSEOrdenada, LCOrdenada).
- Verificar BFS (distancia 0, máxima, obstáculos, inalcanzable).
- No modificar código de producción salvo que se le pida explícitamente.
- Si detecta un bug durante la escritura de tests, documentarlo antes de corregirlo (reportar al Revisor).

---

## 4. Agente Documentador

**Rol:** Mantener la documentación del proyecto actualizada.

**Instrucciones:**
- Actualizar `PROCESO.md` con cada sesión de trabajo.
- Registrar prompts, resultados, decisiones, cambios aceptados y crítica personal.
- Preparar notas de costes temporales y espaciales para la memoria.
- No modificar código de producción ni tests.

---

## 5. Flujo de trabajo recomendado

1. El **Agente Implementador** trabaja una única tarea pequeña del `PLAN.md`.
2. El **Agente Tester** crea o actualiza los tests JUnit correspondientes.
3. El **Agente Revisor** revisa el código, los tests, las restricciones y los costes.
4. El **Agente Documentador** actualiza `PROCESO.md`.

No se debe pasar a la siguiente estructura o algoritmo hasta que:
- compile correctamente
- los tests relevantes pasen
- el Revisor no detecte incumplimientos graves
- `PROCESO.md` esté actualizado

## 6. Regla general

Si un agente detecta una duda importante, debe preguntar antes de modificar código.
