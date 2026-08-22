import java.util.Scanner;

public class GrafoMatriz {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos vértices tendrá el grafo?: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] vertices = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del vértice " + (i + 1) + ": ");
            vertices[i] = sc.nextLine();
        }

        int[][] matriz = new int[n][n];

        System.out.println("\n--- Ingrese las relaciones ---");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(vertices[i] + " -> " + vertices[j] + " (1=Sí,0=No): ");
                matriz[i][j] = sc.nextInt();
            }
        }

        while (true) {

            mostrarMatriz(vertices, matriz);

            System.out.print("\n¿Desea agregar más vértices? (S/N): ");
            sc.nextLine();
            String opcion = sc.nextLine();

            if (!opcion.equalsIgnoreCase("S")) {
                break;
            }

            System.out.print("¿Cuántos vértices desea agregar?: ");
            int nuevos = sc.nextInt();
            sc.nextLine();

            int total = vertices.length + nuevos;

            String[] nuevosVertices = new String[total];
            int[][] nuevaMatriz = new int[total][total];

            for (int i = 0; i < vertices.length; i++)
                nuevosVertices[i] = vertices[i];

            for (int i = 0; i < matriz.length; i++)
                for (int j = 0; j < matriz.length; j++)
                    nuevaMatriz[i][j] = matriz[i][j];

            for (int i = vertices.length; i < total; i++) {
                System.out.print("Nombre del vértice " + (i + 1) + ": ");
                nuevosVertices[i] = sc.nextLine();
            }

            System.out.println("\n--- Relaciones de los nuevos vértices ---");

            for (int i = 0; i < total; i++) {
                for (int j = vertices.length; j < total; j++) {
                    System.out.print(nuevosVertices[i] + " -> " + nuevosVertices[j] + " (1=Sí,0=No): ");
                    nuevaMatriz[i][j] = sc.nextInt();
                }
            }

            for (int i = vertices.length; i < total; i++) {
                for (int j = 0; j < vertices.length; j++) {
                    System.out.print(nuevosVertices[i] + " -> " + nuevosVertices[j] + " (1=Sí,0=No): ");
                    nuevaMatriz[i][j] = sc.nextInt();
                }
            }

            vertices = nuevosVertices;
            matriz = nuevaMatriz;
        }

        System.out.println("\nMatriz final:");
        mostrarMatriz(vertices, matriz);

        sc.close();
    }

    public static void mostrarMatriz(String[] vertices, int[][] matriz) {

        System.out.println("\nMatriz de Adyacencia");

        System.out.print("\t");
        for (String v : vertices)
            System.out.print(v + "\t");

        System.out.println();

        for (int i = 0; i < matriz.length; i++) {
            System.out.print(vertices[i] + "\t");
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }
}