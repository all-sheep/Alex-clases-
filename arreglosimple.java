public class si {
    //Con retorno de datos y con parametros
    public static double suma(double num1, double num2){
        double resultado = num1 + num2;
        return resultado;
    }
    //Sin retorno con paremetros
    public static void resta(double num1, double num2){
        double resultado = num1 - num2;
        System.out.println("Resta:" + resultado);
    }
    //Sin retorno y sin paremetros
    public static void multiplicacion(){
        double num1 = 8;
        double num2 = 7;
        double resultado = num1 * num2;
        System.out.println("Multiplicacion:" + resultado);
    }
    //Con retorno y sin paremetros
    public static double division(){
        double num1 = 87;
        double num2 = 7;
        double resultado = num1 / num2;
        return resultado;
    }

}
 
