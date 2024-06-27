package dao;

import model.Category;

import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;

public interface CategoryDAO extends DAOInterface<Category> {
    @Override
    List<Category> selectAll();

    @Override
    Category selectById(Category category);

    @Override
    int insert(Category category);

    @Override
    int insertAll(List<Category> categories);

    @Override
    int delete(Category category);

    @Override
    int deleteAll(List<Category> categories);

    @Override
    int update(Category category);

    @Override
    Category extractFromResultSet(ResultSet rs) throws SQLException;
}
