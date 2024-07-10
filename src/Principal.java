import java.util.Scanner;

public class Principal {
    private static final String API_KEY = System.getenv("API_KEY");

    public static void main(String[] args) {
        MonedaCambio rates = new MonedaCambio(API_KEY);
        ConvertidorMonedas converter = new ConvertidorMonedas(rates);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n**************************************************************************");
            System.out.println("¡Bienvenido al Convertidor de Monedas!");
            System.out.println("Seleccione una opción:");
            System.out.println("1) Dólar estadounidense a Peso chileno\n");
            System.out.println("2) Peso chileno a Dólar estadounidense\n");
            System.out.println("3) Peso colombiano a Peso mexicano\n");
            System.out.println("4) Peso mexicano a Peso colombiano\n");
            System.out.println("5) Peso argentino a Real brasileño\n");
            System.out.println("6) Real brasileño a Peso argentino\n");
            System.out.println("7) Salir");
            System.out.print("Opción: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                if (option == 7) {
                    running = false;
                } else if (option < 1 || option > 6) {
                    System.out.println("Opción inválida. Intente de nuevo.");
                } else {
                    System.out.print("Ingrese el valor a convertir: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    double result = 0;

                    switch (option) {
                        case 1:
                            result = converter.convert(amount, "USD", "CLP");
                            System.out.println(amount + " USD = " + result + " CLP");
                            break;
                        case 2:
                            result = converter.convert(amount, "CLP", "USD");
                            System.out.println(amount + " CLP = " + result + " USD");
                            break;
                        case 3:
                            result = converter.convert(amount, "COP", "MXN");
                            System.out.println(amount + " COP = " + result + " MXN");
                            break;
                        case 4:
                            result = converter.convert(amount, "MXN", "COP");
                            System.out.println(amount + " MXN = " + result + " COP");
                            break;
                        case 5:
                            result = converter.convert(amount, "ARS", "BRL");
                            System.out.println(amount + " ARS = " + result + " BRL");
                            break;
                        case 6:
                            result = converter.convert(amount, "BRL", "ARS");
                            System.out.println(amount + " BRL = " + result + " ARS");
                            break;
                        default:
                            System.out.println("Opción inválida. Intente de nuevo.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor incorrecto. Intente de nuevo.");
            }
        }
        System.out.println("¡Hasta Pronto!");
        scanner.close();
    }
}
