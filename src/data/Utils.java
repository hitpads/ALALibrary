package data;

import schemas.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Utils {
    public String getHashedPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    public boolean AuthUser(User user, String password) {
        String enteredHashedPassword = getHashedPassword(password);
        return user.getHashedPassword().equals(enteredHashedPassword);
    }

    public Connection GetConnector()  {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/ALALibrary";

            Properties authorization = new Properties();
            authorization.put("user", "postgres");
            authorization.put("password", "amanisgay");

            return DriverManager.getConnection(url, authorization);
        } catch (Exception e) {
            System.err.println("Error accessing database!");
        }
        return null;
    }

    public static Connection closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            System.err.println("Error closing connection!");
        }
        return null;
    }

    public Utils() {
    }
}