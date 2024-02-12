package records;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Fine {

    public static void payFine(Connection con)
    {
        //	Records record=new Records();
        int fine=0,resultId=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Student Id :");
        int studentId=sc.nextInt();
        System.out.println("Enter Book Id :");
        int bookId=sc.nextInt();
        try {
            String query1="select record_id from record where student_id=? and book_id=?";
            PreparedStatement ps=con.prepareStatement(query1);
            ps.setInt(1, studentId);
            ps.setInt(2, bookId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                resultId=rs.getInt(1);
            }
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Unable to find record....!");
        }

        try {
            String query2="select fine from record where record_id=?";
            PreparedStatement ps=con.prepareStatement(query2);
            ps.setInt(1, resultId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                fine=rs.getInt(1);
            }
            System.out.println("Fine Amount : "+fine);
            System.out.println("1.Paid \n 2.Unpaid");
            int choice=sc.nextInt();
            String status=null;
            if(choice==1)
                status="Paid";
            else
                status="Unpaid";

            String query3="update record set fine_status=? where record_id=?";
            PreparedStatement ps1=con.prepareStatement(query3);
            ps1.setInt(2, resultId);
            ps1.setString(1, status);
            int count=ps1.executeUpdate();
            if(count>0)
                System.out.println("Updated..");
            else
                System.out.println("Unable to update...!");
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error....!");
        }
    }
}