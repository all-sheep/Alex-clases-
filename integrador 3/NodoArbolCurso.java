public class NodoArbolCurso {

    private Curso curso;
    private NodoArbolCurso izquierdo;
    private NodoArbolCurso derecho;

    public NodoArbolCurso(Curso curso) {
        this.curso = curso;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public NodoArbolCurso getIzquierdo() {
        return izquierdo;
    }
    public void setIzquierdo(NodoArbolCurso izquierdo) {
        this.izquierdo = izquierdo;
    }
    public NodoArbolCurso getDerecho() {
        return derecho;
    }
    public void setDerecho(NodoArbolCurso derecho) {
        this.derecho = derecho;
    }
}
