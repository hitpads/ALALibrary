package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class AddStudentToDatabase extends Student{

    public static void addStudent(Connection con)
    {
        Student student=new Student();


        Scanner sc=new Scanner(System.in);

        student.setStudentFname();
        student.setStudentLname();
        student.setstudentMajor();

        System.out.println("Enter Students Age:");
        student.setStudentAge(sc.nextInt());


        try {
            String sql="insert into student(student_fname,student_lname,student_age,student_major) values(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, student.getStudentFname());
            ps.setString(2, student.getStudentLname());
            ps.setInt(3, student.getStudentAge());
            ps.setString(4, student.getstudentMajor());
            int count=ps.executeUpdate();
            if(count!=0)
            {
                System.out.println("Student Added!");
            }
            else
            {
                System.out.println("Error!");
            }
        }catch (Exception e) {
            System.out.println("Error!");
            e.getMessage();
        }

    }
}