public class NumerosPrimos {

    // Método para comprobar si un número es primo
    public static boolean esPrimo(int numero) {

        if (numero <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println("Números primos entre 1 y 100:");

        for (int i = 1; i <= 100; i++) {
            if (esPrimo(i)) {
                System.out.print(i + " ");
            }
        }
    }
}
