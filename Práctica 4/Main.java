import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static void printFormattedValue(double value)
    {
        long truncated = (long) value;
        if (value == truncated)
        {
            System.out.println(truncated);
        }
        else
        {
            System.out.println(value);
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        while (sc.hasNext())
        {
            String[] data = sc.nextLine().split(",");
            Employee newEmployee;
            if (data[2].equals("S"))
            {
                newEmployee = new SalaryEmployee(data[0], data[1], data[3]);
            }
            else
            {
                newEmployee = new HourlyEmployee(data[0], data[1], data[3], data[4]);
            }
            employees.add(newEmployee);
        }
        Payroll payroll = new Payroll(employees);
        printFormattedValue(payroll.monthlyPayrollBeforeTaxes());
        printFormattedValue(payroll.monthlyPayroll());
        printFormattedValue(payroll.extraHours());
        payroll.increasePayroll(4, 5);
        payroll.increasePayroll(6, 3);
        printFormattedValue(payroll.monthlyPayrollBeforeTaxes());
        printFormattedValue(payroll.monthlyPayroll());
    }
}
