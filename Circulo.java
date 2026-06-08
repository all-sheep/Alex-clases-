import java.util.Scanner;

public class Circulo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double radio;

        System.out.print("Ingresa el radio del círculo: ");
        radio = sc.nextDouble();

        double area = Math.PI * Math.pow(radio, 2);
        double perimetro = 2 * Math.PI * radio;

        System.out.println("Área: " + area);
        System.out.println("Perímetro: " + perimetro);
    }
}
