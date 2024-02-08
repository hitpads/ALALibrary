package records;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;


public class ReturningBook extends Records {

    public static void returnBook(Connection con)
    {
        Records record=new Records();
        int resultId = 0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Student Id :");
        int studentId = sc.nextInt();
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
            record.setSubmitDate();
            String query3="update record set submit_date=? where record_id=?";
            PreparedStatement ps=con.prepareStatement(query3);
            ps.setString(1,record.getSubmitDate().toString());
            ps.setInt(2, resultId);
            int count=ps.executeUpdate();
            if(count>0)
                System.out.println("Processing date......");
            else
                System.out.println("Unable to Process date....!");

        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error in date...!");
        }


        try {
            String query2="update record  set fine=datediff(submit_date,due_date)*5 where record_id=?";
            PreparedStatement ps=con.prepareStatement(query2);
            ps.setInt(1, resultId);
            int count=ps.executeUpdate();
            if(count>0)
                System.out.println("Processing......");
            else
                System.out.println("Unable to Process....!");

        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error...!");
        }
        int fine=0;
        try {

            String query3="select fine from record where record_id=?";
            PreparedStatement ps=con.prepareStatement(query3);
            ps.setInt(1, resultId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                fine=rs.getInt(1);
            }
            if(fine>0)
            {
                System.out.println("Fine Amount : "+fine);
            }
            else
                System.out.println("No Dues...");
        }catch (Exception e) {
            // TODO: handle exception
        }


    }


}