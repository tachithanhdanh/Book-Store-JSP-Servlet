package dao;

import database.JDBCUtil;
import model.Customer;
import model.CustomerAuth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerAuthDAOImpl implements CustomerAuthDAO {
    public int insert(CustomerAuth customerAuth) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "INSERT INTO customer_auth (selector, validator, customer_id) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, customerAuth.getSelector());
            st.setString(2, customerAuth.getValidator());
            st.setString(3, customerAuth.getCustomer().getCustomerId());

            // Step 3: Execute the SQL query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Insert failed for customer auth");
            System.out.println(e.getMessage());
        }
        return result;
    }

    public CustomerAuth selectBySelector(String selector) {
        CustomerAuth customerAuth = null;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "SELECT * FROM customer_auth WHERE selector = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, selector);

            // Step 3: Execute the SQL query
//            System.out.println(sql);
            st.executeQuery();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                customerAuth = new CustomerAuth();
                customerAuth.setAuthId(rs.getLong("auth_id"));
                customerAuth.setSelector(rs.getString("selector"));
                customerAuth.setValidator(rs.getString("validator"));
                CustomerDAO customerDAO = new CustomerDAOImpl();
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customerAuth.setCustomer(customerDAO.selectById(customer));
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select by selector failed for customer auth");
            System.out.println(e.getMessage());
        }
        return customerAuth;
    }

    public int update(CustomerAuth customerAuth) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "UPDATE customer_auth SET selector = ?, validator = ?, customer_id = ? WHERE auth_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, customerAuth.getSelector());
            st.setString(2, customerAuth.getValidator());
            st.setString(3, customerAuth.getCustomer().getCustomerId());
            st.setLong(4, customerAuth.getAuthId());

            // Step 3: Execute the SQL query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Update failed for customer auth");
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int deleteBySelector(String selector) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "DELETE FROM customer_auth WHERE selector = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, selector);

            // Step 3: Execute the SQL query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Delete by selector failed for customer auth");
            System.out.println(e.getMessage());
        }
        return result;
    }
}
