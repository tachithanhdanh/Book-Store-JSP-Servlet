package dao;

import database.JDBCUtil;
import model.Customer;
import model.TokenForgetPassword;

import java.sql.*;

public class TokenForgetPasswordDAOImpl implements TokenForgetPasswordDAO {
    @Override
    public boolean insertToken(TokenForgetPassword tokenForgetPassword) {
        int result = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "INSERT INTO token_forget_password (customer_id, token, expiry_date, is_used) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tokenForgetPassword.getCustomer().getCustomerId());
            preparedStatement.setString(2, tokenForgetPassword.getToken());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(tokenForgetPassword.getExpiryDate()));
            preparedStatement.setBoolean(4, tokenForgetPassword.isUsed());
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result > 0;
    }

    @Override
    public void updateStatus(TokenForgetPassword tokenForgetPassword) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "UPDATE token_forget_password SET is_used = ? WHERE token = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, tokenForgetPassword.isUsed());
            preparedStatement.setString(2, tokenForgetPassword.getToken());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public TokenForgetPassword getToken(String token) {
        TokenForgetPassword tokenForgetPassword = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            String query = "SELECT * FROM token_forget_password WHERE token = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tokenForgetPassword = new TokenForgetPassword();
                tokenForgetPassword.setId(rs.getLong("id"));
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                tokenForgetPassword.setCustomer(new CustomerDAOImpl().selectById(customer));
                tokenForgetPassword.setToken(rs.getString("token"));
                tokenForgetPassword.setExpiryDate(rs.getTimestamp("expiry_date").toLocalDateTime());
                tokenForgetPassword.setUsed(rs.getBoolean("is_used"));
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tokenForgetPassword;
    }
}
