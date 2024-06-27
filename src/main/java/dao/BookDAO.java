package dao;

import model.Book;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;

public interface BookDAO extends DAOInterface<Book> {
    @Override
    List<Book> selectAll();

    @Override
    Book selectById(Book book);

    @Override
    int insert(Book book);

    @Override
    int insertAll(List<Book> books);

    @Override
    int delete(Book book);

    @Override
    int deleteAll(List<Book> books);

    @Override
    int update(Book book);

    @Override
    Book extractFromResultSet(ResultSet rs) throws SQLException;
}
