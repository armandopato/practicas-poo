package practica6;

/**
 * Representa un empleado con pago por hora
 *
 * @author Armando Ugalde Velasco
 * @version 1.0
 */
public class HourlyEmployee extends Employee
{
    /**
     * Porcentaje de salario a pagarle al empleado después de impuestos, dividido entre 100.
     */
    private final static double NORMAL_PERCENTAGE = 0.9;
    /**
     * Número de horas trabajadas por este empleado
     */
    private final int workedHours;
    /**
     * Pago por hora de este empleado
     */
    private double hourFee;
    
    /**
     * Único constructor. Utiliza el constructor de Employee.
     * @param fullName Nombre completo del empleado
     * @param fecha Fecha de nacimiento del empleado
     * @param workedHours Número de horas de trabajo del empleado
     * @param hourFee Pago por hora del empleado
     * @throws Exception Si el primer apellido no contiene vocales internas.
     */
    public HourlyEmployee(String fullName, String fecha, String workedHours, String hourFee) throws Exception
    {
        super(fullName, fecha);
        this.workedHours = Integer.parseInt(workedHours);
        this.hourFee = Double.parseDouble(hourFee);
    }
    
    /**
     * Obtiene el pago mensual de este empleado, después de impuestos.
     * @return El pago mensual de este empleado después de impuestos
     */
    @Override
    public double monthlyPayment()
    {
        return monthlyPaymentBeforeTaxes() * NORMAL_PERCENTAGE;
    }
    
    /**
     * Obtiene el pago mensual de este empleado, antes de impuestos.
     * @return El pago mensual de este empleado antes de impuestos
     */
    @Override
    public double monthlyPaymentBeforeTaxes()
    {
        return hourFee * workedHours;
    }
    
    /**
     * Aumenta el salario de este empleado por el porcentaje proporcionado
     *
     * @param percentage El porcentaje por el que se aumentará el salario
     */
    @Override
    public void increaseSalaryBy(double percentage)
    {
        this.hourFee *= 1 + (percentage / 100);
    }
    
    /**
     * Obtiene el número de horas trabajadas por este empleado
     * @return El número de horas trabajadas por este empleado
     */
    public int getWorkedHours()
    {
        return workedHours;
    }
    
    /**
     * Obtiene la cuota por hora de este empleado
     * @return La cuota por hora de este empleado
     */
    public double getHourFee()
    {
        return hourFee;
    }
}