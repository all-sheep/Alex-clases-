import java.util.List;

public class Ordenamientos {

    public enum Criterio { ID, CLAVE, NOMBRE, CUPO, INSCRITOS }

    private static int comparar(Curso a, Curso b, Criterio criterio) {
        switch (criterio) {
            case ID:
                return Integer.compare(a.getIdCurso(), b.getIdCurso());
            case CLAVE:
                return a.getClave().compareToIgnoreCase(b.getClave());
            case NOMBRE:
                return a.getNombre().compareToIgnoreCase(b.getNombre());
            case CUPO:
                return Integer.compare(a.getCupoMaximo(), b.getCupoMaximo());
            case INSCRITOS:
                return Integer.compare(a.getNumeroInscritos(), b.getNumeroInscritos());
            default:
                return 0;
        }
    }

    private static void intercambiar(List<Curso> lista, int i, int j) {
        Curso temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }

    // ---------- Bubble Sort directo (menor a mayor) ----------
    public static void bubbleSortDirecto(List<Curso> lista, Criterio criterio) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparar(lista.get(j), lista.get(j + 1), criterio) > 0) {
                    intercambiar(lista, j, j + 1);
                }
            }
        }
    }

    // ---------- Bubble Sort inverso (mayor a menor) ----------
    public static void bubbleSortInverso(List<Curso> lista, Criterio criterio) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparar(lista.get(j), lista.get(j + 1), criterio) < 0) {
                    intercambiar(lista, j, j + 1);
                }
            }
        }
    }

    // ---------- Inserción directa ----------
    public static void insercionDirecta(List<Curso> lista, Criterio criterio) {
        for (int i = 1; i < lista.size(); i++) {
            Curso actual = lista.get(i);
            int j = i - 1;
            while (j >= 0 && comparar(lista.get(j), actual, criterio) > 0) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, actual);
        }
    }

    // ---------- Selección directa ----------
    public static void seleccionDirecta(List<Curso> lista, Criterio criterio) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                if (comparar(lista.get(j), lista.get(menor), criterio) < 0) {
                    menor = j;
                }
            }
            if (menor != i) {
                intercambiar(lista, i, menor);
            }
        }
    }
}
