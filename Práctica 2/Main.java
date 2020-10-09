import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		String aPaterno = sc.nextLine();
		String aMaterno = sc.nextLine();
		String nacimiento = sc.nextLine();
		Persona persona = new Persona(nombre, aPaterno, aMaterno, nacimiento);
		System.out.println(persona.nombreCompleto());
		System.out.println(persona.calcularEdad());
		System.out.println(persona.calcularRFC());
	}
}
