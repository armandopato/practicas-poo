public class SalaryEmployee extends Employee
{
    private static final double MAX_SALARY = 10000;
    private static final double EXCEEDED_PERCENTAGE = 0.7;
    private static final double NORMAL_PERCENTAGE = 0.8;
    private double salary;
    
    public SalaryEmployee(String fullName, String fecha, String salary) throws Exception
    {
        super(fullName, fecha);
        this.salary = Double.parseDouble(salary);
    }
    
    public double getSalary()
    {
        return salary;
    }
    
    @Override
    public double monthlyPayment()
    {
        return salary > MAX_SALARY ? salary * EXCEEDED_PERCENTAGE : salary * NORMAL_PERCENTAGE;
    }
    
    @Override
    public double monthlyPaymentBeforeTaxes()
    {
        return this.salary;
    }
    
    @Override
    public void increaseSalaryBy(double percentage)
    {
        this.salary *= 1 + (percentage / 100);
    }
}