import practica6.Employee;
import practica6.HourlyEmployee;
import practica6.Payroll;
import practica6.SalaryEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase driver
 *
 * @author Armando Ugalde Velasco
 * @version 1.0
 */
public class Main
{
    /**
     * Trunca <code>value</code> al entero menor o igual más cercano y lo imprime
     *
     * @param value El valor a truncar
     */
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
    
    /**
     * Método driver
     */
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        while (sc.hasNextLine())
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
