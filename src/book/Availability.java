package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Availability {

    public static boolean isAvailable(Connection con,int book_id)
    {
        String query="select book_copies from book where book_id=?";
        boolean flag=false;
        try {

            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, book_id);
            ResultSet rs=ps.executeQuery();
            int count=0;
            while(rs.next())
            {
                count=rs.getInt(1);
            }
            if(count>0)
            {
                return true;
            }
            else
                return false;
        }catch (Exception e) {
            // TODO: handle exception
            return false;

        }

    }

}