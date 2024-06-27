package dao;

import model.Author;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;

public interface AuthorDAO extends DAOInterface<Author> {
    @Override
    List<Author> selectAll();

    @Override
    Author selectById(Author author);

    @Override
    int insert(Author author);

    @Override
    int insertAll(List<Author> authors);

    @Override
    int delete(Author author);

    @Override
    int deleteAll(List<Author> authors);

    @Override
    int update(Author author);

    @Override
    Author extractFromResultSet(ResultSet rs) throws SQLException;
}
