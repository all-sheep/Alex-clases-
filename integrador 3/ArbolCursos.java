public class ArbolCursos {

    private NodoArbolCurso raiz;
    private int comparacionesUltimaBusqueda;

    public ArbolCursos() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // ---------- Insertar ----------
    public void insertar(Curso curso) {
        if (buscar(curso.getIdCurso()) != null) {
            System.out.println("Ya existe un curso con id " + curso.getIdCurso() + " en el árbol. No se insertó.");
            return;
        }
        raiz = insertarAux(raiz, curso);
    }

    private NodoArbolCurso insertarAux(NodoArbolCurso nodo, Curso curso) {
        if (nodo == null) {
            return new NodoArbolCurso(curso);
        }
        if (curso.getIdCurso() < nodo.getCurso().getIdCurso()) {
            nodo.setIzquierdo(insertarAux(nodo.getIzquierdo(), curso));
        } else if (curso.getIdCurso() > nodo.getCurso().getIdCurso()) {
            nodo.setDerecho(insertarAux(nodo.getDerecho(), curso));
        }
        return nodo;
    }

    // ---------- Buscar ----------
    public Curso buscar(int idCurso) {
        comparacionesUltimaBusqueda = 0;
        return buscarAux(raiz, idCurso);
    }

    private Curso buscarAux(NodoArbolCurso nodo, int idCurso) {
        if (nodo == null) {
            return null;
        }
        comparacionesUltimaBusqueda++;
        if (idCurso == nodo.getCurso().getIdCurso()) {
            return nodo.getCurso();
        } else if (idCurso < nodo.getCurso().getIdCurso()) {
            return buscarAux(nodo.getIzquierdo(), idCurso);
        } else {
            return buscarAux(nodo.getDerecho(), idCurso);
        }
    }

    public int getComparacionesUltimaBusqueda() {
        return comparacionesUltimaBusqueda;
    }

    // ---------- Recorrido inorden ----------
    public void mostrarInorden() {
        if (estaVacio()) {
            System.out.println("El árbol de cursos está vacío.");
            return;
        }
        System.out.println("--- Recorrido inorden (ordenado por idCurso) ---");
        inordenAux(raiz);
    }

    private void inordenAux(NodoArbolCurso nodo) {
        if (nodo == null) {
            return;
        }
        inordenAux(nodo.getIzquierdo());
        System.out.println(nodo.getCurso());
        inordenAux(nodo.getDerecho());
    }
}
