
package library;

public class Book {

    private int bookId;
    private String title;
    private String author;
    private int borrowedByMemberId;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.borrowedByMemberId = 0;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return borrowedByMemberId == 0;
    }

    public int getBorrowedByMemberId() {
        return borrowedByMemberId;
    }

    public void setBorrowedByMemberId(int memberId) {
        this.borrowedByMemberId = memberId;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId
                + ", Title: " + title
                + ", Author: " + author
                + ", Status: " + (isAvailable() ? "Available" : "Issued");
    }
}
