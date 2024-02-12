package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentList extends Student{

    public static void getAllStudent(Connection con)
    {
        String query="select * from student";
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                Student student=new Student();
                student.setStudentId(rs.getInt(1));
                student.setStudentFname(rs.getString(2));
                student.setStudentLname(rs.getString(3));
                student.setStudentClass(rs.getString(4));
                student.setStudentAge(rs.getInt(5));
                student.setStudentGender(rs.getString(6));
                System.out.println(student.toString());
                System.out.println("--------");
            }
        }catch (Exception e) {
            System.out.println("Unable to Fetch List!");
            e.getMessage();
        }
    }

}