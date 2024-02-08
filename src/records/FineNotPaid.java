package records;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FineNotPaid {

    public static void listOfUnpaidFine(Connection con)
    {
        String query="select concat(s.student_fname,\" \",s.student_lname),sum(r.fine) from record r inner join student s on s.student_id=r.student_id where r.fine_status is null group by s.student_id";
        try
        {
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                System.out.println("Name : "+rs.getString(1));
                System.out.println("Amount : "+rs.getInt(2));
            }
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Unable to get data.....");
        }
    }

}