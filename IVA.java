public class ejercicios_funciones {
    public static void main(String[] args) {
        Funciones.saludo();
        Funciones.saludarNombre("Ztamtzel");
        System.out.println(Funciones.factorial(5));
        System.out.println("Factura con IVA sin especificar: " + Funciones.calcularFactura(100.0));
        System.out.println("Factura con IVA especificado: " + Funciones.calcularFactura(100.0,16.0));
        System.out.println("Área del círculo: " + Funciones.areaCirculo(7.0));
        System.out.println("Volumen del cilindro: " + Funciones.volumenCilindro(5.0, 10.0));
    }
}
