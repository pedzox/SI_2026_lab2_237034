import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SI_lab2_test{

    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
        library.addBook(new Book("Clean Code", "Robert C. Martin", "Programming"));
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));
    }

    // ===== Every Statement - searchBookByTitle =====

    @Test
    void searchBookEveryStatementTest() {
        // Test 1: Празен наслов -> IllegalArgumentException
        assertThrows(IllegalArgumentException.class,
                () -> library.searchBookByTitle(""));

        // Test 2: Книгата постои и не е изнајмена -> враќа листа
        List<Book> result = library.searchBookByTitle("Clean Code");
        assertNotNull(result);
        assertEquals(1, result.size());

        // Test 3: Книгата не постои -> враќа null
        List<Book> notFound = library.searchBookByTitle("Harry Potter");
        assertNull(notFound);
    }

    // ===== Every Branch - borrowBook =====

    @Test
    void borrowBookEveryBranchTest() {
        // Test 1: Празен наслов -> IllegalArgumentException
        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook("", "Author"));

        // Test 2: Книга пронајдена, не е изнајмена -> успешно
        assertDoesNotThrow(
                () -> library.borrowBook("The Hobbit", "J.R.R. Tolkien"));

        // Test 3: Книга веќе изнајмена -> RuntimeException
        assertThrows(RuntimeException.class,
                () -> library.borrowBook("The Hobbit", "J.R.R. Tolkien"));

        // Test 4: Книга не постои -> RuntimeException
        assertThrows(RuntimeException.class,
                () -> library.borrowBook("Nonexistent", "Nobody"));
    }

    // ===== Multiple Condition - searchBookByTitle =====

    @Test
    void searchBookMultipleConditionTest() {
        // TT: title match = true, !isBorrowed = true -> book се додава
        List<Book> tt = library.searchBookByTitle("Clean Code");
        assertNotNull(tt);
        assertEquals(1, tt.size());

        // TF: title match = true, !isBorrowed = false -> book се прескокнува
        library.borrowBook("Clean Code", "Robert C. Martin");
        List<Book> tf = library.searchBookByTitle("Clean Code");
        assertNull(tf);

        // FT: title match = false, !isBorrowed = true -> book се прескокнува
        List<Book> ft = library.searchBookByTitle("Harry Potter");
        assertNull(ft);

        // FF: опфатено со FT преку кратко спојување
    }

    // ===== Multiple Condition - borrowBook =====

    @Test
    void borrowBookMultipleConditionTest() {
        // TT: title = empty, author = empty -> exception
        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook("", ""));

        // TF: title = empty, author = valid -> exception
        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook("", "Martin"));

        // FT: title = valid, author = empty -> exception
        assertThrows(IllegalArgumentException.class,
                () -> library.borrowBook("Clean Code", ""));

        // FF: title = valid, author = valid -> продолжува нормално
        assertDoesNotThrow(
                () -> library.borrowBook("Clean Code", "Robert C. Martin"));
    }
}