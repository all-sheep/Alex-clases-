import java.util.Stack;

public class HistorialAcciones {

    private Stack<String> acciones;

    public HistorialAcciones() {
        acciones = new Stack<>();
    }

    public void registrar(String descripcion) {
        acciones.push(descripcion);
    }

    public boolean estaVacio() {
        return acciones.isEmpty();
    }

    public void mostrar() {
        if (estaVacio()) {
            System.out.println("Aún no se han realizado acciones.");
            return;
        }
        System.out.println("--- Historial de acciones (más reciente primero) ---");
        int total = acciones.size();
        for (int i = total - 1; i >= 0; i--) {
            System.out.println((total - i) + ". " + acciones.get(i));
        }
    }
}
