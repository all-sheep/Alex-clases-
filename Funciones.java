import java.util.Scanner;

public class Funciones {

    public static void saludo() {
        System.out.println("¡Hola amigo!");
    }

    public static void saludar(String nombre) {
        System.out.println("¡Hola " + nombre + "!");
    }

    public static long factorial(int n) {
        long r = 1;
        for (int i = 1; i <= n; i++) r *= i;
        return r;
    }

    public static double calcularFactura(double cantidad) {
        return cantidad * 1.21;
    }

    public static double calcularFactura(double cantidad, double iva) {
        return cantidad + (cantidad * iva / 100);
    }

    public static double areaCirculo(double radio) {
        return Math.PI * radio * radio;
    }

    public static double volumenCilindro(double radio, double altura) {
        return areaCirculo(radio) * altura;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        saludo();

        System.out.print("Nombre: ");
        saludar(sc.nextLine());

        System.out.print("Número: ");
        int n = sc.nextInt();
        System.out.println("Factorial: " + factorial(n));

        System.out.print("Cantidad: ");
        double cantidad = sc.nextDouble();
        System.out.println("IVA 21%: " + calcularFactura(cantidad));

        System.out.print("IVA: ");
        double iva = sc.nextDouble();
        System.out.println("Total: " + calcularFactura(cantidad, iva));

        System.out.print("Radio: ");
        double radio = sc.nextDouble();

        System.out.print("Altura: ");
        double altura = sc.nextDouble();

        System.out.println("Área: " + areaCirculo(radio));
        System.out.println("Volumen: " + volumenCilindro(radio, altura));

        sc.close();
    }
}
