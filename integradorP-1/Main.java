import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;


public class Main {

    private static ArrayList<Curso> cursos = new ArrayList<>();
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
                    mostrarCursos();
                    break;
                case 3:
                    buscarCursoPorClave();
                    break;
                case 4:
                    inscribirEstudiante();
                    break;
                case 5:
                    darDeBajaEstudiante();
                    break;
                case 6:
                    eliminarCurso();
                    break;
                case 7:
                    mostrarHistorial();
                    break;
                case 8:
                    ordenarCursos();
                    break;
                case 9:
                    System.out.println("Saliendo del sistema... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }

            System.out.println();

        } while (opcion != 9);

        sc.close();
    }
    private static void mostrarMenu() {
        System.out.println("===================================================");
        System.out.println("       SISTEMA DE GESTIÓN DE CURSOS UTC");
        System.out.println("===================================================");
        System.out.println("1. Agregar curso");
        System.out.println("2. Mostrar cursos");
        System.out.println("3. Buscar curso por clave");
        System.out.println("4. Inscribir estudiante");
        System.out.println("5. Dar de baja estudiante");
        System.out.println("6. Eliminar curso");
        System.out.println("7. Mostrar historial de acciones");
        System.out.println("8. Ordenar cursos");
        System.out.println("9. Salir");
        System.out.println("===================================================");
    }

    private static void agregarCurso() {
        System.out.println("--- Agregar nuevo curso ---");

        String clave = leerTexto("Clave del curso: ");

        if (buscarPorClaveInterno(clave) != null) {
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
        cursos.add(nuevo);

        String accion = "Se agregó el curso " + nombre + " (" + clave + ")";
        historial.push(accion);

        System.out.println("Curso agregado correctamente.");
    }
    private static void mostrarCursos() {
        System.out.println("--- Lista de cursos ---");

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i));
        }
    }

    private static void buscarCursoPorClave() {
        System.out.println("--- Buscar curso por clave ---");
        String clave = leerTexto("Ingresa la clave a buscar: ");

        Curso encontrado = buscarPorClaveInterno(clave);

        if (encontrado != null) {
            System.out.println("Curso encontrado:");
            System.out.println(encontrado);
        } else {
            System.out.println("No se encontró ningún curso con la clave: " + clave);
        }
    }

    private static void inscribirEstudiante() {
        System.out.println("--- Inscribir estudiante ---");
        String clave = leerTexto("Clave del curso: ");

        Curso curso = buscarPorClaveInterno(clave);

        if (curso == null) {
            System.out.println("No existe un curso con esa clave.");
            return;
        }

        if (!curso.hayCupoDisponible()) {
            System.out.println("El curso ya alcanzó su cupo máximo. No se puede inscribir.");
            return;
        }

        curso.inscribirEstudiante();

        String accion = "Se inscribió un estudiante en " + curso.getNombre() + " (" + curso.getClave() + ")";
        historial.push(accion);

        System.out.println("Estudiante inscrito correctamente. Cupo actual: "
                + curso.getInscritos() + "/" + curso.getCupoMaximo());
    }

    private static void darDeBajaEstudiante() {
        System.out.println("--- Dar de baja estudiante ---");
        String clave = leerTexto("Clave del curso: ");

        Curso curso = buscarPorClaveInterno(clave);

        if (curso == null) {
            System.out.println("No existe un curso con esa clave.");
            return;
        }

        boolean exito = curso.darDeBajaEstudiante();

        if (exito) {
            String accion = "Se dio de baja un estudiante de " + curso.getNombre() + " (" + curso.getClave() + ")";
            historial.push(accion);
            System.out.println("Estudiante dado de baja correctamente. Cupo actual: "
                    + curso.getInscritos() + "/" + curso.getCupoMaximo());
        } else {
            System.out.println("El curso no tiene estudiantes inscritos para dar de baja.");
        }
    }

    private static void eliminarCurso() {
        System.out.println("--- Eliminar curso ---");
        String clave = leerTexto("Clave del curso a eliminar: ");

        Curso curso = buscarPorClaveInterno(clave);

        if (curso == null) {
            System.out.println("No existe un curso con esa clave.");
            return;
        }

        cursos.remove(curso);

        String accion = "Se eliminó el curso " + curso.getNombre() + " (" + curso.getClave() + ")";
        historial.push(accion);

        System.out.println("Curso eliminado correctamente.");
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

    private static void ordenarCursos() {
        System.out.println("--- Ordenar cursos ---");

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados para ordenar.");
            return;
        }

        System.out.println("1. Ordenar por nombre (A-Z)");
        System.out.println("2. Ordenar por cupo máximo (menor a mayor)");
        System.out.println("3. Ordenar por número de inscritos (mayor a menor)");
        int opcion = leerEntero("Selecciona un criterio de ordenamiento: ");

        String criterio;

        switch (opcion) {
            case 1:
                Collections.sort(cursos, Comparator.comparing(Curso::getNombre));
                criterio = "nombre";
                break;
            case 2:
                Collections.sort(cursos, Comparator.comparingInt(Curso::getCupoMaximo));
                criterio = "cupo máximo";
                break;
            case 3:
                Collections.sort(cursos, Comparator.comparingInt(Curso::getInscritos).reversed());
                criterio = "número de inscritos";
                break;
            default:
                System.out.println("Opción inválida. No se realizó ningún ordenamiento.");
                return;
        }

        String accion = "Se ordenaron los cursos por " + criterio;
        historial.push(accion);

        System.out.println("Cursos ordenados por " + criterio + ":");
        mostrarCursos();
    }

    private static Curso buscarPorClaveInterno(String clave) {
        for (Curso c : cursos) {
            if (c.getClave().equalsIgnoreCase(clave)) {
                return c;
            }
        }
        return null;
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
