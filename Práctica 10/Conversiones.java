public class Conversiones
{
    public static int hexToDec(String numberString) throws InvalidHexException, NegativeValueEnteredException
    {
        int convertedInt;
        try
        {
            convertedInt = Integer.parseInt(numberString, 16);
        }
        catch (NumberFormatException error)
        {
            throw new InvalidHexException();
        }
        
        if (convertedInt < 0) throw new NegativeValueEnteredException();
        return convertedInt;
    }
    
    public static String decToHex(int number) throws NegativeValueEnteredException
    {
        if (number < 0) throw new NegativeValueEnteredException();
        return "0X" + Integer.toHexString(number).toUpperCase();
    }
}
