// ******************************************************
//Numbers.java
//
// ******************************************************

import java.util.Scanner;

public class Numbers
{
    // --------------------------------------------
// Reads in an array of integers, sorts them,
// then prints them in sorted order.
// --------------------------------------------
    public static void main(String[] args)
    {
        Integer[] intList;
        int size;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("\n¿Cúantos números enteros quieres ordenar? ");
        size = scan.nextInt();
        intList = new Integer[size];
        
        System.out.println("\nDame los números...");
	    for (int i = 0; i < size; i++)
	    {
		    intList[i] = scan.nextInt();
	    }
        Sorting.insertionSort(intList);
        System.out.println("\nLos números ordenados son:");
	    for (int i = 0; i < size; i++)
	    {
		    System.out.print(intList[i] + " ");
	    }
        System.out.println();
    }
}