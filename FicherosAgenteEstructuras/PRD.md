# PRD — Documento de Requisitos del Producto

## 1. Descripción general del proyecto

**JuegoTrabajoFinal** es un videojuego de exploración por habitaciones desarrollado en Java, donde el jugador se mueve en un tablero bidimensional dentro de cada habitación y navega entre habitaciones conectadas mediante un grafo. El juego debe implementar sus propias estructuras de datos (listas, colas, pilas, árboles, grafos, matrices) sin usar `java.util.ArrayList`, `java.util.LinkedList`, `java.util.HashMap` ni equivalentes.

## 2. Objetivo del producto

Proveer un conjunto de estructuras de datos lineales, arbóreas, grafos y matriciales —junto con algoritmos BFS— que sirvan como base para la lógica de movimiento y navegación del juego, cumpliendo las restricciones del enunciado de no usar las estructuras estándar de `java.util`.

## 3. Alcance de mi parte

Mi responsabilidad abarca:

- **Estructuras a implementar:** MyLinkedList, MyQueue, MyStack, MyCircularList, MyTree, MyGraph (revisión y corrección de existentes) y MyMatrix (implementación nueva).
- **Algoritmos:** BFS sobre matriz (casillas alcanzables) y BFS sobre grafo (camino más corto a salida).
- **Tests unitarios** JUnit 5 para todas las estructuras y algoritmos anteriores.
- **Documentación** de costes temporales y espaciales de cada operación.

Queda fuera de mi alcance la lógica del juego (combate, inventario, UI, persistencia JSON, etc.).

## 4. Requisitos funcionales

| ID | Requisito | Prioridad |
|---|---|---|
| RF-01 | MyLinkedList debe permitir inserción al inicio (`addInicio`), al final (`add`), búsqueda por valor (`get(T)`), borrado (`del`), vaciado (`clear`), consulta de tamaño y recorrido con iterador. | Alta |
| RF-02 | LSEOrdenada debe mantener los elementos ordenados ascendentemente al insertar. | Alta |
| RF-03 | MyQueue debe permitir encolar, desencolar, consultar frente y recorrer con iterador. | Alta |
| RF-04 | MyStack debe permitir apilar, desapilar, consultar tope y recorrer con iterador. | Alta |
| RF-05 | MyCircularList debe permitir las mismas operaciones que MyLinkedList con comportamiento circular. | Alta |
| RF-06 | LCOrdenada debe mantener los elementos ordenados ascendentemente en estructura circular. | Alta |
| RF-07 | MyTree (ABB) debe permitir inserción, recorridos in-order/pre-order/post-order, consulta de altura, grado, homogeneidad, completitud, camino y obtención de subárbol. | Alta |
| RF-08 | MyGraph debe permitir gestionar vértices y aristas dirigidas, cargar datos desde JSON y ejecutar consultas sobre el grafo. | Alta |
| RF-09 | **MyMatrix**: construir matriz con `(filas, columnas)`, obtener (`get`) y establecer (`set`) celdas, verificar posición válida, obtener vecinos ortogonales (arriba, abajo, izquierda, derecha) y representación textual. | Alta |
| RF-10 | **Coordenada**: clase auxiliar con fila, columna, getters, `equals()` y `toString()`. | Alta |
| RF-11 | **BFSMatriz**: dado un tablero (MyMatrix), una casilla inicial y una distancia máxima, devolver todas las casillas alcanzables mediante BFS en 4 direcciones. | Alta |
| RF-12 | **BFSGrafo**: dado el grafo de habitaciones, encontrar el camino más corto desde una habitación origen hasta una habitación salida (o entre dos vértices específicos) usando BFS. | Alta |
| RF-13 | Los tests JUnit deben cubrir casos nominales, bordes (vacío, un elemento) y de error (nulos, posiciones inválidas, elementos inexistentes) para cada estructura y algoritmo. | Media |
| RF-14 | Los iteradores deben recorrer correctamente la estructura completa y detenerse cuando corresponda. | Alta |

## 5. Requisitos no funcionales

| ID | Requisito |
|---|---|
| RNF-01 | Ninguna estructura evaluable debe usar `java.util.ArrayList`, `java.util.LinkedList`, `java.util.HashMap` o equivalentes. |
| RNF-02 | Los arrays nativos (`T[]`, `T[][]`) están permitidos por ser del lenguaje, no de `java.util`. |
| RNF-03 | Las estructuras deben ser genéricas para que el juego pueda definir sus propios tipos de datos. |
| RNF-04 | Los algoritmos BFS deben usar las implementaciones propias (MyQueue, MyMatrix, MyLinkedList, etc.). |
| RNF-05 | Costes esperados: MyMatrix get/set O(1), getVecinos O(1); BFSMatriz O(filas×columnas); BFSGrafo O(V+E). |
| RNF-06 | El código debe mantener compatibilidad con las interfaces existentes (`Interfaces/`) sin modificarlas. |
| RNF-07 | Los métodos añadidos no deben eliminar ni cambiar firmas de métodos existentes. |

## 6. Restricciones del enunciado

- Prohibido usar `java.util.ArrayList`, `java.util.LinkedList`, `java.util.HashMap` y otras estructuras equivalentes en el código evaluado.
- Uso excepcional a revisar: `java.util.List` en `DatosGrafo.java` para deserialización con Gson. No debe usarse como estructura evaluada ni en la lógica principal.
- `java.util.Random` permitido como utilidad.
- Todas las estructuras del juego deben usar las implementaciones propias del proyecto, no las de `java.util`.
- No modificar las interfaces del paquete `Interfaces/`.

## 7. Fuera de alcance para mi parte

| Área | Motivo |
|---|---|
| JavaFX / interfaz gráfica | No corresponde a mi parte |
| Persistencia JSON (salvo retoques menores en estructuras) | No es mi responsabilidad |
| Lógica de combate, jugador, enemigos, inventario | No implementar |
| Sistema de turnos | No implementar |
| Diseño visual / UI | No es mi responsabilidad |
| `Main.java` raíz | No tocar |
| `Trabajo_Anteriores/` | Código previo no evaluado |
| Código del resto del grupo (lógica del juego) | Cada miembro tiene su asignación |

## 8. Criterios de aceptación

| ID | Criterio |
|---|---|
| CA-01 | Todas las estructuras existentes están revisadas y libres de bugs conocidos. |
| CA-02 | MyMatrix implementada con array bidimensional y todas las operaciones funcionan correctamente. |
| CA-03 | BFSMatriz devuelve las casillas alcanzables correctamente para distancia 0, 1, n y con obstáculos. |
| CA-04 | BFSGrafo devuelve el camino más corto entre dos habitaciones, o lista vacía si no hay camino. |
| CA-05 | Todos los tests JUnit pasan (cobertura: nominal, borde, error). |
| CA-06 | No hay imports de `java.util.ArrayList`, `java.util.LinkedList`, `java.util.HashMap` en el código evaluable. |
| CA-07 | La documentación de costes (temporal y espacial) está completa para cada estructura. |

## 9. Riesgos principales

| Riesgo | Impacto | Mitigación |
|---|---|---|
| Uso accidental de `java.util.*` prohibido | Estructura evaluada como no válida | Revisar imports con grep tras cada cambio |
| Bugs no detectados en estructuras existentes | Fallos en el juego que las usa | Tests unitarios exhaustivos |
| Incompatibilidad con el código del grupo | El juego no compila o no funciona | No modificar interfaces, mantener firmas |
| Mezclar lógica del juego con estructuras | Estructuras no reutilizables | Mantener estructuras genéricas sin dependencias del juego |
| Cobertura de tests insuficiente | Bugs no detectados | Seguir la tabla de cobertura definida en PLAN.md |
| Dependencia de Gson para deserialización | Uso de librería externa | Aceptado como excepción justificada (solo DatosGrafo.java) |
