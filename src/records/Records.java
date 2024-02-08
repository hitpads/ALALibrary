package records;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Records {

    private int recordId;
    private int studentId;
    private int bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate submitDate;
    Scanner sc=new Scanner(System.in);

    public int getRecordId() {
        return recordId;
    }
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public LocalDate getSubmitDate() {
        return submitDate;
    }
    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public void setIssueDate() {
        System.out.println("Issue Date -:");
        String date=sc.next();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            this.issueDate=LocalDate.parse(date, formatter);
        }
        catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error...!");
            System.out.println("Try again");
            this.setIssueDate();
        }
    }

    public void setSubmitDate() {
        System.out.println("Return Date -:");
        String date=sc.next();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            this.submitDate=LocalDate.parse(date, formatter);
        }
        catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error...!");
            System.out.println("Try again");
            this.setSubmitDate();
        }
    }

}