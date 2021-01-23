package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RunFinanceProgram
{
    
    //Replace url with the path to your project's directory
    //Mac OS /path/to/directory/test.db
    //Windows C:/path/to/directory/test.db
    final public static String DATABASE_URL = "C:\\Users\\armau\\OneDrive\\Documentos\\Escuela\\POO\\practicas-poo\\Práctica 13\\src\\app\\test.db";
    
    public static void main(String[] args)
    {
        
        Connection c = null;
        Statement stmt = null;
        
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_URL);
            
            
            stmt = c.createStatement();
            String sqlDropTable = "DROP TABLE FINANCES";
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS FINANCES " +
                    "(ID INT PRIMARY  KEY     NOT NULL," +
                    " INCOME          CHAR(50)    , " +
                    " EXPENSES        CHAR(50)    , " +
                    " INVESTMENTPERC  CHAR(10), " +
                    " CASH            CHAR(50))";
            String sqlInsert = "INSERT INTO FINANCES (ID,CASH,INCOME,EXPENSES,INVESTMENTPERC) " +
                    "VALUES (1, 0.0, 0.0, 0.0, 0.0 );";
            
            stmt.executeUpdate(sqlCreateTable);
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM FINANCES");
            if (!rs.next())
            {
                stmt.executeUpdate(sqlInsert);
            }
            
            //stmt.executeUpdate(sqlDropTable);
            stmt.close();
            
            c.close();
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        
        
        Model model = new Model();
        View view = new View("Finance App");
        Controller controller = new Controller(model, view);
        controller.initController();
    }
    
    
}
