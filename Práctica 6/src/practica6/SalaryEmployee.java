package practica6;

/**
 * Representa un empleado con pago por mes.
 * @author Armando Ugalde Velasco
 * @version 1.0
 */
public class SalaryEmployee extends Employee
{
    /**
     * Salario máximo para el que se aplicará el porcentaje de impuestos
     * <code>NORMAL_PERCENTAGE</code>. Si el salario excede esta cantidad, se le
     * aplicará el porcentaje de impuestos <code>EXCEEDED_PERCENTAGE</code>.
     */
    private static final double MAX_SALARY = 10000;
    /**
     * Porcentaje final del salario, después de los impuestos
     * a aplicar si el empleado tiene un salario mayor a <code>MAX_SALARY</code>,
     * dividido entre 100.
     */
    private static final double EXCEEDED_PERCENTAGE = 0.7;
    /**
     * Porcentaje final del salario, después de los impuestos
     * a aplicar si el empleado tiene un salario menor o igual a <code>MAX_SALARY</code>,
     * dividido entre 100.
     */
    private static final double NORMAL_PERCENTAGE = 0.8;
    /**
     * Salario mensual de este empleado.
     */
    private double salary;
    /**
     * Único constructor. Utiliza el constructor de Employee.
     * @param fullName Nombre completo del empleado
     * @param fecha Fecha de nacimiento del empleado
     * @param salary Salario mensual del empleado
     * @throws Exception Si el primer apellido no contiene vocales internas.
     */
    public SalaryEmployee(String fullName, String fecha, String salary) throws Exception
    {
        super(fullName, fecha);
        this.salary = Double.parseDouble(salary);
    }
    
    /**
     * Obtiene el salario de este empleado.
     * @return El salario de este empleado.
     */
    public double getSalary()
    {
        return salary;
    }
    
    /**
     * Obtiene el pago mensual de este empleado, después de impuestos.
     * @return El pago mensual de este empleado después de impuestos
     */
    @Override
    public double monthlyPayment()
    {
        return salary > MAX_SALARY ? salary * EXCEEDED_PERCENTAGE : salary * NORMAL_PERCENTAGE;
    }
    
    /**
     * Obtiene el pago mensual de este empleado, antes de impuestos.
     * @return El pago mensual de este empleado antes de impuestos
     */
    @Override
    public double monthlyPaymentBeforeTaxes()
    {
        return this.salary;
    }
    
    /**
     * Aumenta el salario de este empleado por el porcentaje proporcionado.
     *
     * @param percentage El porcentaje por el que se aumentará el salario.
     */
    @Override
    public void increaseSalaryBy(double percentage)
    {
        this.salary *= 1 + (percentage / 100);
    }
}