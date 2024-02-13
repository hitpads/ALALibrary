package student;


import java.util.Scanner;
public class Student {

    private int studentId;
    private String studentFname;
    private String studentLname;
    private int studentAge;
    private String studentMajor;
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

    public void setstudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }
    public int getStudentAge() {
        return studentAge;
    }
    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }
    public String getstudentMajor() {
        return studentMajor;
    }
    public void setstudentMajor() {
        System.out.println("Major: ");
        String studClass=sc.next();
        if(studClass.equalsIgnoreCase("MT") || studClass.equalsIgnoreCase("SE") || studClass.equalsIgnoreCase("CS") || studClass.equalsIgnoreCase("IT"))
            this.studentMajor=studClass;
        else
        {
            System.out.println("Invalid");
            this.setstudentMajor();
        }
    }
    @Override
    public String toString() {
        return "StudentId : " + this.studentId +
                "\n"+"Name : " + this.studentFname+  " " + this.studentLname
                + "\n"+"Age : " + this.studentAge + "\n"+"Major : " + this.studentMajor;
    }


}