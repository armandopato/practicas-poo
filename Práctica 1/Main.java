import java.util.Scanner;

class Main
{
	public static void main(String[] args)
	{
		double sum = 0;
		
		Scanner stdin = new Scanner(System.in);
		while (stdin.hasNextDouble())
		{
			sum += stdin.nextDouble();
		}
		stdin.close();
		
		long truncatedSum = (long) sum;
		
		if (sum == truncatedSum) System.out.println(truncatedSum);
		else System.out.println(sum);
	}
}
