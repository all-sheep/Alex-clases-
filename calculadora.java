public class calculadora {
    void main() {
        double Resultado = Funciones.suma(158.0,204.0);
        System.out.println("Suma:" + Resultado);

        Funciones.resta(789,412);
        Funciones.multiplicacion();
        Resultado = Funciones.division();
        System.out.println("Division:" + Resultado);
    }
}
