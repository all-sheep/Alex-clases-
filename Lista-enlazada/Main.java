import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Nodo inicio = null;
        Nodo ultimo = null;

        System.out.print("¿Cuántos nodos desea crear?: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Dato del nodo " + i + ": ");
            String dato = sc.nextLine();

            Nodo nuevo = new Nodo(dato);

            if (inicio == null) {
                inicio = nuevo;
                ultimo = nuevo;
            } else {
                ultimo.setSiguienteNodo(nuevo);
                ultimo = nuevo;
            }
        }

        int opcion;

        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Mostrar lista");
            System.out.println("2. Buscar nodo");
            System.out.println("3. Actualizar nodo");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Lista.mostrar(inicio);
                    break;

                case 2:
                    System.out.print("Dato a buscar: ");
                    String buscar = sc.nextLine();

                    Nodo encontrado = Lista.buscar(inicio, buscar);

                    if (encontrado != null)
                        System.out.println("Encontrado: " + encontrado.getDato());
                    else
                        System.out.println("No existe.");
                    break;

                case 3:
                    System.out.print("Dato actual: ");
                    String viejo = sc.nextLine();

                    System.out.print("Dato nuevo: ");
                    String nuevo = sc.nextLine();

                    if (Lista.actualizar(inicio, viejo, nuevo))
                        System.out.println("Actualizado correctamente.");
                    else
                        System.out.println("No se encontró el dato.");
                    break;

                case 4:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);

        sc.close();
    }
}