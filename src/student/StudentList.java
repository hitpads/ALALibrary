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
//                student.setStudentBirthDate(rs.getDate(7).toLocalDate());
                System.out.println(student.toString());
                System.out.println("--------");
            }
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Unable to Fetch Information....!");
            e.printStackTrace();
        }
    }

}