
package library;

public interface Searchable {

    Book searchBookById(int bookId);

    void searchBookByTitle(String title);
}
