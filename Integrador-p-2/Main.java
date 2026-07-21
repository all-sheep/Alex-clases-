import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static ListaEnlazadaSimple listaSimple = new ListaEnlazadaSimple();
    private static ListaDoblementeEnlazada listaDoble = new ListaDoblementeEnlazada();
    private static Stack<String> historial = new Stack<>();
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
                    mostrarHistorial();
                    break;
                case 13:
                    System.out.println("Saliendo del sistema... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }

            System.out.println();

        } while (opcion != 13);

        sc.close();
    }
    private static void mostrarMenu() {
        System.out.println("===== SISTEMA DE GESTIÓN DE CURSOS UTC 2.0 =====");
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
        System.out.println("12. Mostrar historial de acciones");
        System.out.println("13. Salir");
        System.out.println("=================================================");
    }
    private static void agregarCurso() {
        System.out.println("--- Agregar nuevo curso ---");

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

        Curso nuevo = new Curso(clave, nombre, docente, cupo);

        listaSimple.agregar(nuevo);
        listaDoble.agregarAlFinal(nuevo);

        historial.push("Se agregó el curso " + nombre + " (" + clave + ")");
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

        historial.push("Se eliminó el curso " + curso.getNombre() + " (" + clave + ")");
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
        historial.push("Se inscribió un estudiante en " + curso.getNombre() + " (" + curso.getClave() + ")");
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
            historial.push("Se dio de baja un estudiante de " + curso.getNombre() + " (" + curso.getClave() + ")");
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
        historial.push("Se contaron los cursos de forma recursiva (" + total + " encontrados)");
        System.out.println("Número total de cursos (calculado recursivamente): " + total);
    }

    private static void buscarCursoRecursivo() {
        System.out.println("--- Buscar curso por clave (recursivo) ---");
        String clave = leerTexto("Ingresa la clave a buscar: ");

        Curso encontrado = listaSimple.buscarRecursivo(clave);

        if (encontrado != null) {
            historial.push("Se buscó de forma recursiva el curso " + clave + " (encontrado)");
            System.out.println("Curso encontrado:");
            System.out.println(encontrado);
        } else {
            historial.push("Se buscó de forma recursiva el curso " + clave + " (no encontrado)");
            System.out.println("No se encontró ningún curso con la clave: " + clave);
        }
    }

    private static void mostrarHistorial() {
        System.out.println("--- Historial de acciones (más reciente primero) ---");

        if (historial.isEmpty()) {
            System.out.println("Aún no se han realizado acciones.");
            return;
        }

        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.println((historial.size() - i) + ". " + historial.get(i));
        }
    }

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
