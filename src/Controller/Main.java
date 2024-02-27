package Controller;

import BookOperations.AddBookToDatabase;
import BookOperations.SearchBook;
import data.DBWork;
import data.Utils;
import schemas.User;
import schemas.UserType;
import UserMenu.SearchUserByName;
import UserMenu.UserList;
import BookOperations.GetBooksFromDatabase;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, NumberFormatException {
        final Scanner scanner = new Scanner(System.in);
        final DBWork dbWork = new DBWork();
        final Utils utils = new Utils();

        System.out.println("Welcome to the ALAlibrary!");

        User curUser = null;
        boolean isAuth = false;

        while (curUser == null) {
            System.out.println("-----------------");
            System.out.println("1: Authorize");
            System.out.println("2: Register");
            System.out.println("3: Exit");
            System.out.println();
            System.out.print("Enter action: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter your data: ");
                    System.out.print("Your login: ");
                    String username = scanner.nextLine();
                    curUser = dbWork.getCurrUser(username);
                    if (curUser != null) {

                        if (curUser.getUserType() == UserType.Admin) {
                            System.out.println("Welcome, administrator " + curUser + "!");
                        } else {
                            System.out.println("Welcome, " + curUser);
                        }
                        System.out.print("Enter your password to continue: ");
                        String password = scanner.next();
                        isAuth = utils.AuthUser(curUser, password);
                        if (isAuth) {
                            System.out.println("Successfully authorized!");
                        } else {
                            System.out.println("You are not authorized.");
                        }
                    } else {
                        System.out.println("No such student in the database.");
                    }
                    break;
                case 2:
                    System.out.println("Enter your data:");
                    System.out.print("Your name: ");
                    String newUserName = scanner.next();
                    System.out.print("Your surname: ");
                    String newUserSurname = scanner.next();
                    System.out.print("Your age: ");
                    int newUserAge = Integer.parseInt(scanner.next());
                    System.out.print("Your login: ");
                    String newUserUsername = scanner.next();
                    System.out.print("Your password: ");
                    String newUserPassword = scanner.next();
                    System.out.println();
                    System.out.println("Re-enter the password: ");
                    System.out.print("Your password: ");
                    String newUserRepeatedPassword = scanner.next();

                    if (newUserPassword.equals(newUserRepeatedPassword)) {
                        int newUserId = DBWork.getNextUserId();
                        curUser = new User(
                                newUserName,
                                newUserSurname,
                                newUserAge,
                                newUserUsername,
                                newUserPassword,
                                newUserId
                                );
                        System.out.println("You are registered and authorized!");
                        dbWork.InsertUser(curUser);
                        isAuth = true;

                    } else {
                        System.out.println("You haven't completed the registration. Try again.");
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the library!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("No such action. Try again.");
                    System.exit(0);
                    break;
            }
        }

        // MENUS

        while (true) {
            if (isAuth) {
                switch (curUser.getUserType()) {
                    case UserType.User:
                        System.out.println("1: Change password");
                        System.out.println("2. Display books");
                        System.out.println("3. Search book");
                        System.out.println("4: Issue book");
                        System.out.println("5: Exit");
                        System.out.println();
                        System.out.print("Enter action: ");
                        int typeOfAction = Integer.parseInt(scanner.next());
                        switch (typeOfAction) {
                            case 1:
                                System.out.println("Enter new password: ");
                                String newPassword = scanner.next();
                                dbWork.updatePassword(curUser, newPassword);
                                System.out.println("Password changed.");
                                break;
                            case 2:
                                assert utils.GetConnector() != null;
                                GetBooksFromDatabase.getAllBooks(Objects.requireNonNull(utils.GetConnector()));
                                break;
                            case 3:
                                SearchBook.searchBookDetails(utils.GetConnector());
                                break;
                            case 4:
                                System.out.print("Enter book ID: ");
                                int bookId = Integer.parseInt(scanner.next());
                                dbWork.issueBook(bookId, curUser.getId());
                                System.out.println("Book issued.");
                                break;
                            case 5:
                                System.out.println("Thanks for using the library!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("No such action. Try again.");
                        }
                        break;
                    case UserType.Admin:
                        System.out.println();
                        System.out.println("1: Change password");
                        System.out.println("2. Add book");
                        System.out.println("3: Display books");
                        System.out.println("4: Delete user");
                        System.out.println("5: Get users list");
                        System.out.println("6. Search users");
                        System.out.println("7: Exit");
                        System.out.println();
                        System.out.print("Enter action: ");
                        typeOfAction = Integer.parseInt(scanner.next());
                        switch (typeOfAction) {
                            case 1:
                                System.out.println("Enter new password: ");
                                String newPassword = scanner.next();
                                dbWork.updatePassword(curUser, newPassword);
                                System.out.println("Password changed.");
                                break;

                            case 2:
                                AddBookToDatabase.addBook(utils.GetConnector());
                                break;
                            case 3:
                                assert utils.GetConnector() != null;
                                GetBooksFromDatabase.getAllBooks(Objects.requireNonNull(utils.GetConnector()));
                                break;
                            case 4:
                                System.out.print("The login (username) of the user you want to delete: ");
                                String deletedUserNickname = scanner.next();
                                User deletedUser = dbWork.getCurrUser(deletedUserNickname);
                                if (deletedUser != null) {
                                    if (deletedUser.getUsername().equals(curUser.getUsername())) {
                                        System.out.println("You can't delete yourself!");
                                    } else {
                                        dbWork.deleteUser(deletedUser);
                                        System.out.println("User " + deletedUser + " has been deleted.");
                                    }
                                } else {
                                    System.out.println("No such User in the database.");
                                }
                                break;
                            case 5:
                                UserList.getAllStudent(utils.GetConnector());
                                break;
                            case 6:
                                SearchUserByName.searchStudent(utils.GetConnector());
                                break;
                            case 7:
                                System.out.println("Thanks for using the library!");
                                System.exit(0);
                            default:
                                System.out.println("No such action. Try again.");
                        }
                        break;
                    default:
                        //...
                }
            } else {
                System.out.println("You are not authorized. Goodbye!");
                System.exit(0);
            }


        }
    }
}