public class Fibonacci {

    public static void main(String[] args) {

        int a = 0;
        int b = 1;

        System.out.println("Los 50 primeros números de Fibonacci son:");

        for (int i = 1; i <= 50; i++) {

            System.out.print(a + " ");

            int siguiente = a + b;
            a = b;
            b = siguiente;
        }
    }
}
