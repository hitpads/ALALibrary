package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class GetBooksFromDatabase extends Book {

    public  static void getAllBooks(Connection con)
    {
        String query="select * from book";

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                System.out.println("-------");
                System.out.println("Book Id : "+rs.getInt(1));
                System.out.println("Title : "+rs.getString(2));
                System.out.println("Author : "+rs.getString(3));
                System.out.println("Price : "+rs.getInt(4));
                System.out.println("Count : "+rs.getInt(5));
                System.out.println("-------");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}