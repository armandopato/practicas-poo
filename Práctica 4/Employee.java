import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public abstract class Employee
{
    private static final Set<String> NOMBRES_PROHIBIDOS;
    private static final Set<String> APELLIDOS_PROHIBIDOS;
    private static final Set<Character> VOCALES;
    private static int currentId = 1;
    
    static
    {
        APELLIDOS_PROHIBIDOS = Set.of("DA", "DAS", "DE", "DEL", "DER", "DI", "DIE", "DD", "EL", "LA", "LOS", "LAS", "LE", "LES", "MAC", "MC", "VAN", "VON", "Y");
        NOMBRES_PROHIBIDOS = new HashSet<>();
        NOMBRES_PROHIBIDOS.addAll(APELLIDOS_PROHIBIDOS);
        NOMBRES_PROHIBIDOS.addAll(Set.of("MARIA", "MA.", "MA", "JOSE", "J", "J."));
        VOCALES = Set.of('A', 'E', 'I', 'O', 'U');
    }
    
    private final LocalDate birthLocalDate;
    
    private final int employeeId;
    private final String employeeName;
    private final String dateOfBirth;
    private final String RFC;
    private final int age;
    
    public Employee(String fullName, String fecha) throws Exception
    {
        this.employeeId = currentId++;
        this.employeeName = fullName;
        this.dateOfBirth = fecha;
        this.birthLocalDate = LocalDate.parse(this.dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.age = LocalDate.now().getYear() - this.birthLocalDate.getYear();
        this.RFC = this.calcularRFC();
    }
    
    public int getEmployeeId()
    {
        return employeeId;
    }
    
    public String getEmployeeName()
    {
        return employeeName;
    }
    
    
    private char findFirstInternalVowel(String str) throws Exception
    {
        char[] internalStr = str.substring(1, str.length() - 1).toCharArray();
        for (char c : internalStr)
        {
            if (Employee.VOCALES.contains(c)) return c;
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
        String[] names = this.employeeName.split(" ");
        String cleanName = this.getCleanName(names[0], NOMBRES_PROHIBIDOS);
        String cleanFirstLastName = this.getCleanName(names[1], APELLIDOS_PROHIBIDOS);
        String cleanSecondLastName = this.getCleanName(names[2], APELLIDOS_PROHIBIDOS);
        String dateInfo = this.birthLocalDate.format(DateTimeFormatter.ofPattern("yyMMdd"));
        
        return cleanFirstLastName.substring(0, 1)
                + this.findFirstInternalVowel(cleanFirstLastName)
                + cleanSecondLastName.charAt(0)
                + cleanName.charAt(0)
                + dateInfo;
    }
    
    abstract public double monthlyPayment();
    
    abstract public double monthlyPaymentBeforeTaxes();
    
    abstract public void increaseSalaryBy(double percentage);
}