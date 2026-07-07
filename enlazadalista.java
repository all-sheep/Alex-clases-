public class ListaEnlazadaBasica {

    static class Nodo {
        String dato;
        Nodo siguienteNodo;

        Nodo(String dato) {
            this.dato = dato;
            this.siguienteNodo = null;
        }
    }

    public static void main(String[] args) {
        Nodo n1 = new Nodo("A");
        Nodo n2 = new Nodo("B");
        Nodo cabeza = n1;

        n1.siguienteNodo = n2;

        Nodo n3 = new Nodo("C");
        n2.siguienteNodo = n3;

        Nodo n4 = new Nodo("D");
        n3.siguienteNodo = n4;

        Nodo n5 = new Nodo("E");
        n4.siguienteNodo = n5;

        // Recorrido para imprimir la lista
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguienteNodo;
        }
        System.out.println("null");
    }
}