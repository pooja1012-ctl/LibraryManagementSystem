package library;

import java.util.Scanner;

public class LibraryManagementApplication {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		Library library=new Library();
		
		int choice;
		
		do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Search Book by Title");
            System.out.println("5. Register Member");
            System.out.println("6. Display All Members");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {

            case 1:
                System.out.print("Enter book ID: ");
                int bookId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter book title: ");
                String title = sc.nextLine();

                System.out.print("Enter author name: ");
                String author = sc.nextLine();

                Book book = new Book(bookId, title, author);

                if (library.addBook(book)) {
                    System.out.println("Book added successfully.");
                } else {
                    System.out.println("A book with this ID already exists.");
                }
                break;

            case 2:
                library.displayBooks();
                break;

            case 3:
                System.out.print("Enter book ID to search: ");
                int searchId = sc.nextInt();
                sc.nextLine();

                Book foundBook = library.searchBookById(searchId);

                if (foundBook != null) {
                    System.out.println(foundBook);
                } else {
                    System.out.println("Book not found.");
                }
                break;

            case 4:
                System.out.print("Enter book title to search: ");
                String searchTitle = sc.nextLine();

                library.searchBookByTitle(searchTitle);
                break;

            case 5:
                System.out.print("Enter member ID: ");
                int memberId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter member name: ");
                String name = sc.nextLine();

                Member member = new Member(memberId, name);

                if (library.registerMember(member)) {
                    System.out.println("Member registered successfully.");
                } else {
                    System.out.println("A member with this ID already exists.");
                }
                break;

            case 6:
                library.displayMembers();
                break;

            case 7:
                System.out.print("Enter book ID to issue: ");
                int issueId = sc.nextInt();

                System.out.print("Enter member ID: ");
                int issueMemberId = sc.nextInt();
                sc.nextLine();

                library.issueBook(issueId, issueMemberId);
                break;

            case 8:
                System.out.print("Enter book ID to return: ");
                int returnId = sc.nextInt();
                sc.nextLine();

                library.returnBook(returnId);
                break;

            case 9:
                FileManager.saveBooks(library.getBooks());
                FileManager.saveMembers(library.getMembers());

                System.out.println("Exiting Library Management System.");
                break;

            default:
                System.out.println("Invalid choice");
        }

    } while (choice != 9);

    sc.close();
}
}
