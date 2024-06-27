package dao;

import database.JDBCUtil;
import model.Customer;
import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> selectAll() {
        List<Order> orderList = new ArrayList<>();
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "SELECT * FROM `order`";
            PreparedStatement st = con.prepareStatement(sql);

            // Step 3: Execute the query
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            while (rs.next()) {
                Order order = extractFromResultSet(rs);
                orderList.add(order);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select all failed for order");
            System.out.println(e.getMessage());
        }
        return orderList;
    }

    @Override
    public Order selectById(Order order) {
        Order resultOrder = null;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "SELECT * FROM `order` WHERE order_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, order.getOrderId());

            // Step 3: Execute the query
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            if (rs.next()) {
                resultOrder = extractFromResultSet(rs);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select by id failed for order");
            System.out.println(e.getMessage());
        }
        return resultOrder;
    }

    @Override
    public int insert(Order order) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "INSERT INTO `order`("
                    + "order_id, customer_id, billing_address, shipping_address, "
                    + "payment_method, order_status, payment_status, paid_amount, "
                    + "remaining_amount, order_date, delivery_date) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, order.getOrderId());
            st.setString(2, order.getCustomer().getCustomerId());
            st.setString(3, order.getBillingAddress());
            st.setString(4, order.getShippingAddress());
            st.setString(5, order.getPaymentMethod());
            st.setString(6, order.getOrderStatus());
            st.setString(7, order.getPaymentStatus());
            st.setDouble(8, order.getPaidAmount());
            st.setDouble(9, order.getRemainingAmount());
            st.setDate(10, order.getOrderDate());
            st.setDate(11, order.getDeliveryDate());

            // Step 3: Execute the query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("Insert successfully for order");
            System.out.println("(" + result + " row(s) affected)");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Insert failed for order");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertAll(List<Order> orderList) {
        int result = 0;
        for (Order order : orderList) {
            result += insert(order);
        }
        return result;
    }

    @Override
    public int delete(Order order) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "DELETE FROM `order` WHERE order_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, order.getOrderId());

            // Step 3: Execute the query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("Delete successfully for order");
            System.out.println("(" + result + " row(s) affected)");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Delete failed for order");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAll(List<Order> orderList) {
        int result = 0;
        for (Order order : orderList) {
            result += delete(order);
        }
        return result;
    }

    @Override
    public int update(Order order) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "UPDATE `order` SET "
                    + "customer_id=?, billing_address=?, shipping_address=?, "
                    + "payment_method=?, order_status=?, payment_status=?, "
                    + "paid_amount=?, remaining_amount=?, order_date=?, delivery_date=? "
                    + "WHERE order_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, order.getCustomer().getCustomerId());
            st.setString(2, order.getBillingAddress());
            st.setString(3, order.getShippingAddress());
            st.setString(4, order.getPaymentMethod());
            st.setString(5, order.getOrderStatus());
            st.setString(6, order.getPaymentStatus());
            st.setDouble(7, order.getPaidAmount());
            st.setDouble(8, order.getRemainingAmount());
            st.setDate(9, order.getOrderDate());
            st.setDate(10, order.getDeliveryDate());
            st.setString(11, order.getOrderId());

            // Step 3: Execute the query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("Update successfully for order");
            System.out.println("(" + result + " row(s) affected)");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Update failed for order");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        String orderId = rs.getString("order_id");
        String customerId = rs.getString("customer_id");
        String billingAddress = rs.getString("billing_address");
        String shippingAddress = rs.getString("shipping_address");
        String paymentMethod = rs.getString("payment_method");
        String orderStatus = rs.getString("order_status");
        String paymentStatus = rs.getString("payment_status");
        double paidAmount = rs.getDouble("paid_amount");
        double remainingAmount = rs.getDouble("remaining_amount");
        Date orderDate = rs.getDate("order_date");
        Date deliveryDate = rs.getDate("delivery_date");
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer tempCustomer = new Customer();
        tempCustomer.setCustomerId(customerId);
        Customer customer = customerDAO.selectById(tempCustomer);
        return new Order(orderId, customer, billingAddress,
                shippingAddress, paymentMethod, orderStatus,
                paymentStatus, paidAmount, remainingAmount,
                orderDate, deliveryDate);
    }
}
