package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public Connection con=null;

    public Connection getConnection(String dbname, String user, String pass)
    {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (con != null) {
                System.out.println("Connected to the Database!");
            }
            else {
                System.out.println("Connection Failed!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }


    public void closeConnection()
    {
        try {
            this.con.close();
            System.out.println("Connection Closed!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Unable to Close Connection!" + e.getMessage());
        }
    }
}