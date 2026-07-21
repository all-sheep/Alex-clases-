import java.util.Scanner;

public class MetodoRecursivo {
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos términos de la serie deseas ver? ");
        int cantidad = sc.nextInt();

        for (int i = 0; i < cantidad; i++) {
            System.out.print(fibonacci(i));
            if (i < cantidad - 1) {
                System.out.print(", ");
            }
        }

        sc.close();
    }
}
