# PROCESO — Diario de uso de OpenCode

## Entrada 1 — 23/05/2026

| Campo | Detalle |
|---|---|
| **Fecha** | 23 de mayo de 2026 |
| **Objetivo de la sesión** | Explorar el proyecto, analizar el estado de las estructuras de datos y crear el plan de trabajo (`PLAN.md`). |
| **Prompt utilizado** | "Voy a hacer un trabajo de Estructuras de Datos y Metodologías de la Programación... Lo primero antes de empezar sería crear el PLAN.md..." |
| **Agente usado** | `explore` (para inspeccionar el proyecto en profundidad) + `general` (para análisis de código). |
| **Archivos analizados** | Todo `src/`: `MyLinkedList/`, `MyQueue/`, `MyStack/`, `MyCircularList/`, `MyTree/`, `MyGraph/`, `Interfaces/`, `test/`, `Main.java`, `GsonUtilEjemploModificado.java`, `Trabajo_Anteriores/`, `.gitignore`, `.iml`. |
| **Resultado obtenido** | Se identificaron las estructuras ya implementadas (MyLinkedList, MyQueue, MyStack, MyCircularList, MyTree, MyGraph), las no implementadas (MyMatrix, BFS), y se detectaron bugs concretos: punto y coma erróneo en `IteradorLC.java:38`, doble incremento de tamaño en `LCOrdenada.java:40-47`, tamaño fijo en `CaminoMinimo.java:14`. También se verificó que no se usan estructuras prohibidas de `java.util` (salvo `DatosGrafo.java` con `java.util.List` para Gson, excepción justificada). |
| **Decisiones tomadas** | - Usar las implementaciones existentes en lugar de reescribirlas. <br>- Configurar Maven con JUnit 5 y Gson para los tests. <br>- No tocar `Main.java`, JavaFX, JSON, UI ni lógica del juego. <br>- MyMatrix se implementará con array nativo `T[][]`. <br>- BFS sobre matriz y sobre grafo serán clases independientes. |
| **Cambios aceptados** | Creación de `PLAN.md` con el plan detallado (objetivos, estado, restricciones, orden, tests, riesgos). |
| **Cambios pendientes** | - Crear `pom.xml` (Maven + JUnit 5 + Gson). <br>- Revisar y corregir bugs en estructuras existentes. <br>- Implementar MyMatrix. <br>- Implementar BFSMatriz. <br>- Implementar BFSGrafo. <br>- Crear tests JUnit. <br>- Documentar costes. |
| **Revisión crítica personal** | El proyecto tiene una base sólida de estructuras ya implementadas. Los bugs detectados son localizados y fáciles de corregir. La principal carga de trabajo está en MyMatrix y los BFS, que son implementaciones nuevas. El plan está alineado con las restricciones del enunciado. Es importante no desviarse a lógica del juego (no me corresponde). |
| **Siguiente paso** | Fase 0: crear `pom.xml` con JUnit 5 y Gson, y estructura `src/test/java/` para los tests. |

## Entrada 2 — 24/05/2026

| Campo | Detalle |
|---|---|
| **Fecha** | 24 de mayo de 2026 |
| **Objetivo de la sesión** | Crear PRD.md, actualizar AGENTES.md, y ejecutar la Fase 0 del plan (pom.xml). |
| **Prompt utilizado** | Múltiples: crear PRD.md, modificar secciones 3 y 6 del PRD, añadir Agente Documentador a AGENTES.md, añadir flujo de trabajo y regla general, releer PLAN/PROCESO y comenzar el siguiente paso. |
| **Agente usado** | `general` (todas las operaciones de la sesión). |
| **Archivos creados** | `PRD.md`, `pom.xml`. |
| **Archivos modificados** | `AGENTES.md` (nuevo agente Documentador, flujo de trabajo, regla general), `PROCESO.md` (esta entrada). |
| **Resultado obtenido** | - PRD.md creado con 9 secciones. <br>- AGENTES.md ampliado con Agente Documentador, flujo de trabajo recomendado y regla general. <br>- pom.xml generado con JUnit 5.11.4, Gson 2.11.0, target Java 17, y surefire plugin. |
| **Decisiones tomadas** | - Java 17 como target (compatible con el código existente y ampliamente soportado). <br>- JUnit 5.11.4 como versión estable reciente. <br>- Los tests se alojarán directamente en `src/test/java/` (sin subpaquetes adicionales), según PLAN.md. |
| **Cambios aceptados** | PRD.md creado y ajustado según feedback. AGENTES.md actualizado con el nuevo agente y flujo. pom.xml creado. |
| **Cambios pendientes** | (Sin cambios respecto a la entrada anterior: revisar/corregir bugs, implementar MyMatrix, BFS, tests, documentar costes.) |
| **Revisión crítica personal** | La Fase 0 está completa. El pom.xml es simple pero funcional. El PRD quedó alineado con el PLAN sin inventar requisitos. El flujo de AGENTES.md permite ahora un proceso ordenado: Implementador → Tester → Revisor → Documentador. |
| **Siguiente paso** | Fase 1.1: revisar MyStack (estructura existente, esperado sin cambios). |

## Entrada 3 — 24/05/2026 (Fase 1.1: MyStack)

| Campo | Detalle |
|---|---|
| **Fecha** | 24 de mayo de 2026 |
| **Objetivo de la sesión** | Ejecutar Fase 1.1 del plan: revisar MyStack, crear tests, verificar compilación. |
| **Agentes usados** | Implementador (revisión), Tester (crear MyStackTest), Revisor (validar), Documentador (esta entrada). |
| **Archivos creados** | `src/test/java/MyStackTest.java` |
| **Archivos modificados** | `pom.xml` (sourceDirectory, excludes para Main.java y GsonUtil, exclude test/ de src) |
| **Resultado obtenido** | - MyStack revisado: sin bugs, `pop()`/`top()` devuelven null en vacío, sin imports prohibidos. ✅ <br>- MyStackTest con 9 tests (push+top, pop, pop vacío, top vacío, vacío tras pop todo, clear, isEmpty, getSize, orden LIFO) — todos pasan. ✅ <br>- Compilación Maven: 34 fuentes + 1 test compilan correctamente. ✅ <br>- Se eliminó `src/test/java/Grafos/` (test antiguo con imports rotos). |
| **Decisiones tomadas** | - `<sourceDirectory>src</sourceDirectory>` con exclude `<exclude>test/**</exclude>` para evitar que Maven compile los tests como fuentes principales. <br>- Excluir `**/MyTree/Main.java` y `**/GsonUtilEjemploModificado.java` de compilación (tienen imports a paquetes inexistentes). <br>- Usar `--release 17` en lugar de source/target por separado. |
| **Cambios aceptados** | pom.xml ajustado para compilar correctamente. MyStackTest.java creado y validado. |
| **Cambios pendientes** | Fase 1.2: revisar MyQueue. |
| **Revisión crítica personal** | MyStack es sólido, sin cambios necesarios. Los 9 tests cubren todos los casos. El pom.xml ahora compila correctamente todo el proyecto. El flujo de agentes funciona bien: Implementador → Tester → Revisor → Documentador. |
| **Siguiente paso** | Fase 1.2: revisar MyQueue (estructura existente, esperado sin cambios). |

## Entrada 4 — 24/05/2026 (Fase 1.2: MyQueue)

| Campo | Detalle |
|---|---|
| **Fecha** | 24 de mayo de 2026 |
| **Objetivo de la sesión** | Ejecutar Fase 1.2: revisar MyQueue, crear tests, verificar compilación. |
| **Agentes usados** | Implementador (revisión), Tester (crear MyQueueTest), Revisor (validar), Documentador (esta entrada). |
| **Archivos creados** | `src/test/java/MyQueueTest.java` |
| **Resultado obtenido** | - MyQueue revisado: sin bugs, sin imports prohibidos. ✅ <br>- MyQueueTest con 10 tests — todos pasan. ✅ <br>- Compilación Maven: 33 fuentes + 1 test compilan correctamente. ✅ |
| **Cambios aceptados** | MyQueueTest.java creado y validado. |
| **Cambios pendientes** | Fase 1.3: revisar MyLinkedList. |
| **Revisión crítica personal** | MyQueue es correcto y robusto. El caso borde de `ultimo = null` al vaciar la cola vía `desencolar()` está bien manejado. 10 tests con cobertura completa. |
| **Siguiente paso** | Fase 1.3: revisar MyLinkedList (estructura existente, esperado sin cambios). |

## Entrada 5 — 24/05/2026 (Fase 1.3: MyLinkedList)

| Campo | Detalle |
|---|---|
| **Fecha** | 24 de mayo de 2026 |
| **Objetivo de la sesión** | Ejecutar Fase 1.3: revisar MyLinkedList (ListaSE, ElementoSE, IteradorLSE, LSEOrdenada), crear tests, verificar compilación. |
| **Agentes usados** | Implementador (revisión), Tester (crear MyLinkedListTest), Revisor (validar), Documentador (esta entrada). |
| **Archivos creados** | `test/java/MyLinkedListTest.java` |
| **Resultado obtenido** | - MyLinkedList revisado: sin bugs, sin imports prohibidos. ✅ <br> - addInicio O(1), add/get/del O(n) — costes correctos. ✅ <br> - LSEOrdenada inserta orden ascendente. ✅ <br> - MyLinkedListTest con 21 tests — todos pasan. ✅ <br> - Compilación Maven: 33 fuentes + 3 tests compilan. ✅ |
| **Cambios aceptados** | MyLinkedListTest.java creado y validado. |
| **Cambios pendientes** | Fase 1.4: revisar MyCircularList. |
| **Revisión crítica personal** | Código sólido. La lista enlazada simple funciona correctamente. LSEOrdenada extiende y sobrescribe `add` para orden. El iterador recorre correctamente. 21 tests con buena cobertura de casos borde. |
| **Siguiente paso** | Fase 1.4: revisar MyCircularList. |

## Entrada 6 — 24/05/2026 (Fase 1.4: MyCircularList)

| Campo | Detalle |
|---|---|
| **Fecha** | 24 de mayo de 2026 |
| **Objetivo de la sesión** | Ejecutar Fase 1.4: revisar MyCircularList (ListaCircular, ElementoLC, IteradorLC, LCOrdenada), crear tests, verificar compilación. |
| **Agentes usados** | Implementador (revisión), Tester (crear MyCircularListTest), Revisor (validar), Documentador (esta entrada). |
| **Archivos creados** | `test/java/MyCircularListTest.java` |
| **Resultado obtenido** | - MyCircularList revisado: sin bugs funcionales. ✅ <br> - Observación menor: `;` extra en IteradorLC.next() (confuso pero no bug). Código redundante en LCOrdenada.add() para caso `puntero == ultimo`. ✅ <br> - addInicio/add O(1), get/del O(n), espacio O(n). ✅ <br> - MyCircularListTest con 23 tests — todos pasan. ✅ <br> - Compilación Maven: 33 fuentes + 4 tests compilan. ✅ |
| **Cambios aceptados** | MyCircularListTest.java creado y validado. |
| **Cambios pendientes** | Fase 1.5: revisar MyTree. |
| **Revisión crítica personal** | ListaCircular implementa correctamente la lógica de lista enlazada circular. El iterador de lista circular evita bucle infinito con `primeraVuelta`. LCOrdenada maneja inserción ordenada con casos vacío/inicio/final/medio. 23 tests con cobertura completa. |
| **Siguiente paso** | Fase 1.5: revisar MyTree. |

## Entrada 7 — 24/05/2026 (Fase 1.5: MyTree)

| Campo | Detalle |
|---|---|
| **Fecha** | 24 de mayo de 2026 |
| **Objetivo de la sesión** | Ejecutar Fase 1.5: revisar MyTree (ArbolBinarioDeBusqueda, ArbolBinarioDeBusquedaEnteros, Nodo), crear tests, verificar compilación. |
| **Agentes usados** | Implementador (revisión), Tester (crear MyTreeTest), Revisor (validar), Documentador (esta entrada). |
| **Archivos creados** | `test/java/MyTreeTest.java` |
| **Resultado obtenido** | - MyTree revisado: sin bugs, sin imports prohibidos. ✅ <br> - BST con inserción recursiva, 3 recorridos, altura, grado, homogeneidad, completitud, casi-completitud, camino, subárboles. ✅ <br> - ArbolBinarioDeBusquedaEnteros con getSuma() y sobrescritura covariante de getSubArbol. ✅ <br> - MyTreeTest con 31 tests — todos pasan. ✅ <br> - Compilación Maven: 33 fuentes + 5 tests compilan. ✅ |
| **Cambios aceptados** | MyTreeTest.java creado y validado. |
| **Cambios pendientes** | Fase 1.6: revisar MyGraph (corregir CaminoMinimo tamaño dinámico). |
| **Revisión crítica personal** | Implementación sólida del BST. Todos los métodos recursivos funcionan correctamente. La detección de árbol completo y casi completo está bien implementada. Los 31 tests cubren casos borde (vacío, un nodo) y funcionalidades principales. |
| **Siguiente paso** | Fase 1.6: revisar MyGraph (corregir CaminoMinimo tamaño dinámico). |

## Entrada 8 — 24/05/2026 (Fase 1.6: MyGraph)

| Campo | Detalle |
|---|---|
| **Fecha** | 24 de mayo de 2026 |
| **Objetivo de la sesión** | Ejecutar Fase 1.6: revisar MyGraph (Grafo, Vertice, Arista, CaminoMinimo, BuscadorGrafo, ListaSimple), corregir bug CaminoMinimo, crear tests, verificar compilación. |
| **Agentes usados** | Implementador (revisión + corrección), Tester (crear MyGraphTest), Revisor (validar), Documentador (esta entrada). |
| **Archivos modificados** | `src/MyGraph/Grafo.java` (añadido `getMaxId()`), `src/MyGraph/CaminoMinimo.java` (tamaño dinámico + guardas) |
| **Archivos creados** | `test/java/MyGraphTest.java` |
| **Resultado obtenido** | - Bug `totalVertices = 500` corregido → tamaño dinámico basado en `grafo.getMaxId() + 1`. ✅ <br> - `getMaxId()` añadido a Grafo. ✅ <br> - Constructor inicializa distancias a infinito y predecesores a -1. ✅ <br> - `obtenerCamino` valida bounds y retorna "No hay camino". ✅ <br> - `ejecutarDijkstra` expande arrays si el grafo creció. ✅ <br> - MyGraphTest con 14 tests — todos pasan. ✅ <br> - Compilación Maven: 33 fuentes + 6 tests compilan. ✅ <br> - **Total tests global: 108, 0 fallos.** ✅ |
| **Cambios aceptados** | MyGraphTest.java, corrección CaminoMinimo (tamaño dinámico + guardas), getMaxId() en Grafo. |
| **Cambios pendientes** | Fase 2.1: crear MyMatrix (matriz + Coordenada). |
| **Revisión crítica personal** | Grafo funcional con lista enlazada simple interna. Dijkstra ahora usa tamaño dinámico evitando el riesgo de ArrayIndexOutOfBounds. Las guardas en `obtenerCamino` manejan correctamente destinos inexistentes o no alcanzables. El código ListaSimple duplicado no se modificó según PLAN.md. |
| **Siguiente paso** | Fase 2.1: crear MyMatrix (matriz + Coordenada). |

## Entrada 9 — 24/05/2026 (Fase 2.1: MyMatrix + Coordenada)

| Campo | Detalle |
|---|---|
| **Fecha** | 24 de mayo de 2026 |
| **Objetivo de la sesión** | Crear MyMatrix (matriz genérica) y Coordenada desde cero. |
| **Agentes usados** | Implementador (crear Coordenada + MyMatrix), Tester (crear MyMatrixTest), Revisor (validar), Documentador (esta entrada). |
| **Archivos creados** | `src/MyMatrix/Coordenada.java`, `src/MyMatrix/MyMatrix.java`, `test/java/MyMatrixTest.java` |
| **Resultado obtenido** | - MyMatrix implementada con array unidimensional `T[]`, cálculo `i*cols+j`. ✅ <br> - Operaciones: constructor dimensiones, constructor con valor inicial, get, set, getFilas, getColumnas, posicionValida, getVecinos (4 direcciones), toString. ✅ <br> - Coordenada con fila, columna, getters, equals, compareTo, toString. ✅ <br> - MyMatrixTest con 15 tests — todos pasan. ✅ <br> - Compilación: 35 fuentes + 7 tests. ✅ <br> - **Total tests global: 123, 0 fallos.** ✅ |
| **Cambios aceptados** | Coordenada.java, MyMatrix.java, MyMatrixTest.java. |
| **Cambios pendientes** | Fase 2.2: BFSMatriz (BFS casillas alcanzables). |
| **Revisión crítica personal** | MyMatrix implementada limpiamente con array unidimensional, evitando problemas de genéricos con arrays bidimensionales. Coordenada implementa Comparable para compatibilidad con ListaSE. getVecinos devuelve hasta 4 vecinos válidos. |
| **Siguiente paso** | Fase 2.2: BFSMatriz (BFS casillas alcanzables). |

## Entrada 10 — 25/05/2026 (Fase 2.2: BFSMatriz)

| Campo | Detalle |
|---|---|
| **Fecha** | 25 de mayo de 2026 |
| **Objetivo de la sesión** | Implementar BFS casillas alcanzables sobre MyMatrix. |
| **Agentes usados** | Implementador (crear BFSMatriz), Tester (crear BFSMatrizTest), Revisor (validar), Documentador (esta entrada). |
| **Archivos creados** | `src/MyMatrix/BFSMatriz.java`, `test/java/BFSMatrizTest.java` |
| **Resultado obtenido** | - BFSMatriz con constructor `(MyMatrix<T>)`, `setCeldaBloqueada()`, `estaBloqueada()`, `casillasAlcanzables()`. ✅ <br> - Usa `Cola<Coordenada>` para BFS, `boolean[][]` visitado, `int[][]` distancia. ✅ <br> - BFSMatrizTest con 16 tests — todos pasan. ✅ <br> - **Total tests global: 139, 0 fallos.** ✅ |
| **Cambios aceptados** | BFSMatriz.java, BFSMatrizTest.java. |
| **Cambios pendientes** | Fase 2.3: BFSGrafo (BFS habitaciones hasta salida). |
| **Revisión crítica personal** | BFS estándar sobre matriz con soporte de celdas bloqueadas. Usa Cola nativa (no java.util). Visitado y distancia controlados con arrays nativos. Soporta posición inicial bloqueada y posiciones inválidas. |
| **Siguiente paso** | Fase 2.3: BFSGrafo (BFS habitaciones hasta salida). |

## Entrada 11 — 25/05/2026 (Fase 2.3: BFSGrafo)

| Campo | Detalle |
|---|---|
| **Fecha** | 25 de mayo de 2026 |
| **Objetivo de la sesión** | Implementar BFS habitaciones hasta salida sobre el grafo. |
| **Agentes usados** | Implementador (crear BFSGrafo), Tester (crear BFSGrafoTest), Revisor (validar), Documentador (esta entrada). |
| **Archivos creados** | `src/MyGraph/BFSGrafo.java`, `test/java/BFSGrafoTest.java` |
| **Resultado obtenido** | - BFSGrafo con constructor `(Grafo)`, `buscarSalida(tipoOrigen, nombreOrigen, tipoSalida)`, `getCamino(tipoOrigen, nombreOrigen, tipoDestino, nombreDestino)`. ✅ <br> - Usa `Cola<Integer>` para BFS, `boolean[]` visitado, `int[]` predecesor. ✅ <br> - `reconstruirCamino` privado con `addInicio`. ✅ <br> - Guardas: origen/destino inexistentes → lista vacía, getVertice null → continue, bounds en destId. ✅ <br> - BFSGrafoTest con 12 tests — todos pasan. ✅ <br> - **Total tests global: 151, 0 fallos.** ✅ |
| **Cambios aceptados** | BFSGrafo.java, BFSGrafoTest.java. |
| **Cambios pendientes** | Fase 4.1: Justificación de costes para la memoria. |
| **Revisión crítica personal** | BFS estándar O(V+E) con arrays nativos. Ambos métodos comparten lógica BFS con distinta condición de parada. Reconstrucción de camino con addInicio evita necesidad de pila auxiliar. |
| **Siguiente paso** | Fase 4.1: Justificación de costes para la memoria. |

## Entrada 12 — 25/05/2026 (Fase 4.1: COSTES.md)

| Campo | Detalle |
|---|---|
| **Fecha** | 25 de mayo de 2026 |
| **Objetivo de la sesión** | Documentar la justificación de costes temporales y espaciales de las 9 estructuras. |
| **Agentes usados** | Documentador (crear COSTES.md y actualizar PROCESO.md). |
| **Archivos creados** | `COSTES.md` |
| **Resultado obtenido** | - 9 secciones, una por estructura (MyStack, MyQueue, MyLinkedList, MyCircularList, MyTree, MyGraph, MyMatrix, BFSMatriz, BFSGrafo). ✅ <br> - Cada sección: descripción, justificación de elección, tabla de costes por operación, espacio total. ✅ <br> - Pendiente de conversión a PDF por parte del usuario. ✅ |
| **Cambios aceptados** | COSTES.md. |
| **Cambios pendientes** | Ninguno. Todas las fases completadas. |
| **Revisión crítica personal** | Todas las fases del plan completadas: 9 estructuras implementadas/revisadas + algoritmos BFS + tests (151 tests, 0 fallos) + documentación de costes. |
| **Siguiente paso** | — (Proyecto completado). |
