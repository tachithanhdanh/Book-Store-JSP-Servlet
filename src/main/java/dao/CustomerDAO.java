package dao;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends DAOInterface<Customer> {
    @Override
    List<Customer> selectAll();

    @Override
    Customer selectById(Customer customer);

    @Override
    int insert(Customer customer);

    @Override
    int insertAll(List<Customer> t);

    @Override
    int delete(Customer customer);

    @Override
    int deleteAll(List<Customer> t);

    @Override
    int update(Customer customer);

    @Override
    Customer extractFromResultSet(java.sql.ResultSet rs) throws SQLException;
}
