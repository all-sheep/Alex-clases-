import java.util.Scanner;

public class grafo1matris {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuantos vertices?: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] vertices = new String[n];
        int[][] matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del vertice " + (i + 1) + ": ");
            vertices[i] = sc.nextLine();
        }

        System.out.println("\nIngrese las relaciones (1 = Si, 0 = No)");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(vertices[i] + " -> " + vertices[j] + ": ");
                matriz[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nMatriz");

        System.out.print("\t");
        for (int i = 0; i < n; i++) {
            System.out.print(vertices[i] + "\t");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(vertices[i] + "\t");
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }

        sc.close();
    }
}