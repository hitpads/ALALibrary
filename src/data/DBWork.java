package data;

import schemas.Admin;
import schemas.User;
import schemas.UserType;

import java.sql.*;
import java.util.Objects;

public class DBWork {
    private final Utils utils = new Utils();

    public static int getNextUserId() {
        try {
            Connection conn = new Utils().GetConnector();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT MAX(id) FROM users";
            ResultSet table = statement.executeQuery(query);
            table.beforeFirst();
            while (table.next()) {
                return table.getInt(1) + 1;
            }
        } catch (Exception e) {
            //...
        }
        return 0;
    }


    public void InsertUser(User user) throws SQLException {
        try {
            Connection conn = utils.GetConnector();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = "INSERT INTO users VALUES ('" +
                    user.getName() + "', '" + user.getSurname() + "', " + user.getAge() + ", '" +
                    user.getUsername() + "', '" + user.getUserType() + "', '" + user.getHashedPassword() + "')";
            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }
    User currUser = new User();

    public void issueBook(int bookId, int userId) {
        try (Connection connection = utils.GetConnector()) {
            String userQuery = "SELECT COUNT(*) FROM users WHERE id = ?";
            PreparedStatement userStatement = connection.prepareStatement(userQuery);
            userStatement.setInt(1, userId);
            ResultSet userResultSet = userStatement.executeQuery();
            userResultSet.next();
            int userCount = userResultSet.getInt(1);
            if (userCount == 0) {
                System.out.println("Error issuing book: User not found.");
                return;
            }

            String query = "INSERT INTO book_issues (book_id, user_id, issue_date) VALUES (?, ?, CURRENT_DATE)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookId);
            statement.setInt(2, userId);
            statement.executeUpdate();
            System.out.println("Book issued.");
        } catch (SQLException e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }


    public User getCurrUser(String username) {
        try {
            Connection conn = utils.GetConnector();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT * " +
                    "FROM users " +
                    "WHERE username = '" + username + "'";
            ResultSet table = statement.executeQuery(query);
            table.beforeFirst();

            if (table.next()) {
                User curUser = new User();
                curUser.setId(table.getInt("id"));
                curUser.setName(table.getString("name"));
                curUser.setSurname(table.getString("surname"));
                curUser.setAge(table.getInt("age"));
                curUser.setUsername(table.getString("username"));
                curUser.setHashedPassword(table.getString("hashed_password"));
                String userType = table.getString("user_type");
                if(userType.equals("Admin")){
                    curUser.setUserType(UserType.Admin);
                }else{
                    curUser.setUserType(UserType.User);
                }
                return curUser;
            }
        } catch (Exception e) {
            //...
        }
        return null;
    }


    public void updatePassword(User currUser, String newPass) {
        try {
            Connection conn = utils.GetConnector();
            String newHashPass = utils.getHashedPassword(newPass);
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String currUsername = currUser.getUsername();
            String query = "UPDATE users SET hashed_password = '" + newHashPass + "'" +
                    " WHERE username = '" + currUsername + "'";
            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }

    public void deleteUser(User deletedUser) {
        try {
            Connection conn = utils.GetConnector();
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            String currUsername = deletedUser.getUsername();
            String query = "DELETE FROM users" +
                    " WHERE username = '" + currUsername + "'";
            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }

    public DBWork() {
    }
}