
package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    private static final String BOOK_FILE = "books.txt";
    private static final String MEMBER_FILE = "members.txt";

    // Save all books
    public static void saveBooks(ArrayList<Book> books) {

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(BOOK_FILE))) {

            for (Book book : books) {

                writer.write(book.getBookId() + "|"
                        + book.getTitle() + "|"
                        + book.getAuthor() + "|"
                        + book.getBorrowedByMemberId());

                writer.newLine();
            }

            System.out.println("Books saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    // Load all books
    public static ArrayList<Book> loadBooks() {

        ArrayList<Book> books = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(BOOK_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length == 4) {

                    try {
                        int id = Integer.parseInt(data[0]);
                        String title = data[1];
                        String author = data[2];

                        int memberId;

                        // Support records saved by the earlier version
                        if (data[3].equals("true")) {
                            memberId = 0;
                        } else if (data[3].equals("false")) {
                            memberId = -1;
                        } else {
                            memberId = Integer.parseInt(data[3]);
                        }

                        Book book = new Book(id, title, author);
                        book.setBorrowedByMemberId(memberId);

                        books.add(book);

                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid book record: " + line);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("No previous book data found.");
        }

        return books;
    }

    // Save all members
    public static void saveMembers(ArrayList<Member> members) {

        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(MEMBER_FILE))) {

            for (Member member : members) {

                writer.write(member.getMemberId() + "|"
                        + member.getName());

                writer.newLine();
            }

            System.out.println("Members saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    // Load all members
    public static ArrayList<Member> loadMembers() {

        ArrayList<Member> members = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(new FileReader(MEMBER_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                if (data.length == 2) {

                    try {
                        int id = Integer.parseInt(data[0]);
                        String name = data[1];

                        members.add(new Member(id, name));

                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid member record: " + line);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("No previous member data found.");
        }

        return members;
    }
}
