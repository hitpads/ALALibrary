package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchStudentByName extends Student {

    public static void searchStudent(Connection con)
    {
        String query = "SELECT * FROM student WHERE student_fname=? AND student_lname=?";

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Student Name");
        String name=sc.nextLine();
        String[] names=name.split(" ");
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,names[0]);
            ps.setString(2, names[1]);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Student student=new Student();
                student.setStudentId(rs.getInt(1));
                student.setStudentFname(rs.getString(2));
                student.setStudentLname(rs.getString(3));
                student.setStudentAge(rs.getInt(4));
                student.setstudentMajor(rs.getString(5));
                System.out.println(student);
                System.out.println("--------");
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            System.out.println("Unable to get record!");
            System.out.println(e.getMessage());
        }
    }

}