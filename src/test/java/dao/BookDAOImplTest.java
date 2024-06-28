package dao;

import model.Author;
import model.Book;
import model.Category;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookDAOImplTest {
    private BookDAOImpl bookDAO;

    @BeforeEach
    void setUp() {
        bookDAO = new BookDAOImpl();
    }

    @Test
    @Order(1)
    void selectAll() {
        List<Book> bookList = bookDAO.selectAll();
        assertNotNull(bookList);
        assertEquals(10, bookList.size());
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            assertEquals(String.format("BOOK%03d", i + 1), book.getBookId());
            assertNotNull(book.getBookTitle());
            assertNotEquals("", book.getBookTitle());
            assertNotNull(book.getAuthor());
            assertEquals(String.format("AUTH%03d", i + 1), book.getAuthor().getAuthorId());
            assertNotNull(book.getCategory());
            assertEquals("CAT0", book.getCategory().getCategoryId().substring(0, 4));
            assertNotNull(book.getDescription());
        }
    }

    @Test
    @Order(2)
    void selectById() {
        Book book = new Book();
        book.setBookId("BOOK006");
        Book resultBook = bookDAO.selectById(book);
        assertNotNull(resultBook);
        assertEquals("BOOK006", resultBook.getBookId());
        assertNotNull(resultBook.getBookTitle());
        assertEquals("Sherlock Holmes: The Complete Novels and Stories", resultBook.getBookTitle());
        assertNotNull(resultBook.getAuthor());
        assertEquals("AUTH006", resultBook.getAuthor().getAuthorId());
        assertNotNull(resultBook.getCategory());
        assertEquals("CAT003", resultBook.getCategory().getCategoryId());
        assertNotNull(resultBook.getDescription());
    }

    @Test
    @Order(3)
    void insertSuccess() {
        Book book = new Book();
        book.setBookId("BOOK011");
        book.setBookTitle("Test Book 11");
        Author author = new Author();
        author.setAuthorId("AUTH010");
        book.setAuthor(author);
        book.setPublicationYear(2021);
        book.setImportPrice(10.0);
        book.setListPrice(20.0);
        book.setSellingPrice(15.0);
        book.setQuantity(100);
        book.setCategory(new Category("CAT010", ""));
        book.setDescription("Test Description 11");
        int result = bookDAO.insert(book);
        assertEquals(1, result);
    }

    @Test
    @Order(4)
    void insertFailedDueToDuplicateId() {
        Book book = new Book();
        book.setBookId("BOOK011");
        book.setBookTitle("Test Book 11");
        Author author = new Author();
        author.setAuthorId("AUTH010");
        book.setAuthor(author);
        book.setPublicationYear(2021);
        book.setImportPrice(10.0);
        book.setListPrice(20.0);
        book.setSellingPrice(15.0);
        book.setQuantity(100);
        book.setCategory(new Category("CAT010", ""));
        book.setDescription("Test Description 11");
        int result = bookDAO.insert(book);
        assertEquals(0, result);
    }

    @Test
    @Order(5)
    void insertFailedDueToInvalidAuthorId() {
        Book book = new Book();
        book.setBookId("BOOK012");
        book.setBookTitle("Test Book 12");
        Author author = new Author();
        author.setAuthorId("AUTH011");
        book.setAuthor(author);
        book.setPublicationYear(2021);
        book.setImportPrice(10.0);
        book.setListPrice(20.0);
        book.setSellingPrice(15.0);
        book.setQuantity(100);
        book.setCategory(new Category("CAT010", ""));
        book.setDescription("Test Description 12");
        int result = bookDAO.insert(book);
        assertEquals(0, result);
    }

    @Test
    @Order(6)
    void insertFailedDueToInvalidCategoryId() {
        Book book = new Book();
        book.setBookId("BOOK012");
        book.setBookTitle("Test Book 12");
        Author author = new Author();
        author.setAuthorId("AUTH010");
        book.setAuthor(author);
        book.setPublicationYear(2021);
        book.setImportPrice(10.0);
        book.setListPrice(20.0);
        book.setSellingPrice(15.0);
        book.setQuantity(100);
        book.setCategory(new Category("CAT011", ""));
        book.setDescription("Test Description 12");
        int result = bookDAO.insert(book);
        assertEquals(0, result);
    }

    @Test
    @Order(7)
    void updateSuccess() {
        Book book = new Book();
        book.setBookId("BOOK011");
        book.setBookTitle("Updated Book 11");
        Author author = new Author();
        author.setAuthorId("AUTH001");
        book.setAuthor(author);
        book.setPublicationYear(2021);
        book.setImportPrice(10.0);
        book.setListPrice(20.0);
        book.setSellingPrice(15.0);
        book.setQuantity(100);
        book.setCategory(new Category("CAT005", ""));
        book.setDescription("Updated Description 11");
        int result = bookDAO.update(book);
        assertEquals(1, result);
    }

    @Test
    @Order(8)
    void updateFailed() {
        Book book = new Book();
        book.setBookId("BOOK011");
        book.setBookTitle("Updated Book 11");
        Author author = new Author();
        author.setAuthorId("AUTH001");
        book.setAuthor(author);
        book.setPublicationYear(2021);
        book.setImportPrice(10.0);
        book.setListPrice(20.0);
        book.setSellingPrice(15.0);
        book.setQuantity(100);
        book.setCategory(new Category("CAT099", ""));
        book.setDescription("Updated Description 11");
        int result = bookDAO.update(book);
        assertEquals(0, result);
    }

    @Test
    @Order(9)
    void delete() {
        Book book = new Book();
        book.setBookId("BOOK011");
        int result = bookDAO.delete(book);
        assertEquals(1, result);
    }

    @Test
    @Order(10)
    void insertAll() {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            Book book = new Book();
            book.setBookId(String.format("BOOK%03d", i + 12));
            book.setBookTitle(String.format("Test Book %d", i + 12));
            Author author = new Author();
            author.setAuthorId(String.format("AUTH%03d", i + 1));
            book.setAuthor(author);
            book.setPublicationYear(2021);
            book.setImportPrice(10.0);
            book.setListPrice(20.0);
            book.setSellingPrice(15.0);
            book.setQuantity(100);
            book.setCategory(new Category(String.format("CAT%03d", i + 1), ""));
            book.setDescription(String.format("Test Description %d", i + 12));
            bookList.add(book);
        }
        int result = bookDAO.insertAll(bookList);
        assertEquals(5, result);
    }



    @Test
    @Order(11)
    void deleteAll() {
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            Book book = new Book();
            book.setBookId(String.format("BOOK%03d", i + 12));
            bookList.add(book);
        }
        int result = bookDAO.deleteAll(bookList);
        assertEquals(5, result);
    }
}