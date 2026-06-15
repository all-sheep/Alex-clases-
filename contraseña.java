import java.util.Scanner;

public class contraseña {
        public static void main(String[] args) {
            Scanner entrada = new Scanner(System.in);
            String contraseñacorrecta = "123450";
            String contraseña = "";
            while(!contraseña.equals(contraseñacorrecta)){
                System.out.println("Introduce la contraseña:");
                contraseña = entrada.nextLine();
                if(contraseña.equals(contraseñacorrecta)){
                    System.out.println("Contraseña correcta, acceso concedido");
                } else {
                    System.out.println("Contraseña incorrecta, intenta de nuevo");
                }
            }
            
    }
}
