package student;


import java.util.Scanner;
public class Student {

    private int studentId;
    private String studentFname;
    private String studentLname;
    private String studentGender;
    private int studentAge;
    private String studentClass;
    public Scanner sc=new Scanner(System.in);


    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getStudentFname() {
        return studentFname;
    }
    public void setStudentFname() {
        System.out.println("Enter First name -:");
        String fName=sc.next();
        if(ValidationName.isValid(fName))
            this.studentFname = fName;
        else
        {
            System.out.println("Invalid name...!");
            this.setStudentFname();
        }
    }

    public String getStudentLname() {
        return studentLname;
    }
    public void setStudentLname() {
        System.out.println("Enter Last name -:");
        String lName=sc.next();
        if(ValidationName.isValid(lName))
            this.studentLname = lName;
        else
        {
            System.out.println("Invalid name...!");
            this.setStudentLname();
        }
    }
    public void setStudentFname(String studentFname) {
        this.studentFname = studentFname;
    }
    public void setStudentLname(String studentLname) {
        this.studentLname = studentLname;
    }
    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }
    public String getStudentGender() {
        return studentGender;
    }
    public void setStudentGender() {
        System.out.println("Gender:");
        String gender=sc.next();
        if(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"))
            this.studentGender = gender;
        else
        {
            System.out.println("Invalid...!");
            this.setStudentGender();
        }
    }
    public int getStudentAge() {
        return studentAge;
    }
    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }
    public String getStudentClass() {
        return studentClass;
    }
    public void setStudentClass() {
        System.out.println("Class -:");
        String studClass=sc.next();
        if(studClass.equalsIgnoreCase("FE") || studClass.equalsIgnoreCase("SE") || studClass.equalsIgnoreCase("TE") || studClass.equalsIgnoreCase("BE"))
            this.studentClass=studClass;
        else
        {
            System.out.println("Invalid");
            this.setStudentClass();
        }
    }
//    public LocalDate getStudentBirthDate() {
//        return studentBirthDate;
//    }
//    public void setStudentBirthDate() {
//        System.out.println("Birthdate in yyyy--mm-d format:");
//        String date=sc.next();
//
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
//            this.studentBirthDate=LocalDate.parse(date, formatter);
//        }
//        catch (Exception e) {
//            // TODO: handle exception
//            System.out.println("Error...!");
//            System.out.println("Try again");
//            this.setStudentBirthDate();
//        }
//    }
//    public void setStudentBirthDate(LocalDate studentBirthDate) {
//        this.studentBirthDate = studentBirthDate;
//    }
    @Override
    public String toString() {
        return "StudentId : " + this.studentId + "\n"+"Name : " + this.studentFname+  " " + this.studentLname
                + "\n"+ "Gender : " + this.studentGender + "\n"+"Age : " + this.studentAge + "\n"+"Class : " + this.studentClass;
    }


}