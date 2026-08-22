import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoCursos {

    // Grafo dirigido y ponderado: idCurso -> lista de aristas salientes
    private Map<Integer, List<Arista>> adyacencia;

    public GrafoCursos() {
        adyacencia = new HashMap<>();
    }

    public void agregarVertice(int idCurso) {
        adyacencia.putIfAbsent(idCurso, new ArrayList<>());
    }

    public boolean existeVertice(int idCurso) {
        return adyacencia.containsKey(idCurso);
    }

    // origen = curso prerrequisito/relacionado, destino = curso relacionado, peso = dificultad/prioridad/créditos
    public boolean crearRelacion(int origen, int destino, int peso) {
        if (!existeVertice(origen) || !existeVertice(destino)) {
            return false;
        }
        if (origen == destino) {
            return false;
        }
        adyacencia.get(origen).add(new Arista(destino, peso));
        return true;
    }

    public boolean estaVacio() {
        return adyacencia.isEmpty();
    }

    public void mostrarListaAdyacencia(List<Curso> cursos) {
        if (estaVacio()) {
            System.out.println("El grafo no tiene cursos registrados todavía.");
            return;
        }
        System.out.println("--- Lista de adyacencia (grafo dirigido y ponderado) ---");
        List<Integer> ids = new ArrayList<>(adyacencia.keySet());
        Collections.sort(ids);
        for (int origen : ids) {
            StringBuilder sb = new StringBuilder();
            sb.append("Curso ").append(origen).append(" (").append(obtenerNombre(cursos, origen)).append(") -> ");
            List<Arista> relaciones = adyacencia.get(origen);
            if (relaciones.isEmpty()) {
                sb.append("sin relaciones");
            } else {
                for (Arista a : relaciones) {
                    sb.append("[").append(a.getDestino()).append(" ")
                      .append(obtenerNombre(cursos, a.getDestino()))
                      .append(", peso=").append(a.getPeso()).append("] ");
                }
            }
            System.out.println(sb.toString());
        }
    }

    public void mostrarMatrizAdyacencia(List<Curso> cursos) {
        if (estaVacio()) {
            System.out.println("El grafo no tiene cursos registrados todavía.");
            return;
        }
        System.out.println("--- Matriz de adyacencia (peso de la relación entre cursos) ---");
        List<Integer> ids = new ArrayList<>(adyacencia.keySet());
        Collections.sort(ids);

        System.out.print("        ");
        for (int id : ids) {
            System.out.printf("%-6d", id);
        }
        System.out.println();

        for (int origen : ids) {
            System.out.printf("%-8d", origen);
            for (int destino : ids) {
                int peso = 0;
                for (Arista a : adyacencia.get(origen)) {
                    if (a.getDestino() == destino) {
                        peso = a.getPeso();
                        break;
                    }
                }
                System.out.printf("%-6d", peso);
            }
            System.out.println();
        }
    }

    private String obtenerNombre(List<Curso> cursos, int idCurso) {
        for (Curso c : cursos) {
            if (c.getIdCurso() == idCurso) {
                return c.getNombre();
            }
        }
        return "?";
    }

    private static class Arista {
        private int destino;
        private int peso;

        public Arista(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
        public int getDestino() {
            return destino;
        }
        public int getPeso() {
            return peso;
        }
    }
}
