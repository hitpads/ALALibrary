package StudentMenu;

import schemas.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserList extends User{

    public static void getAllStudent(Connection con)
    {
        String query="select * from users WHERE user_type='User'";
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                User user = new User();
                user.setName(rs.getString(1));
                user.setSurname(rs.getString(2));
                user.setAge(rs.getInt(3));
                user.setUsername(rs.getString(4));
                System.out.println(user.getStudentListingFormat());
                System.out.println("--------");
            }
        }catch (Exception e) {
            System.out.println("Unable to Fetch List!");
            e.printStackTrace();
        }
    }
}