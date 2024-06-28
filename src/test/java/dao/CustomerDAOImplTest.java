package dao;

import model.Customer;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerDAOImplTest {
    private CustomerDAOImpl customerDAO;

    @BeforeEach
    void setUp() {
        customerDAO = new CustomerDAOImpl();
    }

    @Test
    @Order(1)
    void selectAll() {
        List<Customer> customerList = customerDAO.selectAll();
        assertNotNull(customerList);
        assertEquals(10, customerList.size());
        for (int i = 0; i < 10; ++i) {
            Customer customer = customerList.get(i);
            assertNotNull(customer);
            assertEquals(String.format("CUST%03d", i + 1), customer.getCustomerId());
            assertNotNull(customer.getUsername());
            assertNotEquals("", customer.getUsername());
            assertNotNull(customer.getPassword());
            assertNotEquals("", customer.getPassword());
            assertNotNull(customer.getFullName());
            assertNotEquals("", customer.getFullName());
        }
    }

    @Test
    @Order(2)
    void selectByIdFailed() {
        Customer customer = new Customer();
        customer.setCustomerId("CUST011");
        Customer resultCustomer = customerDAO.selectById(customer);
        assertNull(resultCustomer);
    }

    @Test
    @Order(3)
    void insert() {
        Customer customer = new Customer();
        customer.setCustomerId("CUST011");
        customer.setUsername("user11");
        customer.setPassword("pass11");
        customer.setFullName("Full Name 11");
        int result = customerDAO.insert(customer);
        assertEquals(1, result);
        Customer resultCustomer = customerDAO.selectById(customer);
        assertNotNull(resultCustomer);
        assertEquals("CUST011", resultCustomer.getCustomerId());
        assertEquals("user11", resultCustomer.getUsername());
        assertEquals("pass11", resultCustomer.getPassword());
        assertEquals("Full Name 11", resultCustomer.getFullName());
    }

    @Test
    @Order(4)
    void update() {
        Customer customer = new Customer();
        customer.setCustomerId("CUST011");
        customer.setUsername("user11");
        customer.setPassword("pass11");
        customer.setFullName("Full Name 11 Updated");
        int result = customerDAO.update(customer);
        assertEquals(1, result);
        Customer resultCustomer = customerDAO.selectById(customer);
        assertNotNull(resultCustomer);
        assertEquals("CUST011", resultCustomer.getCustomerId());
        assertEquals("user11", resultCustomer.getUsername());
        assertEquals("pass11", resultCustomer.getPassword());
        assertEquals("Full Name 11 Updated", resultCustomer.getFullName());
    }

    @Test
    @Order(5)
    void delete() {
        Customer customer = new Customer();
        customer.setCustomerId("CUST011");
        int result = customerDAO.delete(customer);
        assertEquals(1, result);
        Customer resultCustomer = customerDAO.selectById(customer);
        assertNull(resultCustomer);
    }

    @Test
    @Order(6)
    void insertAll() {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 12; i <= 20; ++i) {
            Customer customer = new Customer();
            customer.setCustomerId(String.format("CUST%03d", i));
            customer.setUsername(String.format("user%02d", i));
            customer.setPassword(String.format("pass%02d", i));
            customer.setFullName(String.format("Full Name %02d", i));
            customerList.add(customer);
        }
        int result = customerDAO.insertAll(customerList);
        assertEquals(9, result);
    }



    @Test
    @Order(7)
    void deleteAll() {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 12; i <= 20; ++i) {
            Customer customer = new Customer();
            customer.setCustomerId(String.format("CUST%03d", i));
            customerList.add(customer);
        }
        int result = customerDAO.deleteAll(customerList);
        assertEquals(9, result);
    }

    @Test
    @Order(8)
    void selectAllNull() {
        List<Customer> customerList = new ArrayList<>();
        for (int i = 12; i <= 20; ++i) {
            Customer customer = new Customer();
            customer.setCustomerId(String.format("CUST%03d", i));
            customerList.add(customer);
        }
        for (Customer customer : customerList) {
            Customer resultCustomer = customerDAO.selectById(customer);
            assertNull(resultCustomer);
        }
    }
}