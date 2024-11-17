import java.time.LocalDate;
import java.util.*;

public class Library {
    private List<Book> books;
    private Map<String, Book> borrowedBooks;
    private Map<String, LocalDate> borrowDates;
    private Map<String, LocalDate> returnDates;

    public Library() {
        books = new ArrayList<>();
        borrowedBooks = new HashMap<>();
        borrowDates = new HashMap<>();
        returnDates = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>(books);
        availableBooks.removeAll(borrowedBooks.values());
        return availableBooks;
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks.values());
    }

    public List<Book> getReturnedBooks() {
        List<Book> returnedBooks = new ArrayList<>();
        for (String isbn : returnDates.keySet()) {
            returnedBooks.add(borrowedBooks.get(isbn));
        }
        return returnedBooks;
    }

    public boolean borrowBook(String isbn, String username) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !borrowedBooks.containsKey(isbn)) {
                borrowedBooks.put(isbn, book);
                borrowDates.put(isbn, LocalDate.now());
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        if (borrowedBooks.containsKey(isbn)) {
            returnDates.put(isbn, LocalDate.now());
            borrowedBooks.remove(isbn);
            return true;
        }
        return false;
    }

    public List<Book> getMyBorrowedBooks(String username) {
        List<Book> myBooks = new ArrayList<>();
        for (String isbn : borrowDates.keySet()) {
            myBooks.add(borrowedBooks.get(isbn));
        }
        return myBooks;
    }
}
