package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {
    public List<T> selectAll();

    public T selectById(T t);

    public int insert(T t);

    public int insertAll(List<T> t);

    public int delete(T t);

    public int deleteAll(List<T> t);

    public int update(T t);

    public T extractFromResultSet(ResultSet rs) throws SQLException;
}
