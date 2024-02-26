package student;

import schemas.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchStudentByName extends User {

    public static void searchStudent(Connection con)
    {
        String query = "SELECT * FROM users WHERE name LIKE ?\n" +
                "AND user_type='User'";

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter student's name: ");
        String name=sc.nextLine();
        String[] names=name.split(" ");
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, "%" + names[0] + "%");

            ResultSet rs=ps.executeQuery();
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
        }
        catch (Exception e) {
            // TODO: handle exception
            System.out.println("Unable to get record!");
            System.out.println(e.getMessage());
        }
    }

}