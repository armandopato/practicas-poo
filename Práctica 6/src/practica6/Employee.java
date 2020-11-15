package practica6;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Representa un empleado
 *
 * @author Armando Ugalde Velasco
 * @version 1.0
 */
public abstract class Employee
{
    /**
     * Nombres no permitidos en el cálculo del RFC.
     */
    private static final Set<String> NOMBRES_PROHIBIDOS;
    /**
     * Apellidos no permitidos en el cálculo del RFC.
     */
    private static final Set<String> APELLIDOS_PROHIBIDOS;
    /**
     * Conjunto de vocales
     */
    private static final Set<Character> VOCALES;
    /**
     * Id por asignar al siguiente empleado.
     */
    private static int currentId = 1;
    
    static
    {
        APELLIDOS_PROHIBIDOS = Set.of("DA", "DAS", "DE", "DEL", "DER", "DI", "DIE", "DD", "EL", "LA", "LOS", "LAS", "LE", "LES", "MAC", "MC", "VAN", "VON", "Y");
        NOMBRES_PROHIBIDOS = new HashSet<>();
        NOMBRES_PROHIBIDOS.addAll(APELLIDOS_PROHIBIDOS);
        NOMBRES_PROHIBIDOS.addAll(Set.of("MARIA", "MA.", "MA", "JOSE", "J", "J."));
        VOCALES = Set.of('A', 'E', 'I', 'O', 'U');
    }
    
    /**
     * Fecha de nacimiento
     */
    private final LocalDate birthLocalDate;
    /**
     * Id de este empleado
     */
    private final int employeeId;
    /**
     * Nombre completo
     */
    private final String employeeName;
    /**
     * Fecha de nacimiento
     */
    private final String dateOfBirth;
    /**
     * RFC obtenido
     */
    private final String RFC;
    /**
     * Edad
     */
    private final int age;
    
    /**
     * Único constructor (para uso de los constructores
     * de las subclases).
     * Calcula el RFC y edad del empleado creado
     * e inicializa las propiedades respectivas.
     * @param fullName Nombre completo del empleado.
     * @param fecha Fecha de nacimiento del empleado.
     * @throws Exception Si el primer apellido no tiene vocales internas.
     */
    public Employee(String fullName, String fecha) throws Exception
    {
        this.employeeId = currentId++;
        this.employeeName = fullName;
        this.dateOfBirth = fecha;
        this.birthLocalDate = LocalDate.parse(this.dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.age = LocalDate.now().getYear() - this.birthLocalDate.getYear();
        this.RFC = this.calcularRFC();
    }
    
    /**
     * Obtiene el id de este empleado.
     * @return El id de este empleado.
     */
    public int getEmployeeId()
    {
        return employeeId;
    }
    
    /**
     * Obtiene el nombre de este empleado.
     * @return El nombre completo de este empleado.
     */
    public String getEmployeeName()
    {
        return employeeName;
    }
    
    /**
     * Encuentra la primera vocal interna en una cadena.
     * @param str La cadena a analizar.
     * @return La primera vocal interna en <code>str</code>.
     * @throws Exception Si <code>str</code> no contiene ninguna vocal
     *                   interna.
     */
    private char findFirstInternalVowel(String str) throws Exception
    {
        char[] internalStr = str.substring(1, str.length() - 1).toCharArray();
        for (char c : internalStr)
        {
            if (Employee.VOCALES.contains(c)) return c;
        }
        throw new Exception("La cadena no contiene ninguna vocal interna");
    }
    
    /**
     * Elimina las entradas prohibidas de <code>name</code> presentes en el conjunto
     * <code>forbiddenNames</code>. También normaliza <code>name</code> quitando
     * los acentos presentes. Si la entrada está compuesta únicamente de excepciones,
     * se retorna la primer palabra.
     *
     * @param name           El nombre a limpiar
     * @param forbiddenNames El conjunto de palabras prohibidas o excepciones
     * @return La cadena libre de excepciones
     */
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
    
    /**
     * Obtiene el RFC de este empleado, a partir de sus
     * atributos.
     *
     * @return El RFC de este empleado
     * @throws Exception Si el primer apellido no contiene ninguna vocal interna.
     */
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
    
    /**
     * Obtiene el pago mensual de este empleado, después de impuestos.
     * @return El pago mensual de este empleado después de impuestos
     */
    abstract public double monthlyPayment();
    
    /**
     * Obtiene el pago mensual de este empleado, antes de impuestos.
     * @return El pago mensual de este empleado antes de impuestos
     */
    abstract public double monthlyPaymentBeforeTaxes();
    
    /**
     * Aumenta el salario de este empleado por el porcentaje proporcionado
     *
     * @param percentage El porcentaje por el que se aumentará el salario
     */
    abstract public void increaseSalaryBy(double percentage);
}