package book;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderBook {

    public static void orderABOok(Connection con, String name)
    {
        String query="insert into orders values(default,?)";

        try
        {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, name);
            int count=ps.executeUpdate();
            if(count>0)
                System.out.println("Order Placed");
            else
                System.out.println("Error");
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error....!");
        }
    }
}