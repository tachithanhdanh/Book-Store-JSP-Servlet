package dao;

import database.JDBCUtil;
import model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public List<Author> selectAll() {
        List<Author> resultList = new ArrayList<>();
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "SELECT * FROM author";
            PreparedStatement st = con.prepareStatement(sql);

            // Step 3: Execute a SQL query
            System.out.println(sql + "\n");
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            while (rs.next()) {
                Author author = extractFromResultSet(rs);
                resultList.add(author);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select all failed for author");
            System.out.println(e.getMessage());
        }

        return resultList;
    }

    @Override
    public Author selectById(Author author) {
        Author resultAuthor = null;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "SELECT * FROM author WHERE author_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, author.getAuthorId());

            // Step 3: Execute the SQL query
            System.out.println(sql + "\n");
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            if (rs.next()) {
                resultAuthor = extractFromResultSet(rs);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select by ID failed for author");
            System.out.println(e.getMessage());
        }
        return resultAuthor;
    }

    @Override
    public int insert(Author author) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "INSERT INTO author"
                        + "(author_id, full_name, date_of_birth, bio)"
                        + " VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, author.getAuthorId());
            st.setString(2, author.getFullName());
            st.setDate(3, author.getDateOfBirth());
            st.setString(4, author.getBio());

            // Step 3: Execute the SQL query
            result = st.executeUpdate();

            // Step 4: Process the result set
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Insert failed for author");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertAll(List<Author> authorList) {
        int result = 0;
        for (Author author : authorList) {
            result += insert(author);
        }
        return result;
    }

    @Override
    public int delete(Author author) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "DELETE FROM author WHERE author_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, author.getAuthorId());

            // Step 3: Execute the SQL query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Delete failed for author");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAll(List<Author> authors) {
        int result = 0;
        for (Author author : authors) {
            result += delete(author);
        }
        return result;
    }

    @Override
    public int update(Author author) {
        int result = 0;
        try {
            // Step 1: Open a database connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create the statement
            String sql = "UPDATE author SET full_name=?, date_of_birth=?, bio=? WHERE author_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, author.getFullName());
            st.setDate(2, author.getDateOfBirth());
            st.setString(3, author.getBio());
            st.setString(4, author.getAuthorId());

            // Step 3: Execute the SQL query
            result = st.executeUpdate();

            // Step 4: Process the result set
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Update failed for author");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Author extractFromResultSet(ResultSet rs) throws SQLException {
        String authorId = rs.getString("author_id");
        String fullName = rs.getString("full_name");
        Date dateOfBirth = rs.getDate("date_of_birth");
        String bio = rs.getString("bio");
        return new Author(authorId, fullName, dateOfBirth, bio);
    }
}
