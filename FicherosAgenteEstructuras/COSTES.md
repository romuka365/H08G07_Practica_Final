# Justificación de Costes — Estructuras de Datos

## 1. MyStack (Pila)

**Estructura:** Pila genérica (LIFO) implementada con lista simplemente enlazada. Cada nodo (`ElementoPila<T>`) contiene un dato y un puntero al siguiente.

**Justificación de elección:** La pila LIFO es eficiente con lista enlazada porque todas las operaciones relevantes (push/pop) ocurren en la cabeza, sin necesidad de recorrer la estructura.

| Operación | Coste temporal | Explicación |
|---|---|---|
| `push(T)` | O(1) | Inserción al principio: crear nodo y reasignar cabeza |
| `pop()` | O(1) | Eliminación del primero: reasignar cabeza al siguiente |
| `top()` | O(1) | Acceso directo a la cabeza |
| `isEmpty()` | O(1) | Comprobar si cabeza es null |
| `getSize()` | O(1) | Variable mantenida |
| `clear()` | O(1) | Asignar cabeza a null |
| Espacio total | O(n) | n elementos almacenados, cada uno en su nodo |

---

## 2. MyQueue (Cola)

**Estructura:** Cola genérica (FIFO) implementada con lista simplemente enlazada con punteros a frente y último.

**Justificación de elección:** La cola FIFO requiere acceso eficiente a ambos extremos: encolar por el final y desencolar por el frente. Mantener un puntero al último nodo permite encolar en O(1) sin recorrer toda la lista.

| Operación | Coste temporal | Explicación |
|---|---|---|
| `encolar(T)` | O(1) | Inserción al final: reasignar puntero último y su siguiente |
| `desencolar()` | O(1) | Eliminación del frente: reasignar frente al siguiente |
| `frente()` | O(1) | Acceso directo al frente |
| `isEmpty()` | O(1) | Comprobar si frente es null |
| `getSize()` | O(1) | Variable mantenida |
| `clear()` | O(1) | Asignar frente y último a null |
| Espacio total | O(n) | n elementos almacenados |

---

## 3. MyLinkedList (ListaSE + LSEOrdenada)

**Estructura:** Lista simplemente enlazada genérica. Cada nodo (`ElementoSE<T>`) contiene dato y puntero al siguiente. `LSEOrdenada` extiende `ListaSE` insertando en orden.

**Justificación de elección:** La lista enlazada simple es la estructura lineal más básica. Se eligió sobre un array por su crecimiento dinámico sin necesidad de redimensionamiento. La variante ordenada mantiene los elementos ordenados durante la inserción, evitando ordenaciones posteriores.

| Operación | Coste temporal | Explicación |
|---|---|---|
| `add(T)` — ListaSE | O(n) | Recorrido hasta el último nodo |
| `addInicio(T)` | O(1) | Inserción directa en cabeza |
| `get(T)` | O(n) | Búsqueda lineal comparando dato |
| `del(T)` | O(n) | Búsqueda lineal + reenganche de punteros |
| `isEmpty()` | O(1) | Comprobar si primero es null |
| `getSize()` | O(1) | Variable mantenida |
| `clear()` | O(1) | Asignar primero a null |
| `add(T)` — LSEOrdenada | O(n) | Recorrido hasta encontrar posición correcta |
| Espacio total | O(n) | n elementos, cada uno en su nodo |

---

## 4. MyCircularList (ListaCircular + LCOrdenada)

**Estructura:** Lista circular genérica donde el último nodo apunta al primero. Cada nodo (`ElementoLC<T>`) contiene dato y puntero al siguiente.

**Justificación de elección:** La lista circular permite recorridos infinitos y es útil para bucles de turnos o rotaciones. El último nodo conoce al primero, lo que permite inserciones al final en O(1).

| Operación | Coste temporal | Explicación |
|---|---|---|
| `add(T)` | O(1) | Inserción después del último: reasignar punteros último↔primero |
| `addInicio(T)` | O(1) | Inserción al principio: reasignar cabeza y puntero del último |
| `del(T)` | O(n) | Búsqueda lineal + reenganche; caso especial si se borra el único |
| `get(T)` | O(n) | Búsqueda lineal (recorrido circular con control de vuelta) |
| `isEmpty()` | O(1) | Comprobar si primero es null |
| `getSize()` | O(1) | Variable mantenida |
| `clear()` | O(1) | Asignar primero a null |
| `add(T)` — LCOrdenada | O(n) | Recorrido hasta posición correcta |
| Espacio total | O(n) | n elementos almacenados |

---

## 5. MyTree — MyTree + Node

**Estructura:** Árbol n-ario genérico. Cada nodo (`Node<T>`) contiene dato, referencia al padre y una `ListaSE<Node<T>>` para sus hijos. No requiere orden entre hermanos.

**Justificación de elección:** Un árbol binario de búsqueda solo permite 2 hijos por nodo y ordena por `compareTo`, lo cual no sirve para representar jerarquías con múltiples opciones (ej. acciones del juego). `MyTree` permite N hijos por nodo y no impone ordenación, adaptándose a menús, árboles de decisión o cualquier jerarquía sin restricción binaria. `Node` envuelve el dato y proporciona acceso al padre e hijos.

| Operación | Coste temporal | Explicación |
|---|---|---|
| `MyTree(T)` | O(1) | Crear nodo raíz |
| `addChild(padre, hijo)` | O(k) | k = nº hijos del padre (añade al final de la lista) |
| `removeChild(padre, dato)` | O(k) | Búsqueda lineal + eliminación en lista de hijos |
| `buscar(dato)` | O(n) | Recorrido preorden recursivo completo |
| `getPreorden()` | O(n) | Recorrido recursivo preorden |
| `getPostorden()` | O(n) | Recorrido recursivo postorden |
| `getPorNiveles()` | O(n) | BFS con Cola propia |
| `getAltura()` | O(n) | Recorrido recursivo completo |
| `getGrado()` | O(n) | Recorrido recursivo completo |
| `getNumNodos()` | O(n) | Recorrido recursivo completo |
| `isEmpty()` | O(1) | Comprobar si raíz es null |
| Espacio total | O(n) | n nodos, cada uno con su lista de hijos |

**Nota:** Como cada padre suele tener pocos hijos (en el árbol de acciones del juego, máximo 4), `addChild` y `removeChild` son O(1) efectivos en la práctica.

---

## 6. MyGraph + CaminoMinimo (Grafo + Dijkstra)

**Estructura:** Grafo dirigido con lista de adyacencia implementada con lista enlazada simple propia (`MyGraph.ListaSimple`). Cada vértice tiene su propia lista de aristas.

**Justificación de elección:** La lista de adyacencia es la representación más eficiente para grafos dispersos (pocas aristas por vértice frente al total posible). Se eligió sobre matriz de adyacencia por eficiencia espacial. Dijkstra con arrays es la versión clásica para grafos con pesos no negativos.

### Grafo

| Operación | Coste temporal | Explicación |
|---|---|---|
| `addVertice(tipo, nombre)` | O(V) | Búsqueda de duplicado con `buscarIdVertice` + inserción |
| `addArista(origen, destino, pred)` | O(V) | Búsqueda de ambos vértices + inserción de arista en origen |
| `getVertice(id)` | O(V) | Búsqueda lineal por id |
| `buscarIdVertice(tipo, nombre)` | O(V) | Búsqueda lineal comparando tipo y nombre |
| Espacio total | O(V + E) | V vértices + E aristas |

### CaminoMinimo (Dijkstra)

| Operación | Coste temporal | Explicación |
|---|---|---|
| `ejecutarDijkstra(tipo, nombre)` | O(V²) | Bucle exterior V veces, búsqueda de mínimos sin cola de prioridad |
| `obtenerCamino(tipo, nombre)` | O(V) | Reconstrucción caminando por predecesores |
| Espacio total | O(V) | Arrays de visitado, distancia y predecesor |

---

## 7. MyMatrix + Coordenada

**Estructura:** Matriz bidimensional genérica implementada con array unidimensional (`T[]`) y cálculo de índice `i * columnas + j`.

**Justificación de elección:** Se optó por array unidimensional en lugar de `T[][]` para evitar problemas de genericidad en Java (no es posible crear `new T[f][c]` directamente). El cálculo `i * columnas + j` mantiene acceso O(1) idéntico al bidimensional.

| Operación | Coste temporal | Explicación |
|---|---|---|
| `MyMatrix(filas, columnas)` | O(f*c) | Creación e inicialización del array a null |
| `MyMatrix(filas, columnas, valorInicial)` | O(f*c) | Creación + asignación de valor a cada celda |
| `get(fila, columna)` | O(1) | Acceso directo por índice calculado |
| `set(fila, columna, valor)` | O(1) | Asignación directa por índice calculado |
| `getFilas()` | O(1) | Variable mantenida |
| `getColumnas()` | O(1) | Variable mantenida |
| `posicionValida(fila, columna)` | O(1) | Dos comprobaciones de rango |
| `getVecinos(fila, columna)` | O(1) | Máximo 4 comprobaciones de vecinos |
| `toString()` | O(f*c) | Recorrido completo de la matriz |
| Espacio total | O(f*c) | f*c elementos almacenados |

---

## 8. BFSMatriz

**Estructura:** Algoritmo BFS sobre matriz genérica. Usa `Cola<Coordenada>` propia, arrays nativos `boolean[][]` para visitados y `int[][]` para distancias.

**Justificación de elección:** BFS es el algoritmo óptimo para encontrar el camino más corto en un grafo no ponderado (todos los pasos cuestan 1). Se usan arrays nativos para visitado y distancia por eficiencia espacial y temporal. La cola es la estructura natural para BFS.

| Operación | Coste temporal | Explicación |
|---|---|---|
| `casillasAlcanzables(inicio, maxMov)` | O(f*c) | Cada celda se visita como máximo una vez |
| `setCeldaBloqueada(fila, col, bool)` | O(1) | Asignación directa + comprobación de rango |
| `estaBloqueada(fila, col)` | O(1) | Acceso directo al array |
| Espacio total | O(f*c) | Arrays visitado, distancia y bloqueado |

---

## 9. BFSGrafo

**Estructura:** Algoritmo BFS sobre el grafo de habitaciones. Usa `Cola<Integer>` propia, arrays nativos `boolean[]` para visitados y `int[]` para predecesores.

**Justificación de elección:** BFS es el algoritmo óptimo para grafos no ponderados. Los arrays indexados por id de vértice proporcionan acceso O(1) a visitado y predecesor. La cola gestiona el orden de exploración por niveles.

| Operación | Coste temporal | Explicación |
|---|---|---|
| `buscarSalida(tipoOrigen, nombre, tipoSalida)` | O(V + E) | BFS estándar: cada vértice y arista se procesa una vez |
| `getCamino(tipoOrigen, nombre, tipoDestino, nombre)` | O(V + E) | BFS estándar: cada vértice y arista se procesa una vez |
| `reconstruirCamino(predecesor, origen, destino)` | O(V) | Recorrido desde destino hasta origen por predecesores |
| Espacio total | O(V) | Arrays visitado y predecesor tamaño V+1 |
