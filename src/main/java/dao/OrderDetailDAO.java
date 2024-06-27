package dao;

import model.OrderDetail;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;

public interface OrderDetailDAO extends DAOInterface<OrderDetail> {
    @Override
    List<OrderDetail> selectAll();

    @Override
    OrderDetail selectById(OrderDetail orderDetail);

    @Override
    int insert(OrderDetail orderDetail);

    @Override
    int insertAll(List<OrderDetail> t);

    @Override
    int delete(OrderDetail orderDetail);

    @Override
    int deleteAll(List<OrderDetail> t);

    @Override
    int update(OrderDetail orderDetail);

    @Override
    OrderDetail extractFromResultSet(ResultSet rs) throws SQLException;
}
