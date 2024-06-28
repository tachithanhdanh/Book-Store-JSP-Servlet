package dao;

import model.Author;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorDAOImplTest {
    private AuthorDAOImpl authorDAO;

    @BeforeEach
    void setUp() {
        authorDAO = new AuthorDAOImpl();
    }

    @Test
    @Order(1)
    void selectAll() {
        List<Author> authorList = authorDAO.selectAll();
        assertNotNull(authorList);
        assertEquals(10, authorList.size());
    }

    @Test
    @Order(2)
    void selectById() {
        Author author = new Author();
        author.setAuthorId("AUTH007");
        Author resultAuthor = authorDAO.selectById(author);
        assertNotNull(resultAuthor);
        assertEquals("AUTH007", resultAuthor.getAuthorId());
    }

    @Test
    @Order(3)
    void insert() {
        Author author = new Author();
        author.setAuthorId("AUTH011");
        author.setFullName("Test Author 11");
        author.setDateOfBirth(new Date(System.currentTimeMillis()));
        author.setBio("Test Bio 11");
        int result = authorDAO.insert(author);
        assertEquals(1, result);
    }

    @Test
    @Order(4)
    void insertAll() {
        List<Author> authorList = new ArrayList<>();
        for (int i = 1; i <= 5; ++i) {
            Author author = new Author();
            author.setAuthorId(String.format("AUTH%03d", i + 11));
            author.setFullName(String.format("Test Author %d", i + 11));
            author.setDateOfBirth(new Date(System.currentTimeMillis()));
            author.setBio(String.format("Test Bio %d", i + 11));
            authorList.add(author);
        }
        int result = authorDAO.insertAll(authorList);
        assertEquals(5, result);
    }

    @Test
    @Order(5)
    void update() {
        Author author = new Author();
        author.setAuthorId("AUTH001");
        author.setFullName("Updated Author 1");
        author.setDateOfBirth(new Date(System.currentTimeMillis()));
        author.setBio("Updated Bio 1");
        int result = authorDAO.update(author);
        assertEquals(1, result);
    }

    @Test
    @Order(6)
    void delete() {
        Author author = new Author();
        author.setAuthorId("AUTH011");
        int result = authorDAO.delete(author);
        assertEquals(1, result);
    }

    @Test
    @Order(7)
    void deleteAll() {
        List<Author> authorList = new ArrayList<>();
        for (int i = 1; i <= 5; ++i) {
            Author author = new Author();
            author.setAuthorId(String.format("AUTH%03d", i + 11));
            authorList.add(author);
        }
        int result = authorDAO.deleteAll(authorList);
        assertEquals(5, result);
    }
}