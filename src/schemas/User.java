package schemas;

import data.Utils;

import java.sql.Connection;

public class User extends Person {
    private int id;
    private final Utils utils = new Utils();
    private String username;
    private UserType userType;
    private String hashedPassword;

    public User(String name, String surname, int age, String username, String password) {
        super();
    }


    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public int getId() {
        return id;
    }

    public User() {
    }

    public User(String name, String surname, int age, String username, String password, int id) {
        setName(name);
        setSurname(surname);
        setAge(age);
        setUsername(username);
        hashedPassword = utils.getHashedPassword(password);
        setHashedPassword(hashedPassword);
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname();
    }

    public String getStudentListingFormat() {
        return this.getName() + " " + this.getSurname() + " (" + this.getUsername() + ")";
    }


    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setId(int id) {
    }
}