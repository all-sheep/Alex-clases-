public class Nodo {
    private String dato;
    private Nodo siguienteNodo;

    public Nodo(String dato) {
        this.dato = dato;
        this.siguienteNodo = null;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getSiguienteNodo() {
        return siguienteNodo;
    }

    public void setSiguienteNodo(Nodo siguienteNodo) {
        this.siguienteNodo = siguienteNodo;
    }
}