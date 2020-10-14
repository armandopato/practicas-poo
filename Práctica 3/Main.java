import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		List<Persona> personas = new ArrayList<>();
		while (sc.hasNext())
		{
			String[] datos = sc.nextLine().split(",");
			Persona persona = new Persona(datos[0], datos[1], datos[2], datos[3]);
			personas.add(persona);
		}
		int menores = Persona.menoresA40(personas);
		System.out.println("MAYORES A 40: " + (personas.size() - menores));
		System.out.println("MENORES A 40: " + menores);
	}
}
