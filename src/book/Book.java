package book;

public class Book {

    private String bookAuthor;
    private String booktitle;
    private int bookCount;
    private int bookPrice;

    public String getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public String getBooktitle() {
        return booktitle;
    }
    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }
    public int getBookCount() {
        return bookCount;
    }
    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
    public int getBookPrice() {
        return bookPrice;
    }
    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }
}