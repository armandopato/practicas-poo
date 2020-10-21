public class HourlyEmployee extends Employee
{
    private final static double NORMAL_PERCENTAGE = 0.9;
    private final int workedHours;
    private double hourFee;
    
    public HourlyEmployee(String fullName, String fecha, String workedHours, String hourFee) throws Exception
    {
        super(fullName, fecha);
        this.workedHours = Integer.parseInt(workedHours);
        this.hourFee = Double.parseDouble(hourFee);
    }
    
    @Override
    public double monthlyPayment()
    {
        return monthlyPaymentBeforeTaxes() * NORMAL_PERCENTAGE;
    }
    
    @Override
    public double monthlyPaymentBeforeTaxes()
    {
        return hourFee * workedHours;
    }
    
    @Override
    public void increaseSalaryBy(double percentage)
    {
        this.hourFee *= 1 + (percentage / 100);
    }
    
    public int getWorkedHours()
    {
        return workedHours;
    }
    
    public double getHourFee()
    {
        return hourFee;
    }
}