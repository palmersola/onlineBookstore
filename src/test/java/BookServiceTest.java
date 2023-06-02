import org.example.Book;
import org.example.BookService;
import org.example.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookServiceTest {

    private BookService bookService;

    private static final Book BOOK_ONE = new Book("My Autobiography", "Palmer", "Novel", 19.99);

    private static final User USER_ONE = new User("PalmerSola", "Test123", "palmersola@gmail.com");

    @Before
    public void setUp() {
        bookService = new BookService();
    }

    @DisplayName("searchBook() correct")
    @Test
    public void correctSearchBookTest() {

        String keyword = "Palmer";
        List<Book> expected = new ArrayList<>();
        expected.add(BOOK_ONE);

        BookService.addBook(BOOK_ONE);


        List<Book> result = BookService.searchBook(keyword);


        assertEquals(expected.size(), result.size());
        for (int i = 0; i < expected.size(); i++) {
            Book expectedBook = expected.get(i);
            Book resultBook = result.get(i);
            assertEquals(expectedBook.getTitle(), resultBook.getTitle());
            assertEquals(expectedBook.getAuthor(), resultBook.getAuthor());
            assertEquals(expectedBook.getGenre(), resultBook.getGenre());
            assertEquals(expectedBook.getPrice(), resultBook.getPrice(), 0.01);
        }
    }



    @DisplayName("searchBook() incorrect")
    @Test
    public void incorrectSearchBookTest() {

        String keyword = "incorrect";
        List<Book> expected = new ArrayList<>();
        expected.add(BOOK_ONE);
        expected.add(BOOK_ONE);

        BookService.addBook(BOOK_ONE);
        BookService.addBook(BOOK_ONE);


        List<Book> result = BookService.searchBook(keyword);


        assertNotEquals(expected.size(), result.size());
    }

    @DisplayName("purchaseBook() correct")
    @Test
    public void testPurchaseBook_BookAvailable() {
        BookService.addBook(BOOK_ONE);

        boolean result = bookService.purchaseBook(USER_ONE, BOOK_ONE);
        bookService.removeBook(BOOK_ONE);
        List<Book> searchResults = BookService.searchBook("Palmer");

        assertTrue(result);
        assertFalse(searchResults.contains(BOOK_ONE));
    }


    @DisplayName("purchaseBook() incorrect")
    @Test
    public void testPurchaseBook_BookNotAvailable() {
        boolean result = bookService.purchaseBook(USER_ONE, BOOK_ONE);
        assertFalse(result);
    }






}