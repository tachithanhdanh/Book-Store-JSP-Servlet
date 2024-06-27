package dao;

import database.JDBCUtil;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> selectAll() {
        List<Customer> customers = new ArrayList<>();
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "SELECT * FROM customer";
            PreparedStatement st = con.prepareStatement(sql);

            // Step 3: Execute the SQL query
            System.out.println(sql + "\n");
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            while (rs.next()) {
                Customer customer = extractFromResultSet(rs);
                customers.add(customer);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select all failed for customer");
            System.out.println(e.getMessage());
        }
        return customers;
    }

    @Override
    public Customer selectById(Customer customer) {
        Customer resultCustomer = null;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "SELECT * FROM customer WHERE customer_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, customer.getCustomerId());

            // Step 3: Execute the SQL query
            System.out.println(sql + "\n");
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            if (rs.next()) {
                resultCustomer = extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Select by id failed for customer");
            System.out.println(e.getMessage());
        }
        return resultCustomer;
    }

    @Override
    public int insert(Customer customer) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "INSERT INTO customer("
                    + "customer_id, username, password, full_name,"
                    + "gender, billing_address, shipping_address,"
                    + "invoice_address, date_of_birth, phone_number,"
                    + "email, subscribe_to_newsletter)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, customer.getCustomerId());
            st.setString(2, customer.getUsername());
            st.setString(3, customer.getPassword());
            st.setString(4, customer.getFullName());
            st.setString(5, customer.getGender());
            st.setString(6, customer.getBillingAddress());
            st.setString(7, customer.getShippingAddress());
            st.setString(8, customer.getInvoiceAddress());
            st.setDate(9, customer.getDateOfBirth());
            st.setString(10, customer.getPhoneNumber());
            st.setString(11, customer.getEmail());
            st.setBoolean(12, customer.isSubscribeToNewsletter());

            // Step 3: Execute the SQL query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Insert failed for customer");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertAll(List<Customer> customerList) {
        int result = 0;
        for (Customer customer : customerList) {
            result += insert(customer);
        }
        return result;
    }

    @Override
    public int delete(Customer customer) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "DELETE FROM customer WHERE customer_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, customer.getCustomerId());

            // Step 3: Execute the SQL query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Delete failed for customer");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAll(List<Customer> customerList) {
        int result = 0;
        for (Customer customer : customerList) {
            result += delete(customer);
        }
        return result;
    }

    @Override
    public int update(Customer customer) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "UPDATE customer SET "
                    + "username=?, password=?, full_name=?,"
                    + "gender=?, billing_address=?, shipping_address=?,"
                    + "invoice_address=?, date_of_birth=?, phone_number=?,"
                    + "email=?, subscribe_to_newsletter=?"
                    + "WHERE customer_id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, customer.getUsername());
            st.setString(2, customer.getPassword());
            st.setString(3, customer.getFullName());
            st.setString(4, customer.getGender());
            st.setString(5, customer.getBillingAddress());
            st.setString(6, customer.getShippingAddress());
            st.setString(7, customer.getInvoiceAddress());
            st.setDate(8, customer.getDateOfBirth());
            st.setString(9, customer.getPhoneNumber());
            st.setString(10, customer.getEmail());
            st.setBoolean(11, customer.isSubscribeToNewsletter());
            st.setString(12, customer.getCustomerId());

            // Step 3: Execute the SQL query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Update failed for customer");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Customer extractFromResultSet(ResultSet rs) throws SQLException {
        String customerId = rs.getString("customer_id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String fullName = rs.getString("full_name");
        String gender = rs.getString("gender");
        String billingAddress = rs.getString("billing_address");
        String shippingAddress = rs.getString("shipping_address");
        String invoiceAddress = rs.getString("invoice_address");
        Date dateOfBirth = rs.getDate("date_of_birth");
        String phoneNumber = rs.getString("phone_number");
        String email = rs.getString("email");
        boolean subscribeToNewsletter = rs.getBoolean("subscribe_to_newsletter");
        return new Customer(customerId, username, password, fullName,
                gender, billingAddress, shippingAddress,
                invoiceAddress, dateOfBirth, phoneNumber,
                email, subscribeToNewsletter);
    }
}
