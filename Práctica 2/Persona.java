import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class Persona
{
	private static final Set<String> NOMBRES_PROHIBIDOS;
	private static final Set<String> APELLIDOS_PROHIBIDOS;
	private static final Set<Character> VOCALES;
	
	static
	{
		APELLIDOS_PROHIBIDOS = Set.of("DA", "DAS", "DE", "DEL", "DER", "DI", "DIE", "DD", "EL", "LA", "LOS", "LAS", "LE", "LES", "MAC", "MC", "VAN", "VON", "Y");
		NOMBRES_PROHIBIDOS = new HashSet<>();
		NOMBRES_PROHIBIDOS.addAll(APELLIDOS_PROHIBIDOS);
		NOMBRES_PROHIBIDOS.addAll(Set.of("MARIA", "MA.", "MA", "JOSE", "J", "J."));
		VOCALES = Set.of('A', 'E', 'I', 'O', 'U');
	}
	
	private final String nombre;
	private final String aPaterno;
	private final String aMaterno;
	private final LocalDate nacimiento;
	
	public Persona(String nombre, String aPaterno, String materno, String fecha)
	{
		this.nombre = nombre;
		this.aPaterno = aPaterno;
		this.aMaterno = materno;
		this.nacimiento = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public String nombreCompleto()
	{
		return this.nombre + " " + this.aPaterno + " " + this.aMaterno;
	}
	
	public int calcularEdad()
	{
		return LocalDate.now().getYear() - this.nacimiento.getYear();
	}
	
	private char findFirstInternalVowel(String str) throws Exception
	{
		char[] internalStr = str.substring(1, str.length() - 1).toCharArray();
		for (char c : internalStr)
		{
			if (Persona.VOCALES.contains(c)) return c;
		}
		throw new Exception("La cadena no contiene ninguna vocal interna");
	}
	
	private String getCleanName(String name, Set<String> forbiddenNames)
	{
		// Quitar acentos y transformar a mayúsculas
		name = Normalizer.normalize(name.toUpperCase(), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
		
		String[] names = name.split(" ");
		if (names.length == 1) return names[0];
		
		String cleanName = Arrays.stream(names)
				.filter(Predicate.not(forbiddenNames::contains))
				.reduce("", (acc, part) -> acc + " " + part)
				.strip();
		
		if (cleanName.length() == 0) return names[0];
		else return cleanName;
	}
	
	public String calcularRFC() throws Exception
	{
		String cleanName = this.getCleanName(this.nombre, Persona.NOMBRES_PROHIBIDOS);
		String cleanFirstLastName = this.getCleanName(this.aPaterno, Persona.APELLIDOS_PROHIBIDOS);
		String cleanSecondLastName = this.getCleanName(this.aMaterno, Persona.APELLIDOS_PROHIBIDOS);
		String dateInfo = this.nacimiento.format(DateTimeFormatter.ofPattern("yyMMdd"));
		
		return cleanFirstLastName.substring(0, 1)
				+ this.findFirstInternalVowel(cleanFirstLastName)
				+ cleanSecondLastName.charAt(0)
				+ cleanName.charAt(0)
				+ dateInfo;
	}
}
