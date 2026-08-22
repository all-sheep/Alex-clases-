import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ListaEnlazadaSimple listaSimple = new ListaEnlazadaSimple();
    private static ListaDoblementeEnlazada listaDoble = new ListaDoblementeEnlazada();
    private static HistorialAcciones historial = new HistorialAcciones();
    private static ArbolCursos arbol = new ArbolCursos();
    private static GrafoCursos grafo = new GrafoCursos();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    agregarCurso();
                    break;
                case 2:
                    System.out.println("--- Lista de cursos ---");
                    listaSimple.mostrar();
                    break;
                case 3:
                    buscarCursoPorClave();
                    break;
                case 4:
                    eliminarCurso();
                    break;
                case 5:
                    inscribirEstudiante();
                    break;
                case 6:
                    darDeBajaEstudiante();
                    break;
                case 7:
                    System.out.println("--- Cursos de inicio a fin ---");
                    listaDoble.mostrarInicioAFin();
                    break;
                case 8:
                    System.out.println("--- Cursos de fin a inicio ---");
                    listaDoble.mostrarFinAInicio();
                    break;
                case 9:
                    navegadorCarrusel();
                    break;
                case 10:
                    contarCursosRecursivo();
                    break;
                case 11:
                    buscarCursoRecursivo();
                    break;
                case 12:
                    insertarCursosEnArbol();
                    break;
                case 13:
                    buscarCursoEnArbol();
                    break;
                case 14:
                    arbol.mostrarInorden();
                    break;
                case 15:
                    crearRelacionGrafo();
                    break;
                case 16:
                    grafo.mostrarListaAdyacencia(listaSimple.obtenerCursosComoLista());
                    break;
                case 17:
                    grafo.mostrarMatrizAdyacencia(listaSimple.obtenerCursosComoLista());
                    break;
                case 18:
                    ordenarCursos(1);
                    break;
                case 19:
                    ordenarCursos(2);
                    break;
                case 20:
                    ordenarCursos(3);
                    break;
                case 21:
                    ordenarCursos(4);
                    break;
                case 22:
                    busquedaSecuencial();
                    break;
                case 23:
                    busquedaBinaria();
                    break;
                case 24:
                    compararBusquedas();
                    break;
                case 25:
                    historial.mostrar();
                    break;
                case 26:
                    System.out.println("Saliendo del sistema... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }

            System.out.println();

        } while (opcion != 26);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== SISTEMA DE GESTIÓN DE CURSOS UTC 3.0 =====");
        System.out.println("1. Agregar curso");
        System.out.println("2. Mostrar cursos");
        System.out.println("3. Buscar curso por clave");
        System.out.println("4. Eliminar curso");
        System.out.println("5. Inscribir estudiante a curso");
        System.out.println("6. Dar de baja estudiante de curso");
        System.out.println("7. Mostrar cursos de inicio a fin");
        System.out.println("8. Mostrar cursos de fin a inicio");
        System.out.println("9. Navegador de cursos (carrusel)");
        System.out.println("10. Contar cursos usando recursividad");
        System.out.println("11. Buscar curso usando recursividad");
        System.out.println("12. Insertar cursos en árbol binario");
        System.out.println("13. Buscar curso en árbol binario (por id)");
        System.out.println("14. Mostrar recorrido inorden del árbol");
        System.out.println("15. Crear relación entre cursos (grafo)");
        System.out.println("16. Mostrar grafo (lista de adyacencia)");
        System.out.println("17. Mostrar matriz de adyacencia del grafo");
        System.out.println("18. Ordenar cursos con Bubble Sort directo");
        System.out.println("19. Ordenar cursos con Bubble Sort inverso");
        System.out.println("20. Ordenar cursos con inserción directa");
        System.out.println("21. Ordenar cursos con selección directa");
        System.out.println("22. Búsqueda secuencial (por clave)");
        System.out.println("23. Búsqueda binaria (por clave)");
        System.out.println("24. Comparar pasos: secuencial vs binaria");
        System.out.println("25. Mostrar historial de acciones");
        System.out.println("26. Salir");
        System.out.println("=================================================");
    }

    // ---------- CRUD básico de cursos ----------

    private static void agregarCurso() {
        System.out.println("--- Agregar nuevo curso ---");

        int idCurso = leerEntero("Id numérico del curso: ");
        if (listaSimple.buscarPorId(idCurso) != null) {
            System.out.println("Ya existe un curso con ese id. No se agregó.");
            return;
        }

        String clave = leerTexto("Clave del curso: ");
        if (listaSimple.buscarPorClave(clave) != null) {
            System.out.println("Ya existe un curso con esa clave. No se agregó.");
            return;
        }

        String nombre = leerTexto("Nombre del curso: ");
        String docente = leerTexto("Docente: ");
        int cupo = leerEntero("Cupo máximo: ");

        if (cupo <= 0) {
            System.out.println("El cupo máximo debe ser mayor a 0. No se agregó el curso.");
            return;
        }

        Curso nuevo = new Curso(idCurso, clave, nombre, docente, cupo);

        listaSimple.agregar(nuevo);
        listaDoble.agregarAlFinal(nuevo);
        grafo.agregarVertice(idCurso);

        historial.registrar("Se agregó el curso " + nombre + " (" + clave + ", id " + idCurso + ")");
        System.out.println("Curso agregado correctamente.");
    }

    private static void buscarCursoPorClave() {
        System.out.println("--- Buscar curso por clave ---");
        String clave = leerTexto("Ingresa la clave a buscar: ");

        Curso encontrado = listaSimple.buscarPorClave(clave);

        if (encontrado != null) {
            System.out.println("Curso encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("No se encontró ningún curso con la clave: " + clave);
        }
    }

    private static void eliminarCurso() {
        System.out.println("--- Eliminar curso ---");
        String clave = leerTexto("Clave del curso a eliminar: ");

        Curso curso = listaSimple.buscarPorClave(clave);
        if (curso == null) {
            System.out.println("No existe un curso con esa clave.");
            return;
        }

        listaSimple.eliminarPorClave(clave);
        listaDoble.eliminarPorClave(clave);

        historial.registrar("Se eliminó el curso " + curso.getNombre() + " (" + clave + ")");
        System.out.println("Curso eliminado correctamente.");
    }

    private static void inscribirEstudiante() {
        System.out.println("--- Inscribir estudiante ---");
        String clave = leerTexto("Clave del curso: ");

        Curso curso = listaSimple.buscarPorClave(clave);
        if (curso == null) {
            System.out.println("No existe un curso con esa clave.");
            return;
        }

        if (!curso.hayCupoDisponible()) {
            System.out.println("El curso ya alcanzó su cupo máximo. No se puede inscribir.");
            return;
        }

        curso.inscribirEstudiante();
        historial.registrar("Se inscribió un estudiante en " + curso.getNombre() + " (" + curso.getClave() + ")");
        System.out.println("Estudiante inscrito correctamente. Cupo actual: "
                + curso.getNumeroInscritos() + "/" + curso.getCupoMaximo());
    }

    private static void darDeBajaEstudiante() {
        System.out.println("--- Dar de baja estudiante ---");
        String clave = leerTexto("Clave del curso: ");

        Curso curso = listaSimple.buscarPorClave(clave);
        if (curso == null) {
            System.out.println("No existe un curso con esa clave.");
            return;
        }

        boolean exito = curso.darDeBajaEstudiante();
        if (exito) {
            historial.registrar("Se dio de baja un estudiante de " + curso.getNombre() + " (" + curso.getClave() + ")");
            System.out.println("Estudiante dado de baja correctamente. Cupo actual: "
                    + curso.getNumeroInscritos() + "/" + curso.getCupoMaximo());
        } else {
            System.out.println("El curso no tiene estudiantes inscritos para dar de baja.");
        }
    }

    private static void navegadorCarrusel() {
        if (listaDoble.estaVacia()) {
            System.out.println("No hay cursos registrados para navegar.");
            return;
        }

        listaDoble.reiniciarCarrusel();
        int opcion;

        do {
            System.out.println("--- Navegador de cursos (carrusel) ---");
            Curso actual = listaDoble.verActual();
            System.out.println("Curso actual: " + (actual != null ? actual : "N/A"));
            System.out.println("1. Ver curso actual");
            System.out.println("2. Avanzar al siguiente curso");
            System.out.println("3. Regresar al curso anterior");
            System.out.println("4. Salir del navegador");

            opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    System.out.println(listaDoble.verActual());
                    break;
                case 2:
                    if (!listaDoble.avanzar()) {
                        System.out.println("Ya estás en el último curso.");
                    }
                    break;
                case 3:
                    if (!listaDoble.retroceder()) {
                        System.out.println("Ya estás en el primer curso.");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo del navegador...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }

    private static void contarCursosRecursivo() {
        int total = listaSimple.contarCursosRecursivo();
        historial.registrar("Se contaron los cursos de forma recursiva (" + total + " encontrados)");
        System.out.println("Número total de cursos (calculado recursivamente): " + total);
    }

    private static void buscarCursoRecursivo() {
        System.out.println("--- Buscar curso por clave (recursivo) ---");
        String clave = leerTexto("Ingresa la clave a buscar: ");

        Curso encontrado = listaSimple.buscarRecursivo(clave);

        if (encontrado != null) {
            historial.registrar("Se buscó de forma recursiva el curso " + clave + " (encontrado)");
            System.out.println("Curso encontrado:");
            System.out.println(encontrado);
        } else {
            historial.registrar("Se buscó de forma recursiva el curso " + clave + " (no encontrado)");
            System.out.println("No se encontró ningún curso con la clave: " + clave);
        }
    }

    // ---------- Árbol binario de búsqueda ----------

    private static void insertarCursosEnArbol() {
        List<Curso> cursos = listaSimple.obtenerCursosComoLista();
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados para insertar en el árbol.");
            return;
        }
        for (Curso c : cursos) {
            arbol.insertar(c);
        }
        historial.registrar("Se insertaron los cursos registrados en el árbol binario");
        System.out.println("Cursos insertados en el árbol binario (los ids repetidos se omiten).");
    }

    private static void buscarCursoEnArbol() {
        System.out.println("--- Buscar curso en árbol binario ---");
        int id = leerEntero("Id del curso a buscar: ");
        Curso encontrado = arbol.buscar(id);

        if (encontrado != null) {
            historial.registrar("Se buscó en el árbol el curso con id " + id + " (encontrado, "
                    + arbol.getComparacionesUltimaBusqueda() + " comparaciones)");
            System.out.println("Curso encontrado en " + arbol.getComparacionesUltimaBusqueda() + " comparaciones:");
            System.out.println(encontrado);
        } else {
            historial.registrar("Se buscó en el árbol el curso con id " + id + " (no encontrado)");
            System.out.println("No se encontró ningún curso con id " + id + " en el árbol.");
        }
    }

    // ---------- Grafo de cursos ----------

    private static void crearRelacionGrafo() {
        System.out.println("--- Crear relación entre cursos (grafo dirigido y ponderado) ---");
        if (listaSimple.obtenerCursosComoLista().isEmpty()) {
            System.out.println("No hay cursos registrados para relacionar.");
            return;
        }

        int origen = leerEntero("Id del curso origen (por ejemplo, prerrequisito): ");
        int destino = leerEntero("Id del curso destino: ");
        int peso = leerEntero("Peso de la relación (dificultad/prioridad/créditos): ");

        boolean exito = grafo.crearRelacion(origen, destino, peso);
        if (exito) {
            historial.registrar("Se creó una relación en el grafo: " + origen + " -> " + destino + " (peso " + peso + ")");
            System.out.println("Relación creada correctamente.");
        } else {
            System.out.println("No se pudo crear la relación. Verifica que ambos ids existan y sean distintos.");
        }
    }

    // ---------- Ordenamientos ----------

    private static void ordenarCursos(int tipoOrden) {
        List<Curso> cursos = listaSimple.obtenerCursosComoLista();
        if (cursos.size() < 2) {
            System.out.println("Se necesitan al menos 2 cursos registrados para ordenar.");
            return;
        }

        Ordenamientos.Criterio criterio = leerCriterio();
        String nombreMetodo;

        switch (tipoOrden) {
            case 1:
                Ordenamientos.bubbleSortDirecto(cursos, criterio);
                nombreMetodo = "Bubble Sort directo";
                break;
            case 2:
                Ordenamientos.bubbleSortInverso(cursos, criterio);
                nombreMetodo = "Bubble Sort inverso";
                break;
            case 3:
                Ordenamientos.insercionDirecta(cursos, criterio);
                nombreMetodo = "Inserción directa";
                break;
            case 4:
                Ordenamientos.seleccionDirecta(cursos, criterio);
                nombreMetodo = "Selección directa";
                break;
            default:
                return;
        }

        System.out.println("--- Cursos ordenados con " + nombreMetodo + " (criterio: " + criterio + ") ---");
        for (Curso c : cursos) {
            System.out.println(c);
        }
        historial.registrar("Se ordenaron los cursos con " + nombreMetodo + " por " + criterio);
    }

    private static Ordenamientos.Criterio leerCriterio() {
        System.out.println("Criterio de ordenamiento:");
        System.out.println("1. Id");
        System.out.println("2. Clave");
        System.out.println("3. Nombre");
        System.out.println("4. Cupo máximo");
        System.out.println("5. Número de inscritos");
        int opcion = leerEntero("Selecciona una opción: ");

        switch (opcion) {
            case 2:
                return Ordenamientos.Criterio.CLAVE;
            case 3:
                return Ordenamientos.Criterio.NOMBRE;
            case 4:
                return Ordenamientos.Criterio.CUPO;
            case 5:
                return Ordenamientos.Criterio.INSCRITOS;
            default:
                return Ordenamientos.Criterio.ID;
        }
    }

    // ---------- Búsquedas ----------

    private static void busquedaSecuencial() {
        System.out.println("--- Búsqueda secuencial ---");
        List<Curso> cursos = listaSimple.obtenerCursosComoLista();
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        String clave = leerTexto("Clave a buscar: ");
        Busquedas.ResultadoBusqueda resultado = Busquedas.secuencial(cursos, clave);

        if (resultado.encontrado()) {
            System.out.println("Curso encontrado en " + resultado.getPasos() + " pasos:");
            System.out.println(resultado.getCurso());
        } else {
            System.out.println("No se encontró el curso con clave " + clave + " (" + resultado.getPasos() + " pasos revisados).");
        }
        historial.registrar("Búsqueda secuencial de '" + clave + "' (" + resultado.getPasos() + " pasos)");
    }

    private static void busquedaBinaria() {
        System.out.println("--- Búsqueda binaria ---");
        List<Curso> cursos = listaSimple.obtenerCursosComoLista();
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        // La búsqueda binaria requiere la lista ordenada por el mismo criterio (clave)
        Ordenamientos.insercionDirecta(cursos, Ordenamientos.Criterio.CLAVE);
        System.out.println("(Los cursos se ordenaron por clave antes de buscar, como lo requiere la búsqueda binaria.)");

        String clave = leerTexto("Clave a buscar: ");
        Busquedas.ResultadoBusqueda resultado = Busquedas.binaria(cursos, clave);

        if (resultado.encontrado()) {
            System.out.println("Curso encontrado en " + resultado.getPasos() + " pasos:");
            System.out.println(resultado.getCurso());
        } else {
            System.out.println("No se encontró el curso con clave " + clave + " (" + resultado.getPasos() + " pasos revisados).");
        }
        historial.registrar("Búsqueda binaria de '" + clave + "' (" + resultado.getPasos() + " pasos)");
    }

    // ---------- Funcionalidad adicional: comparar pasos de búsqueda ----------

    private static void compararBusquedas() {
        System.out.println("--- Comparar búsqueda secuencial vs binaria ---");
        List<Curso> cursos = listaSimple.obtenerCursosComoLista();
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        String clave = leerTexto("Clave a buscar: ");

        Busquedas.ResultadoBusqueda resultadoSecuencial = Busquedas.secuencial(cursos, clave);

        List<Curso> ordenados = new ArrayList<>(cursos);
        Ordenamientos.insercionDirecta(ordenados, Ordenamientos.Criterio.CLAVE);
        Busquedas.ResultadoBusqueda resultadoBinaria = Busquedas.binaria(ordenados, clave);

        System.out.println("Búsqueda secuencial: " + resultadoSecuencial.getPasos() + " pasos ("
                + (resultadoSecuencial.encontrado() ? "encontrado" : "no encontrado") + ")");
        System.out.println("Búsqueda binaria:    " + resultadoBinaria.getPasos() + " pasos ("
                + (resultadoBinaria.encontrado() ? "encontrado" : "no encontrado") + ")");

        if (resultadoBinaria.getPasos() < resultadoSecuencial.getPasos()) {
            System.out.println("En este caso, la búsqueda binaria necesitó menos pasos que la secuencial.");
        } else if (resultadoBinaria.getPasos() > resultadoSecuencial.getPasos()) {
            System.out.println("En este caso, la búsqueda secuencial necesitó menos pasos que la binaria.");
        } else {
            System.out.println("En este caso, ambas búsquedas necesitaron el mismo número de pasos.");
        }

        historial.registrar("Se compararon los pasos de búsqueda secuencial (" + resultadoSecuencial.getPasos()
                + ") y binaria (" + resultadoBinaria.getPasos() + ") para '" + clave + "'");
    }

    // ---------- Utilidades de lectura ----------

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        int valor;
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                valor = Integer.parseInt(entrada);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número entero.");
            }
        }
        return valor;
    }
}

class NodoSimple {

    private Curso dato;
    private NodoSimple siguiente;

    public NodoSimple(Curso dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    public Curso getDato() {
        return dato;
    }
    public NodoSimple getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }
}

class ListaEnlazadaSimple {

    private NodoSimple cabeza;

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregar(Curso curso) {
        NodoSimple nuevo = new NodoSimple(curso);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        NodoSimple actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        NodoSimple actual = cabeza;
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato());
            actual = actual.getSiguiente();
            i++;
        }
    }

    public Curso buscarPorClave(String clave) {
        NodoSimple actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getClave().equalsIgnoreCase(clave)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Curso buscarPorId(int idCurso) {
        NodoSimple actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getIdCurso() == idCurso) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean eliminarPorClave(String clave) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.getDato().getClave().equalsIgnoreCase(clave)) {
            cabeza = cabeza.getSiguiente();
            return true;
        }

        NodoSimple anterior = cabeza;
        NodoSimple actual = cabeza.getSiguiente();

        while (actual != null) {
            if (actual.getDato().getClave().equalsIgnoreCase(clave)) {
                anterior.setSiguiente(actual.getSiguiente());
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    public int contarCursosRecursivo() {
        return contarRecursivoAux(cabeza);
    }

    private int contarRecursivoAux(NodoSimple nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarRecursivoAux(nodo.getSiguiente());
    }

    public Curso buscarRecursivo(String clave) {
        return buscarRecursivoAux(cabeza, clave);
    }

    private Curso buscarRecursivoAux(NodoSimple nodo, String clave) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getDato().getClave().equalsIgnoreCase(clave)) {
            return nodo.getDato();
        }
        return buscarRecursivoAux(nodo.getSiguiente(), clave);
    }

    // Convierte la lista enlazada en un ArrayList, útil para árbol, grafo, ordenamientos y búsquedas
    public List<Curso> obtenerCursosComoLista() {
        List<Curso> lista = new ArrayList<>();
        NodoSimple actual = cabeza;
        while (actual != null) {
            lista.add(actual.getDato());
            actual = actual.getSiguiente();
        }
        return lista;
    }
}

class NodoDoble {

    private Curso dato;
    private NodoDoble anterior;
    private NodoDoble siguiente;

    public NodoDoble(Curso dato) {
        this.dato = dato;
    }

    public Curso getDato() {
        return dato;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }
}

class ListaDoblementeEnlazada {

    private NodoDoble cabeza;
    private NodoDoble cola;
    private NodoDoble actual;

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregarAlFinal(Curso curso) {
        NodoDoble nuevo = new NodoDoble(curso);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }

        actual = cabeza;
    }

    public boolean eliminarPorClave(String clave) {
        NodoDoble nodo = cabeza;

        while (nodo != null) {
            if (nodo.getDato().getClave().equalsIgnoreCase(clave)) {

                if (nodo.getAnterior() != null) {
                    nodo.getAnterior().setSiguiente(nodo.getSiguiente());
                } else {
                    cabeza = nodo.getSiguiente();
                }

                if (nodo.getSiguiente() != null) {
                    nodo.getSiguiente().setAnterior(nodo.getAnterior());
                } else {
                    cola = nodo.getAnterior();
                }

                actual = cabeza;
                return true;
            }
            nodo = nodo.getSiguiente();
        }

        return false;
    }

    public void mostrarInicioAFin() {
        if (estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        NodoDoble nodo = cabeza;
        int i = 1;
        while (nodo != null) {
            System.out.println(i + ". " + nodo.getDato());
            nodo = nodo.getSiguiente();
            i++;
        }
    }

    public void mostrarFinAInicio() {
        if (estaVacia()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        NodoDoble nodo = cola;
        int i = 1;
        while (nodo != null) {
            System.out.println(i + ". " + nodo.getDato());
            nodo = nodo.getAnterior();
            i++;
        }
    }

    public void reiniciarCarrusel() {
        actual = cabeza;
    }

    public Curso verActual() {
        if (actual == null) {
            return null;
        }
        return actual.getDato();
    }

    public boolean avanzar() {
        if (actual != null && actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
            return true;
        }
        return false;
    }

    public boolean retroceder() {
        if (actual != null && actual.getAnterior() != null) {
            actual = actual.getAnterior();
            return true;
        }
        return false;
    }
}
