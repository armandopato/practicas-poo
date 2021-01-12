import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        Matrix matrixA = Matrix.parseMatrix(scanner);
        Matrix matrixB = Matrix.parseMatrix(scanner);
        
        Matrix result = matrixA.sum(matrixB);
        System.out.println(result);
    }
}
