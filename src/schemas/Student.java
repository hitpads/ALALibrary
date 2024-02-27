package schemas;


import UserMenu.ValidationName;

import java.util.Scanner;
public class Student {

    private int studentId;
    private String studentFname;
    private String studentLname;
    private int studentAge;
    private String username;
    public Scanner sc=new Scanner(System.in);


    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getStudentFname() {
        return studentFname;
    }
    public void setStudentFname() {
        System.out.println("Enter First name: ");
        String fName=sc.next();
        if(ValidationName.isValid(fName))
            this.studentFname = fName;
        else
        {
            System.out.println("Invalid name!");
            this.setStudentFname();
        }
    }

    public String getStudentLname() {
        return studentLname;
    }
    public void setStudentLname() {
        System.out.println("Enter Last name: ");
        String lName=sc.next();
        if(ValidationName.isValid(lName))
            this.studentLname = lName;
        else
        {
            System.out.println("Invalid name!");
            this.setStudentLname();
        }
    }
    public void setStudentFname(String studentFname) {
        this.studentFname = studentFname;
    }
    public void setStudentLname(String studentLname) {
        this.studentLname = studentLname;
    }

    public void setStudentUsername() {
        this.username = username;
    }
    public int getStudentAge() {
        return studentAge;
    }
    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "Name : " + this.studentFname+  " " + this.studentLname
                + "\n"+"Age : " + this.studentAge + "\n"+"Username : " + this.username;
    }


}