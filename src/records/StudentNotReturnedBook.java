package records;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentNotReturnedBook {

    public static void listOfStudentNotReturnedBook(Connection con)
    {
        String query="select s.student_id,r.book_id,concat(student_fname,\" \",s.student_lname) from record r inner join student s using(student_id) where r.submit_date is null";

        try {
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                System.out.println("Student ID : "+rs.getInt(1));
                System.out.println("Book ID : "+rs.getInt(2));
                System.out.println("Name : "+rs.getString(3));
            }
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error ....!");
        }

    }
}