package Controller;

import Connection.DBConnection;
import book.AddBookToDatabase;
import book.GetBooksFromDatabase;
import book.SearchBook;
import student.AddStudentToDatabase;
import student.SearchStudentByName;
import student.StudentList;

import java.sql.Connection;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        DBConnection connect=new DBConnection();
        Connection connectionObj = connect.getConnection("ALALibrary","postgres","amanisgay");

        if(connectionObj!=null)
            System.out.println("---- Connected ----");
        else    
            System.out.println("Error in Connection!");
        while(true)
        {
            System.out.println("1.Admin \n2.Student \n3.Exit");
            Scanner keyboard = new Scanner(System.in);
            int choice=keyboard.nextInt();

            switch(choice)
            {
                case 1:
                    System.out.println("------>>>>>>>>");
                    System.out.println("1.Add Student.");
                    System.out.println("2.Get Student List.");
                    System.out.println("3.Add Book.");
                    System.out.println("4.Search student.");
                    System.out.println("5.Back");
                    int key = keyboard.nextInt();
                    switch (key)
                    {
                        case 1:
                            AddStudentToDatabase.addStudent(connectionObj);
                            break;

                        case 2:
                            StudentList.getAllStudent(connectionObj);
                            break;
                        case 3:
                            AddBookToDatabase.addBook(connectionObj);
                            break;

                        case 4:
                            SearchStudentByName.searchStudent(connectionObj);
                            break;

                        case 5:
                            break;
                    }
                    break;
                case 2:
                    System.out.println("------->>>>>>>>");
                    System.out.println("1.Search Book.");
                    System.out.println("2.Display books.");
                    System.out.println("3.Back");
                    int key2=keyboard.nextInt();
                    switch(key2)
                    {
                        case 1:
                            SearchBook.searchBookDetails(connectionObj);
                            break;
                        case 2:
                            assert connectionObj != null;
                            GetBooksFromDatabase.getAllBooks(connectionObj);
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 3:
                    System.out.println("See you!");
                    connect.closeConnection();
                    keyboard.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Try again");
            }
        }
    }

}