import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Payroll
{
    private final Map<Integer, Employee> employees;
    
    public Payroll(List<Employee> employees)
    {
        this.employees = new HashMap<>();
        employees.forEach(employee -> this.employees.put(employee.getEmployeeId(), employee));
    }
    
    public boolean addNewEmployee(Employee employee)
    {
        this.employees.put(employee.getEmployeeId(), employee);
        return true;
    }
    
    public void deleteEmployee(int employeeID)
    {
        this.employees.remove(employeeID);
    }
    
    public double monthlyPayroll()
    {
        return employees.values()
                .stream()
                .map(Employee::monthlyPayment)
                .reduce(0.0, Double::sum);
    }
    
    public double monthlyPayrollBeforeTaxes()
    {
        return employees.values()
                .stream()
                .map(Employee::monthlyPaymentBeforeTaxes)
                .reduce(0.0, Double::sum);
    }
    
    public void increasePayroll(int employeeID, double percentage)
    {
        this.employees.get(employeeID).increaseSalaryBy(percentage);
    }
    
    public int extraHours()
    {
        return employees.values()
                .stream()
                .filter(employee -> employee instanceof HourlyEmployee)
                .map(employee -> ((HourlyEmployee) employee).getWorkedHours())
                .reduce(0, (acc, hoursWorked) -> hoursWorked >= 40 ? acc + 1 : acc);
    }
}