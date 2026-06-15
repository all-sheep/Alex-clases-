    public class pizzeria {
        public static void main(String[] args) {
            System.out.println("Bienvenido a Pizza Bella Napoli");
            System.out.println("¿Qué tipo de pizza deseas?");
            System.out.println("1. vegetariana");
            System.out.println("2. no vegetariana");
            
            int opcion = 1; 
            switch (opcion) {
                case 1:
                    System.out.println("\nEscoge un ingrediente para tu pizza vegetariana:");
                    System.out.println("a. Pimiento");
                    System.out.println("b. Tofu");
                    String ingrediente = "a";
                    switch (ingrediente) {
                        case "a":
                            System.out.println("Has elegido una pizza vegetariana con pimiento");
                            break;
                        case "b":
                            System.out.println("Has elegido una pizza vegetariana con tofu");
                            break;
                        default:
                            System.out.println("Opción de ingrediente no válida");
                        }
                    break;
                case 2:
                    System.out.println("\nEscoge un ingrediente para tu pizza no vegetariana:");
                    System.out.println("a. Pepperoni");
                    System.out.println("b. Jamón");
                    System.out.println("c. Salmón");
                    String ingredienteNoVeg = "b";
                    switch (ingredienteNoVeg) {
                        case "a":
                            System.out.println("Has elegido una pizza no vegetariana con pepperoni");
                            break;
                        case "b":
                            System.out.println("Has elegido una pizza no vegetariana con jamón");
                            break;
                        case "c":
                            System.out.println("Has elegido una pizza no vegetariana con salmón");
                            break;
                        default:
                            System.out.println("Opción de ingrediente no válida");
                    }
                    break;
                default:
                    System.out.println("Opción no válida");
                }
            }
        }
