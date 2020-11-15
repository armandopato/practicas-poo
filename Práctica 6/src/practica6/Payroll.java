package practica6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representa la nómina de sueldos
 *
 * @author Armando Ugalde Velasco
 * @version 1.0
 */
public class Payroll
{
    /**
     * Conjunto de empleados
     */
    private final Map<Integer, Employee> employees;
    
    /**
     * Único constructor. Inicializa el conjunto de empleados a partir de la lista <code>employees</code>
     *
     * @param employees Lista de empleados a considerar.
     */
    public Payroll(List<Employee> employees)
    {
        this.employees = new HashMap<>();
        employees.forEach(employee -> this.employees.put(employee.getEmployeeId(), employee));
    }
    
    /**
     * Añade un empleado al conjunto de esta nómina
     *
     * @param employee El empleado a agregar
     * @return <code>true</code> si se logró agregar el empleado
     */
    public boolean addNewEmployee(Employee employee)
    {
        this.employees.put(employee.getEmployeeId(), employee);
        return true;
    }
    
    /**
     * Elimina el empleado con el <code>employeeID</code> proporcionado
     *
     * @param employeeID Id del empleado a eliminar
     */
    public void deleteEmployee(int employeeID)
    {
        this.employees.remove(employeeID);
    }
    
    /**
     * Obtiene el pago mensual de este empleado, después de impuestos.
     * @return El pago mensual de este empleado después de impuestos
     */
    public double monthlyPayroll()
    {
        return employees.values()
                .stream()
                .map(Employee::monthlyPayment)
                .reduce(0.0, Double::sum);
    }
    
    /**
     * Obtiene el pago mensual de este empleado, antes de impuestos.
     * @return El pago mensual de este empleado antes de impuestos
     */
    public double monthlyPayrollBeforeTaxes()
    {
        return employees.values()
                .stream()
                .map(Employee::monthlyPaymentBeforeTaxes)
                .reduce(0.0, Double::sum);
    }
    
    /**
     * Aumenta el salario del empleado con el <code>employeeID</code> proporcionado, por el porcentaje <code>percentage</code> dado.
     * @param employeeID El id del empleado del cual se desea aumentar el salario
     * @param percentage El porcentaje por el que se aumentará el salario
     */
    public void increasePayroll(int employeeID, double percentage)
    {
        this.employees.get(employeeID).increaseSalaryBy(percentage);
    }
    
    /**
     * Obtiene el número de empleados que trabajan horas extra.
     * @return El número de empleados que trabajan horas extra, es decir,
     * aquellos que trabajan 40 horas o más. Nótese que ésta condición únicamente
     * se cumplirá para empleados con nómina por hora.
     */
    public int extraHours()
    {
        return employees.values()
                .stream()
                .filter(employee -> employee instanceof HourlyEmployee)
                .map(employee -> ((HourlyEmployee) employee).getWorkedHours())
                .reduce(0, (acc, hoursWorked) -> hoursWorked >= 40 ? acc + 1 : acc);
    }
}