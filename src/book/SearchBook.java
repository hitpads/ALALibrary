package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchBook {

    public static void searchBookDetails(Connection con)
    {
        String query="select * from book where book_name=?";
        @SuppressWarnings("resource")
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Book name:");
        String name=sc.nextLine();
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, name);
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
        }catch (Exception e) {
            System.out.println("Error while searching or no such book found!");
        }

    }
}