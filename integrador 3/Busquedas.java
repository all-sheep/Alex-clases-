import java.util.List;

public class Busquedas {

    public static class ResultadoBusqueda {
        private Curso curso;
        private int pasos;

        public ResultadoBusqueda(Curso curso, int pasos) {
            this.curso = curso;
            this.pasos = pasos;
        }
        public Curso getCurso() {
            return curso;
        }
        public int getPasos() {
            return pasos;
        }
        public boolean encontrado() {
            return curso != null;
        }
    }

    // ---------- Búsqueda secuencial por clave ----------
    public static ResultadoBusqueda secuencial(List<Curso> lista, String clave) {
        int pasos = 0;
        for (Curso c : lista) {
            pasos++;
            if (c.getClave().equalsIgnoreCase(clave)) {
                return new ResultadoBusqueda(c, pasos);
            }
        }
        return new ResultadoBusqueda(null, pasos);
    }

    // ---------- Búsqueda binaria por clave ----------
    // Precondición: listaOrdenadaPorClave debe estar ordenada por clave (ascendente)
    public static ResultadoBusqueda binaria(List<Curso> listaOrdenadaPorClave, String clave) {
        int pasos = 0;
        int izquierda = 0;
        int derecha = listaOrdenadaPorClave.size() - 1;

        while (izquierda <= derecha) {
            pasos++;
            int medio = (izquierda + derecha) / 2;
            int cmp = listaOrdenadaPorClave.get(medio).getClave().compareToIgnoreCase(clave);
            if (cmp == 0) {
                return new ResultadoBusqueda(listaOrdenadaPorClave.get(medio), pasos);
            } else if (cmp < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return new ResultadoBusqueda(null, pasos);
    }
}
