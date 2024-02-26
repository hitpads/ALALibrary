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


    public void issueBook(int bookId, int userId) {
        try (Connection connection = utils.GetConnector()) {
            // user exist
            String userQuery = "SELECT COUNT(*) FROM users WHERE id = " + userId;
            Statement userStatement = connection.createStatement();
            ResultSet userResultSet = userStatement.executeQuery(userQuery);
            userResultSet.next();
            int userCount = userResultSet.getInt(1);
            if (userCount == 0) {
                System.out.println("Error issuing book: User not found.");
                return;
            }

            // Issue the book
            String query = "INSERT INTO book_issues (book_id, user_id, issue_date) VALUES (" + bookId + ", " + userId + ", CURRENT_DATE)";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
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

            User currUser = new User();

            while (table.next()) {
                if (Objects.equals(table.getString(5), UserType.Admin.toString())) {
                    currUser = new Admin();
                }
                currUser.setName(table.getString(1));
                currUser.setSurname(table.getString(2));
                currUser.setAge(Integer.parseInt(table.getString(3)));
                currUser.setUsername(table.getString(4));
                currUser.setHashedPassword(table.getString(6));
                return currUser;
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