
package library;

import java.util.ArrayList;

public class Library implements Searchable  {

    private ArrayList<Book> books;
    private ArrayList<Member> members;

//    public Library() {
//        books = FileManager.loadBooks();
//        members = FileManager.loadMembers();
//    }
    
    public Library() {
        books = FileManager.loadBooks();
        members = FileManager.loadMembers();
    }

    // Add a new book
    public boolean addBook(Book book) {
        for (Book existingBook : books) {
            if (existingBook.getBookId() == book.getBookId()) {
                return false;
            }
        }

        books.add(book);
        FileManager.saveBooks(books);
        return true;
    }

    // Display all books
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

     books.forEach(book->System.out.println(book));
    }
    
    public void displayAvailableBooks() {
    	
    	books.stream()
    	.filter(book->book.isAvailable())
    	.forEach(book->System.out.println(book));
    }

    // Search book by id
    public Book searchBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }

        return null;
    }

    // Search books by title
    public void searchBookByTitle(String title) {
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().toLowerCase()
                    .contains(title.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    // register a member
    public boolean registerMember(Member member) {
        for (Member existingMember : members) {
            if (existingMember.getMemberId() == member.getMemberId()) {
                return false;
            }
        }

        members.add(member);
        FileManager.saveMembers(members);
        return true;
    }

    // display all members
    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        for (Member member : members) {
            System.out.println(member);
        }
    }

    // issue a book to a member
    public boolean issueBook(int bookId, int memberId) {
        Book book = searchBookById(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return false;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already issued.");
            return false;
        }

        Member member = findMemberById(memberId);

        if (member == null) {
            System.out.println("Member not found.");
            return false;
        }

        book.setBorrowedByMemberId(memberId);
        FileManager.saveBooks(books);
        System.out.println("Book issued successfully to " + member.getName());
        return true;
    }

    // Return a book
    public boolean returnBook(int bookId) {
        Book book = searchBookById(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return false;
        }

        if (book.isAvailable()) {
            System.out.println("This book has not been issued.");
            return false;
        }

        book.setBorrowedByMemberId(0);
        FileManager.saveBooks(books);
        System.out.println("Book returned successfully.");
        return true;
    }

    // Find a member by id
    private Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }
    
    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }
    
    
}
