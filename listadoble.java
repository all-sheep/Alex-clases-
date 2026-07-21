import java.util.Scanner;

public class ListaDoble {

    static class NodoDoble {
        private String dato;
        private NodoDoble prev;
        private NodoDoble next;

        public NodoDoble(String dato) {
            this.dato = dato;
            this.prev = null;
            this.next = null;
        }
    }

    private NodoDoble cabeza;
    private NodoDoble cola;

    public void insertar(String dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.next = nuevo;
            nuevo.prev = cola;
            cola = nuevo;
        }
    }

    public NodoDoble buscar(String dato) {
        NodoDoble actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return actual;
            }
            actual = actual.next;
        }
        return null;
    }

    public boolean actualizar(String datoBuscado, String datoNuevo) {
        NodoDoble nodo = buscar(datoBuscado);
        if (nodo != null) {
            nodo.dato = datoNuevo;
            return true;
        }
        return false;
    }

    public boolean eliminar(String dato) {
        NodoDoble nodo = buscar(dato);
        if (nodo == null) {
            return false;
        }
        if (nodo.prev != null) {
            nodo.prev.next = nodo.next;
        } else {
            cabeza = nodo.next;
        }
        if (nodo.next != null) {
            nodo.next.prev = nodo.prev;
        } else {
            cola = nodo.prev;
        }
        return true;
    }

    public void recorrerAdelante() {
        NodoDoble actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.next;
        }
        System.out.println();
    }

    public void recorrerAtras() {
        NodoDoble actual = cola;
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.prev;
        }
        System.out.println();
    }

    public void mostrarListaDoble() {
        NodoDoble actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.next;
        }
    }

    public static void main(String[] args) {
        ListaDoble lista = new ListaDoble();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menu Lista Doblemente Enlazada ---");
            System.out.println("1. Insertar");
            System.out.println("2. Buscar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Recorrer adelante");
            System.out.println("6. Recorrer atras");
            System.out.println("7. Mostrar lista doblemente enlazada");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Dato a insertar: ");
                    lista.insertar(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Dato a buscar: ");
                    NodoDoble encontrado = lista.buscar(sc.nextLine());
                    if (encontrado != null) {
                        System.out.println("Nodo encontrado con dato: " + encontrado.dato);
                    } else {
                        System.out.println("Nodo no encontrado");
                    }
                    break;
                case 3:
                    System.out.print("Dato a buscar: ");
                    String datoBuscado = sc.nextLine();
                    System.out.print("Nuevo dato: ");
                    String datoNuevo = sc.nextLine();
                    if (lista.actualizar(datoBuscado, datoNuevo)) {
                        System.out.println("Actualizado correctamente");
                    } else {
                        System.out.println("No se encontro el nodo");
                    }
                    break;
                case 4:
                    System.out.print("Dato a eliminar: ");
                    if (lista.eliminar(sc.nextLine())) {
                        System.out.println("Eliminado correctamente");
                    } else {
                        System.out.println("No se encontro el nodo");
                    }
                    break;
                case 5:
                    System.out.println("Recorrido adelante:");
                    lista.recorrerAdelante();
                    break;
                case 6:
                    System.out.println("Recorrido atras:");
                    lista.recorrerAtras();
                    break;
                case 7:
                    lista.mostrarListaDoble();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);

        sc.close();
    }
}
