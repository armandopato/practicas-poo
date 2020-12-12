import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        // La entrada es un entero válido, por lo tanto, se convierte a hexadecimal.
        if (scanner.hasNextInt())
        {
            try
            {
                System.out.println(Conversiones.decToHex(scanner.nextInt()));
            }
            catch (NegativeValueEnteredException error)
            {
                System.out.println("LOS NUMEROS NEGATIVOS NO SON ACEPTADOS");
            }
            return;
        }
        
        String numberString = scanner.nextLine();
        String numberPrefixString = numberString.substring(0, 2);
        
        // La entrada es un número hexadecimal, por lo tanto, se intenta convertir a decimal
        if (numberPrefixString.equals("0X"))
        {
            try
            {
                System.out.println(Conversiones.hexToDec(numberString.substring(2)));
            }
            catch (InvalidHexException error)
            {
                System.out.println("EL FORMATO NO CORRESPONDE A UN NUMERO HEXADECIMAL");
            }
            return;
        }
        
        // Se comprueba si los dos primeros dígitos son válidos.
        try
        {
            Integer.parseInt(numberPrefixString);
        }
        catch (NumberFormatException error)
        {
            System.out.println("EL FORMATO NO CORRESPONDE A UN NUMERO");
            return;
        }
        
        // Los dos primeros dígitos indican un número decimal, por lo tanto, se intenta convertir a hexadecimal.
        try
        {
            System.out.println(Conversiones.decToHex(Integer.parseInt(numberString)));
        }
        catch (NumberFormatException error)
        {
            System.out.println("EL FORMATO NO CORRESPONDE A UN NUMERO DECIMAL");
        }
        catch (NegativeValueEnteredException error)
        {
            System.out.println("LOS NUMEROS NEGATIVOS NO SON ACEPTADOS");
        }
        
    }
}
