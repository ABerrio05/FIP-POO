import java.util.*;

public class Normalizar {

    public static Long normalizarLong (Scanner num){
        while (true){   
            String entrada = num.nextLine().trim();
            if (entrada.matches("-?\\d+")){
                long numeroL = Long.parseLong(entrada);
                if(numeroL>=0) {
                    return numeroL;
                } else{
                    System.out.println("***** error, intente de nuevo con un numero positivo *****");
                }

            } else {
                System.out.println("***** entrada no valida *****");
                System.out.println("** intentelo de nuevo **");
            }
        }

    }

    public static Integer normalizarInt (Scanner num){
        while (true){
            String entrada = num.nextLine().trim();
            if (entrada.matches("-?\\d+")){
                int numeroInt = Integer.parseInt(entrada);
                if (numeroInt>=0){
                    return numeroInt;
                }else{
                    System.out.println(" ***** error, ingrese un numero positivo ***** ");
                }
            }else{
                System.out.println("***** error, entrada no valida, ingrese un numero entero ***** ");
                System.out.println(" ** intente de nuevo ** ");
            }

        }
    }

    public static Double normalizarDouble (Scanner num){
        while (true){
            String entrada = num.nextLine().trim().replace(",",".");
            if (entrada.matches("-?\\d+(\\.\\d+)?")){
                double numero= Double.parseDouble(entrada);
                if (numero>=0){
                    return numero;
                }else{
                    System.out.println(" ****** error, el numero debe ser positivo ****** ");
                    System.out.println(" ** intente de nuevo ** ");
                }
            }else {
                System.out.println(" ***** entrada no valida, por favor ingrese un numero decimal positivo ***** ");
                System.out.println("** intente de nuevo **");

            }
        }
    }

}
