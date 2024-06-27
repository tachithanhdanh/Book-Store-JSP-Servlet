package dao;

import model.Order;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;

public interface OrderDAO extends DAOInterface<Order> {
    @Override
    List<Order> selectAll();

    @Override
    Order selectById(Order order);

    @Override
    int insert(Order order);

    @Override
    int insertAll(List<Order> t);

    @Override
    int delete(Order order);

    @Override
    int deleteAll(List<Order> t);

    @Override
    int update(Order order);

    @Override
    Order extractFromResultSet(ResultSet rs) throws SQLException;
}
