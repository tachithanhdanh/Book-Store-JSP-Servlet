package dao;

import model.Category;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryDAOImplTest {
    private CategoryDAOImpl categoryDAO;

    @BeforeEach
    void setUp() {
        categoryDAO = new CategoryDAOImpl();
    }


    @Test
    @Order(1)
    void selectAll() {
        List<Category> categoryList = categoryDAO.selectAll();
        assertNotNull(categoryList);
        assertEquals(10, categoryList.size());
        for (int i = 0; i < categoryList.size(); i++) {
            Category category = categoryList.get(i);
            assertEquals(String.format("CAT%03d", i + 1), category.getCategoryId());
            assertNotNull(category.getCategoryName());
            assertNotEquals("", category.getCategoryName());
        }
    }

    @Test
    @Order(2)
    void selectById() {
        Category category = new Category();
        category.setCategoryId("CAT001");
        Category resultCategory = categoryDAO.selectById(category);
        assertNotNull(resultCategory);
        assertEquals("CAT001", resultCategory.getCategoryId());
    }

    @Test
    @Order(3)
    void insert() {
        Category category = new Category();
        category.setCategoryId("CAT011");
        category.setCategoryName("Test Category 11");
        int result = categoryDAO.insert(category);
        assertEquals(1, result);
    }

    @Test
    @Order(4)
    void update() {
        Category category = new Category();
        category.setCategoryId("CAT011");
        category.setCategoryName("Updated Category 11");
        int result = categoryDAO.update(category);
        assertEquals(1, result);
    }

    @Test
    @Order(5)
    void delete() {
        Category category = new Category();
        category.setCategoryId("CAT011");
        int result = categoryDAO.delete(category);
        assertEquals(1, result);
    }

    @Test
    @Order(6)
    void insertAll() {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setCategoryId(String.format("CAT%03d", 12 + i));
            category.setCategoryName("Test Category " + i);
            categoryList.add(category);
        }
        int result = categoryDAO.insertAll(categoryList);
        assertEquals(5, result);
    }

    @Test
    @Order(7)
    void deleteAll() {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setCategoryId(String.format("CAT%03d", 12 + i));
            categoryList.add(category);
        }
        int result = categoryDAO.deleteAll(categoryList);
        assertEquals(5, result);
    }

    @Test
    @Order(8)
    void SqlInjectionSelect() {
        Category category = new Category();
        category.setCategoryId("CAT001' OR 1=1 -- ");
        Category resultCategory = categoryDAO.selectById(category);
        assertNull(resultCategory);
    }
}