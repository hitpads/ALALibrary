package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddBookToDatabase extends Book {

    public static void addBook(Connection con)
    {
        Book book = new Book();

        @SuppressWarnings("resource")
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Book Title: ");
        book.setBooktitle(sc.nextLine());

        System.out.println("Author name: ");
        book.setBookAuthor(sc.nextLine());

        System.out.println("Price: ");
        book.setBookPrice(sc.nextInt());

        System.out.println("Copies: ");
        int cnt=sc.nextInt();
        book.setBookCount(cnt);

        try {
            String str="insert into book values(default,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(str);
            ps.setString(1, book.getBooktitle());
            ps.setString(2, book.getBookAuthor());
            ps.setInt(3, book.getBookPrice());
            ps.setInt(4, book.getBookCount());
            int count=ps.executeUpdate();
            if(count>0)
            {
                System.out.println("Book Added Successfully!");

            }
            else
                System.out.println("Unable to Process.");


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}