import java.util.Scanner;

public class Raiz {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el valor de la raíz: ");
        int valorRaiz = sc.nextInt();
        Nodo raiz = new Nodo(valorRaiz);

        System.out.print("Ingrese el valor del hijo izquierdo: ");
        int valorIzquierdo = sc.nextInt();
        raiz.izquierda = new Nodo(valorIzquierdo);

        System.out.print("Ingrese el valor del hijo derecho: ");
        int valorDerecho = sc.nextInt();
        raiz.derecha = new Nodo(valorDerecho);

        System.out.println("\nÁrbol creado:");
        System.out.println("Raíz: " + raiz.valor);
        System.out.println("Hijo izquierdo: " + raiz.izquierda.valor);
        System.out.println("Hijo derecho: " + raiz.derecha.valor);

        sc.close();
    }
}