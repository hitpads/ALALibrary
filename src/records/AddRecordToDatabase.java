package records;

import book.Availability;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class AddRecordToDatabase extends Records {

    public static void addRecord(Connection con)
    {
        Records recordObj=new Records();
        @SuppressWarnings("resource")
        Scanner sc=new Scanner(System.in);

        System.out.println("Book ID: ");
        recordObj.setBookId(sc.nextInt());

        if(Availability.isAvailable(con, recordObj.getBookId()))
        {

            System.out.println("Enter Student ID:");
            recordObj.setStudentId(sc.nextInt());

            recordObj.setIssueDate();

            recordObj.setDueDate(recordObj.getIssueDate().plusDays(10));

            System.out.println("Due "+recordObj.getDueDate());

            try {
                String query="insert into record values(?,?,?,?)";
                PreparedStatement ps=con.prepareStatement(query);
                ps.setInt(1, recordObj.getStudentId());
                ps.setInt(2, recordObj.getBookId());
                ps.setString(3, recordObj.getIssueDate().toString());
                ps.setString(4, recordObj.getDueDate().toString());
                int count=ps.executeUpdate();
                if(count>0)
                    System.out.println("Book Issued \n Due Date : "+recordObj.getDueDate());
                else
                    System.out.println("Not avalilable.");


            }catch (Exception e) {
                // TODO: handle exception
                System.out.println("Unable to process!");
                System.out.println(e.getMessage());
            }
        }
        else
            System.out.println("Sorry, book is not available.");
    }

}