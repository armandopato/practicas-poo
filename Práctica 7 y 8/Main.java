import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String flag = scanner.nextLine();
        List<Comparable> elements = new ArrayList<>();
        
        if (scanner.hasNextInt())
            while (scanner.hasNextInt()) elements.add(scanner.nextInt());
        else
            while (scanner.hasNextLine()) elements.add(scanner.nextLine());
        
        Comparable[] sorted = elements.toArray(new Comparable[0]);
        
        if (flag.equals("A")) Sorting.selectionSort(sorted);
        else Sorting.insertionSort(sorted);
        
        Arrays.stream(sorted).forEach(System.out::println);
    }
}
