package dao;

import database.JDBCUtil;
import model.Book;
import model.Order;
import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public List<OrderDetail> selectAll() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "SELECT * FROM order_detail";
            PreparedStatement st = con.prepareStatement(sql);

            // Step 3: Execute the query
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            while (rs.next()) {
                OrderDetail orderDetail = extractFromResultSet(rs);
                orderDetailList.add(orderDetail);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select all failed for order detail");
            System.out.println(e.getMessage());
        }
        return orderDetailList;
    }

    @Override
    public OrderDetail selectById(OrderDetail orderDetail) {
        OrderDetail resultOrderDetail = null;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "SELECT * FROM order_detail WHERE order_detail_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, orderDetail.getOrderDetailId());

            // Step 3: Execute the query
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            if (rs.next()) {
                resultOrderDetail = extractFromResultSet(rs);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select by id failed for order detail");
            System.out.println(e.getMessage());
        }
        return resultOrderDetail;
    }

    @Override
    public int insert(OrderDetail orderDetail) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "INSERT INTO order_detail "
                        + "(order_detail_id, order_id, book_id, quantity,"
                        + " list_price, discount, selling_price, vat)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, orderDetail.getOrderDetailId());
            st.setString(2, orderDetail.getOrder().getOrderId());
            st.setString(3, orderDetail.getBook().getBookId());
            st.setInt(4, orderDetail.getQuantity());
            st.setDouble(5, orderDetail.getListPrice());
            st.setDouble(6, orderDetail.getDiscount());
            st.setDouble(7, orderDetail.getSellingPrice());
            st.setDouble(8, orderDetail.getVat());

            // Step 3: Execute the query
            result = st.executeUpdate();

            // Step 4: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Insert failed for order detail");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertAll(List<OrderDetail> orderDetailList) {
        int result = 0;
        for (OrderDetail orderDetail : orderDetailList) {
            result += insert(orderDetail);
        }
        return result;
    }

    @Override
    public int delete(OrderDetail orderDetail) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "DELETE FROM order_detail WHERE order_detail_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, orderDetail.getOrderDetailId());

            // Step 3: Execute the query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("Delete successfully for order detail");
            System.out.println("(" + result + " row(s) affected)");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Delete failed for order detail");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAll(List<OrderDetail> t) {
        int result = 0;
        for (OrderDetail orderDetail : t) {
            result += delete(orderDetail);
        }
        return result;
    }

    @Override
    public int update(OrderDetail orderDetail) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "UPDATE order_detail SET order_id=?, book_id=?, quantity=?,"
                        + " list_price=?, discount=?, selling_price=?, vat=?"
                        + " WHERE order_detail_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, orderDetail.getOrder().getOrderId());
            st.setString(2, orderDetail.getBook().getBookId());
            st.setInt(3, orderDetail.getQuantity());
            st.setDouble(4, orderDetail.getListPrice());
            st.setDouble(5, orderDetail.getDiscount());
            st.setDouble(6, orderDetail.getSellingPrice());
            st.setDouble(7, orderDetail.getVat());
            st.setString(8, orderDetail.getOrderDetailId());

            // Step 3: Execute the query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("Update successfully for order detail");
            System.out.println("(" + result + " row(s) affected)");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Update failed for order detail");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public OrderDetail extractFromResultSet(ResultSet rs) throws SQLException {
        String orderDetailId = rs.getString("order_detail_id");
        String orderId = rs.getString("order_id");
        String bookId = rs.getString("book_id");
        int quantity = rs.getInt("quantity");
        double listPrice = rs.getDouble("list_price");
        double discount = rs.getDouble("discount");
        double sellingPrice = rs.getDouble("selling_price");
        double vat = rs.getDouble("vat");
        Book tempBook = new Book();
        tempBook.setBookId(bookId);
        Book book = new BookDAOImpl().selectById(tempBook);
        Order tempOrder = new Order();
        Order order = new OrderDAOImpl().selectById(tempOrder);
        return new OrderDetail(orderDetailId, order, book, quantity,
                    listPrice, discount, sellingPrice, vat);
    }
}
