public class Lista {

    public static Nodo buscar(Nodo inicio, String dato) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual;
            }
            actual = actual.getSiguienteNodo();
        }
        return null;
    }

    public static boolean actualizar(Nodo inicio, String datoBuscado, String datoNuevo) {
        Nodo nodo = buscar(inicio, datoBuscado);
        if (nodo != null) {
            nodo.setDato(datoNuevo);
            return true;
        }
        return false;
    }

    public static void mostrar(Nodo inicio) {
        Nodo actual = inicio;
        while (actual != null) {
            String siguiente = (actual.getSiguienteNodo() != null)
                    ? actual.getSiguienteNodo().getDato()
                    : "null";

            System.out.println("Dato: " + actual.getDato()
                    + " -> Siguiente: " + siguiente);

            actual = actual.getSiguienteNodo();
        }
    }
}


