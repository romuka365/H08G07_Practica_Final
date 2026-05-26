# PLAN DE TRABAJO — ESTRUCTURAS DE DATOS

## 1. Objetivo

Implementar, revisar y documentar las siguientes estructuras de datos y algoritmos correspondientes a la parte asignada del proyecto **JuegoTrabajoFinal**:

| Estructura/Algoritmo | Estado actual |
|---|---|
| MyLinkedList | Implementada |
| MyQueue | Implementada |
| MyStack | Implementada |
| MyCircularList | Implementada |
| MyTree | Implementada |
| MyGraph | Implementada |
| MyMatrix | No implementada |
| BFS casillas alcanzables | No implementada |
| BFS habitaciones hasta salida | No implementada |
| Tests JUnit | No implementados |
| Justificación costes (memoria) | No realizada |

---

## 2. Estado de cada estructura

### 2.1 MyLinkedList (`src/MyLinkedList/`) — ✅ Implementada (Falta Revisar)

**Archivos existentes:**
- `ListaSE.java` — Lista simplemente enlazada genérica. Implementa `ListaInterfaz<T>`.
- `LSEOrdenada.java` — Extiende `ListaSE`, inserción ordenada.
- `ElementoSE.java` — Nodo con dato + puntero al siguiente.
- `IteradorLSE.java` — Iterador sobre la lista.

**Problemas detectados:**
- No tiene un `get(int index)` para acceso posicional (necesario para algunas operaciones del juego). Actualmente solo tiene `get(T dato)` por búsqueda de valor.
- Falta verificar gestión de nulos en casos borde.

**Acciones:**
1. Revisar implementación completa.
2. Añadir método `get(int index)` si se considera necesario.
3. Verificar robustez (casos vacío, un elemento, etc.).
4. Documentar costes.

### 2.2 MyQueue (`src/MyQueue/`) — ✅ Implementada (Falta Revisar)

**Archivos existentes:**
- `Cola.java` — Cola genérica con punteros a frente y último. Implementa `ColaInterfaz<T>`.
- `ElementoCola.java` — Nodo de la cola.

**Problemas detectados:** Ninguno aparente.

**Acciones:**
1. Revisar implementación completa.
2. Verificar que `desencolar()` y `frente()` devuelven `null` en cola vacía (correcto).
3. Documentar costes.

### 2.3 MyStack (`src/MyStack/`) — ✅ Implementada (Falta Revisar)

**Archivos existentes:**
- `Pila.java` — Pila genérica (LIFO). Implementa `PilaInterfaz<T>`.
- `ElementoPila.java` — Nodo de la pila.

**Problemas detectados:** Ninguno aparente.

**Acciones:**
1. Revisar implementación completa.
2. Verificar que `pop()` y `top()` devuelven `null` en pila vacía (correcto).
3. Documentar costes.

### 2.4 MyCircularList (`src/MyCircularList/`) — ✅ Implementada (Falta Revisar)

**Archivos existentes:**
- `ListaCircular.java` — Lista circular genérica. Implementa `ListaCircularInterfaz<T>`.
- `LCOrdenada.java` — Extiende `ListaCircular`, inserción ordenada.
- `ElementoLC.java` — Nodo de la lista circular.
- `IteradorLC.java` — Iterador con detección de primera vuelta.

**Problemas detectados:**
- **BUG en `IteradorLC.java:38`:** Punto y coma erróneo tras `if(actual == primero);` → `primeraVuelta = false` se ejecuta SIEMPRE, no solo cuando se vuelve al primero. Rompe la lógica del iterador.
- **BUG en `LCOrdenada.java:40-44`:** En el caso donde `puntero == ultimo`, el método hace `tamaño++` dentro del bloque (línea 43) y luego vuelve a hacer `tamaño++` fuera (línea 47), duplicando el incremento.

**Acciones:**
1. Corregir bug del punto y coma en `IteradorLC.java`.
2. Corregir bug de doble incremento en `LCOrdenada.java`.
3. Revisar el resto de la implementación.
4. Documentar costes.

### 2.5 MyTree (`src/MyTree/`) — ✅ Implementada (Falta Revisar)

**Archivos existentes:**
- `ArbolBinarioDeBusqueda.java` — Árbol binario de búsqueda genérico con recorridos (in-order, pre-order, post-order), altura, grado, homogeneidad, completitud, camino.
- `ArbolBinarioDeBusquedaEnteros.java` — Extiende el BST con `Integer`, añade `getSuma()`.
- `Nodo.java` — Nodo del árbol con hijo izquierdo y derecho.

**Problemas detectados:**
- No se importan estructuras prohibidas (bien).
- Los `Main.java` de prueba tienen imports rotos a paquetes inexistentes (no tocar, son auxiliares).

**Acciones:**
1. Revisar implementación completa.
2. Verificar casos borde (árbol vacío, un nodo, dos nodos, etc.).
3. Documentar costes.

### 2.6 MyGraph (`src/MyGraph/`) — ✅ Implementada (Falta Revisar)

**Archivos existentes:**
- `Grafo.java` — Grafo con lista de vértices, aristas, carga desde JSON.
- `Vertice.java` — Vértice con nombre, tipo, id, lista de aristas.
- `Arista.java` — Arista con idDestino y predicado.
- `BuscadorGrafo.java` — Consultas sobre el grafo.
- `CaminoMinimo.java` — Algoritmo Dijkstra con arrays de tamaño fijo.
- `DatosGrafo.java` — DTO para deserialización JSON.
- `TripletaJson.java` — DTO para tripletas JSON.
- `ListaSimple/` — Implementación duplicada de lista enlazada (propia del paquete del grafo).

**Problemas detectados:**
- **BUG en `CaminoMinimo.java:14`:** `totalVertices = 500` hardcodeado → `ArrayIndexOutOfBoundsException` si el grafo supera 500 vértices.
- `DatosGrafo.java` usa `java.util.List` para la deserialización con Gson (necesario para Gson, no se puede evitar fácilmente).
- Existe `MyGraph.ListaSimple.ListaSimple` que es código duplicado de `MyLinkedList.ListaSE`.

**Acciones:**
1. Corregir `CaminoMinimo.java` para usar tamaño dinámico basado en `grafo.getNumVertices()` o calcular `maxId`.
2. Revisar el resto de la implementación.
3. Añadir BFS sobre el grafo (para habitaciones hasta salida).
4. Documentar costes.

### 2.7 MyMatrix (`src/MyMatrix/`) — ❌ No implementada

**Descripción:** Matriz bidimensional genérica para representar el tablero de cada habitación del juego. Cada celda contendrá un tipo que representa el contenido (vacía, enemigo, objeto, puerta, trampa, jugador, etc.).

**Especificación:**
- Almacenamiento interno: array bidimensional nativo (`T[][]`) — permitido al no ser de `java.util`.
- Alternativa viable: array unidimensional (`T[]`) con cálculo de índice `i * columnas + j`.
- Genérica para que el juego pueda definir su propio tipo de celda.

**Operaciones:**
| Método | Descripción |
|---|---|
| `MyMatrix(int filas, int columnas)` | Constructor con dimensiones |
| `MyMatrix(int filas, int columnas, T valorInicial)` | Constructor con valor por defecto |
| `T get(int fila, int columna)` | Obtener valor en posición |
| `void set(int fila, int columna, T valor)` | Establecer valor en posición |
| `int getFilas()` | Número de filas |
| `int getColumnas()` | Número de columnas |
| `boolean posicionValida(int fila, int columna)` | Verificar si coordenadas están dentro de la matriz |
| `ListaSE<Coordenada> getVecinos(int fila, int columna)` | Devuelve las 4 posiciones adyacentes (arriba, abajo, izquierda, derecha) que sean válidas |
| `String toString()` | Representación textual |

**Clase auxiliar `Coordenada`:**
- `int fila`, `int columna`
- Constructor, getters, `equals()`, `toString()`

**Costes esperados:**
- `get/set`: **O(1)**
- `posicionValida`: **O(1)**
- `getVecinos`: **O(1)** (máximo 4 comprobaciones)
- Espacio: **O(filas × columnas)**

### 2.8 BFS Casillas Alcanzables — ❌ No implementada

**Ubicación propuesta:** `src/MyMatrix/BFSMatriz.java`

**Descripción:** Dada una matriz (tablero de habitación), una posición inicial y una distancia máxima de movimiento, encuentra todas las casillas alcanzables usando BFS. El movimiento solo está permitido en 4 direcciones (arriba, abajo, izquierda, derecha). Cada paso cuesta 1 punto de movimiento.

**Algoritmo:**
1. Usar una `MyQueue` para la cola del BFS.
2. Usar una `MyMatrix<Boolean>` o array `boolean[][]` para visitados.
3. Partir de la posición inicial con distancia 0.
4. Explorar las 4 direcciones, encolando posiciones no visitadas cuya distancia acumulada ≤ movimiento máximo.
5. Devolver lista de posiciones alcanzables.

**Especificación:**
| Método | Descripción |
|---|---|
| `BFSMatriz(MyMatrix<T> matriz)` | Constructor |
| `ListaSE<Coordenada> casillasAlcanzables(Coordenada inicio, int maxMovimiento)` | BFS principal |

**Costes esperados:**
- Tiempo: **O(filas × columnas)** — peor caso: visitar toda la matriz.
- Espacio: **O(filas × columnas)** — cola y matriz de visitados.

### 2.9 BFS Habitaciones Hasta Salida — ❌ No implementada

**Ubicación propuesta:** `src/MyGraph/BFSGrafo.java`

**Descripción:** Dado el grafo de habitaciones, encuentra el camino más corto desde la habitación actual hasta una habitación de salida usando BFS. No requiere pesos (aristas sin peso o peso uniforme).

**Algoritmo:**
1. Usar una `MyQueue` para la cola del BFS.
2. Usar una `MyLinkedList` o array de visitados.
3. Usar un array de predecesores para reconstruir el camino.
4. Partir del vértice (habitación) actual.
5. Explorar vecinos hasta encontrar una salida.
6. Devolver el camino como lista de vértices/habitaciones.

**Especificación:**
| Método | Descripción |
|---|---|
| `BFSGrafo(Grafo grafo)` | Constructor |
| `ListaSE<Vertice> buscarSalida(String tipoOrigen, String nombreOrigen, String tipoSalida)` | BFS desde origen hasta cualquier vértice con tipoSalida |
| `ListaSE<Vertice> getCamino(String tipoOrigen, String nombreOrigen, String tipoDestino, String nombreDestino)` | BFS entre dos vértices específicos |

**Costes esperados:**
- Tiempo: **O(V + E)** — BFS estándar sobre grafo.
- Espacio: **O(V)** — cola, visitados, predecesores.

### 2.10 Tests JUnit — ❌ No implementados

**Ubicación propuesta:** `src/test/java/` (estructura Maven estándar)

Se crearán tests unitarios con **JUnit 5** para todas las estructuras:

| Test | Estructura |
|---|---|
| `MyLinkedListTest.java` | ListaSE, LSEOrdenada |
| `MyQueueTest.java` | Cola |
| `MyStackTest.java` | Pila |
| `MyCircularListTest.java` | ListaCircular, LCOrdenada, IteradorLC |
| `MyTreeTest.java` | ArbolBinarioDeBusqueda, ArbolBinarioDeBusquedaEnteros |
| `MyGraphTest.java` | Grafo, Vertice, Arista, CaminoMinimo |
| `MyMatrixTest.java` | MyMatrix, Coordenada |
| `BFSMatrizTest.java` | BFS casillas alcanzables |
| `BFSGrafoTest.java` | BFS habitaciones hasta salida |

**Cobertura mínima por test:**
- **Caso nominal:** operaciones normales sobre estructuras con datos.
- **Caso borde:** estructuras vacías, un solo elemento.
- **Caso error:** posiciones inválidas, valores nulos, eliminación de elementos inexistentes.
- **Estructuras ordenadas:** verificar orden tras inserciones.
- **Iteradores:** recorrido completo, hasNext/next en vacío, reinicio.
- **BFS:** caminos posibles, imposibles, distancia 0, movimiento máximo 0.

Se añadirá un **`pom.xml`** con JUnit 5 y Gson como dependencias para que los tests se ejecuten con Maven.

### 2.11 Justificación de Costes (Memoria) — ❌ No realizada

**Contenido para la memoria:**
Por cada estructura implementada/revisada, se documentará:
- **Estructura utilizada** y descripción breve.
- **Justificación de elección** frente a alternativas.
- **Coste temporal** de cada operación (notación O grande).
- **Coste espacial** de la estructura completa.

Formato tabla para cada estructura:

| Operación | Coste temporal | Explicación |
|---|---|---|
| `add(T)` | O(n) | Recorrido al final |
| `addInicio(T)` | O(1) | Inserción al principio |
| `get(T)` | O(n) | Búsqueda lineal |
| `del(T)` | O(n) | Búsqueda + reenganche |
| `getSize()` | O(1) | Variable mantenida |
| Espacio total | O(n) | n elementos almacenados |

---

## 3. Restricciones

1. **No usar `java.util.ArrayList`**, `java.util.LinkedList`, `java.util.HashMap` ni estructuras equivalentes en el código evaluado de estructuras de datos.
2. **Excepción justificada:** `java.util.List` en `DatosGrafo.java` para deserialización Gson (no es parte evaluada de ED).
3. **Arrays nativos (`T[]`, `T[][]`)** sí permitidos (son del lenguaje, no de `java.util`).
4. **`java.util.Random`** también permitido si es necesario (utilidad, no estructura de datos).
5. Todas las estructuras del juego deben usar las implementaciones propias del proyecto (MyLinkedList, MyQueue, etc.), no las de `java.util`.

---

## 4. Orden de trabajo recomendado

```
Fase 0: Configuración inicial
  ├── 0.1 Crear pom.xml con JUnit 5 + Gson
  └── 0.2 Crear estructura de directorios test/

Fase 1: Revisión y corrección de estructuras existentes
  ├── 1.1 MyStack       — revisar (esperado: sin cambios)
  ├── 1.2 MyQueue       — revisar (esperado: sin cambios)
  ├── 1.3 MyLinkedList  — revisar, añadir get(int index) si procede
  ├── 1.4 MyCircularList — corregir bug IteradorLC.java + LCOrdenada.java
  ├── 1.5 MyTree        — revisar (esperado: sin cambios)
  └── 1.6 MyGraph       — corregir CaminoMinimo (tamaño dinámico)

Fase 2: Implementación de nuevas estructuras
  ├── 2.1 MyMatrix      — crear clase + Coordenada
  ├── 2.2 BFSMatriz     — BFS casillas alcanzables
  └── 2.3 BFSGrafo      — BFS habitaciones hasta salida

Fase 3: Tests JUnit
  ├── 3.1 MyLinkedListTest
  ├── 3.2 MyQueueTest
  ├── 3.3 MyStackTest
  ├── 3.4 MyCircularListTest
  ├── 3.5 MyTreeTest
  ├── 3.6 MyGraphTest
  ├── 3.7 MyMatrixTest
  ├── 3.8 BFSMatrizTest
  └── 3.9 BFSGrafoTest

Fase 4: Documentación
  └── 4.1 Justificación de costes para la memoria
```

---

## 5. Qué NO debe tocarse

| Área | Motivo |
|---|---|
| **JavaFX** (interfaz gráfica) | No corresponde a mi parte |
| **Persistencia JSON** (salvo estructuras) | No tocar `GsonUtilEjemploModificado.java`, `DatosGrafo.java`, `Grafo.cargarGrafoDesdeJson()` |
| **Diseño visual / UI** | No es mi responsabilidad |
| **Lógica completa de combate** | No implementar jugador, enemigos, inventario, combate, sistema de turnos |
| **Main.java** raíz | No tocar (vacío por decisión) |
| **Trabajo_Anteriores/** | Código previo, no evaluado |
| **Interfaces** (`Interfaces/`) | Ya están definidas, no modificarlas |
| **MyGraph.ListaSimple** | Es código duplicado usado internamente por el grafo; no modificarlo a menos que sea necesario para BFS |

**Sí debo tocar superficies mínimas si es necesario:**
- `MyGraph.Grafo` solo si hace falta añadir métodos auxiliares para BFS (ej. obtener vecinos de un vértice).
- `MyGraph.CaminoMinimo` para corregir el bug del tamaño fijo.

---

## 6. Tests JUnit — Cobertura objetivo

| Test | Casos |
|---|---|
| **ListaSE** | add, addInicio, get (existente/inexistente), del (primero/medio/último/inexistente), clear, isEmpty, getSize, iterador, toString |
| **LSEOrdenada** | add ordenado, orden tras múltiples inserciones, lista vacía |
| **Cola** | encolar, desencolar, frente, vacío tras desencolar todo, clear, iteración |
| **Pila** | push, pop, top, vacío tras pop todo, clear |
| **ListaCircular** | add, addInicio, del (único/primero/último/medio), get, circularidad tras operaciones |
| **LCOrdenada** | add ordenado circular, orden tras inserciones |
| **IteradorLC** | recorrido completo, parada al completar vuelta, lista vacía |
| **ArbolBinarioDeBusqueda** | add, getRaiz, isEmpty, getAltura, getGrado, recorridos (ordenCentral, preOrden, postOrden), getListaDatosNivel, isArbolHomogeneo, isArbolCompleto, isArbolCasiCompleto, getCamino, getSubArbol |
| **ArbolBinarioDeBusquedaEnteros** | getSuma, herencia correcta |
| **MyMatrix** | constructor, get, set, posicionValida, getVecinos (esquinas/bordes/centro), toString |
| **BFSMatriz** | casillas alcanzables distancia 0/1/2/n, obstáculos, matriz completa alcanzable, sin alcanzables |
| **BFSGrafo** | camino entre dos vértices, vértice aislado, destino inalcanzable, origen = destino, grafo vacío |

---

## 7. Riesgos

| Riesgo | Mitigación |
|---|---|
| **Uso accidental de `java.util.ArrayList`/`HashMap`/`LinkedList`** | Revisión manual de imports en cada archivo. Uso de `grep` para detectar imports no permitidos. |
| **Mezclar lógica del juego con estructuras** | Mantener las estructuras genéricas y sin dependencias del juego. Usar paquetes separados. |
| **Reimplementar código ya existente** | Las estructuras ya existen; solo revisar, corregir bugs y documentar. MyMatrix y BFS son nuevos. |
| **No dejar trazabilidad para el diario de IA** | Documentar cada prompt, skill, agente usado, resultado y evaluación. Mantener este PLAN.md actualizado. |
| **Romper compatibilidad con el código del grupo** | No modificar interfaces. Añadir métodos sin eliminar los existentes. Mantener firmas originales. |
| **Tests insuficientes** | Seguir la cobertura definida en la sección 6. Ejecutar tests tras cada cambio. |

---

## 8. Estructura esperada del proyecto (tras completar)

```
src/
├── Interfaces/              # (no tocar)
├── MyLinkedList/            # Revisado ✓
├── MyQueue/                 # Revisado ✓
├── MyStack/                 # Revisado ✓
├── MyCircularList/          # Revisado + bugs corregidos ✓
├── MyTree/                  # Revisado ✓
├── MyGraph/                 # Revisado + CaminoMinimo corregido ✓
│   ├── BFSGrafo.java        # NUEVO
│   └── ...
├── MyMatrix/                # NUEVO
│   ├── MyMatrix.java
│   ├── Coordenada.java
│   └── BFSMatriz.java
├── Main.java                # (no tocar)
└── test/java/               # NUEVO (tests JUnit)
    ├── MyLinkedListTest.java
    ├── MyQueueTest.java
    ├── MyStackTest.java
    ├── MyCircularListTest.java
    ├── MyTreeTest.java
    ├── MyGraphTest.java
    ├── MyMatrixTest.java
    ├── BFSMatrizTest.java
    └── BFSGrafoTest.java

pom.xml                      # NUEVO (Maven + JUnit 5 + Gson)
PLAN.md                      # Este archivo
```

---

*Documento generado el 23 de mayo de 2026.*
