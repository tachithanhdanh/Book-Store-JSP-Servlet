package dao;

import database.JDBCUtil;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public List<Category> selectAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "SELECT * FROM category";
            PreparedStatement st = con.prepareStatement(sql);

            // Step 3: Execute a SQL query
            System.out.println(sql + "\n");
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            while (rs.next()) {
                Category category = extractFromResultSet(rs);
                categoryList.add(category);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select all failed for category");
            System.out.println(e.getMessage());
        }

        return categoryList;
    }

    @Override
    public Category selectById(Category category) {
        Category resultCategory = null;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "SELECT * FROM category WHERE category_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, category.getCategoryId());

            // Step 3: Execute the SQL query
            System.out.println(sql + "\n");
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            if (rs.next()) {
                resultCategory = extractFromResultSet(rs);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select by id failed for category");
            System.out.println(e.getMessage());
        }

        return resultCategory;
    }

    @Override
    public int insert(Category category) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "INSERT INTO category"
                        + "(category_id, category_name)"
                        + " VALUES(?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, category.getCategoryId());
            st.setString(2, category.getCategoryName());

            // Step 3: Execute the SQL query
            System.out.println(sql);
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Insert failed for category");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertAll(List<Category> categoryList) {
        int result = 0;
        for (Category category : categoryList) {
            result += insert(category);
        }
        return result;
    }

    @Override
    public int delete(Category category) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "DELETE FROM category WHERE category_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, category.getCategoryId());

            // Step 3: Execute the SQL query
            System.out.println(sql);
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Delete failed for category");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAll(List<Category> t) {
        int result = 0;
        for (Category category : t) {
            result += delete(category);
        }
        return result;
    }

    @Override
    public int update(Category category) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "UPDATE category SET category_name=? WHERE category_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, category.getCategoryName());
            st.setString(2, category.getCategoryId());

            // Step 3: Execute the SQL query
            System.out.println(sql);
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Update failed for category");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Category extractFromResultSet(ResultSet rs) throws SQLException {
        String categoryId = rs.getString("category_id");
        String categoryName = rs.getString("category_name");

        return new Category(categoryId, categoryName);
    }
}
